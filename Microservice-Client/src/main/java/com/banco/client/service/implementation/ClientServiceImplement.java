package com.banco.client.service.implementation;

import com.banco.client.persistence.models.Client;
import com.banco.client.persistence.repository.IClientRepository;
import com.banco.client.presentation.dto.CardDto;
import com.banco.client.presentation.dto.ClientDto;
import com.banco.client.presentation.dto.SavingsAccountDto;
import com.banco.client.service.interfaces.IClientService;
import com.banco.client.utils.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class ClientServiceImplement implements IClientService {

    @Autowired
    private IClientRepository repository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    private  Mono<CardDto> registerCard(String dni){
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/card/register-card?dni={dni}", dni)
                .retrieve()
                .bodyToMono(CardDto.class);
    }

    private Mono<SavingsAccountDto> registerAccount(int cardId){
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8082/account/register?idCard={cardId}", cardId)
                .retrieve()
                .bodyToMono(SavingsAccountDto.class);
    }

    private Mono<ClientDto> processExistingClient(Client existingClient) {
        return  registerCard(existingClient.getDni())
                .flatMap(card -> registerAccount(card.getId())
                        .map(account -> buildClientDetails(existingClient, card, account)));
    }

    private ClientDto buildClientDetails(Client client, CardDto card, SavingsAccountDto account){
        return ClientDto.builder()
                .name(client.getName())
                .lastname(client.getLastname())
                .dni(client.getDni())
                .phone(client.getPhone())
                .card(card)
                .account(account)
                .build();
    }

    @Override
    public Mono<ClientDto> registerClient(ClientDto clientDto) {
        return repository.findByDni(clientDto.getDni())
                .flatMap(this::processExistingClient)
                .switchIfEmpty(
                        repository.findByPhone(clientDto.getPhone())
                                .flatMap(this::processExistingClient)
                                .switchIfEmpty(saveAndRegisterDetails(clientDto))
                );
    }

    private Mono<ClientDto> saveAndRegisterDetails(ClientDto clientDto) {
        Client client = ClientMapper.toEntity(clientDto);
        return  repository.save(client)
                .flatMap(savedClient -> registerCard(savedClient.getDni())
                        .flatMap(card -> registerAccount(card.getId())
                                .map(account -> buildClientDetails(savedClient, card, account))));
    }
}

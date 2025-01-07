package com.banco.client.service.helper;

import com.banco.client.persistence.models.Client;
import com.banco.client.presentation.dto.CardDto;
import com.banco.client.presentation.dto.ClientDto;
import com.banco.client.presentation.dto.SavingsAccountDto;
import com.banco.client.service.http.webClientService.CardService;
import com.banco.client.service.http.webClientService.SavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClienteProcessorService {
    @Autowired
    private CardService cardService;

    @Autowired
    private SavingAccountService savingAccountService;

    public Mono<ClientDto> processExistingClient(Client existingClient) {
        return cardService.registerCard(existingClient.getDni())
                .flatMap(card -> savingAccountService.registerAccount(card.getId())
                        .map(account -> builClientDetails(existingClient, card,account)));
    }

    public Mono<ClientDto> saveAndRegisterDetails(Client client){
        return cardService.registerCard(client.getDni())
                .flatMap(card -> savingAccountService.registerAccount(card.getId())
                .map(account -> builClientDetails(client, card,account)));
    }

    private ClientDto builClientDetails(Client existingClient, CardDto card, SavingsAccountDto account) {

        return ClientDto.builder()
                .name(existingClient.getName())
                .lastname(existingClient.getLastname())
                .dni(existingClient.getDni())
                .phone(existingClient.getPhone())
                .card(card)
                .account(account)
                .build();
    }
}

package com.banco.client.service.implementation;

import com.banco.client.persistence.models.Client;
import com.banco.client.persistence.repository.IClientRepository;
import com.banco.client.presentation.dto.ClientDto;
import com.banco.client.service.interfaces.IClientService;
import com.banco.client.utils.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class ClientServiceImplement implements IClientService {

    @Autowired
    private IClientRepository repository;


    @Override
    public Mono<ClientDto> registerClient(ClientDto clientDto) {
        Client client = ClientMapper.toEntity(clientDto);
        return repository.save(client)
                .map(ClientMapper::toDto);
    }
}

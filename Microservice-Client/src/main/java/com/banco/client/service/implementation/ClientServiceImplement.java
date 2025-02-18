package com.banco.client.service.implementation;

import com.banco.client.persistence.repository.IClientRepository;
import com.banco.client.presentation.dto.ClientDto;
import com.banco.client.service.helper.ClienteProcessorService;
import com.banco.client.service.interfaces.IClientService;
import com.banco.client.utils.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class ClientServiceImplement implements IClientService {

    @Autowired
    private IClientRepository repository;

    @Autowired
    private ClienteProcessorService clienteProcessorService;

    @Override
    public Mono<ClientDto> registerClient(ClientDto clientDto) {
        return repository.findByDni(clientDto.getDni())
                .flatMap(clienteProcessorService::processExistingClient)
                .switchIfEmpty(
                        repository.findByPhone(clientDto.getPhone())
                                .flatMap(clienteProcessorService::processExistingClient)
                                .switchIfEmpty(
                                        repository.save(ClientMapper.toEntity(clientDto))
                                                .flatMap(clienteProcessorService::processExistingClient)
                                )
                );
    }


}

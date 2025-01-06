package com.banco.client.service.interfaces;

import com.banco.client.presentation.dto.ClientDto;
import reactor.core.publisher.Mono;

public interface IClientService {
    Mono<ClientDto> registerClient(ClientDto clientDto);

}

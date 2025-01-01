package com.banco.client.presentation.controller;

import com.banco.client.persistence.models.Client;
import com.banco.client.presentation.dto.ClientDto;
import com.banco.client.service.interfaces.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @PostMapping("/register")
    public Mono<ClientDto> registerClient(@Valid @RequestBody ClientDto clientDto) {
        return clientService.registerClient(clientDto);
    }
}

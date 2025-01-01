package com.banco.client.utils.mapper;

import com.banco.client.persistence.models.Client;
import com.banco.client.presentation.dto.ClientDto;

public class ClientMapper {

    public static ClientDto toDto (Client client){
        return ClientDto.builder()
                .name(client.getName())
                .lastname(client.getLastname())
                .dni(client.getDni())
                .phone(client.getPhone())
                .build();
    }
    public static Client toEntity (ClientDto dto){
        return Client.builder()
                .name(dto.getName())
                .lastname(dto.getLastname())
                .dni(dto.getDni())
                .phone(dto.getPhone())
                .build();
    }
}

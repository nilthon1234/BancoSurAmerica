package com.banco.client.presentation.dto;

import com.banco.client.persistence.models.Client;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private String name;
    private String lastname;

    @NotNull
    @Min(value = 10000000, message = "El dni debe tener almenos 8 digitos")
    private String dni;

    @NotNull
    @Pattern(regexp = "\\d{9,}", message = "el telefono debe tener almenos 9 digitos")
    private String phone;

    public ClientDto(Client client) {
        this.name = client.getName();
        this.lastname = client.getLastname();
        this.dni = client.getDni();
        this.phone = client.getPhone();

    }

}

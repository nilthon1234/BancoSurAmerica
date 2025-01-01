package com.banco.client.persistence.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Client {

    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    @Min(value = 10000000, message = "El dni debe tener almenos 8 digitos")
    @Indexed(unique = true)
    private String dni;
    @NotNull
    @Pattern(regexp = "\\d{9,}", message = "el telefono debe tener almenos 9 digitos")
    @Indexed(unique = true)
    private String phone;
}

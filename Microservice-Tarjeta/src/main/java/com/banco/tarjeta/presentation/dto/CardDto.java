package com.banco.tarjeta.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {

    private Integer id;
    private String cardNumber;
    private Date expirationDate;
    private int dni;
    private Date creationDate;
}

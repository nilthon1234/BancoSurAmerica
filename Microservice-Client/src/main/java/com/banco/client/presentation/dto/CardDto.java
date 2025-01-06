package com.banco.client.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CardDto {

    private Integer id;
    private String cardNumber;
    private Date expirationDate;
    private int dni;
    private Date creationDate;
    private List<SavingsAccountDto> savingAccounts;
}

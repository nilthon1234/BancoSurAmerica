package com.banco.client.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SavingsAccountDto {

    private Integer id;
    private String account;
    private BigDecimal balance;
    private int idCard;
}

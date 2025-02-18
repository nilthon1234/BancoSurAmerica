package com.banco.surapp.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "financial_bank")
@Data
public class FinancialBank {

    @Id
    private Long id;
    private BigDecimal balance;
}

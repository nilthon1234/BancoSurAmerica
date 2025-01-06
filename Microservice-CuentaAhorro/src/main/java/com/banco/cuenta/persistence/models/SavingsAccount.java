package com.banco.cuenta.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_savingsAccount")
@NamedStoredProcedureQuery(
        name = "sp_generateAccount",
        procedureName = "sp_generateAccount",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "id_card", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "account", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "balance", type = BigDecimal.class),
        }
)
public class SavingsAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String account;
    private BigDecimal balance;
    @Column(name = "id_card")
    private int idCard;
}

package com.banco.operation.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Builder
@Table("tb_operationTransfer")

public class OperationTransfer {

    @Id
    private Integer id;

    @Column("nro_Operation")
    private String nroOperation;

    @Column("sender_account")  // Coincide con la BD
    private String senderAccount;

    @Column("destination_account")  // Coincide con la BD
    private String destinationAccount;

    private BigDecimal amount;
}

package com.banco.operation.services.interfaces;

import com.banco.operation.persistence.models.OperationTransfer;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface OperationService {
    Mono<OperationTransfer> createOperaion(String senderAccount, String destinationAccount, BigDecimal amount);
}

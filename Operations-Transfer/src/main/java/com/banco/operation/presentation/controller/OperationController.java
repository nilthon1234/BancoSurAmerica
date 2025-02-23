package com.banco.operation.presentation.controller;

import com.banco.operation.persistence.models.OperationTransfer;
import com.banco.operation.services.interfaces.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/operation")
public class OperationController {

    private final OperationService operationService;


    @PostMapping("/transfert")
    public Mono<OperationTransfer> createOperation(@RequestParam String senderAccount,
                                                   @RequestParam String destinationAccount,
                                                   @RequestParam BigDecimal amount) {
        return operationService.createOperaion(senderAccount, destinationAccount, amount);
    }


}

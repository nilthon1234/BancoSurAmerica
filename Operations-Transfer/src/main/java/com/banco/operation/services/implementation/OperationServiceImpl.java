package com.banco.operation.services.implementation;

import com.banco.operation.persistence.models.OperationTransfer;
import com.banco.operation.persistence.repository.OperationRepository;
import com.banco.operation.services.http.webClientService.AccountService;
import com.banco.operation.services.interfaces.OperationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final AccountService accountService;
    private final Logger log = LoggerFactory.getLogger(OperationServiceImpl.class);

    private String generateNextNroOperation(String lastNroOperation) {
        int lastNumber = Integer.parseInt(lastNroOperation.substring(3));
        return  String.format("OT-%07d", lastNumber + 1 );
    }

    @Override
    public Mono<OperationTransfer> createOperaion(String senderAccount, String destinationAccount, BigDecimal amount) {
        return accountService.updateBalance(senderAccount, destinationAccount, amount)
                .flatMap(success -> {
                    if (!success) {
                        log.error("❌ Error: Balance no actualizado (sender: {}, destination: {}, amount: {})",
                                senderAccount, destinationAccount, amount);
                        return Mono.error(new RuntimeException("Balance update failed"));
                    }

                    log.info("✅ Balance  actualizado correctamente, continuando con la transacción...");

                    return operationRepository.findFirstByOrderByIdDesc()
                            .map(lasOp -> generateNextNroOperation(lasOp.getNroOperation()))
                            .defaultIfEmpty("OT-0000001")
                            .flatMap(nroOp -> {
                                OperationTransfer newOp = OperationTransfer.builder()
                                        .nroOperation(nroOp)
                                        .senderAccount(senderAccount)
                                        .destinationAccount(destinationAccount)
                                        .amount(amount)
                                        .build();
                                return operationRepository.save(newOp);
                            });
                });
    }



}

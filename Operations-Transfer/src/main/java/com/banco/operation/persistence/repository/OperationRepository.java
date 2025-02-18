package com.banco.operation.persistence.repository;

import com.banco.operation.persistence.models.OperationTransfer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OperationRepository extends ReactiveCrudRepository<OperationTransfer, Integer> {

    Mono<OperationTransfer> findFirstByOrderByIdDesc();

}

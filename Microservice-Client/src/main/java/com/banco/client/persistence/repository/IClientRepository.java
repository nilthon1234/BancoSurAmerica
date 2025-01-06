package com.banco.client.persistence.repository;

import com.banco.client.persistence.models.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IClientRepository extends ReactiveMongoRepository<Client, String> {

    Mono<Client> findByDni (String dni);
    Mono<Client> findByPhone (String phone);
}

package com.banco.client.persistence.repository;

import com.banco.client.persistence.models.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends ReactiveMongoRepository<Client, String> {
}

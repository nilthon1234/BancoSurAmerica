package com.banco.tarjeta.persistence.repository;

import com.banco.tarjeta.persistence.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ICardRepository extends JpaRepository<Card, Integer> {
    @Procedure(name = "sp_InsertCard")
    Map<String, Object> insertCard(@Param("dni") int dni);
}

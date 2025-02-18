package com.banco.surapp.persistence.repository;

import com.banco.surapp.persistence.models.FinancialBank;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface FinancialBankRepository extends ReactiveCrudRepository<FinancialBank, Long> {
    @Modifying
    @Query("UPDATE FinancialBank s SET s.balance = s.balance - :amount WHERE s.")
    int decrementBalance(@Param("balance") String balance, @Param("amount")BigDecimal amount);
}

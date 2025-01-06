package com.banco.cuenta.persistence.repository;

import com.banco.cuenta.persistence.models.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ISavingsAccountRepository extends JpaRepository<SavingsAccount, Integer> {

    @Procedure(name = "sp_generateAccount")
    Map<String, Object> generateAccount(@Param("id_card") Integer idCard);
}

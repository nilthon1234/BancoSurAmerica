package com.banco.cuenta.persistence.repository;

import com.banco.cuenta.persistence.models.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ISavingsAccountRepository extends JpaRepository<SavingsAccount, Integer> {

    @Procedure(name = "sp_generateAccount")
    Map<String, Object> generateAccount(@Param("id_card") Integer idCard);

    @Query("SELECT s FROM SavingsAccount s WHERE s.account = :account")
    Optional<SavingsAccount> findByAccount(@Param("account") String account);

    //decrementar
    @Modifying
    @Query("UPDATE SavingsAccount s SET s.balance = s.balance - :amount WHERE s.account = :account")
    int decrementBalance(@Param("account") String account, @Param("amount") BigDecimal amount);

    //incrementar
    @Modifying
    @Query("UPDATE SavingsAccount s SET s.balance = s.balance + :amount WHERE s.account = :account")
    int incrementBalance(@Param("account") String account, @Param("amount") BigDecimal amount);
}

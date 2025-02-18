package com.banco.cuenta.service.interfaces;

import com.banco.cuenta.persistence.models.SavingsAccount;

import java.math.BigDecimal;

public interface IAccountService {

    SavingsAccount createAccount(int idCard);
    boolean  updateBalance (String senderAccount,String destinationAccount, BigDecimal amount);
}

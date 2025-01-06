package com.banco.cuenta.service.interfaces;

import com.banco.cuenta.persistence.models.SavingsAccount;

public interface IAccountService {

    SavingsAccount createAccount(int idCard);
}

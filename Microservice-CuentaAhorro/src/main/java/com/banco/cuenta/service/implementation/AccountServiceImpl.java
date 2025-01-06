package com.banco.cuenta.service.implementation;

import com.banco.cuenta.persistence.models.SavingsAccount;
import com.banco.cuenta.persistence.repository.ISavingsAccountRepository;
import com.banco.cuenta.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private ISavingsAccountRepository savingsAccountRepository;


    @Transactional
    @Override
    public SavingsAccount createAccount(int idCard) {
        Map<String, Object> result = savingsAccountRepository.generateAccount(idCard);
        return SavingsAccount.builder()
                .id((Integer) result.get("id"))
                .account((String) result.get("account"))
                .balance((BigDecimal) result.get("balance"))
                .idCard(idCard)
                .build();
    }
}

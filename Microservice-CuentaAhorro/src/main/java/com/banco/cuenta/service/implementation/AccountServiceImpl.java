package com.banco.cuenta.service.implementation;

import com.banco.cuenta.persistence.models.SavingsAccount;
import com.banco.cuenta.persistence.repository.ISavingsAccountRepository;
import com.banco.cuenta.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

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
    @Transactional
    @Override
    public boolean updateBalance(String senderAccount,String destinationAccount, BigDecimal amount)  {

        Optional<SavingsAccount> senderOptional = savingsAccountRepository.findByAccount(senderAccount);
        Optional<SavingsAccount> destinationOptional = savingsAccountRepository.findByAccount(destinationAccount);
        if (!senderOptional.isPresent() || !destinationOptional.isPresent()) {
            return false;
        }
        SavingsAccount sender = senderOptional.get();
        SavingsAccount destination = destinationOptional.get();
        if (sender.getBalance().compareTo(amount) < 0) {
            return false;
        }
        int rowsUpdatedSender = savingsAccountRepository.decrementBalance(senderAccount, amount);
        if (rowsUpdatedSender == 0) {
            return false;
        }
        int roumsUpdate = savingsAccountRepository.incrementBalance(destinationAccount, amount);
        if (roumsUpdate == 0) {
            throw new RuntimeException("falled to update destination balance. Rolling back transactional");
        }
        return true;

    }
}

package com.banco.cuenta.presentation.controller;

import com.banco.cuenta.persistence.models.SavingsAccount;
import com.banco.cuenta.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class    AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/register")
    public SavingsAccount register(@RequestParam int idCard){
        return accountService.createAccount(idCard);
    }

    @PutMapping("/add-balance")
    public ResponseEntity<String> addBalance (@RequestParam String senderAccount,
                                              @RequestParam String destinationAccount,
                                              @RequestParam BigDecimal amount){
        boolean update = accountService.updateBalance(senderAccount, destinationAccount, amount);
        if(update){
            return ResponseEntity.ok("Added Balance: " + amount);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Found Account");
        }
    }
}

package com.banco.cuenta.presentation.controller;

import com.banco.cuenta.persistence.models.SavingsAccount;
import com.banco.cuenta.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/register")
    public SavingsAccount register(@RequestParam int idCard){
        return accountService.createAccount(idCard);
    }
}

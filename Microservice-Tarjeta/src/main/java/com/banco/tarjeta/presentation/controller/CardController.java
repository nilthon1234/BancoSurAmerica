package com.banco.tarjeta.presentation.controller;

import com.banco.tarjeta.presentation.dto.CardDto;
import com.banco.tarjeta.service.interfaces.IServiceCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private IServiceCard cardService;

    @PostMapping("/register-card")
    public CardDto registerCard(@RequestParam int dni) {
        return cardService.registerCard(dni);
    }
}

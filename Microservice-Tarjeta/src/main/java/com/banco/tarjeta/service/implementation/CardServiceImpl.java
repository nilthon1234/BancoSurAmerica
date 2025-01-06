package com.banco.tarjeta.service.implementation;

import com.banco.tarjeta.persistence.repository.ICardRepository;
import com.banco.tarjeta.presentation.dto.CardDto;
import com.banco.tarjeta.service.interfaces.IServiceCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.Map;

@Service
public class CardServiceImpl implements IServiceCard {

    @Autowired
    private ICardRepository cardRepository;

    @Override
    public CardDto registerCard(int dni) {
        Map<String, Object> result = cardRepository.insertCard(dni);
        return CardDto.builder()
                .id((Integer) result.get("id"))
                .dni(dni)
                .cardNumber((String) result.get("cardNumber"))
                .creationDate((Date) result.get("creationDate"))
                .expirationDate((Date) result.get("expirationDate"))
                .build();
    }
}

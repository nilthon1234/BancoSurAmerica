package com.banco.tarjeta.service.interfaces;

import com.banco.tarjeta.presentation.dto.CardDto;

public interface IServiceCard {

    CardDto registerCard(int dni);
}

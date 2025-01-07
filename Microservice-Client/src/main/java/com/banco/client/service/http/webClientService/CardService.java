package com.banco.client.service.http.webClientService;

import com.banco.client.presentation.dto.CardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CardService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${services.card.base-url}")
    private String accountBaseUrl;

    public Mono<CardDto> registerCard(String dni) {
        return webClientBuilder.build()
                .post()
                .uri(accountBaseUrl + "/register-card?dni={dni}", dni)
                .retrieve()
                .bodyToMono(CardDto.class);
    }
}

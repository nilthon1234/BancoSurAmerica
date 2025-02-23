package com.banco.operation.services.http.webClientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final WebClient webClientBuilder;
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);


    public AccountService( @Value("${services.account.base-url}")String accountBaseUrl,WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder.baseUrl(accountBaseUrl).build();
    }

    public Mono<Boolean> updateBalance(String senderAccount, String destinationAccount, BigDecimal amount) {
        return webClientBuilder.put()
                .uri(uriBuilder -> uriBuilder.path("/add-balance")
                        .queryParam("senderAccount", senderAccount)
                        .queryParam("destinationAccount", destinationAccount)
                        .queryParam("amount", amount)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> log.info("⚡ Respuesta del servicio balance: '{}'", response)) // Agrega logs
                .map(response -> response.startsWith("Added Balance")) // Verifica solo la estructura
                .onErrorResume(error -> {
                    log.error("❌ Error en updateBalance: {}", error.getMessage());
                    return Mono.just(false);
                });
    }


}

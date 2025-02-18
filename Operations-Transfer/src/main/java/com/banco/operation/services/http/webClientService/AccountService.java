package com.banco.operation.services.http.webClientService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final WebClient webClientBuilder;

    @Value("${services.account.base-url}")
    private String accountBaseUrl;

    public AccountService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder.baseUrl(accountBaseUrl).build();
    }

    public Mono<Boolean> updateBalance (String senderAccount, String destinationAccount, BigDecimal amount){
        return  webClientBuilder.put()
                .uri(uriBuilder -> uriBuilder.path("/add-balance")
                        .queryParam("senderAccount", senderAccount)
                        .queryParam("destinationAccount", destinationAccount)
                        .queryParam("amount", amount)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> response.contains("Added Balance"))
                .onErrorReturn(false);
    }
}

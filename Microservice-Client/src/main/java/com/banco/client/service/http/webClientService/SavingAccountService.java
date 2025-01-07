package com.banco.client.service.http.webClientService;

import com.banco.client.presentation.dto.SavingsAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SavingAccountService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${services.account.base-url}")
    private String accountBaseUrl;

    public Mono<SavingsAccountDto> registerAccount (int idCard){
        return webClientBuilder.build()
                .post()
                .uri(accountBaseUrl + "/register?idCard={idCard}",idCard)
                .retrieve()
                .bodyToMono(SavingsAccountDto.class);
    }
}

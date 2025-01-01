package com.banco.client.service.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DuplicateKeyException.class)
    public Mono<ResponseEntity<Map<String, String>>> handlerDuplicateKeyException(DuplicateKeyException ex, ServerWebExchange exchange){
        Map<String, String> errors = new HashMap<>();
        String message = ex.getMessage();
        if(message.contains("dni")){
            errors.put("dni", "the dni is already registered");
        }else if(message.contains("phone")){
            errors.put("phone", "the phone is already registered");
        }else {
            errors.put("error", "Error en la clave duplicada");
        }
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(errors));
    }
}

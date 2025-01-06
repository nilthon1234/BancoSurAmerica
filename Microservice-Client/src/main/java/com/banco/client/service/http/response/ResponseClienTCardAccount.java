package com.banco.client.service.http.response;

import com.banco.client.presentation.dto.CardDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseClienTCardAccount {

    private String name;
    private String lastname;
    private String dni;
    private String phone;
    private List<CardDto> cards;

}

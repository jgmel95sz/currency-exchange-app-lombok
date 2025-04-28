package com.example.experienceapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExchangeResponse {

    private String username;
    private Double originalAmount;
    private Double convertedAmount;
    private String sourceCurrency;
    private String targetCurrency;
    private Double rate;
}

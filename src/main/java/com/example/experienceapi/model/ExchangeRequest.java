package com.example.experienceapi.model;

import java.time.LocalDateTime;

import com.example.supportapi.model.AuditEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeRequest {

    private Long userId;
    private Double amount;
    private String sourceCurrency;
    private String targetCurrency;
}

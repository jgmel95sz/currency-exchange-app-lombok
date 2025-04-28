package com.example.experienceapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditDto {
    private String username;
    private Double originalAmount;
    private Double convertedAmount;
    private Double rate;
    private String sourceCurrency;
    private String targetCurrency;
    private String date; 
}

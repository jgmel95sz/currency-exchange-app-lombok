package com.example.supportapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("audit_logs")
public class AuditEntity {

    @Id
    private Integer id;
    private String username;
    private Double originalAmount;
    private Double convertedAmount;
    private String sourceCurrency;
    private String targetCurrency;
    private Double rate;
    private String transactionDate;
}

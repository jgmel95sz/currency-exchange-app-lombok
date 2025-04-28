package com.example.supportapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("rates")
public class RateEntity {

    @Id
    private Long id;
    private String sourceCurrency;
    private String targetCurrency;
    private Double rate;
}

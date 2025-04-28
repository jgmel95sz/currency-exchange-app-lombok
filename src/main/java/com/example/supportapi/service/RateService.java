package com.example.supportapi.service;

import com.example.supportapi.model.RateEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RateService {

    Flux<RateEntity> getAllRates();
    Mono<RateEntity> getRate(Long id);
    Mono<RateEntity> saveRate(RateEntity rate);
    Mono<RateEntity> updateRate(Long id, RateEntity newRate);
    Mono<RateEntity> findRateBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetCurrency);
}

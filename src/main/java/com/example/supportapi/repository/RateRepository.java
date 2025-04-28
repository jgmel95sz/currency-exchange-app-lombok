package com.example.supportapi.repository;

import com.example.supportapi.model.RateEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RateRepository extends ReactiveCrudRepository<RateEntity, Long> {

    Mono<RateEntity> findBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetCurrency);
}

package com.example.supportapi.service;

import com.example.supportapi.model.RateEntity;
import com.example.supportapi.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;

    @Override
    public Flux<RateEntity> getAllRates() {
        return rateRepository.findAll();
    }

    @Override
    public Mono<RateEntity> getRate(Long id) {
        return rateRepository.findById(id);
    }

    @Override
    public Mono<RateEntity> saveRate(RateEntity rate) {
        return rateRepository.save(rate);
    }

    @Override
    public Mono<RateEntity> updateRate(Long id, RateEntity newRate) {
        return rateRepository.findById(id)
                .flatMap(existing -> {
                    existing.setSourceCurrency(newRate.getSourceCurrency());
                    existing.setTargetCurrency(newRate.getTargetCurrency());
                    existing.setRate(newRate.getRate());
                    return rateRepository.save(existing);
                });
    }

    @Override
    public Mono<RateEntity> findRateBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetCurrency) {
        return rateRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency);
    }
}

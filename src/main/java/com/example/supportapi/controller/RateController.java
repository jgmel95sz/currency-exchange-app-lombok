package com.example.supportapi.controller;

import com.example.supportapi.model.RateEntity;
import com.example.supportapi.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/support/rates")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @GetMapping
    public Flux<RateEntity> getAllRates() {
        return rateService.getAllRates();
    }

    @GetMapping("/{id}")
    public Mono<RateEntity> getRateById(@PathVariable Long id) {
        return rateService.getRate(id);
    }

    @PostMapping
    public Mono<RateEntity> createRate(@RequestBody RateEntity rate) {
        return rateService.saveRate(rate);
    }

    @PutMapping("/{id}")
    public Mono<RateEntity> updateRate(@PathVariable Long id, @RequestBody RateEntity rate) {
        return rateService.updateRate(id, rate);
    }

    @GetMapping("/search")
    public Mono<Double> findRateBySourceAndTarget(@RequestParam String sourceCurrency, @RequestParam String targetCurrency) {
        return rateService.findRateBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency)
                .map(RateEntity::getRate);
    }
}

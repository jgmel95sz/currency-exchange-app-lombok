package com.example.experienceapi.controller;

import com.example.experienceapi.model.ExchangeRequest;
import com.example.experienceapi.model.ExchangeResponse;
import com.example.experienceapi.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/experience/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping
    public Mono<ExchangeResponse> exchangeCurrency(@RequestBody ExchangeRequest request) {
        return exchangeService.performExchange(request);
    }
}

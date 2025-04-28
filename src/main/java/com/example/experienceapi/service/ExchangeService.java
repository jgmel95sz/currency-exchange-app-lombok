package com.example.experienceapi.service;

import com.example.experienceapi.model.ExchangeRequest;
import com.example.experienceapi.model.ExchangeResponse;
import reactor.core.publisher.Mono;

public interface ExchangeService {

    Mono<ExchangeResponse> performExchange(ExchangeRequest request);
}

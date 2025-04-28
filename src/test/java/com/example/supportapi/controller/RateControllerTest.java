package com.example.supportapi.controller;

import com.example.config.TestSecurityConfig;
import com.example.supportapi.model.RateEntity;
import com.example.supportapi.service.RateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@WebFluxTest(RateController.class)
@Import(TestSecurityConfig.class) 
class RateControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private RateService rateService;

    @Test
    void getAllRates_ShouldReturnList() {
        RateEntity rate = RateEntity.builder()
                .id(1L)
                .sourceCurrency("USD")
                .targetCurrency("PEN")
                .rate(3.5)
                .build();

        Mockito.when(rateService.getAllRates()).thenReturn(Flux.fromIterable(Collections.singletonList(rate)));

        webTestClient.get()
                .uri("/api/v1/support/rates")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RateEntity.class)
                .hasSize(1);
    }

    @Test
    void createRate_ShouldSaveRate() {
        RateEntity rate = RateEntity.builder()
                .sourceCurrency("USD")
                .targetCurrency("PEN")
                .rate(3.5)
                .build();

        Mockito.when(rateService.saveRate(Mockito.any())).thenReturn(Mono.just(rate));

        webTestClient.post()
                .uri("/api/v1/support/rates")
                .bodyValue(rate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RateEntity.class)
                .isEqualTo(rate);
    }
}

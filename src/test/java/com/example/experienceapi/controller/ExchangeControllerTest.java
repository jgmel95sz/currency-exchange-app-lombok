package com.example.experienceapi.controller;

import com.example.config.TestSecurityConfig;
import com.example.experienceapi.model.ExchangeRequest;
import com.example.experienceapi.model.ExchangeResponse;
import com.example.experienceapi.service.ExchangeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(ExchangeController.class)
@Import(TestSecurityConfig.class) 
class ExchangeControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ExchangeService exchangeService;

    @Test
    void exchangeCurrency_ShouldReturnResponse() {
        ExchangeRequest request = ExchangeRequest.builder()
                .userId(1L)
                .amount(100.0)
                .sourceCurrency("USD")
                .targetCurrency("PEN")
                .build();

        ExchangeResponse response = ExchangeResponse.builder()
                .username("User_1")
                .originalAmount(100.0)
                .convertedAmount(350.0)
                .rate(3.5)
                .sourceCurrency("USD")
                .targetCurrency("PEN")
                .build();

        Mockito.when(exchangeService.performExchange(Mockito.any())).thenReturn(Mono.just(response));

        webTestClient.post()
                .uri("/api/v1/experience/exchange")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ExchangeResponse.class)
                .isEqualTo(response);
    }
}

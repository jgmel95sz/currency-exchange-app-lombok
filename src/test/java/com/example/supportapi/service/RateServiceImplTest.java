package com.example.supportapi.service;

import com.example.supportapi.model.RateEntity;
import com.example.supportapi.repository.RateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class RateServiceImplTest {

    private RateRepository rateRepository;
    private RateServiceImpl rateService;

    @BeforeEach
    void setUp() {
        rateRepository = mock(RateRepository.class);
        rateService = new RateServiceImpl(rateRepository);
    }

    @Test
    void saveRate_ShouldSaveRateSuccessfully() {
        RateEntity rate = RateEntity.builder()
                .id(1L)
                .sourceCurrency("USD")
                .targetCurrency("PEN")
                .rate(3.5)
                .build();

        when(rateRepository.save(rate)).thenReturn(Mono.just(rate));

        StepVerifier.create(rateService.saveRate(rate))
                .expectNext(rate)
                .verifyComplete();
    }
}

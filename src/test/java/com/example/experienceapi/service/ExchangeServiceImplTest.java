package com.example.experienceapi.service;

import com.example.experienceapi.model.ExchangeRequest;
import com.example.experienceapi.model.ExchangeResponse;
import com.example.experienceapi.proxy.GoRestProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ExchangeServiceImplTest {

    @Mock
    private WebClient webClient;

    @Mock
    private GoRestProxy goRestProxy;

    private ExchangeServiceImpl exchangeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exchangeService = new ExchangeServiceImpl(webClient, goRestProxy);

        try {
            java.lang.reflect.Field field = ExchangeServiceImpl.class.getDeclaredField("supportApiBaseUrl");
            field.setAccessible(true);
            field.set(exchangeService, "http://localhost:8080");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPerformExchange_Success() {
        // Arrange
        ExchangeRequest request = ExchangeRequest.builder()
                .userId(123L)
                .amount(100.0)
                .sourceCurrency("USD")
                .targetCurrency("PEN")
                .build();

        WebClient.RequestHeadersUriSpec requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

        when(goRestProxy.getUserNameById(123)).thenReturn(Mono.just("Juan Perez"));

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(), any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Double.class)).thenReturn(Mono.just(3.5));

        // Act
        Mono<ExchangeResponse> result = exchangeService.performExchange(request);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response ->
                        response.getUsername().equals("Juan Perez") &&
                        response.getOriginalAmount().equals(100.0) &&
                        response.getConvertedAmount().equals(350.0) &&
                        response.getSourceCurrency().equals("USD") &&
                        response.getTargetCurrency().equals("PEN") &&
                        response.getRate().equals(3.5)
                )
                .verifyComplete();

        verify(goRestProxy, times(1)).getUserNameById(123);
        verify(webClient, times(1)).get();
    }
}

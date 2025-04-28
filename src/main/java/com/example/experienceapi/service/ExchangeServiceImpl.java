package com.example.experienceapi.service;

import com.example.experienceapi.model.ExchangeRequest;
import com.example.experienceapi.model.ExchangeResponse;
import com.example.experienceapi.proxy.GoRestProxy;
import com.example.experienceapi.model.AuditDto;  
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final WebClient webClient;
    private final GoRestProxy goRestProxy;

    @Value("${api.support.base-url:http://localhost:8080}")
    private String supportApiBaseUrl;

    @Override
    public Mono<ExchangeResponse> performExchange(ExchangeRequest request) {
        return goRestProxy.getUserNameById(request.getUserId().intValue())
            .flatMap(nombreUsuario ->
                ReactiveSecurityContextHolder.getContext()
                    .flatMap(context -> {
                        Authentication auth = context.getAuthentication();
                        String token = auth.getCredentials().toString();

                        // 1. Obtener la tasa de cambio desde Support API
                        return webClient.get()
                            .uri(supportApiBaseUrl + "/api/v1/support/rates/search?sourceCurrency={source}&targetCurrency={target}",
                                request.getSourceCurrency(), request.getTargetCurrency())
                            .headers(headers -> headers.setBearerAuth(token))
                            .retrieve()
                            .bodyToMono(Double.class)
                            .flatMap(rate -> {
                                Double convertedAmount = request.getAmount() * rate;

                                // 2. Crear el AuditDto
                                AuditDto auditDto = AuditDto.builder()
                                    .username(nombreUsuario)
                                    .originalAmount(request.getAmount())
                                    .convertedAmount(convertedAmount)
                                    .rate(rate)
                                    .sourceCurrency(request.getSourceCurrency())
                                    .targetCurrency(request.getTargetCurrency())
                                    .date(LocalDateTime.now().toString())  // Asignamos la fecha actual
                                    .build();

                                // 3. Hacer el POST a Support API para registrar la auditoría
                                return webClient.post()
                                    .uri(supportApiBaseUrl + "/api/v1/support/audit")
                                    .headers(headers -> headers.setBearerAuth(token))
                                    .bodyValue(auditDto)  // Enviar el DTO AuditDto
                                    .retrieve()
                                    .bodyToMono(AuditDto.class)  // Recibimos la respuesta del AuditDto
                                    .flatMap(savedAudit -> {
                                        // 4. Crear y devolver el ExchangeResponse
                                        ExchangeResponse response = ExchangeResponse.builder()
                                            .username(nombreUsuario)
                                            .originalAmount(request.getAmount())
                                            .convertedAmount(convertedAmount)
                                            .sourceCurrency(request.getSourceCurrency())
                                            .targetCurrency(request.getTargetCurrency())
                                            .rate(rate)
                                            .build();

                                        return Mono.just(response);  // Finalmente, retornamos el ExchangeResponse
                                    });
                            });
                    })
            );
    }
}

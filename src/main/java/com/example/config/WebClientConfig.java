package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
    
    @Bean
    public WebClient gorestWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://gorest.co.in/public/v2") // Base URL del servicio externo
                .build();
    }
}

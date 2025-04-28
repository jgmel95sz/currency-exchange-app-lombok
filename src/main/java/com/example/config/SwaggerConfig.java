package com.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Currency Exchange API")
                .version("1.0")
                .description("Documentación automática para la API de Cambio de Moneda usando Spring Boot, WebFlux, JWT y Swagger")
            );
    }
}

package com.example.experienceapi.controller;

import com.example.config.TestSecurityConfig;
import com.example.experienceapi.model.LoginRequest;
import com.example.experienceapi.model.LoginResponse;
import com.example.experienceapi.service.AuthService;

import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(AuthController.class)
@Import(TestSecurityConfig.class) 
class AuthControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AuthService authService;

    @Test
    void loginSuccess_ShouldReturnToken() {
    	 // Arrange
        String username = "admin";
        String password = "password123";
        String fakeToken = "fake-jwt-token";

        LoginRequest loginRequest = LoginRequest.builder()
                .username(username)
                .password(password)
                .build();

        Mockito.when(authService.login(anyString(), anyString()))
                .thenReturn(Mono.just(fakeToken));

        // Act & Assert
        webTestClient.post()
                .uri("/api/v1/auth/login")
                .bodyValue(loginRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LoginResponse.class)
                .value(response -> {
                    assert response.getToken().equals(fakeToken);
                });
    }

}

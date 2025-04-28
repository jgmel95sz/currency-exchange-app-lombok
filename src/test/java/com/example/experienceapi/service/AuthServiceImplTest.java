package com.example.experienceapi.service;

import com.example.config.JwtUtil;
import com.example.exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    private JwtUtil jwtUtil;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        jwtUtil = mock(JwtUtil.class);
        authService = new AuthServiceImpl(jwtUtil);
    }

    @Test
    void loginSuccess_ShouldReturnToken() {
        // Arrange
        String username = "testuser";
        String password = "password123";
        String token = "fake-jwt-token";

        when(jwtUtil.generateToken(username)).thenReturn(token);

        // Act
        Mono<String> result = authService.login(username, password);

        // Assert
        StepVerifier.create(result)
                .expectNext(token)
                .verifyComplete();

        verify(jwtUtil, times(1)).generateToken(username);
    }

    @Test
    void loginFailure_ShouldThrowUnauthorizedException() {
        // Arrange
        String username = "testuser";
        String password = "wrongpassword";

        // Act
        Mono<String> result = authService.login(username, password);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof UnauthorizedException
                        && throwable.getMessage().equals("Credenciales inválidas"))
                .verify();
        
        verify(jwtUtil, never()).generateToken(anyString());
    }
}

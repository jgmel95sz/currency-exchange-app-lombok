package com.example.experienceapi.service;

import com.example.config.JwtUtil;
import com.example.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<String> login(String username, String password) {
        if ("password123".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return Mono.just(token);
        } else {
            return Mono.error(new UnauthorizedException("Credenciales inválidas"));
        }
    }
}

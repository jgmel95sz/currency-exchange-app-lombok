package com.example.experienceapi.controller;

import com.example.experienceapi.model.LoginRequest;
import com.example.experienceapi.model.LoginResponse;
import com.example.experienceapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Mono<LoginResponse> login(@RequestBody LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword())
                .map(LoginResponse::new);
    }

}

package com.example.experienceapi.service;

import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<String> login(String username, String password);
}

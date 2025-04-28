package com.example.experienceapi.proxy;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.experienceapi.proxy.data.GoRestUserDTO;

import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GoRestProxy {

    private final WebClient gorestWebClient;

    public Mono<String> getUserNameById(Integer userId) {
        return gorestWebClient.get()
            .uri("/users/{id}", userId)
            .retrieve()
            .bodyToMono(GoRestUserDTO.class)
            .map(GoRestUserDTO::getName);
    }
}

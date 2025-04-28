package com.example.supportapi.controller;

import com.example.experienceapi.model.AuditDto;
import com.example.supportapi.model.AuditEntity;
import com.example.supportapi.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/support")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @PostMapping("/audit")
    public Mono<AuditEntity> createAudit(@RequestBody AuditDto audit) {
        return auditService.saveAudit(audit); 
    }
}

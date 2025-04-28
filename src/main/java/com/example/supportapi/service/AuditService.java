package com.example.supportapi.service;

import com.example.experienceapi.model.AuditDto;
import com.example.supportapi.model.AuditEntity;
import reactor.core.publisher.Mono;

public interface AuditService {

    Mono<AuditEntity> saveAudit(AuditDto audit);
}

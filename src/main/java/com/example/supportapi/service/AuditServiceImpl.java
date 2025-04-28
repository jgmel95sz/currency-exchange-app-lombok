package com.example.supportapi.service;

import com.example.experienceapi.model.AuditDto;
import com.example.supportapi.model.AuditEntity;
import com.example.supportapi.repository.AuditRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    public Mono<AuditEntity> saveAudit(AuditDto audit) {

        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setUsername(audit.getUsername());
        auditEntity.setOriginalAmount(audit.getOriginalAmount());
        auditEntity.setConvertedAmount(audit.getConvertedAmount());
        auditEntity.setSourceCurrency(audit.getSourceCurrency());
        auditEntity.setTargetCurrency(audit.getTargetCurrency());
        auditEntity.setRate(audit.getRate());
        auditEntity.setTransactionDate(audit.getDate());


        
        
        return auditRepository.save(auditEntity);
    }
}

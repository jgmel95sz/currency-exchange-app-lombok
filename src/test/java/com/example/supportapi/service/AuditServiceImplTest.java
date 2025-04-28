package com.example.supportapi.service;

import com.example.experienceapi.model.AuditDto;
import com.example.supportapi.model.AuditEntity;
import com.example.supportapi.repository.AuditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class AuditServiceImplTest {

    private AuditRepository auditRepository;
    private AuditServiceImpl auditService;

    @BeforeEach
    void setUp() {
        auditRepository = mock(AuditRepository.class);
        auditService = new AuditServiceImpl(auditRepository);
    }

    @Test
    void saveAudit_ShouldSaveAuditSuccessfully() {
        AuditDto audit = AuditDto.builder()
                .username("testuser")
                .originalAmount(100.0)
                .convertedAmount(350.0)
                .rate(3.5)
                .date(java.time.LocalDateTime.now().toString())
                .build();
        
        AuditEntity auditx = AuditEntity.builder()
        		.id(1)
                .username("testuser")
                .originalAmount(100.0)
                .convertedAmount(350.0)
                .rate(3.5)
                .transactionDate(java.time.LocalDateTime.now().toString())
                .build();


        when(auditRepository.save(any())).thenReturn(Mono.just(auditx));

        StepVerifier.create(auditService.saveAudit(audit))
                .expectNext(auditx)
                .verifyComplete();
    }
}

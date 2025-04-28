package com.example.supportapi.repository;

import com.example.supportapi.model.AuditEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends ReactiveCrudRepository<AuditEntity, Integer> {

}

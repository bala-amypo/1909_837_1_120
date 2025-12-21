package com.example.service.impl;

import com.example.entity.RateLimitEnforcement;
import com.example.repository.RateLimitEnforcementRepository;
import com.example.service.RateLimitEnforcementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    @Autowired
    private RateLimitEnforcementRepository repository;

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {

        if (enforcement.getLimitPerMinute() <= 0) {
            throw new IllegalArgumentException("Rate limit must be greater than 0");
        }

        return repository.save(enforcement);
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return repository.findByApiKeyId(keyId);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RateLimit Enforcement not found"));
    }
}

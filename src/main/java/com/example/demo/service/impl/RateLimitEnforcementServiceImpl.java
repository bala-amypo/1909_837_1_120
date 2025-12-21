package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {
    private final RateLimitEnforcementRepository enforcementRepository;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository enforcementRepository) {
        this.enforcementRepository = enforcementRepository;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {
        enforcement.setBlockedAt(LocalDateTime.now());
        return enforcementRepository.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return enforcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement record not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return enforcementRepository.findByApiKey_Id(keyId);
    }
}
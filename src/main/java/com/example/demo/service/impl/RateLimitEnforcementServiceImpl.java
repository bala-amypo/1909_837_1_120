package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;

import java.util.List;

public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repo;
    private final ApiKeyRepository keyRepo;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement e) {
        ApiKey key = keyRepo.findById(e.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        if (e.getLimitExceededBy() == null || e.getLimitExceededBy() < 1)
            throw new BadRequestException("Invalid limit");

        return repo.save(e);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return repo.findByApiKey_Id(keyId);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repo;
    private final ApiKeyRepository api;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repo,
                                           ApiKeyRepository api) {
        this.repo = repo;
        this.api = api;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement e) {
        api.findById(e.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        if(e.getLimitExceededBy() < 1)
            throw new BadRequestException("Invalid exceed value");

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

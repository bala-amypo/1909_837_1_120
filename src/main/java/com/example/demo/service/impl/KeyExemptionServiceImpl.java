package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository apiKeyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo,
                                   ApiKeyRepository apiKeyRepo) {
        this.repo = repo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public KeyExemption createExemption(KeyExemption e) {

        apiKeyRepo.findById(e.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key missing"));

        if(e.getValidUntil().isBefore(Instant.now()))
            throw new BadRequestException("Expired");

        return repo.save(e);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption e) {
        KeyExemption x = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));

        x.setNotes(e.getNotes());
        x.setUnlimitedAccess(e.getUnlimitedAccess());
        x.setTemporaryExtensionLimit(e.getTemporaryExtensionLimit());
        x.setValidUntil(e.getValidUntil());
        return repo.save(x);
    }

    @Override
    public KeyExemption getExemptionByKey(Long keyId) {
        return repo.findByApiKey_Id(keyId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return repo.findAll();
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

import java.time.Instant;
import java.util.List;

public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository keyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public KeyExemption createExemption(KeyExemption e) {
        ApiKey key = keyRepo.findById(e.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        if (e.getTemporaryExtensionLimit() != null && e.getTemporaryExtensionLimit() < 0)
            throw new BadRequestException("Invalid extension");

        return repo.save(e);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption e) {
        KeyExemption ex = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));

        ex.setNotes(e.getNotes());
        ex.setTemporaryExtensionLimit(e.getTemporaryExtensionLimit());
        ex.setUnlimitedAccess(e.getUnlimitedAccess());
        ex.setValidUntil(e.getValidUntil());

        return repo.save(ex);
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

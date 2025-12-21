package com.example.service.impl;

import com.example.entity.KeyExemption;
import com.example.repository.KeyExemptionRepository;
import com.example.service.KeyExemptionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    @Autowired
    private KeyExemptionRepository repository;

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {
        exemption.setActive(true);
        return repository.save(exemption);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption updated) {

        KeyExemption existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exemption Not Found"));

        existing.setReason(updated.getReason());
        existing.setActive(updated.isActive());
        existing.setExpiryDate(updated.getExpiryDate());

        return repository.save(existing);
    }

    @Override
    public KeyExemption getExemptionByKey(Long apiKeyId) {
        return repository.findByApiKeyId(apiKeyId)
                .orElseThrow(() -> new EntityNotFoundException("Exemption Not Found for Key"));
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return repository.findAll();
    }
}

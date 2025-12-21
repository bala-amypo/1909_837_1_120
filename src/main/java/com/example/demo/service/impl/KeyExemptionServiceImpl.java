package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {
    private final KeyExemptionRepository exemptionRepository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionServiceImpl(KeyExemptionRepository exemptionRepository, ApiKeyRepository apiKeyRepository) {
        this.exemptionRepository = exemptionRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {
        return exemptionRepository.save(exemption);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {
        return exemptionRepository.save(exemption);
    }

    @Override
    public Optional<KeyExemption> getExemptionByKey(Long apiKeyId) {
        return exemptionRepository.findByApiKey_Id(apiKeyId);
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return exemptionRepository.findAll();
    }

    @Override
    public void deleteExemption(Long id) {
        exemptionRepository.deleteById(id);
    }
}
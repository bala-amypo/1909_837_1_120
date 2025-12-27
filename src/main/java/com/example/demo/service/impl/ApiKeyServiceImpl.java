package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.ApiKeyService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiRepo;
    private final QuotaPlanRepository planRepo;

    public ApiKeyServiceImpl(ApiKeyRepository apiRepo, QuotaPlanRepository planRepo) {
        this.apiRepo = apiRepo;
        this.planRepo = planRepo;
    }

    @Override
    public ApiKey createApiKey(ApiKey k) {
        QuotaPlan p = planRepo.findById(k.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        if(!p.getActive()) throw new BadRequestException("Plan inactive");

        return apiRepo.save(k);
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey k) {
        ApiKey x = getApiKeyById(id);
        x.setActive(k.getActive());
        x.setOwnerId(k.getOwnerId());
        x.setPlan(k.getPlan());
        return apiRepo.save(x);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @Override
    public ApiKey getApiKeyByValue(String value) {
        return apiRepo.findByKeyValue(value)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiRepo.findAll();
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey k = getApiKeyById(id);
        k.setActive(false);
        apiRepo.save(k);
    }
}

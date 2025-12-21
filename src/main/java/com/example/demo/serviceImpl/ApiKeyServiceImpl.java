package com.example.demo.service.impl;

import com.example.demo.dto.ApiKeyDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ApiKey;
import com.example.demo.model.QuotaPlan;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepo;
    private final QuotaPlanRepository planRepo;
    private final ModelMapper mapper;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepo,
                             QuotaPlanRepository planRepo,
                             ModelMapper mapper){

        this.apiKeyRepo = apiKeyRepo;
        this.planRepo = planRepo;
        this.mapper = mapper;
    }

    @Override
    public ApiKeyDto createApiKey(ApiKeyDto dto) {

        QuotaPlan plan = planRepo.findById(dto.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        if(!plan.getActive())
            throw new BadRequestException("Cannot assign inactive plan");

        ApiKey key = mapper.map(dto, ApiKey.class);
        key.setPlan(plan);
        key.setActive(true);
        key.setCreatedAt(Timestamp.from(Instant.now()));
        key.setUpdatedAt(Timestamp.from(Instant.now()));

        ApiKey saved = apiKeyRepo.save(key);

        return mapper.map(saved, ApiKeyDto.class);
    }

    @Override
    public ApiKeyDto updateApiKey(Long id, ApiKeyDto dto) {

        ApiKey key = apiKeyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API key not found"));

        mapper.map(dto, key);
        key.setUpdatedAt(Timestamp.from(Instant.now()));

        return mapper.map(apiKeyRepo.save(key), ApiKeyDto.class);
    }

    @Override
    public ApiKeyDto getApiKeyById(Long id) {
        ApiKey key = apiKeyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));
        return mapper.map(key, ApiKeyDto.class);
    }

    @Override
    public ApiKeyDto getApiKeyByValue(String keyValue) {
        ApiKey key = apiKeyRepo.findByKeyValue(keyValue)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));
        return mapper.map(key, ApiKeyDto.class);
    }

    @Override
    public List<ApiKeyDto> getAllApiKeys() {
        return apiKeyRepo.findAll()
                .stream()
                .map(k -> mapper.map(k, ApiKeyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey key = apiKeyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        key.setActive(false);
        apiKeyRepo.save(key);
    }
}

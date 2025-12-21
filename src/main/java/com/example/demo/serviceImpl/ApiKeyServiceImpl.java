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

    private final ApiKeyRepository apiKeyRepository;
    private final QuotaPlanRepository quotaPlanRepository;
    private final ModelMapper mapper;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository,
                             QuotaPlanRepository quotaPlanRepository,
                             ModelMapper mapper) {
        this.apiKeyRepository = apiKeyRepository;
        this.quotaPlanRepository = quotaPlanRepository;
        this.mapper = mapper;
    }

    @Override
    public ApiKeyDto createApiKey(ApiKeyDto dto) {

        if (apiKeyRepository.findByKeyValue(dto.getKeyValue()).isPresent())
            throw new BadRequestException("API Key already exists");

        QuotaPlan plan = quotaPlanRepository.findById(dto.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        if (!plan.getActive())
            throw new BadRequestException("Plan inactive");

        ApiKey key = new ApiKey();
        key.setKeyValue(dto.getKeyValue());
        key.setOwnerId(dto.getOwnerId());
        key.setPlan(plan);
        key.setActive(true);
        key.setCreatedAt(Timestamp.from(Instant.now()));
        key.setUpdatedAt(Timestamp.from(Instant.now()));

        ApiKey saved = apiKeyRepository.save(key);

        return mapper.map(saved, ApiKeyDto.class);
    }

    @Override
    public ApiKeyDto updateApiKey(Long id, ApiKeyDto dto) {
        ApiKey key = apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        if (!key.getActive())
            throw new BadRequestException("Cannot update inactive key");

        key.setActive(dto.getActive());
        key.setUpdatedAt(Timestamp.from(Instant.now()));

        return mapper.map(apiKeyRepository.save(key), ApiKeyDto.class);
    }

    @Override
    public ApiKeyDto getApiKeyById(Long id) {
        ApiKey key = apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
        return mapper.map(key, ApiKeyDto.class);
    }

    @Override
    public ApiKeyDto getApiKeyByValue(String value) {
        ApiKey key = apiKeyRepository.findByKeyValue(value)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
        return mapper.map(key, ApiKeyDto.class);
    }

    @Override
    public List<ApiKeyDto> getAllApiKeys() {
        return apiKeyRepository.findAll()
                .stream()
                .map(k -> mapper.map(k, ApiKeyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey key = apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        if (!key.getActive())
            throw new BadRequestException("Already inactive");

        key.setActive(false);
        key.setUpdatedAt(Timestamp.from(Instant.now()));
        apiKeyRepository.save(key);
    }
}

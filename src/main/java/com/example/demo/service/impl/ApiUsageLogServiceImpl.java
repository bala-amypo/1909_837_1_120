package com.example.demo.service.impl;

import com.example.demo.dto.ApiUsageLogDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ApiKey;
import com.example.demo.model.ApiUsageLog;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository logRepo;
    private final ApiKeyRepository apiKeyRepo;
    private final ModelMapper mapper;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository logRepo,
                                  ApiKeyRepository apiKeyRepo,
                                  ModelMapper mapper) {
        this.logRepo = logRepo;
        this.apiKeyRepo = apiKeyRepo;
        this.mapper = mapper;
    }

    @Override
    public ApiUsageLogDto logUsage(ApiUsageLogDto dto) {

        ApiKey key = apiKeyRepo.findById(dto.getApiKeyId())
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        if (!key.getActive())
            throw new BadRequestException("Cannot log usage for inactive key");

        if (dto.getTimestamp().after(Timestamp.from(Instant.now())))
            throw new BadRequestException("Timestamp cannot be in future");

        ApiUsageLog log = mapper.map(dto, ApiUsageLog.class);
        log.setApiKey(key);

        return mapper.map(logRepo.save(log), ApiUsageLogDto.class);
    }

    @Override
    public List<ApiUsageLogDto> getUsageForApiKey(Long keyId) {
        return logRepo.findByApiKey_Id(keyId)
                .stream()
                .map(l -> mapper.map(l, ApiUsageLogDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApiUsageLogDto> getUsageForToday(Long keyId) {

        Instant start = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant now = Instant.now();

        return logRepo.findForKeyBetween(keyId, start, now)
                .stream()
                .map(l -> mapper.map(l, ApiUsageLogDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long countRequestsToday(Long keyId) {

        Instant start = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant now = Instant.now();

        return logRepo.countForKeyBetween(keyId, start, now);
    }
}

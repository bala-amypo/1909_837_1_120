package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository usageRepo;
    private final ApiKeyRepository apiKeyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository usageRepo,
                                  ApiKeyRepository apiKeyRepo) {
        this.usageRepo = usageRepo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {

        ApiKey key = apiKeyRepo.findById(log.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        if (log.getTimestamp().isAfter(Instant.now())) {
            throw new BadRequestException("Timestamp cannot be in the future");
        }

        return usageRepo.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return usageRepo.findByApiKey_Id(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        Instant start = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = Instant.now();
        return usageRepo.findForKeyBetween(keyId, start, end);
    }

    @Override
    public int countRequestsToday(Long keyId) {
        Instant start = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = Instant.now();
        return usageRepo.countForKeyBetween(keyId, start, end).intValue();
    }
}

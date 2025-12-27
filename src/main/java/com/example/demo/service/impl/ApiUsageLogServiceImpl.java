package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repo;
    private final ApiKeyRepository keyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {

        ApiKey key = keyRepo.findById(log.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        if (log.getTimestamp().isAfter(Instant.now()))
            throw new BadRequestException("Future timestamp");

        return repo.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return repo.findByApiKey_Id(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        Instant start = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant now = Instant.now();
        return repo.findForKeyBetween(keyId, start, now);
    }

    @Override
    public int countRequestsToday(Long keyId) {
        Instant start = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant now = Instant.now();
        return repo.countForKeyBetween(keyId, start, now);
    }
}

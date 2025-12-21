package com.example.service.impl;

import com.example.entity.ApiUsageLog;
import com.example.repository.ApiUsageLogRepository;
import com.example.service.ApiUsageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    @Autowired
    private ApiUsageLogRepository usageRepo;

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        if (log.getTimestamp() == null) {
            log.setTimestamp(LocalDate.now());
        }
        return usageRepo.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return usageRepo.findByApiKeyId(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        return usageRepo.findByApiKeyIdAndTimestamp(keyId, LocalDate.now());
    }

    @Override
    public Long countRequestsToday(Long keyId) {
        return usageRepo.countByApiKeyIdAndTimestamp(keyId, LocalDate.now());
    }
}

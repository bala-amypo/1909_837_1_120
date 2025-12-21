package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {
    private final ApiUsageLogRepository usageLogRepository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository usageLogRepository) {
        this.usageLogRepository = usageLogRepository;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        log.setTimestamp(LocalDateTime.now());
        return usageLogRepository.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return usageLogRepository.findByApiKey_Id(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        return usageLogRepository.findByApiKey_IdAndTimestampAfter(keyId, startOfDay);
    }

    @Override
    public int countRequestsToday(Long keyId) {
        return getUsageForToday(keyId).size();
    }
}
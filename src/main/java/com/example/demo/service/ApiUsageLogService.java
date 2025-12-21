package com.example.service;

import com.example.entity.ApiUsageLog;
import java.util.List;

public interface ApiUsageLogService {

    ApiUsageLog logUsage(ApiUsageLog log);

    List<ApiUsageLog> getUsageForApiKey(Long keyId);

    List<ApiUsageLog> getUsageForToday(Long keyId);

    Long countRequestsToday(Long keyId);
}

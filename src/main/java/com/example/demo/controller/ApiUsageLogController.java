package com.example.controller;

import com.example.entity.ApiUsageLog;
import com.example.service.ApiUsageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
public class ApiUsageLogController {

    @Autowired
    private ApiUsageLogService apiUsageLogService;

    @PostMapping
    public ApiUsageLog logUsage(@RequestBody ApiUsageLog log) {
        return apiUsageLogService.logUsage(log);
    }

    @GetMapping("/key/{keyId}")
    public List<ApiUsageLog> getUsageForKey(@PathVariable Long keyId) {
        return apiUsageLogService.getUsageForApiKey(keyId);
    }

    @GetMapping("/key/{keyId}/today")
    public List<ApiUsageLog> getTodayUsage(@PathVariable Long keyId) {
        return apiUsageLogService.getUsageForToday(keyId);
    }

    @GetMapping("/key/{keyId}/count-today")
    public Long countTodayRequests(@PathVariable Long keyId) {
        return apiUsageLogService.countRequestsToday(keyId);
    }
}

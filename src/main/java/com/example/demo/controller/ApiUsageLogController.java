package com.example.demo.controller;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
public class ApiUsageLogController {

    private final ApiUsageLogService service;

    public ApiUsageLogController(ApiUsageLogService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiUsageLog> log(@RequestBody ApiUsageLog log) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.logUsage(log));
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<ApiUsageLog>> getByKey(@PathVariable Long keyId) {
        return ResponseEntity.ok(service.getUsageForApiKey(keyId));
    }

    @GetMapping("/key/{keyId}/today")
    public ResponseEntity<List<ApiUsageLog>> today(@PathVariable Long keyId) {
        return ResponseEntity.ok(service.getUsageForToday(keyId));
    }

    @GetMapping("/key/{keyId}/count-today")
    public ResponseEntity<Integer> countToday(@PathVariable Long keyId) {
        return ResponseEntity.ok(service.countRequestsToday(keyId));
    }
}

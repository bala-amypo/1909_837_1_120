package com.example.demo.controller;

import com.example.demo.dto.ApiUsageLogDto;
import com.example.demo.dto.CountResponseDto;
import com.example.demo.service.ApiUsageLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
@Tag(name = "Usage Logs", description = "API Usage Tracking")
public class ApiUsageLogController {

    private final ApiUsageLogService service;

    public ApiUsageLogController(ApiUsageLogService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiUsageLogDto> log(@RequestBody ApiUsageLogDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.logUsage(dto));
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<ApiUsageLogDto>> getForKey(@PathVariable Long keyId) {
        return ResponseEntity.ok(service.getUsageForApiKey(keyId));
    }

    @GetMapping("/key/{keyId}/today")
    public ResponseEntity<List<ApiUsageLogDto>> getToday(@PathVariable Long keyId) {
        return ResponseEntity.ok(service.getUsageForToday(keyId));
    }

    @GetMapping("/key/{keyId}/count-today")
    public ResponseEntity<CountResponseDto> countToday(@PathVariable Long keyId) {
        return ResponseEntity.ok(new CountResponseDto(service.countRequestsToday(keyId)));
    }
}

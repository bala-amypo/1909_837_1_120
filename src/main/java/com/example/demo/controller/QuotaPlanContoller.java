package com.example.demo.controller;

import com.example.demo.dto.QuotaPlanDto;
import com.example.demo.service.QuotaPlanService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quota-plans")
public class QuotaPlanController {

    private final QuotaPlanService service;

    public QuotaPlanController(QuotaPlanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<QuotaPlanDto> create(@RequestBody QuotaPlanDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createQuotaPlan(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuotaPlanDto> update(@PathVariable Long id,
                                               @RequestBody QuotaPlanDto dto) {
        return ResponseEntity.ok(service.updateQuotaPlan(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuotaPlanDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getQuotaPlanById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuotaPlanDto>> getAll() {
        return ResponseEntity.ok(service.getAllPlans());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateQuotaPlan(id);
        return ResponseEntity.noContent().build();
    }
}

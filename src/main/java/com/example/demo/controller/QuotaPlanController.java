package com.example.demo.controller;

import com.example.demo.entity.QuotaPlan;
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
    public ResponseEntity<QuotaPlan> create(@RequestBody QuotaPlan plan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createQuotaPlan(plan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuotaPlan> update(@PathVariable Long id, @RequestBody QuotaPlan plan) {
        return ResponseEntity.ok(service.updateQuotaPlan(id, plan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuotaPlan> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getQuotaPlanById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuotaPlan>> all() {
        return ResponseEntity.ok(service.getAllPlans());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateQuotaPlan(id);
        return ResponseEntity.noContent().build();
    }
}

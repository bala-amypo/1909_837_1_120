package com.example.demo.controller;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.service.QuotaPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quota-plans")
@Tag(name = "Quota Plan Management")
public class QuotaPlanController {
    private final QuotaPlanService quotaPlanService;

    public QuotaPlanController(QuotaPlanService quotaPlanService) {
        this.quotaPlanService = quotaPlanService;
    }

    @PostMapping
    public QuotaPlan createQuotaPlan(@RequestBody QuotaPlan plan) {
        return quotaPlanService.createQuotaPlan(plan);
    }

    @GetMapping("/{id}")
    public QuotaPlan getQuotaPlan(@PathVariable Long id) {
        return quotaPlanService.getQuotaPlanById(id);
    }

    @GetMapping
    public List<QuotaPlan> getAllPlans() {
        return quotaPlanService.getAllPlans();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        quotaPlanService.deactivateQuotaPlan(id);
    }
}
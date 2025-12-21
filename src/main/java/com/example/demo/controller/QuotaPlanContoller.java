package com.example.controller;

import com.example.entity.QuotaPlan;
import com.example.service.QuotaPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quota-plans")
public class QuotaPlanController {

    @Autowired
    private QuotaPlanService quotaPlanService;

    @PostMapping
    public QuotaPlan createPlan(@RequestBody QuotaPlan plan) {
        return quotaPlanService.createQuotaPlan(plan);
    }

    @PutMapping("/{id}")
    public QuotaPlan updatePlan(@PathVariable Long id, @RequestBody QuotaPlan plan) {
        return quotaPlanService.updateQuotaPlan(id, plan);
    }

    @GetMapping("/{id}")
    public QuotaPlan getPlanById(@PathVariable Long id) {
        return quotaPlanService.getQuotaPlanById(id);
    }

    @GetMapping
    public List<QuotaPlan> getAllPlans() {
        return quotaPlanService.getAllPlans();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivatePlan(@PathVariable Long id) {
        quotaPlanService.deactivateQuotaPlan(id);
        return "Quota Plan Deactivated Successfully";
    }
}

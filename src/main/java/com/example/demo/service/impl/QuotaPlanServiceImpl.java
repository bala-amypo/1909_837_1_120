package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {
    private final QuotaPlanRepository quotaPlanRepository;

    public QuotaPlanServiceImpl(QuotaPlanRepository quotaPlanRepository) {
        this.quotaPlanRepository = quotaPlanRepository;
    }

    @Override
    public QuotaPlan createQuotaPlan(QuotaPlan plan) {
        return quotaPlanRepository.save(plan);
    }

    @Override
    public QuotaPlan getQuotaPlanById(Long id) {
        return quotaPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return quotaPlanRepository.findAll();
    }

    // This fixes the "does not override abstract method deactivateQuotaPlan" error
    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan plan = getQuotaPlanById(id);
        plan.setActive(false);
        quotaPlanRepository.save(plan);
    }
}
package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;

import java.util.List;

public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repo;

    public QuotaPlanServiceImpl(QuotaPlanRepository repo) {
        this.repo = repo;
    }

    @Override
    public QuotaPlan createQuotaPlan(QuotaPlan p) {
        if (p.getDailyLimit() == null || p.getDailyLimit() <= 0)
            throw new BadRequestException("Invalid daily limit");
        return repo.save(p);
    }

    @Override
    public QuotaPlan updateQuotaPlan(Long id, QuotaPlan update) {
        QuotaPlan p = getQuotaPlanById(id);
        p.setPlanName(update.getPlanName());
        p.setDailyLimit(update.getDailyLimit());
        p.setDescription(update.getDescription());
        return repo.save(p);
    }

    @Override
    public QuotaPlan getQuotaPlanById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quota plan not found"));
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return repo.findAll();
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan p = getQuotaPlanById(id);
        p.setActive(false);
        repo.save(p);
    }
}

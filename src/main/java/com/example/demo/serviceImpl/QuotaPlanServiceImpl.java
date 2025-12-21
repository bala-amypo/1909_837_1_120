package com.example.service.impl;

import com.example.entity.QuotaPlan;
import com.example.repository.QuotaPlanRepository;
import com.example.service.QuotaPlanService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    @Autowired
    private QuotaPlanRepository quotaPlanRepository;

    @Override
    public QuotaPlan createQuotaPlan(QuotaPlan plan) {
        if (plan.getLimitPerDay() <= 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
        plan.setActive(true);
        return quotaPlanRepository.save(plan);
    }

    @Override
    public QuotaPlan updateQuotaPlan(Long id, QuotaPlan updated) {
        QuotaPlan existing = getQuotaPlanById(id);
        existing.setName(updated.getName());
        existing.setLimitPerDay(updated.getLimitPerDay());
        existing.setActive(updated.isActive());
        return quotaPlanRepository.save(existing);
    }

    @Override
    public QuotaPlan getQuotaPlanById(Long id) {
        return quotaPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quota Plan Not Found"));
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return quotaPlanRepository.findAll();
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan plan = getQuotaPlanById(id);
        plan.setActive(false);
        quotaPlanRepository.save(plan);
    }
}

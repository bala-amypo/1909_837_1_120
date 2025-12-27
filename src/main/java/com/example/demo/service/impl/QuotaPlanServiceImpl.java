package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.*;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repo;

    public QuotaPlanServiceImpl(QuotaPlanRepository repo) {
        this.repo = repo;
    }

    @Override
    public QuotaPlan createQuotaPlan(QuotaPlan p) {
        if(p.getDailyLimit() <= 0)
            throw new BadRequestException("Invalid limit");
        return repo.save(p);
    }

    @Override
    public QuotaPlan updateQuotaPlan(Long id, QuotaPlan p) {
        QuotaPlan q = getQuotaPlanById(id);
        q.setPlanName(p.getPlanName());
        q.setDailyLimit(p.getDailyLimit());
        return repo.save(q);
    }

    @Override
    public QuotaPlan getQuotaPlanById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
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

package com.example.demo.service;

import com.example.demo.entity.QuotaPlan;
import java.util.List;

public interface QuotaPlanService {
    QuotaPlan createQuotaPlan(QuotaPlan p);
    QuotaPlan updateQuotaPlan(Long id, QuotaPlan p);
    QuotaPlan getQuotaPlanById(Long id);
    List<QuotaPlan> getAllPlans();
    void deactivateQuotaPlan(Long id);
}

package com.example.service;

import com.example.entity.QuotaPlan;
import java.util.List;

public interface QuotaPlanService {

    QuotaPlan createQuotaPlan(QuotaPlan plan);

    QuotaPlan updateQuotaPlan(Long id, QuotaPlan plan);

    QuotaPlan getQuotaPlanById(Long id);

    List<QuotaPlan> getAllPlans();

    void deactivateQuotaPlan(Long id);
}

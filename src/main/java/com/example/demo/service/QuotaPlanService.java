package com.example.demo.service;
import com.example.demo.entity.QuotaPlan;
import java.util.List;

public interface QuotaPlanService {
    QuotaPlan createQuotaPlan(QuotaPlan plan); // Rename to match Controller
    QuotaPlan getQuotaPlanById(Long id);       // Rename to match Controller
    void deactivateQuotaPlan(Long id);         // Rename to match Controller
    List<QuotaPlan> getAllPlans();
}
package com.example.demo.service;

import com.example.demo.dto.QuotaPlanDto;
import java.util.List;

public interface QuotaPlanService {

    QuotaPlanDto createQuotaPlan(QuotaPlanDto planDto);

    QuotaPlanDto updateQuotaPlan(Long id, QuotaPlanDto planDto);

    QuotaPlanDto getQuotaPlanById(Long id);

    List<QuotaPlanDto> getAllPlans();

    void deactivateQuotaPlan(Long id);
}

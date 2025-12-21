package com.example.demo.service.impl;

import com.example.demo.dto.QuotaPlanDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.QuotaPlan;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repo;
    private final ApiKeyRepository apiKeyRepo;
    private final ModelMapper mapper;

    public QuotaPlanServiceImpl(QuotaPlanRepository repo,
                                ApiKeyRepository apiKeyRepo,
                                ModelMapper mapper) {
        this.repo = repo;
        this.apiKeyRepo = apiKeyRepo;
        this.mapper = mapper;
    }

    @Override
    public QuotaPlanDto createQuotaPlan(QuotaPlanDto dto) {
        QuotaPlan plan = mapper.map(dto, QuotaPlan.class);
        plan.setActive(true);
        return mapper.map(repo.save(plan), QuotaPlanDto.class);
    }

    @Override
    public QuotaPlanDto updateQuotaPlan(Long id, QuotaPlanDto dto) {
        QuotaPlan plan = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        plan.setDailyLimit(dto.getDailyLimit());
        plan.setDescription(dto.getDescription());
        return mapper.map(repo.save(plan), QuotaPlanDto.class);
    }

    @Override
    public QuotaPlanDto getQuotaPlanById(Long id) {
        return repo.findById(id)
                .map(p -> mapper.map(p, QuotaPlanDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
    }

    @Override
    public List<QuotaPlanDto> getAllPlans() {
        return repo.findAll().stream().map(p -> mapper.map(p, QuotaPlanDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan plan = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        if (!apiKeyRepo.findByPlan_Id(id).isEmpty())
            throw new BadRequestException("Cannot deactivate, active keys exist");

        plan.setActive(false);
        repo.save(plan);
    }
}

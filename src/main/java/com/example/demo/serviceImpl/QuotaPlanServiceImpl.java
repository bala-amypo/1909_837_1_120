package com.example.demo.service.impl;

import com.example.demo.dto.QuotaPlanDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.QuotaPlan;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository quotaPlanRepository;
    private final ModelMapper mapper;

    public QuotaPlanServiceImpl(QuotaPlanRepository quotaPlanRepository,
                                ModelMapper mapper) {
        this.quotaPlanRepository = quotaPlanRepository;
        this.mapper = mapper;
    }

    @Override
    public QuotaPlanDto createQuotaPlan(QuotaPlanDto dto) {

        if (dto.getDailyLimit() <= 0) {
            throw new BadRequestException("Daily limit must be greater than 0");
        }

        QuotaPlan plan = mapper.map(dto, QuotaPlan.class);
        plan.setActive(true);

        return mapper.map(quotaPlanRepository.save(plan), QuotaPlanDto.class);
    }

    @Override
    public QuotaPlanDto updateQuotaPlan(Long id, QuotaPlanDto dto) {

        QuotaPlan plan = quotaPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quota Plan not found"));

        mapper.map(dto, plan);
        return mapper.map(quotaPlanRepository.save(plan), QuotaPlanDto.class);
    }

    @Override
    public QuotaPlanDto getQuotaPlanById(Long id) {
        QuotaPlan plan = quotaPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quota Plan not found"));

        return mapper.map(plan, QuotaPlanDto.class);
    }

    @Override
    public List<QuotaPlanDto> getAllPlans() {
        return quotaPlanRepository.findAll()
                .stream()
                .map(p -> mapper.map(p, QuotaPlanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan plan = quotaPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quota Plan not found"));

        plan.setActive(false);
        quotaPlanRepository.save(plan);
    }
}
    
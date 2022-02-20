package com.shilov.shilov.service;

import com.shilov.shilov.entity.Deal;
import com.shilov.shilov.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealService {
    private final DealRepository dealRepository;

    @Autowired
    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public void create(Deal deal) {
        dealRepository.save(deal);
    }

    public List<Deal> getAll() {
        return dealRepository.findAll();
    }

    public Optional<Deal> getById(Long id) {
        return dealRepository.findById(id);
    }

    public void deleteById(Long id) {
        dealRepository.deleteById(id);
    }
}

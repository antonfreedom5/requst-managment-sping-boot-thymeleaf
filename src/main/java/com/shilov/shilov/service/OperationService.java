package com.shilov.shilov.service;

import com.shilov.shilov.entity.Operation;
import com.shilov.shilov.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationService {
    private final OperationRepository operationRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public void create(Operation operation) {
        operationRepository.save(operation);
    }

    public List<Operation> getAll() {
        return operationRepository.findAll();
    }

    public Optional<Operation> getById(Long id) {
        return operationRepository.findById(id);
    }

    public void deleteById(Long id) {
        operationRepository.deleteById(id);
    }
}

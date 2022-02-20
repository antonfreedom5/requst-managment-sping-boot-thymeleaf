package com.shilov.shilov.service;

import com.shilov.shilov.entity.Request;
import com.shilov.shilov.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void create(Request request) {
        requestRepository.save(request);
    }

    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    public Optional<Request> getById(Long id) {
        return requestRepository.findById(id);
    }

    public void deleteById(Long id) {
        requestRepository.deleteById(id);
    }
}

package com.example.multiplace.service;

import com.example.multiplace.repository.ToolEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class ToolEntityService {

    private final ToolEntityRepository toolEntityRepository;

    public ToolEntityService(ToolEntityRepository toolEntityRepository) {
        this.toolEntityRepository = toolEntityRepository;
    }
}

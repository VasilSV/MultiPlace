package com.example.multiplace.service;

import com.example.multiplace.dtos.ToolDTO;
import com.example.multiplace.model.entity.ToolEntity;
import com.example.multiplace.repository.ToolEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToolEntityService {
    private final ModelMapper modelMapper;
    private final ToolEntityRepository toolEntityRepository;

    public ToolEntityService(ModelMapper modelMapper, ToolEntityRepository toolEntityRepository) {
        this.modelMapper = modelMapper;
        this.toolEntityRepository = toolEntityRepository;
    }

    public List<ToolDTO> getAllTools() {
        return
                toolEntityRepository.findAll().stream()
                        .map(ToolEntityService::mapToolsDTO)
                        .collect(Collectors.toList());

    }

    private static ToolDTO mapToolsDTO(ToolEntity toolEntity) {

        return new ToolDTO()
                .setId(toolEntity.getId())
                .setToolName(toolEntity.getToolName())
                .setDescription(toolEntity.getDescription())
                .setPrice(toolEntity.getPrice());
    }

    public Optional<ToolDTO> findById(Long id) {
        return  toolEntityRepository.findById(id)
                .map(ToolEntityService::mapToolsDTO);
    }
    public void deleteToolByID(Long id) {
        this.toolEntityRepository.deleteById(id);
    }

    public Long createTool(ToolDTO toolDTO) {

//        Optional<AuthorEntity> authorOpt = authorRepository
//                .findByName(bookDTO.getAuthor().getName());

        ToolEntity newTool = new ToolEntity()
                .setToolName(toolDTO.getToolName())
                .setDescription(toolDTO.getDescription())
                .setPrice(toolDTO.getPrice());

        newTool = toolEntityRepository.save(newTool);

        return newTool.getId();
    }
}
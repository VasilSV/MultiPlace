package com.example.multiplace.service;

import com.example.multiplace.dtos.ToolDTO;
import com.example.multiplace.model.entity.ToolEntity;
import com.example.multiplace.repository.ToolEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ToolEntityServiceTest {
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    private ToolEntityService toolEntityService;
    private ToolEntityRepository toolEntityRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        toolEntityRepository = mock(ToolEntityRepository.class);
        modelMapper = new ModelMapper();
        toolEntityService = new ToolEntityService(modelMapper, toolEntityRepository);
    }

    @Test
    void getAllTools() {

        List<ToolEntity> toolEntities = Arrays.asList(
                new ToolEntity().setId(1L).setToolName("Hammer")
                        .setDescription("Tool for hammering").setPrice(BigDecimal.valueOf(39)),
                new ToolEntity().setId(2L).setToolName("Screwdriver")
                        .setDescription("Tool for screwing").setPrice(BigDecimal.valueOf(34))
        );
        when(toolEntityRepository.findAll()).thenReturn(toolEntities);


        List<ToolDTO> result = toolEntityService.getAllTools();


        assertEquals(toolEntities.size(), result.size());
        for (int i = 0; i < toolEntities.size(); i++) {
            ToolEntity toolEntity = toolEntities.get(i);
            ToolDTO toolDTO = result.get(i);
            assertEquals(toolEntity.getId(), toolDTO.getId());
            assertEquals(toolEntity.getToolName(), toolDTO.getToolName());
            assertEquals(toolEntity.getDescription(), toolDTO.getDescription());
            assertEquals(toolEntity.getPrice(), toolDTO.getPrice());
        }
    }

    @Test
    void findById() {

        Long toolId = 1L;
        ToolEntity toolEntity = new ToolEntity().setId(toolId).setToolName("Wrench").setDescription("Tool for turning nuts").setPrice(BigDecimal.valueOf(14.99));
        when(toolEntityRepository.findById(toolId)).thenReturn(Optional.of(toolEntity));


        Optional<ToolDTO> result = toolEntityService.findById(toolId);


        assertTrue(result.isPresent());
        assertEquals(toolEntity.getId(), result.get().getId());
        assertEquals(toolEntity.getToolName(), result.get().getToolName());
        assertEquals(toolEntity.getDescription(), result.get().getDescription());
        assertEquals(toolEntity.getPrice(), result.get().getPrice());
    }

    @Test
    void findById_NotFound() {

        Long toolId = 1L;
        when(toolEntityRepository.findById(toolId)).thenReturn(Optional.empty());


        Optional<ToolDTO> result = toolEntityService.findById(toolId);


        assertTrue(result.isEmpty());
    }

    @Test
    void deleteToolByID() {

        Long toolId = 1L;


        toolEntityService.deleteToolByID(toolId);


        verify(toolEntityRepository, times(1)).deleteById(toolId);
    }



    @Test
    void addTool() {

        ToolDTO toolDTO = new ToolDTO().setId(1L).setToolName("Gosho")
                .setDescription("Tool for gosho").setPrice(BigDecimal.valueOf(49.99));
        ToolEntity toolEntity = modelMapper.map(toolDTO, ToolEntity.class);
        when(toolEntityRepository.save(any())).thenReturn(toolEntity);

        ToolEntity result = toolEntityService.addTool(toolDTO);
        assertNotNull(result);
        assertEquals(toolEntity.getId(), result.getId());
        assertEquals(toolEntity.getToolName(), result.getToolName());
        assertEquals(toolEntity.getDescription(), result.getDescription());
        assertEquals(toolEntity.getPrice(), result.getPrice());
    }
}
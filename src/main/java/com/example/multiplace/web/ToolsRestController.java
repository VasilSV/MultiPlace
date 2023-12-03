package com.example.multiplace.web;

import com.example.multiplace.dtos.ToolDTO;
import com.example.multiplace.model.entity.ToolEntity;
import com.example.multiplace.service.ToolEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tools")
public class ToolsRestController {
    private final ToolEntityService toolEntityService;

    public ToolsRestController(ToolEntityService toolEntityService) {
        this.toolEntityService = toolEntityService;
    }

    @GetMapping
    public ResponseEntity<List<ToolDTO>> getAllTools() {
        return ResponseEntity.ok(toolEntityService.getAllTools());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolDTO> getToolsById(@PathVariable("id") Long id) {
        Optional<ToolDTO> toolDTOOptional = toolEntityService.findById(id);

        return toolDTOOptional.map(ResponseEntity::ok)
                .orElse(  ResponseEntity.notFound().build());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ToolDTO> deleteToolByID(@PathVariable("id") Long id) {

        toolEntityService.deleteToolByID(id);

        return ResponseEntity
                .noContent()
                .build();
    }
    @PostMapping
    public ResponseEntity<ToolDTO> createTool(
            @RequestBody ToolDTO toolDTO,
            UriComponentsBuilder uriComponentsBuilder) {

        long newToolID = toolEntityService.createTool(toolDTO);

        return ResponseEntity.created(
                uriComponentsBuilder.path("/api/tools/{id}").buildAndExpand(newToolID).toUri()
        ).build();
    }

//    @PostMapping
//    public ResponseEntity<ToolDTO> createTool(
//            @RequestBody ToolDTO toolDTO,
//            UriComponentsBuilder uriComponentsBuilder) {
//
//        long newToolID = toolEntityService.createTool(toolDTO);
//
//        return ResponseEntity.created(
//                uriComponentsBuilder.path("/api/tools/{id}").build(newToolID)
//        ).build();
//    }
//    @PostMapping
//    public ResponseEntity<ToolDTO> addTool(@RequestBody ToolDTO tool) {
//        try {
//            ToolEntity newTool = toolEntityService.addTool(tool);
//            return new ResponseEntity<>(newTool, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}


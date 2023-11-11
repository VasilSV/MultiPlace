package com.example.multiplace.web;

import com.example.multiplace.dtos.ToolDTO;
import com.example.multiplace.dtos.UserDTO;
import com.example.multiplace.service.ToolEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

}


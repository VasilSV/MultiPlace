package com.example.multiplace.web;

import com.example.multiplace.dtos.ToolDTO;
import com.example.multiplace.service.ToolEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
public class ToolsRestController {
    private final ToolEntityService toolEntityService;

    public ToolsRestController(ToolEntityService toolEntityService) {
        this.toolEntityService = toolEntityService;
    }

//    @GetMapping
//    public ResponseEntity<List<ToolDTO>> getAllTools() {
//        return ResponseEntity.ok(toolEntityService.());
//    }


}

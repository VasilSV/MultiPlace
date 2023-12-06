package com.example.multiplace.dtos;


import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ToolDTO {

    private Long id;
    @NotNull
    private String toolName;

    private String description;
    @NotNull
    private BigDecimal price;


    public ToolDTO() {
    }

    public Long getId() {
        return id;
    }

    public ToolDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getToolName() {
        return toolName;
    }

    public ToolDTO setToolName(String toolName) {
        this.toolName = toolName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ToolDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ToolDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}

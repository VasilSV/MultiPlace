package com.example.multiplace.dtos;


import java.math.BigDecimal;

public class ToolDTO {


    private String toolName;

    private String description;

    private BigDecimal price;


    public ToolDTO() {
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

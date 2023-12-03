package com.example.multiplace.dtos;

import com.example.multiplace.model.entity.ToolEntity;
import com.example.multiplace.model.entity.UserEntity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersDTO {


    private Long id;


    private BigDecimal orderPrice;

    private Integer quantity;
    private LocalDateTime orderTime;

    private UserEntity customer;

    private List<ToolEntity> orderedTools;

    public OrdersDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrdersDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OrdersDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public OrdersDTO setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrdersDTO setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public OrdersDTO setCustomer(UserEntity customer) {
        this.customer = customer;
        return this;
    }



    public List<ToolEntity> getOrderedTools() {
        return orderedTools;
    }

    public OrdersDTO setOrderedTools(List<ToolDTO> orderedTools) {
        this.orderedTools = orderedTools.stream()
                .map(toolDTO -> new ToolEntity()
                        .setId(toolDTO.getId())
                        .setToolName(toolDTO.getToolName())
                        .setDescription(toolDTO.getDescription())
                        .setPrice(toolDTO.getPrice()))
                .collect(Collectors.toList());
        return this;
    }
}

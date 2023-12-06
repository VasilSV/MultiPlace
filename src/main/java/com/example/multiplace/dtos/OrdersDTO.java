package com.example.multiplace.dtos;

import com.example.multiplace.model.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrdersDTO {


    private Long id;

    @NotNull
    @Positive
    private BigDecimal orderPrice;
    @NotNull
    private Integer quantity;
    @NotNull
    private LocalDateTime orderTime;
    @NotNull
    @JsonIgnore
    private UserEntity customer;
    @NotNull
    private List<ToolDTO> orderedTools;

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


    public List<ToolDTO> getOrderedTools() {
        return orderedTools;
    }

    public OrdersDTO setOrderedTools(List<ToolDTO> orderedTools) {
        this.orderedTools = orderedTools;
        return this;
    }
}
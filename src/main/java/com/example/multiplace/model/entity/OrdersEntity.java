package com.example.multiplace.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal orderPrice;
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime orderTime;
    @ManyToMany
    private List<ToolEntity> orderedTools;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private UserEntity customer;

    public OrdersEntity() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrdersEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public OrdersEntity setCustomer(UserEntity customer) {
        this.customer = customer;
        return this;
    }

    public List<ToolEntity> getOrderedTools() {
        return orderedTools;
    }

    public OrdersEntity setOrderedTools(List<ToolEntity> orderedTools) {
        this.orderedTools = orderedTools;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OrdersEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public OrdersEntity setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrdersEntity setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

}

package com.example.multiplace.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String quantity;

    @OneToMany(mappedBy = "orders")
    private List<ToolEntity> orderedTools;
    @ManyToOne
    private UserEntity customer;

    public OrdersEntity() {
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

    public String getQuantity() {
        return quantity;
    }

    public OrdersEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
}

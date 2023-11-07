package com.example.multiplace.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "tools")
@Entity
public class ToolEntity {
    //offers
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String toolName;
    @Column( columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;


    @ManyToOne
    private OrdersEntity orders;
    @ManyToOne
    private UserEntity username;

    public ToolEntity() {
    }


    public OrdersEntity getOrders() {
        return orders;
    }

    public ToolEntity setOrders(OrdersEntity orders) {
        this.orders = orders;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ToolEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public UserEntity getUsername() {
        return username;
    }

    public ToolEntity setUsername(UserEntity username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ToolEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getToolName() {
        return toolName;
    }

    public ToolEntity setToolName(String toolName) {
        this.toolName = toolName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ToolEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "ToolEntity{" +
                "id=" + id +
                ", toolName='" + toolName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

package com.example.multiplace.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tool_type")
public class ToolTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(nullable = false)
//    private ToolTypeEntityEnum toolTypeName;

}

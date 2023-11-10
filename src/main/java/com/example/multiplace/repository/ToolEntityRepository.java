package com.example.multiplace.repository;

import com.example.multiplace.model.entity.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolEntityRepository extends JpaRepository<ToolEntity, Long> {
}
package com.example.multiplace.repository;

import com.example.multiplace.dtos.ToolDTO;
import com.example.multiplace.model.entity.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolEntityRepository extends JpaRepository<ToolEntity, Long> {
    Optional<ToolDTO> findToolEntitiesByToolName(String toolName);


    Optional<ToolDTO> deleteToolEntityById(Long id);
}
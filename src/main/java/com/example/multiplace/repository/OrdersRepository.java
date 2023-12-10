package com.example.multiplace.repository;

import com.example.multiplace.model.entity.OrdersEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository  extends JpaRepository<OrdersEntity, Long> {
    Optional<Object> findFirstByCustomer_Email(String email);
}

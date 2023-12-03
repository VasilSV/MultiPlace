package com.example.multiplace.repository;

import com.example.multiplace.model.entity.OrdersEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository  extends JpaRepository<OrdersEntity, Long> {
}

package com.example.multiplace.service;

import com.example.multiplace.dtos.OrdersDTO;
import com.example.multiplace.repository.OrdersRepository;
import com.example.multiplace.repository.ToolEntityRepository;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.repository.UserRoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class OrdersServiceTest {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToolEntityRepository toolEntityRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @BeforeEach
    public void setUp() {
        toolEntityRepository = mock(ToolEntityRepository.class);

        userRoleRepository = mock(UserRoleRepository.class);
        userRepository = mock(UserRepository.class);
        ordersRepository = mock(OrdersRepository.class);
        ordersService = mock(OrdersService.class);
    }

    @Test
    @DirtiesContext
    void testDeleteOrdersByID() {

        Long orderId = 1L;
        ordersService.deleteOrdersByID(orderId);

        Optional<OrdersDTO> ordersDTO = ordersService.findById(orderId);
        assertFalse(ordersDTO.isPresent());
    }

}

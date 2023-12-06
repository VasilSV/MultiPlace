package com.example.multiplace.service;

import com.example.multiplace.dtos.OrdersDTO;
import com.example.multiplace.dtos.ToolDTO;
import com.example.multiplace.model.entity.OrdersEntity;
import com.example.multiplace.model.entity.ToolEntity;
import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.model.entity.UserRoleEntity;
import com.example.multiplace.repository.OrdersRepository;
import com.example.multiplace.repository.ToolEntityRepository;
import com.example.multiplace.repository.UserRepository;
import com.example.multiplace.repository.UserRoleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final ToolEntityRepository toolEntityRepository;
    private final ToolEntityService toolEntityService;
    private UserRoleRepository userRoleRepository;

    public OrdersService(OrdersRepository ordersRepository,
                         UserRepository userRepository,
                         ToolEntityRepository toolEntityRepository,
                         ToolEntityService toolEntityService, UserRoleRepository userRoleRepository) {
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
        this.toolEntityRepository = toolEntityRepository;
        this.toolEntityService = toolEntityService;
        this.userRoleRepository = userRoleRepository;
    }

    public List<OrdersDTO> getAllOrders() {
        return
                ordersRepository.findAll().stream()
                        .map(OrdersService::mapOrdersDTO)
                        .collect(Collectors.toList());

    }

    private static OrdersDTO mapOrdersDTO(OrdersEntity ordersEntity) {
        List<ToolDTO> orderedTools = ordersEntity.getOrderedTools().stream()
                .map(ToolEntityService ::mapToolsDTO)
                .collect(Collectors.toList());

        return new OrdersDTO()
                .setId(ordersEntity.getId())
                .setCustomer(ordersEntity.getCustomer())
                .setOrderedTools(orderedTools)
                .setOrderTime(ordersEntity.getOrderTime())
                .setQuantity(ordersEntity.getQuantity())
                .setOrderPrice(ordersEntity.getOrderPrice());

    }

    public Optional<OrdersDTO> findById(Long id) {
        return ordersRepository.findById(id)
                .map(OrdersService::mapOrdersDTO);
    }

    public void deleteOrdersByID(Long id) {
        this.ordersRepository.deleteById(id);
    }

@Transactional
public Long createOrder(OrdersDTO ordersDTO, Authentication authentication) {
    if (ordersDTO == null || ordersDTO.getOrderPrice() == null) {
        throw new IllegalArgumentException("OrdersDTO is null or Order Price is null");
    }


//        String customerEmail = ((UserEntity) authentication.getPrincipal()).getEmail();
//        UserEntity customer = userRepository.findByEmail(customerEmail)
//                .orElseThrow(() -> new IllegalArgumentException("Customer not found with email: " + customerEmail));

        String username = authentication.getName();
        UserEntity customer = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with username: " + username));

        List<UserRoleEntity> customerRoles = customer.getRoles();
        if (customerRoles == null) {
            throw new IllegalArgumentException("Customer roles are null");
        }

        List<UserRoleEntity> roles = userRoleRepository.findAllByRoleIn(customerRoles.stream()
                .map(UserRoleEntity::getRole)
                .collect(Collectors.toList()));

        if (roles.size() != customerRoles.size()) {
            throw new IllegalArgumentException("Not all roles were found");
        }
        customer.setRoles(roles);
//        UserEntity customer = userRepository.findByEmail(ordersDTO.getCustomer().getEmail())
//                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));


//        List<ToolEntity> orderedTools = toolEntityRepository
//                .findToolEntitiesByToolName(String.valueOf(ordersDTO.getOrderedTools()));
//        List<ToolEntity> orderedTools = toolEntityRepository
//                .findByToolNameIn(Collections.singletonList(ordersDTO.getOrderedTools()
//                        .stream().map(ToolDTO::getToolName)
//                        .collect(Collectors.toList()).toString()));
    List<String> toolNames = ordersDTO.getOrderedTools()
            .stream()
            .map(ToolDTO::getToolName)
            .collect(Collectors.toList());

    List<ToolEntity> orderedTools = toolEntityRepository.findByToolNameIn(toolNames);
        if (orderedTools.isEmpty()) {
            throw new IllegalArgumentException("No tools found");
        }

        OrdersEntity newOrder = new OrdersEntity()
                .setOrderPrice(ordersDTO.getOrderPrice())
                .setOrderTime(LocalDateTime.now())
                .setOrderedTools(orderedTools)
                .setQuantity(ordersDTO.getQuantity())
                .setCustomer(customer);

        newOrder = ordersRepository.save(newOrder);

        return newOrder.getId();
    }

//    public Long createOrder(OrdersDTO ordersDTO) {
//
//        Optional<UserEntity> customerOpt = userRepository
//                .findByEmail(ordersDTO.getCustomer().getEmail());
//        List<ToolEntity> toolsOpt = toolEntityRepository
//                .findToolEntitiesByToolName(String.valueOf(ordersDTO.getOrderedTools()));
//
//        OrdersEntity newOrder = new OrdersEntity()
//                .setOrderPrice(ordersDTO.getOrderPrice())
//                .setOrderTime(LocalDateTime.now())
//                .setOrderedTools(toolsOpt)
//                .setCustomer(customerOpt);
//
//        newOrder = ordersRepository.save(newOrder);
//
//        return newOrder.getId();
//    }
}

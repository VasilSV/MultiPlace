package com.example.multiplace.service;

import com.example.multiplace.dtos.OrdersDTO;
import com.example.multiplace.dtos.ToolDTO;
import com.example.multiplace.model.entity.OrdersEntity;
import com.example.multiplace.model.entity.ToolEntity;
import com.example.multiplace.model.entity.UserEntity;
import com.example.multiplace.repository.OrdersRepository;
import com.example.multiplace.repository.ToolEntityRepository;
import com.example.multiplace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final ToolEntityRepository toolEntityRepository;
    private final ToolEntityService toolEntityService;

    public OrdersService(OrdersRepository ordersRepository,
                         UserRepository userRepository,
                         ToolEntityRepository toolEntityRepository,
                         ToolEntityService toolEntityService) {
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
        this.toolEntityRepository = toolEntityRepository;
        this.toolEntityService = toolEntityService;
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
    public Long createOrder(OrdersDTO ordersDTO) {
        UserEntity customer = userRepository.findByEmail(ordersDTO.getCustomer().getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

//        List<ToolEntity> orderedTools = toolEntityRepository
//                .findToolEntitiesByToolName(String.valueOf(ordersDTO.getOrderedTools()));
        List<ToolEntity> orderedTools = toolEntityRepository
                .findByToolName(String.valueOf(ordersDTO.getOrderedTools()));
        if (orderedTools.isEmpty()) {
            throw new IllegalArgumentException("No tools found");
        }

        OrdersEntity newOrder = new OrdersEntity()
                .setOrderPrice(ordersDTO.getOrderPrice())
                .setOrderTime(LocalDateTime.now())
                .setOrderedTools(orderedTools)
                .setQuantity(ordersDTO.getQuantity())
                .setCustomer(ordersDTO.getCustomer());

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

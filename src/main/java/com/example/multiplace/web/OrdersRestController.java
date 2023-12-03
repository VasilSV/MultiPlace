package com.example.multiplace.web;

import com.example.multiplace.dtos.OrdersDTO;
import com.example.multiplace.dtos.ToolDTO;
import com.example.multiplace.dtos.UserRegistrationDTO;
import com.example.multiplace.service.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class OrdersRestController {
    private final OrdersService ordersService;

    public OrdersRestController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }


    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrders() {
        return ResponseEntity.ok(ordersService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getToolsById(@PathVariable("id") Long id) {
        Optional<OrdersDTO> orderDTOOptional = ordersService.findById(id);

        return orderDTOOptional.map(ResponseEntity::ok)
                .orElse(  ResponseEntity.notFound().build());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OrdersDTO> deleteOrdersByID(@PathVariable("id") Long id) {

        ordersService.deleteOrdersByID(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping
    public ResponseEntity<OrdersDTO> createOrder(
            @RequestBody OrdersDTO ordersDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        if (ordersDTO == null || ordersDTO.getOrderedTools() == null || ordersDTO.getOrderedTools().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Long newOrderID = ordersService.createOrder(ordersDTO);
        Optional<OrdersDTO> newOrderDTO = ordersService.findById(newOrderID);

        return newOrderDTO.map(order -> ResponseEntity.created(
                uriComponentsBuilder.path("/api/orders/{id}").buildAndExpand(newOrderID).toUri()
        ).body(order)).orElse(ResponseEntity.badRequest().build());
    }



    @ModelAttribute("ordersDTO")
    public OrdersDTO initOrdersDTO() {
        return new OrdersDTO();
    }
}

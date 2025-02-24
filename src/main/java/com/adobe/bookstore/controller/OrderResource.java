package com.adobe.bookstore.controller;

import com.adobe.bookstore.entity.Order;
import com.adobe.bookstore.service.OrderService;
import com.adobe.bookstore.dto.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.createOrder(orderDTO);
        if (order == null) {
            return ResponseEntity.badRequest().body("Not enough stock for one or more books.");
        }
        return ResponseEntity.ok(Map.of("orderId", order.getId()));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}


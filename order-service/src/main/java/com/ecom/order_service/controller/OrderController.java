package com.ecom.order_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.order_service.model.Orders;
import com.ecom.order_service.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController 
{
	
	private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Orders placeOrder(@RequestBody Orders order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Orders getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
}

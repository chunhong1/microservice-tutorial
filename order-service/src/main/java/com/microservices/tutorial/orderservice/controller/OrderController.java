package com.microservices.tutorial.orderservice.controller;

import com.microservices.tutorial.orderservice.dto.OrderRequest;
import com.microservices.tutorial.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest)
    {
        System.out.println("➡️ Received OrderRequest: " + orderRequest);
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
}

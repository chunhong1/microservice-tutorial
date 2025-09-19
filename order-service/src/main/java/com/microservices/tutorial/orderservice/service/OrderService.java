package com.microservices.tutorial.orderservice.service;

import com.microservices.tutorial.orderservice.dto.OrderRequest;
import com.microservices.tutorial.orderservice.model.Order;
import com.microservices.tutorial.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest)
    {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());

        orderRepository.save(order);
    }
}

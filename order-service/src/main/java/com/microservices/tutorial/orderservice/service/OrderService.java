package com.microservices.tutorial.orderservice.service;

import com.microservices.tutorial.orderservice.client.InventoryClient;
import com.microservices.tutorial.orderservice.dto.OrderRequest;
import com.microservices.tutorial.orderservice.model.Order;
import com.microservices.tutorial.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService
{
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest)
    {
        boolean isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isProductInStock)
        {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
        }else
        {
            throw new RuntimeException("Product with SKU Code " + orderRequest.skuCode() + " is not in stock.");
        }
    }
}

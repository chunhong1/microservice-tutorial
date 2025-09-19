package com.microservices.tutorial.orderservice.repository;

import com.microservices.tutorial.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>
{
}

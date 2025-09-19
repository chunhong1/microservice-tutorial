package com.microservices.tutorial.inventoryservice.service;

import com.microservices.tutorial.inventoryservice.repostory.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService
{
    private final InventoryRepository inventoryRepository;

    public Boolean isInStock(String skuCode, Integer quantity)
    {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}

package com.microservices.tutorial.productservice.service;

import com.microservices.tutorial.productservice.dto.ProductRequest;
import com.microservices.tutorial.productservice.dto.ProductResponse;
import com.microservices.tutorial.productservice.model.Product;
import com.microservices.tutorial.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest)
    {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product created successfully");

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public List<ProductResponse> getAllProducts()
    {
        return productRepository.findAll()
                .stream().
                map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                ))
                .toList();
    }
}

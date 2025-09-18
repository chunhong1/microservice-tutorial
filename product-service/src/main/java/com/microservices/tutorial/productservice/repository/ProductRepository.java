package com.microservices.tutorial.productservice.repository;

import com.microservices.tutorial.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>
{
}

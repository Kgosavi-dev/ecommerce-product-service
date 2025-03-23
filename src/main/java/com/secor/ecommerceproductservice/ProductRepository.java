package com.secor.ecommerceproductservice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> getDistinctFirstByCategory(String category);
}

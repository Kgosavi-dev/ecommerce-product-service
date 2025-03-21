package com.secor.ecommerceproductservice;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface MenuRepository extends MongoRepository<MenuItem, String> {
}

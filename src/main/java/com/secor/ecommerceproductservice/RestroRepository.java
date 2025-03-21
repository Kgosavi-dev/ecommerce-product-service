package com.secor.ecommerceproductservice;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface RestroRepository extends MongoRepository<Restro, String> {
}

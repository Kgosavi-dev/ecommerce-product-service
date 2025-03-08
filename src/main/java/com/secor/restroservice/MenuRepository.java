package com.secor.restroservice;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface MenuRepository extends MongoRepository<MenuItem, String> {
}

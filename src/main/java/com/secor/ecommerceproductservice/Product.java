package com.secor.ecommerceproductservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter @Setter
public class Product {

    @Id
    private String productid;
    private String name;
    private String description;
    private Double price;
    private String category;

}

package com.secor.restroservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menu")
@Getter @Setter
public class MenuItem {

    @Id
    private String itemid;
    private String restroid;
    private String dishid;
    private Integer price;

}

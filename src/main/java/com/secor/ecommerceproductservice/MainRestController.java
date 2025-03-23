package com.secor.ecommerceproductservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("api/v1")
public class MainRestController {

    private static final Logger log = LoggerFactory.getLogger(MainRestController.class);

    @Autowired
    ProductRepository productRepository;
    @Autowired
    AuthService authService;
    @Autowired
    MenuRepository menuRepository;


    @PostMapping("create/product")
    public ResponseEntity<?> createProduct(@RequestBody Product product,
                                           @RequestHeader("Authorization") String token)
    {
        log.info("Received request to create product: {}", product);
        if(!authService.validateToken(token))
        {
            log.info("Invalid token: {}", token);
            return ResponseEntity.badRequest().body("Invalid token");
        }
        log.info("Token is valid: {}", token);
        log.info("Saving product: {}", product);

        product.setProductid(String.valueOf(new Random().nextInt(1000)));
        productRepository.save(product);

        return ResponseEntity.ok("Product created successfully");
    }

    @GetMapping("get/product/{productid}")
    public ResponseEntity<?> getOrder(@PathVariable("productid") String productid)
    {
        Product product = productRepository.findById(productid).get();
        return ResponseEntity.ok(product);
    }

}

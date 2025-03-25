package com.secor.ecommerceproductservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.List;

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
    public ResponseEntity<?> getProduct(@PathVariable("productid") String productid)
    {
        Product product = productRepository.findById(productid).get();
        return ResponseEntity.ok(product);
    }


//    public List<Product> searchProducts(String name, String category) {
//        List<Product> matchingProducts = new ArrayList<>();
//        for (Product product : products) {
//            if ((name == null || product.getName().equalsIgnoreCase(name)) &&
//                    (category == null || product.getCategory().equalsIgnoreCase(category))) {
//                matchingProducts.add(product);
//            }
//        }
//        return matchingProducts;
//    }

    @GetMapping("/products/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
//        return productService.searchProducts(name, category);
        return productRepository.findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name != null ? name : "", category != null ? category : "");
    }


}

package com.secor.restroservice;

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
    RestroRepository restroRepository;
    @Autowired
    AuthService authService;
    @Autowired
    MenuRepository menuRepository;


    @PostMapping("create/restro")
    public ResponseEntity<?> createRestro(@RequestBody Restro restro,
                                           @RequestHeader("Authorization") String token)
    {
        log.info("Received request to create restro: {}", restro);
        if(!authService.validateToken(token))
        {
            log.info("Invalid token: {}", token);
            return ResponseEntity.badRequest().body("Invalid token");
        }
        log.info("Token is valid: {}", token);
        log.info("Saving restro: {}", restro);

        restro.setRestroid(String.valueOf(new Random().nextInt(1000)));
        restroRepository.save(restro);

        return ResponseEntity.ok("Restro created successfully");
    }

    @PostMapping("create/menuitem")
    public ResponseEntity<?> createMenuItem(@RequestBody MenuItem menuItem,
                                            @RequestHeader("Authorization") String token)
    {
        log.info("Received request to create menu item: {}", menuItem);
        if(!authService.validateToken(token))
        {
            log.info("Invalid token: {}", token);
            return ResponseEntity.badRequest().body("Invalid token");
        }
        log.info("Token is valid: {}", token);
        log.info("Saving menu item: {}", menuItem);

        menuItem.setItemid(String.valueOf(new Random().nextInt(1000)));
        menuRepository.save(menuItem);

        return ResponseEntity.ok("Menu item created successfully");
    }

}

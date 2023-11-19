package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.services.FridgeService;

@RestController
public class FridgeController {
    
    @Autowired
    private FridgeService fridgeService;
    
    @RequestMapping("/hello-fridge")
    public String helloFridge() {
        return "Hello, world!";
    }

    @PostMapping("/new-fridge/")
    public ResponseEntity<String> createFridge(Account account) {
        System.out.println("Creating fridge...");

        // Create the fridge
        try {
            fridgeService.createFridge(account);
            return ResponseEntity.ok().body("Fridge created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating fridge");
        }
    }
}

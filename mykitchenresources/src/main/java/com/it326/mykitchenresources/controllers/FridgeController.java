package com.it326.mykitchenresources.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it326.mykitchenresources.services.FridgeService;

@RestController
public class FridgeController {
    
    @Autowired
    private FridgeService fridgeService;
    
    @RequestMapping("/hello-fridge")
    public String helloFridge() {
        return "Hello, world!";
    }

    public ResponseEntity<String> createFridge(Integer accountId) {
        System.out.println("Creating fridge...");

        // Create the fridge
        try {
            fridgeService.createFridge(accountId);
            return ResponseEntity.ok().body("Fridge created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating fridge");
        }
    }

    public ResponseEntity<String> addIngredientToFridge(Integer accountId, 
    String ingName, Optional<Double> ingQuantity, Optional<String> ingExpDate) {
        System.out.println("Adding ingredient to fridge...");

        // Add the ingredient to the fridge
        try {
            fridgeService.addIngredientToFridge(accountId, ingName, ingQuantity, ingExpDate);
            return ResponseEntity.ok().body("Ingredient added to fridge successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding ingredient to fridge");
        }
    }
}

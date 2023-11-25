package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.services.ShoppingListService;
import com.it326.mykitchenresources.entities.Ingredient;
import java.util.ArrayList;

@RestController
public class ShoppingListController {
    
    @Autowired
    private ShoppingListService shoppingListService;

    @RequestMapping("/hello-list")
    public String helloList() {
        return "Hello, list!";
    }

    public ResponseEntity<String> createShoppingList(Integer accountId) {
        System.out.println("Creating shopping list...");

        // Create the shopping list
        try {
            shoppingListService.createShoppingList(accountId, new ArrayList<Ingredient>());
            return ResponseEntity.ok().body("Shopping list created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating shopping list");
        }
    }
}

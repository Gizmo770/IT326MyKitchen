package com.it326.mykitchenresources.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it326.mykitchenresources.entities.ShoppingListIngredient;
import com.it326.mykitchenresources.services.ShoppingListService;

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
            shoppingListService.createShoppingList(accountId);
            return ResponseEntity.ok().body("Shopping list created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating shopping list");
        }
    }

    public ResponseEntity<List<ShoppingListIngredient>> getListItems(Integer accountId) {
        System.out.println("Getting shopping list ingredients...");

        // Get the shopping list ingredients
        try {
            List<ShoppingListIngredient> ingredients = shoppingListService.getListItems(accountId);
            return ResponseEntity.ok().body(ingredients);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> updateShoppingListIngredients(Integer accountId, List<ShoppingListIngredient> listItems) {
        System.out.println("Updating shopping list ingredients...");

        // Update the shopping list ingredients
        try {
            shoppingListService.updateShoppingListIngredients(accountId, listItems);
            return ResponseEntity.ok().body("Shopping list ingredients updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating shopping list ingredients");
        }
    }
}

package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/{shoppingListId}/add-ingredient")
    public ResponseEntity<String> addIngredientToShoppingList(
        @PathVariable Integer shoppingListId,
        @RequestParam Integer ingredientId,
        @RequestParam(required = false) Double quantity) {

        try {
            shoppingListService.addIngredientToShoppingList(shoppingListId, ingredientId, quantity);
            return ResponseEntity.ok("Ingredient added to shopping list successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding ingredient to shopping list: " + e.getMessage());
        }
    }
    

    @DeleteMapping("/{shoppingListId}/remove-ingredient/{ingredientId}")
    public ResponseEntity<String> removeIngredientFromShoppingList(
        @PathVariable Integer shoppingListId,
        @PathVariable Integer ingredientId) {

        try {
            shoppingListService.deleteShoppingListIngredient(shoppingListId.longValue(), ingredientId.longValue());
            return ResponseEntity.ok("Ingredient removed from shopping list successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing ingredient from shopping list: " + e.getMessage());
        }
    }
}

package com.it326.mykitchenresources.controllers;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.services.IngredientService;
import com.it326.mykitchenresources.services.ShoppingListService;

@RestController
public class ShoppingListController {
    
    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private IngredientService ingredientService;

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
    @PostMapping("/shopping-list/add-ingredient")
    @ResponseBody
    public ResponseEntity<String> addIngredientToShoppingList(
            @RequestParam Integer shoppingListId,
            @RequestParam String ingredientName,
            @RequestParam(required = false) Optional<Double> quantity) {
        
        // The logic here assumes you will find or create an ingredient based on the name.
        // You might need to adjust this based on your actual application logic.
        Ingredient ingredient = ingredientService.findByName(ingredientName)
                .orElseGet(() -> ingredientService.createIngredient(ingredientName, quantity.orElse(0.0)));

        shoppingListService.addIngredientToShoppingList(shoppingListId, ingredient.getId(), 1); // Assuming priority is set to 1 for now
        return ResponseEntity.ok("Ingredient added to shopping list");
    }

    @DeleteMapping("/shopping-list/remove-ingredient")
    @ResponseBody
    public ResponseEntity<String> removeIngredientFromShoppingList(
            @RequestParam Integer shoppingListId,
            @RequestParam Integer ingredientId) {
        
        shoppingListService.removeIngredientFromShoppingList(shoppingListId, ingredientId);
        return ResponseEntity.ok("Ingredient removed from shopping list");
    }


}

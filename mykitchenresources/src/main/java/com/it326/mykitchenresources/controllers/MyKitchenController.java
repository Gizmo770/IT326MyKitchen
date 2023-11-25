package com.it326.mykitchenresources.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it326.mykitchenresources.entities.recipe.RecipeDetails;

@Controller
public class MyKitchenController {

    @Autowired
    private AccountController accountController;

    @Autowired
    private ShoppingListController shoppingListController;

    @Autowired
    private RecipeController recipeController;

    @Autowired
    private FridgeController fridgeController;

    @RequestMapping("/hello-kitchen")
    @ResponseBody
    public String helloKitchen() {
        return "Hello, kitchen!";
    }

    /* ----------------------------
    Account Controller Functions
    ----------------------------- */
    // Works
    @PostMapping("/account/create")
    @ResponseBody
	public ResponseEntity<String> createAccount(
        @RequestParam String name, 
        @RequestParam String username, 
        @RequestParam String password) {
		return accountController.createAccount(name, username, password);
	}

    // Works
    @DeleteMapping("/account/delete")
    @ResponseBody
    public ResponseEntity<String> deleteAccount(
        @PathVariable Integer accountId) {
        return accountController.deleteAccount(accountId);
    }

    /* ----------------------------------
    Shopping List Controller Functions
    ----------------------------------- */
    // Works
    @PostMapping("/shopping-list/create")
    @ResponseBody
    public ResponseEntity<String> createShoppingList(
        @PathVariable Integer currentAccId) {
        return shoppingListController.createShoppingList(currentAccId);
    }

    //TODO: Method for adding ingredient to shopping list

    //TODO: Method for removing ingredient from shopping list


    /* --------------------------
    Fridge Controller Functions
    --------------------------- */
    // Works
    @PostMapping("/fridge/create")
    @ResponseBody
    public ResponseEntity<String> createFridge(
        @PathVariable Integer currentAccId) {
        return fridgeController.createFridge(currentAccId);
    }

    // Works
    @PostMapping("/fridge/add-ingredient")
    @ResponseBody
    public ResponseEntity<String> addIngredientToFridge(
        @RequestParam Integer currentAccId,
        @RequestParam String ingName,
        @RequestParam(required = false) Optional<Double> ingQuantity,
        @RequestParam(required = false) Optional<String> ingExpDate) {
        return fridgeController.addIngredientToFridge(currentAccId, ingName, ingQuantity, ingExpDate);
    }

    //TODO: Method for removing ingredient from fridge


    /* --------------------------
    Recipe Controller Functions
    --------------------------- */
    // TODO: Test and debug this function when Fridge with Ingredients are implemented
    @RequestMapping("/recipe/fridge/search")
    @ResponseBody
    public RecipeDetails[] searchRecipesByFridge(
        @PathVariable Integer currentAccId) {
        return recipeController.searchRecipesByFridge(currentAccId);
    }

    // Works
    @RequestMapping("/recipe/string/search")
    @ResponseBody
    public RecipeDetails[] searchRecipesByString(
        @RequestParam String ingredients) {
        return recipeController.searchRecipesByString(ingredients);
    }
}

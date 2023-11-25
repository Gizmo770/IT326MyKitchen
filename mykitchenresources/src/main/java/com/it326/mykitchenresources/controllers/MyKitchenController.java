package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it326.mykitchenresources.entities.Account;
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
	public ResponseEntity<String> createAccount(String name, String username, String password) {
		return accountController.createAccount(name, username, password);
	}

    // Works
    @DeleteMapping("/account/delete")
    @ResponseBody
    public ResponseEntity<String> deleteAccount(Integer accountId) {
        return accountController.deleteAccount(accountId);
    }

    /* ----------------------------------
    Shopping List Controller Functions
    ----------------------------------- */
    // Works
    @PostMapping("/shopping-list/create")
    @ResponseBody
    public ResponseEntity<String> createShoppingList(Integer currentAccId) {
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
    public ResponseEntity<String> createFridge(Integer currentAccId) {
        return fridgeController.createFridge(currentAccId);
    }

    //TODO: Method for adding ingredient to fridge

    //TODO: Method for removing ingredient from fridge


    /* --------------------------
    Recipe Controller Functions
    --------------------------- */
    // TODO: Test and debug this function when Fridge with Ingredients are implemented
    @RequestMapping("/recipe/fridge/search")
    @ResponseBody
    public RecipeDetails[] searchRecipesByFridge(Account currentAccount) {
        return recipeController.searchRecipesByFridge(currentAccount);
    }

    // Works
    @RequestMapping("/recipe/string/search")
    @ResponseBody
    public RecipeDetails[] searchRecipesByString(String ingredients) {
        return recipeController.searchRecipesByString(ingredients);
    }
}

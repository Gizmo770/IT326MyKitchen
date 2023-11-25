package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.entities.recipe.Recipe;
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
    @PostMapping("/account/create")
    @ResponseBody
	public ResponseEntity<String> createAccount(String name, String username, String password) {
		return accountController.createAccount(name, username, password);
	}

    @DeleteMapping("/account/delete")
    @ResponseBody
    public ResponseEntity<String> deleteAccount(Integer accountId) {
        return accountController.deleteAccount(accountId);
    }

    /* ----------------------------------
    Shopping List Controller Functions
    ----------------------------------- */
    @PostMapping("/shopping-list/create")
    @ResponseBody
    public ResponseEntity<String> createShoppingList(Account currentAccount) {
        return shoppingListController.createShoppingList(currentAccount);
    }


    /* --------------------------
    Fridge Controller Functions
    --------------------------- */
    @PostMapping("/fridge/create")
    @ResponseBody
    public ResponseEntity<String> createFridge(Account currentAccount) {
        return fridgeController.createFridge(currentAccount);
    }


    /* --------------------------
    Recipe Controller Functions
    --------------------------- */
    @RequestMapping("/recipe/fridge/search")
    @ResponseBody
    public RecipeDetails[] searchRecipesByFridge(Account currentAccount) {
        return recipeController.searchRecipesByFridge(currentAccount);
    }

    @RequestMapping("/recipe/string/search")
    @ResponseBody
    public RecipeDetails[] searchRecipesByString(String ingredients) {
        return recipeController.searchRecipesByString(ingredients);
    }
}

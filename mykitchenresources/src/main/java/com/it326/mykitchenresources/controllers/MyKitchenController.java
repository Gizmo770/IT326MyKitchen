package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it326.mykitchenresources.entities.Account;

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

    @PostMapping("/account/create")
    @ResponseBody
	public ResponseEntity<String> createAccount(String name, String username, String password) {
		return accountController.createAccount(name, username, password);
	}

    @PostMapping("/shopping-list/create")
    @ResponseBody
    public ResponseEntity<String> createShoppingList(Account currentAccount) {
        return shoppingListController.createShoppingList(currentAccount);
    }

    @PostMapping("/fridge/create")
    @ResponseBody
    public ResponseEntity<String> createFridge(Account currentAccount) {
        return fridgeController.createFridge(currentAccount);
    }

    @RequestMapping("/recipe/search")
    @ResponseBody
    public String searchRecipesByFridge(Account currentAccount) {
        return recipeController.searchRecipesByFridge(currentAccount);
    }
}

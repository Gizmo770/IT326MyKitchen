package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it326.mykitchenresources.entities.Account;

public class MyKitchenController {

    @Autowired
    private AccountController accountController;

    @Autowired
    private ShoppingListController shoppingListController;

    @PostMapping("/account/create")
    @ResponseBody
	public ResponseEntity<String> createAccount(
		String username, String password, String email) {
		return accountController.createAccount(username, password, email);
	}

    @PostMapping("/shopping-list/create")
    @ResponseBody
    public ResponseEntity<String> createShoppingList(Account currentAccount) {
        return shoppingListController.createShoppingList(currentAccount);
    }
}

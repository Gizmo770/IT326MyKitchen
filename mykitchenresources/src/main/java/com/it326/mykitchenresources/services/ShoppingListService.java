package com.it326.mykitchenresources.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.ShoppingListDb;
import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.entities.ShoppingList;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListDb shoppingListDb;

    @Autowired
    private AccountService accountService;

    public void createShoppingList(Integer accountId) {

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setAccount(accountService.findByAccountId(accountId));
        shoppingList.setIngredientsInShoppingList(new ArrayList<Ingredient>());

        shoppingListDb.save(shoppingList);
    }
}

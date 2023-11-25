package com.it326.mykitchenresources.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.AccountDb;
import com.it326.mykitchenresources.dbs.ShoppingListDb;
import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.entities.ShoppingList;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListDb shoppingListDb;

    @Autowired
    private AccountDb accountDb;

    public void createShoppingList(Integer accountId, ArrayList<Ingredient> ingredients) {

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setIngredients(ingredients);
        shoppingList.setAccount(accountDb.findByAccountId(accountId));

        shoppingListDb.save(shoppingList);
    }
}

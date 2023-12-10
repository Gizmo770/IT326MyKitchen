package com.it326.mykitchenresources.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.ShoppingListDb;
import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.entities.ShoppingList;
import com.it326.mykitchenresources.entities.ShoppingListIngredient;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListDb shoppingListDb;

    @Autowired
<<<<<<< HEAD
    private ShoppingListIngredientRepository shoppingListIngredientRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    // Assuming AccountService and AccountRepository are implemented
    @Autowired
=======
>>>>>>> parent of 8a10727 (Adding shopping list function and a servicetest with it)
    private AccountService accountService;

    public void createShoppingList(Integer accountId) {

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setAccount(accountService.findByAccountId(accountId));
        shoppingList.setIngredientsInShoppingList(new ArrayList<ShoppingListIngredient>());

        shoppingListDb.save(shoppingList);
    }

    public ShoppingList findShoppingList(Integer accountId) {

        return shoppingListDb.findByAccount(accountService.findByAccountId(accountId));
    }

    public List<Ingredient> getIngredientsInShoppingList(Integer accountId) {

        ShoppingList shoppingList = findShoppingList(accountId);
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        for (ShoppingListIngredient shoppingListIngredient : shoppingList.getIngredientsInShoppingList()) {
            ingredients.add(shoppingListIngredient.getIngredient());
        }

        return ingredients;
    }
}

package com.it326.mykitchenresources.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.IngredientDb;
import com.it326.mykitchenresources.dbs.ShoppingListDb;
import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.entities.ShoppingList;
import com.it326.mykitchenresources.entities.ShoppingListIngredient;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListDb shoppingListDb;

    @Autowired
    private IngredientDb ingredientDb;

    @Autowired
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

    public List<ShoppingListIngredient> getListItems(Integer accountId) {

        ShoppingList shoppingList = findShoppingList(accountId);
        return shoppingList.getIngredientsInShoppingList();
    }

    public void updateShoppingListIngredients(Integer accountId, List<ShoppingListIngredient> listItems) {

        ShoppingList shoppingList = findShoppingList(accountId);

        // Clear the existing list
        shoppingList.getIngredientsInShoppingList().clear();


        List<ShoppingListIngredient> updatedList = new ArrayList<ShoppingListIngredient>();

        for(ShoppingListIngredient listItem : listItems) {
            Ingredient thisIngredient = listItem.getIngredient();
            List<Ingredient> matchIngredients = ingredientDb.findAllByName(thisIngredient.getName());

            if(matchIngredients.isEmpty()) {
                thisIngredient = ingredientDb.save(thisIngredient);
                listItem.setIngredient(thisIngredient);
            } else {
                thisIngredient = matchIngredients.get(0);
                listItem.setIngredient(thisIngredient);
            }

            listItem.setShoppingList(shoppingList);

            shoppingList.getIngredientsInShoppingList().add(listItem);
        }

        shoppingListDb.save(shoppingList);
    }
}

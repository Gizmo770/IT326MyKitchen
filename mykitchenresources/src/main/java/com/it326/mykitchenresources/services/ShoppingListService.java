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
    public ShoppingListService(ShoppingListDb shoppingListDb, IngredientDb ingredientDb) {
        this.shoppingListDb = shoppingListDb;
        this.ingredientDb = ingredientDb;
    }

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

    public void addIngredientToShoppingList(Integer shoppingListId, Integer ingredientId, Double quantity) {
        ShoppingList shoppingList = shoppingListDb.findById(shoppingListId.longValue())
                .orElseThrow(() -> new RuntimeException("Shopping list not found"));
        
        Ingredient ingredient = ingredientDb.findById(ingredientId.longValue())
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        if (quantity != null) {
            ingredient.setQuantity(quantity);
        }

        ShoppingListIngredient shoppingListIngredient = new ShoppingListIngredient();
        shoppingListIngredient.setIngredient(ingredient);
        shoppingList.addShoppingListIngredient(shoppingListIngredient); // Add to the shopping list

        shoppingListDb.save(shoppingList); // Save the shopping list
    }

    public List<ShoppingList> findAllShoppingLists() {
        return shoppingListDb.findAll();
    }
    public void deleteShoppingListIngredient(Long shoppingListId, Long ingredientId) {
        ShoppingList shoppingList = shoppingListDb.findById(shoppingListId)
            .orElseThrow(() -> new RuntimeException("Shopping list not found"));
    
        ShoppingListIngredient toRemove = null;
        for (ShoppingListIngredient sli : shoppingList.getIngredientsInShoppingList()) {
            if (sli.getIngredient().getIngredientId().equals(ingredientId)) { // Corrected line
                toRemove = sli;
                break;
            }
        }
    
        if (toRemove != null) {
            shoppingList.getIngredientsInShoppingList().remove(toRemove);
            shoppingListDb.save(shoppingList); // This will cascade the delete operation if properly configured
        } else {
            throw new RuntimeException("Ingredient not found in shopping list");
        }
    }
    
    
}

package com.it326.mykitchenresources.services;

import com.it326.mykitchenresources.entities.*;
import com.it326.mykitchenresources.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private ShoppingListIngredientRepository shoppingListIngredientRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    // Assuming AccountService and AccountRepository are implemented
    @Autowired
    private AccountService accountService;

    public ShoppingList createShoppingList(Integer accountId) {
        // Find the account using AccountService
        Account account = accountService.findByAccountId(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        ShoppingList newShoppingList = new ShoppingList();
        newShoppingList.setAccount(account);
        return shoppingListRepository.save(newShoppingList);
    }

    public ShoppingListIngredient addIngredientToShoppingList(Integer shoppingListId, Integer ingredientId, int priority) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId.longValue()) // If shoppingListId is Long
            .orElseThrow(() -> new RuntimeException("Shopping list not found"));
        Ingredient ingredient = ingredientRepository.findById(ingredientId) // Here ingredientId is an Integer
            .orElseThrow(() -> new RuntimeException("Ingredient not found"));


        ShoppingListIngredient shoppingListIngredient = new ShoppingListIngredient();
        shoppingListIngredient.setShoppingList(shoppingList);
        shoppingListIngredient.setIngredient(ingredient);
        shoppingListIngredient.setPriority(priority);
        return shoppingListIngredientRepository.save(shoppingListIngredient);
    }

    public void removeIngredientFromShoppingList(Integer shoppingListId, Integer ingredientId) {
        shoppingListIngredientRepository.deleteByShoppingListIdAndIngredientId(shoppingListId, ingredientId);
    }

    public List<Ingredient> getIngredientsInShoppingList(Integer shoppingListId) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId.longValue());
        return shoppingList.map(list -> list.getIngredientsInShoppingList().stream()
                .map(ShoppingListIngredient::getIngredient)
                .collect(Collectors.toList()))
            .orElseThrow(() -> new RuntimeException("Shopping list not found"));
    }

    public List<Ingredient> searchShoppingListIngredients(Integer shoppingListId, String searchTerm) {
        return shoppingListIngredientRepository.findAllByShoppingListId(shoppingListId).stream()
            .map(ShoppingListIngredient::getIngredient)
            .filter(ingredient -> ingredient.getName().toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
    }

    public void setShoppingListBudget(Integer shoppingListId, Double budget) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId.longValue())
            .orElseThrow(() -> new RuntimeException("Shopping list not found"));
        shoppingList.setBudget(budget);
        shoppingListRepository.save(shoppingList);
    }
    
    public Double getShoppingListBudget(Integer shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId.longValue())
            .orElseThrow(() -> new RuntimeException("Shopping list not found"));
        return shoppingList.getBudget();
    }

    

    public void prioritizeIngredientInShoppingList(Integer shoppingListId, Integer ingredientId, int newPriority) {
        ShoppingListIngredientId id = new ShoppingListIngredientId(shoppingListId, ingredientId);
        ShoppingListIngredient shoppingListIngredient = shoppingListIngredientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Shopping list ingredient not found"));
        shoppingListIngredient.setPriority(newPriority);
        shoppingListIngredientRepository.save(shoppingListIngredient);
    }


}
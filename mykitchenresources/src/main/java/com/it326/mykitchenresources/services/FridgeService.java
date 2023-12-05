package com.it326.mykitchenresources.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.FridgeDb;
import com.it326.mykitchenresources.dbs.IngredientDb;
import com.it326.mykitchenresources.entities.Fridge;
import com.it326.mykitchenresources.entities.Ingredient;

@Service
public class FridgeService {
    
    @Autowired
    private FridgeDb fridgeDb;

    @Autowired
    private IngredientDb ingredientDb;

    @Autowired 
    private AccountService accountService;


    public void createFridge(Integer accountId) {

        Fridge newFridge = new Fridge();
        newFridge.setAccount(accountService.findByAccountId(accountId));
        newFridge.setIngredients(new ArrayList<Ingredient>());

        fridgeDb.save(newFridge);
    }

    public void addIngredientToFridge(Integer accountId, 
        String ingName, Optional<Double> ingQuantity, Optional<String> ingExpDate) {

        // Convert ingExpDate from String to Date
        Date convertedDate = null;
        if (ingExpDate.isPresent()) {
            try {
                convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(ingExpDate.get());
            } catch (Exception e) {
                System.out.println("Error converting date");
            }
        }
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(ingName);
        newIngredient.setQuantity(ingQuantity.orElse(1.0));
        newIngredient.setExpirationDate(convertedDate);
        ingredientDb.save(newIngredient);

        Fridge fridgeData = getFridgeByAccountId(accountId);
        fridgeData.getIngredients().add(newIngredient);
        fridgeDb.save(fridgeData);
    }

    public Fridge getFridgeByAccountId(Integer accountId) {
        return fridgeDb.findByAccount(accountService.findByAccountId(accountId));
    }

      // Search for an ingredient by name
    public Ingredient searchIngredient(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.toString().equalsIgnoreCase(name)) {
                return ingredient;
            }
        }
        return null; // Ingredient not found
    }

    // Search for a recipe by name
    public Recipe searchRecipe(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.toString().equalsIgnoreCase(name)) {
                return recipe;
            }
        }
        return null; // Recipe not found
    }
    // Filter search results
    public List<Ingredient> filterSearchResults(List<Ingredient> results, String filter) {
        List<Ingredient> filteredResults = new ArrayList<>();
        for (Ingredient ingredient : results) {
            if (ingredient.toString().toLowerCase().contains(filter.toLowerCase())) {
                filteredResults.add(ingredient);
            }
        }
        return filteredResults;
    }

    // Provide search suggestions based on input
    public List<String> getSearchSuggestions(String input) {
        List<String> suggestions = new ArrayList<>();
        // Logic to generate suggestions based on input
      
        return suggestions;
    }

    // Update an existing ingredient
    public void updateIngredient(Ingredient oldIngredient, Ingredient newIngredient) {
        int index = ingredients.indexOf(oldIngredient);
        if (index != -1) {
            ingredients.set(index, newIngredient);
        }
    }

    // Delete an existing ingredient
    public void deleteIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }


}

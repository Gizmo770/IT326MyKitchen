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

    public List<Ingredient> searchIngredient(Integer accountId, String searchQuery) {
    Fridge fridgeData = getFridgeByAccountId(accountId);

    List<Ingredient> searchResults = new ArrayList<>();
    for (Ingredient ingredient : fridgeData.getIngredients()) {
        if (ingredient.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
            searchResults.add(ingredient);
        }
    }

    return searchResults;
}

public void updateIngredient(Integer accountId, Long ingredientId, String newName, Double newQuantity, String newExpDate) {
    Fridge fridgeData = getFridgeByAccountId(accountId);

    for (Ingredient ingredient : fridgeData.getIngredients()) {
        if (ingredient.getId().equals(ingredientId)) {
            ingredient.setName(newName);
            ingredient.setQuantity(newQuantity);
            try {
                Date convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(newExpDate);
                ingredient.setExpirationDate(convertedDate);
            } catch (ParseException e) {
                System.out.println("Error converting date");
            }
            break;
        }
    }

    fridgeDb.save(fridgeData);
}

public void deleteIngredient(Integer accountId, Long ingredientId) {
    Fridge fridgeData = getFridgeByAccountId(accountId);

    fridgeData.getIngredients().removeIf(ingredient -> ingredient.getId().equals(ingredientId));

    fridgeDb.save(fridgeData);
}
}

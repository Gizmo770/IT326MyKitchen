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

        Fridge fridgeData = fridgeDb.findByAccount(accountService.findByAccountId(accountId));
        fridgeData.getIngredients().add(newIngredient);
        fridgeDb.save(fridgeData);
    }
}

package com.it326.mykitchenresources.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.FridgeDb;
import com.it326.mykitchenresources.entities.Fridge;
import com.it326.mykitchenresources.entities.Ingredient;

@Service
public class FridgeService {
    
    @Autowired
    private FridgeDb fridgeDb;

    @Autowired 
    private AccountService accountService;

    public void createFridge(Integer accountId) {

        Fridge newFridge = new Fridge();
        newFridge.setAccount(accountService.findByAccountId(accountId));
        newFridge.setIngredients(new ArrayList<Ingredient>());

        fridgeDb.save(newFridge);
    }
}

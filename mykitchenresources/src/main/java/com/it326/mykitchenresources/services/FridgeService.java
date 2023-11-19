package com.it326.mykitchenresources.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.FridgeDb;
import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.entities.Fridge;

@Service
public class FridgeService {
    
    @Autowired
    private FridgeDb fridgeDb;

    public void createFridge(Account account) {
        Fridge newFridge = new Fridge();
        newFridge.setAccount(account);

        fridgeDb.save(newFridge);
    }
}

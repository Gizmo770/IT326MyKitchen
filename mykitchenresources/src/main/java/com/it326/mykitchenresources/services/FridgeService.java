package com.it326.mykitchenresources.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.AccountDb;
import com.it326.mykitchenresources.dbs.FridgeDb;
import com.it326.mykitchenresources.entities.Fridge;

@Service
public class FridgeService {
    
    @Autowired
    private FridgeDb fridgeDb;

    @Autowired 
    private AccountDb accountDb;

    public void createFridge(Integer accountId) {
        Fridge newFridge = new Fridge();
        newFridge.setAccount(accountDb.findByAccountId(accountId));

        fridgeDb.save(newFridge);
    }
}

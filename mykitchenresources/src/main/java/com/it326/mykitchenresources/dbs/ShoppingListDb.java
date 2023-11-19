package com.it326.mykitchenresources.dbs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.entities.ShoppingList;

@Repository
public interface ShoppingListDb extends JpaRepository<ShoppingList, Account> {
    
}
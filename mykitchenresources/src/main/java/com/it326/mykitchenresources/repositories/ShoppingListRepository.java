package com.it326.mykitchenresources.repositories;

import com.it326.mykitchenresources.entities.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    // Additional query methods, if needed
    
}


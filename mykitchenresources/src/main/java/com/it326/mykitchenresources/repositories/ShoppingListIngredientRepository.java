package com.it326.mykitchenresources.repositories;

import com.it326.mykitchenresources.entities.ShoppingListIngredient;
import com.it326.mykitchenresources.entities.ShoppingListIngredientId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListIngredientRepository extends JpaRepository<ShoppingListIngredient, ShoppingListIngredientId> {
    
    // Find all ShoppingListIngredient entries by shoppingListId
    List<ShoppingListIngredient> findAllByShoppingListId(Integer shoppingListId);
    void deleteByShoppingListIdAndIngredientId(Integer shoppingListId, Integer ingredientId);
}

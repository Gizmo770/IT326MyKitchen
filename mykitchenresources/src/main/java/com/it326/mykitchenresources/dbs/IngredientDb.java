package com.it326.mykitchenresources.dbs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it326.mykitchenresources.entities.Ingredient;

@Repository
public interface IngredientDb extends JpaRepository<Ingredient, Long> {
    
    public Ingredient findByName(String name);
}

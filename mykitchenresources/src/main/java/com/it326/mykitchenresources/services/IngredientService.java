package com.it326.mykitchenresources.services;

import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Optional<Ingredient> findByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public Ingredient createIngredient(String name, Double quantity) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setQuantity(quantity);
        // Set any other properties of Ingredient here.
        return ingredientRepository.save(ingredient);
    }
}
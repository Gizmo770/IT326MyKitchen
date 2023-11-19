package com.it326.mykitchenresources.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.RecipeDb;
import com.it326.mykitchenresources.entities.Fridge;
import com.it326.mykitchenresources.entities.Ingredient;

@Service
public class RecipeService {

    private RecipeDb recipeDb;

    public String serachRecipesByFridge(Fridge fridge) {
        String ingredientString = "";

        for(Ingredient ingredient : fridge.getIngredients()) {
            ingredientString += ingredient.getName() + " ";
        }

        return searchRecipesByString(ingredientString);
    }

    public String searchRecipesByString(String ingredients) {
        return recipeDb.getRecipeData(ingredients);
    }
}

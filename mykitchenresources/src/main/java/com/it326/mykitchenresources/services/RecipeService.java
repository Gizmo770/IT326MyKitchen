package com.it326.mykitchenresources.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it326.mykitchenresources.dbs.RecipeDb;
import com.it326.mykitchenresources.entities.Fridge;
import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.entities.recipe.RecipeDetails;
import com.it326.mykitchenresources.mappers.RecipeMapper;

@Service
public class RecipeService {

    @Autowired
    private RecipeDb recipeDb;

    @Autowired
    private FridgeService fridgeService;

    public RecipeDetails[] searchRecipesByFridge(Integer accountId) {
        Fridge fridgeData = fridgeService.getFridgeByAccountId(accountId);
        String fridgeIngredientString = "";

        for (Ingredient ingredient : fridgeData.getIngredients()) {
            fridgeIngredientString += ingredient.getName() + " ";
        }

        String recipeJson = recipeDb.searchRecipeData(fridgeIngredientString);
        return RecipeMapper.mapJsonToRecipes(recipeJson);
    }

    public RecipeDetails[] searchRecipesByString(String ingredients) {
        String recipeJson = recipeDb.searchRecipeData(ingredients);
        return RecipeMapper.mapJsonToRecipes(recipeJson);
    }
}

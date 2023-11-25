package com.it326.mykitchenresources.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it326.mykitchenresources.dbs.RecipeDb;
import com.it326.mykitchenresources.entities.Fridge;
import com.it326.mykitchenresources.entities.Ingredient;
import com.it326.mykitchenresources.entities.recipe.Recipe;
import com.it326.mykitchenresources.entities.recipe.RecipeDetails;
import com.it326.mykitchenresources.entities.recipe.RecipeHit;

@Service
public class RecipeService {

    @Autowired
    private RecipeDb recipeDb;

    @Autowired
    private FridgeService fridgeService;

    private final ObjectMapper objectMapper;

    public RecipeService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public RecipeDetails[] searchRecipesByFridge(Integer accountId) {
        Fridge fridgeData = fridgeService.getFridgeByAccountId(accountId);
        String fridgeIngredientString = "";

        for (Ingredient ingredient : fridgeData.getIngredients()) {
            fridgeIngredientString += ingredient.getName() + " ";
        }

        String recipeJson = recipeDb.searchRecipeData(fridgeIngredientString);
        return mapJsonToRecipes(recipeJson);
    }

    public RecipeDetails[] searchRecipesByString(String ingredients) {
        String recipeJson = recipeDb.searchRecipeData(ingredients);
        return mapJsonToRecipes(recipeJson);
    }

    // Helper method to map JSON to RecipeDetails objects
    private RecipeDetails[] mapJsonToRecipes(String recipeJson) {
        try {
            Recipe response = objectMapper.readValue(recipeJson, Recipe.class);
            if (response != null && response.getHits() != null) {
                return response.getHits()
                        .stream()
                        .map(RecipeHit::getRecipe)
                        .toArray(RecipeDetails[]::new);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle or log the exception
        }
        return new RecipeDetails[0]; // Return an empty array or handle null response
    }
}

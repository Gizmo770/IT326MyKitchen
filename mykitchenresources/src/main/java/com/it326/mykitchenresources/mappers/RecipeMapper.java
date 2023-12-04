package com.it326.mykitchenresources.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it326.mykitchenresources.entities.recipe.Recipe;
import com.it326.mykitchenresources.entities.recipe.RecipeDetails;
import com.it326.mykitchenresources.entities.recipe.RecipeHit;

public class RecipeMapper {

    private static ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // Helper method to map JSON to RecipeDetails objects
    public static RecipeDetails[] mapJsonToRecipes(String recipeJson) {
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

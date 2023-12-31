package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it326.mykitchenresources.entities.recipe.RecipeDetails;
import com.it326.mykitchenresources.services.RecipeService;

@RestController
public class RecipeController {
    
    @Autowired
    private RecipeService recipeService;

    @RequestMapping("/hello-recipe")
    public String helloRecipe() {
        return "Hello, recipe!";
    }

    public RecipeDetails[] searchRecipesByFridge(Integer accountId) {
        System.out.println("Getting recipe by fridge ingredients...");
        return recipeService.searchRecipesByFridge(accountId);
    }

    public RecipeDetails[] searchRecipesByString(String ingredients) {
        System.out.println("Getting recipe by string: " + ingredients + "...");
        return recipeService.searchRecipesByString(ingredients);
    }
}

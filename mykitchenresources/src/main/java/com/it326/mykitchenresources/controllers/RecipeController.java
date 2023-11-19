package com.it326.mykitchenresources.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it326.mykitchenresources.entities.Account;
import com.it326.mykitchenresources.services.RecipeService;

@RestController
public class RecipeController {
    
    @Autowired
    private RecipeService recipeService;

    @RequestMapping("/hello-recipe")
    public String helloRecipe() {
        return "Hello, recipe!";
    }

    public String searchRecipesByFridge(Account account) {
        return recipeService.serachRecipesByFridge(account.getFridge());
    }

    public String searchRecipesByString(String ingredients) {
        return recipeService.searchRecipesByString(ingredients);
    }
}

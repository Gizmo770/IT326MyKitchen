package com.it326.mykitchenresources.entities.recipe;

import java.util.List;

public class Recipe {

    private List<RecipeHit> hits;

    // Getters and setters
    public List<RecipeHit> getHits() {
        return hits;
    }

    public void setHits(List<RecipeHit> hits) {
        this.hits = hits;
    }
}
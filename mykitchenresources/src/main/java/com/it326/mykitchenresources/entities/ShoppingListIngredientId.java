package com.it326.mykitchenresources.entities;

import java.io.Serializable;
import java.util.Objects;

public class ShoppingListIngredientId implements Serializable {
    private Integer shoppingListId;
    private Integer ingredientId;

    public ShoppingListIngredientId() {}

    public ShoppingListIngredientId(Integer shoppingListId, Integer ingredientId) {
        this.shoppingListId = shoppingListId;
        this.ingredientId = ingredientId;
    }

    public Integer getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Integer shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingListIngredientId)) return false;
        ShoppingListIngredientId that = (ShoppingListIngredientId) o;
        return shoppingListId.equals(that.shoppingListId) && ingredientId.equals(that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingListId, ingredientId);
    }
}

package com.it326.mykitchenresources.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_list_ingredients")
public class ShoppingListIngredient {

    @Id
    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    private ShoppingList shoppingList;

    
    // This class contains an ingredient and a prirority 
    // for that ingredient, somewhat of an "extends" relationship.
    @Id
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    
    private Long id;

    @Column(name = "priority")
    private int priority;

    public ShoppingListIngredient() {
        // Default constructor
    }

    
    

    // Getters and setters for shoppingList
    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    // Getters and setters for ingredient
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    // Getters and setters for priority
    public int getPriority() {
        return priority;
    }

    public Long getId()
    {
        return id;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

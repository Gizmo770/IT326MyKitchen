package com.it326.mykitchenresources.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ShoppingListItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private ShoppingList shoppingList;

    @ManyToOne
    private Ingredient ingredient;

    @Column(name = "priority_level")
    private int priorityLevel;

    //Constructors, getters, setters, etc...
    public ShoppingListItems() {
    }

    public ShoppingListItems(ShoppingList shoppingList, Ingredient ingredient, int priorityLevel) {
        this.shoppingList = shoppingList;
        this.ingredient = ingredient;
        this.priorityLevel = priorityLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        if (priorityLevel < 1) {
            this.priorityLevel = 1;
        } else if (priorityLevel > 5) {
            this.priorityLevel = 5;
        } else {
            this.priorityLevel = priorityLevel;
        }
    }
}

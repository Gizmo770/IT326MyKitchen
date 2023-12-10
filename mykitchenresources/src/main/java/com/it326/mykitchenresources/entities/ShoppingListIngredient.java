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

    @Column(name = "priority")
    private int priority;

    public Ingredient getIngredient() {
        return ingredient;
    }
}
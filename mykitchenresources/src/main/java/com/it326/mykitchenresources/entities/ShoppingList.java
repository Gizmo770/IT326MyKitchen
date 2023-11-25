package com.it326.mykitchenresources.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {
    @Id
    @Column(name = "list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Association table for the list of ingredients in the shopping list.
    @ManyToMany
    @JoinTable(
        name = "shopping_list_ingredients",
        joinColumns = @JoinColumn(name = "shopping_list_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> shoppingIngredients;

    public ShoppingList() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Ingredient> getIngredientsInShoppingList() {
        return this.shoppingIngredients;
    }

    public void setIngredientsInShoppingList(List<Ingredient> ingredients) {
        this.shoppingIngredients = ingredients;
    }
}

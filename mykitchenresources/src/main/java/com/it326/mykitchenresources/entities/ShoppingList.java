package com.it326.mykitchenresources.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {
    @Id
    @Column(name = "shopping_list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Association table for the list of ingredients in the shopping list.
    // In other words, a list of ShoppingListIngredient objects specifice
    // to represent the ingredients in the shopping list.
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingListIngredient> shoppingIngredients;

    public ShoppingList() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<ShoppingListIngredient> getIngredientsInShoppingList() {
        return this.shoppingIngredients;
    }

    public void setIngredientsInShoppingList(List<ShoppingListIngredient> newIngredients) {
        this.shoppingIngredients.clear();
        if (newIngredients != null) {
            this.shoppingIngredients.addAll(newIngredients);
        }
    }

    public void addIngredientToShoppingList(ShoppingListIngredient newIng) {
        this.shoppingIngredients.add(newIng);
    }

    public void removeIngredientFromShoppingList(ShoppingListIngredient ing) {
        this.shoppingIngredients.remove(ing);
    }
}

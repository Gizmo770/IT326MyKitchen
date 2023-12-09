package com.it326.mykitchenresources.entities;

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
    private Integer listId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Double budget;

    // Association table for the list of ingredients in the shopping list.
    // In other words, a list of ShoppingListIngredient objects specifice
    // to represent the ingredients in the shopping list.
    @OneToMany(mappedBy = "shoppingList")
    private List<ShoppingListIngredient> shoppingIngredients;

    public ShoppingList() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public List<ShoppingListIngredient> getIngredientsInShoppingList() {
        return this.shoppingIngredients;
    }

    public void setIngredientsInShoppingList(List<ShoppingListIngredient> ingredients) {
        this.shoppingIngredients = ingredients;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}

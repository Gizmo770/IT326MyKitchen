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

    // Association table, doesn't exist as a class.
    @ManyToMany
    @JoinTable(
        name = "ingredient_list",
        joinColumns = @JoinColumn(name = "list_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredientList;


    public ShoppingList() {
    }

    public ShoppingList(Account account, List<Ingredient> ingredients) {
        this.account = account;
        this.ingredientList = ingredients;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Ingredient> getIngredients() {
        return ingredientList;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredientList = ingredients;
    }
}

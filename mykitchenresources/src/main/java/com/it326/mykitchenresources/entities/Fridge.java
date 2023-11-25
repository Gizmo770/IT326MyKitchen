package com.it326.mykitchenresources.entities;

import java.util.List;

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

@Entity
@Table(name = "fridge")
public class Fridge {
    @Id
    @Column(name = "fridge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fridgeId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Association table for the list of ingredients in the fridge.
    @ManyToMany
    @JoinTable(
        name = "fridge_ingredients",
        joinColumns = @JoinColumn(name = "fridge_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> fridgeIngredients;

    public Fridge() {
    }

    public Fridge(Account account, List<Ingredient> ingredients) {
        this.account = account;
        this.fridgeIngredients = ingredients;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Ingredient> getIngredients() {
        return fridgeIngredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.fridgeIngredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.fridgeIngredients.add(ingredient);
    }
}

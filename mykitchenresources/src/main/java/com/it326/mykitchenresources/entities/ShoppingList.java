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

import java.util.ArrayList;
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

    // Association class for association table and its priority for each item in the list.
        // In short, ShoppingListItems manages a list of ingredients 
        // and their priority in the SQL table.
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingListItems> ingredientsInList = new ArrayList<>();


    public ShoppingList() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<ShoppingListItems> getIngredientsInShoppingList() {
        return ingredientsInList;
    }

    public void setIngredientsInShoppingList(List<ShoppingListItems> ingredients) {
        this.ingredientsInList = ingredients;
    }
}

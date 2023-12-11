package com.it326.mykitchenresources.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")   
    private int accountId;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "hashed_password", length = 60, columnDefinition = "CHAR(60) BINARY")
    private String hashedPassword;
    
    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "phone_number", length = 45)
    private String phoneNumber;

    @Column(name = "carrier", length = 45)
    private String phoneCarrier;

    @Column(name = "ingredient_low_threshold")
    private Double ingredientLowThreshold;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private ShoppingList shoppingList;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Fridge fridge;
    
    // Constructors, getters, setters, etc.
    public Account(){}

    public Account(int accountId, String name, String username, String hashedPassword){
        this.accountId = accountId;
        this.name = name;
        this.username = username;
        this.hashedPassword = hashedPassword;
    }
    
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return username;
    }
    public void setUserName(String username) {
        this.username = username;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public ShoppingList getShoppingList() {
        return shoppingList;
    }
    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }
    public Fridge getFridge() {
        return fridge;
    }
    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }
    public String getPhoneCarrier() {
        return phoneCarrier;
    }
    public void setPhoneCarrier(String phoneCarrier) {
        this.phoneCarrier = phoneCarrier;
    }
    public void setLowIngredientThreshold(double ingredientLowThreshold) {
        this.ingredientLowThreshold = ingredientLowThreshold;
    }
    public double getLowIngredientThreshold() {
        return ingredientLowThreshold;
    }
}
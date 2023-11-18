package com.it326.mykitchenresources.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "hashed_password", length = 45)
    private String hashedPassword;
    
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
}
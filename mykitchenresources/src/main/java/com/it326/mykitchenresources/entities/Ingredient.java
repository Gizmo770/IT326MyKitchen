package com.it326.mykitchenresources.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @Column(name = "ingredient_id")
    private int ingredientId;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "priority_level", length = 45)
    private String priorityLevel;

    //Constructors, getters, setters, etc...
    public Ingredient() {
    }

    public Ingredient(String name, double quantity, Date expirationDate, String priorityLevel) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.priorityLevel = priorityLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void getExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void getPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}

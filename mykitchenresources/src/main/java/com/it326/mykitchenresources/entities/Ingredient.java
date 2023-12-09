package com.it326.mykitchenresources.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Integer ingredientId;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "expiration_date")
    private Date expirationDate;

    //Constructors, getters, setters, etc...
    public Ingredient() {
    }

    public Ingredient(String name, double quantity, Date expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return ingredientId;
    }

    public void setId(Integer id) {
        ingredientId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date date) {
        this.expirationDate = date;
    }
}

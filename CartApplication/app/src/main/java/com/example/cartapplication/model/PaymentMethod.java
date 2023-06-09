package com.example.cartapplication.model;

import java.io.Serializable;

public class PaymentMethod implements Serializable {
    private int id;
    private String name;
    private String description;

    // Constructor, getters và setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

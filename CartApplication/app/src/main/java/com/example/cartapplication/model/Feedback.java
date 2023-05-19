package com.example.cartapplication.model;

import java.io.Serializable;
import java.util.Date;

public class Feedback implements Serializable {
    private int id;
    private Product product;
    private User user;
    private String content;
    private Date date;

    // Default constructor
    public Feedback() {
    }

    // Constructor with parameters
    public Feedback(int id, Product product, User user, String content, Date date) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.content = content;
        this.date = date;
    }
    public Feedback(int id,String content){
        this.id=id;
        this.content=content;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

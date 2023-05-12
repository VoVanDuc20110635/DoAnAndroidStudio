package com.example.cartapplication.model;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Serializable {
    private int id;
    private User user;
    private Date createdDate;
    private Date updatedDate;
    private int status;

    public Cart(int id, User user, Date createdDate, Date updatedDate, int status) {
        this.id = id;
        this.user = user;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.status = status;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

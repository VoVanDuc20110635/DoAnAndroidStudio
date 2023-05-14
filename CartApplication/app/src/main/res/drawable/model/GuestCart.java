package com.example.cartapplication.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GuestCart implements Serializable {
    private int id;
    private Date createdDate;
    private Date updatedDate;
    private List<CartItem> cartItemList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public GuestCart(int id, Date createdDate, Date updatedDate, List<CartItem> cartItemList) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.cartItemList = cartItemList;
    }

    public GuestCart() {
    }
}

package com.example.cartapplication.model;

import java.io.Serializable;
import java.util.Date;

public class CartItem implements Serializable {
    private int id;
    private Cart cart;
    private Product product;
    private int quantity;
    private int price;
    private int total;
    private Date purchasedDate;
    private GuestCart guestCart;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return (int)(quantity * product.getPrice());
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public GuestCart getGuestCart() {
        return this.guestCart;
    }

    public void setGuestCart(GuestCart guestCart) {
        this.guestCart = guestCart;
    }

    public CartItem(int id, Cart cart, Product product, int quantity, int price, int total, Date purchasedDate, GuestCart guestCart) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.purchasedDate = purchasedDate;
        this.guestCart = guestCart;
    }

    public CartItem() {
    }
}

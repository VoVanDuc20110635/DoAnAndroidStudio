package com.example.cartapplication.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private int id;
    private Product product;
    private int quantity;
    private int price;
    private int total;

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
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public OrderDetail() {
    }

    public OrderDetail(int id, Product product, int quantity, int price, int total) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
}

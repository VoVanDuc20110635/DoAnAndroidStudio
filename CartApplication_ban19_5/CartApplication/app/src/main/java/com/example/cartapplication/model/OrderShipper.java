package com.example.cartapplication.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderShipper implements Serializable {
    private int id;
    private User user;
    private Date orderDate;
    private String address;
    private int zip;
    private String city;
    private String phoneNumber;
    private String email;
    private int total;
    private int status;
    private PaymentMethod paymentMethod;

    private List<OrderDetail> orderDetails;

    // Constructor, getters và setters

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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderShipper() {
    }

    public OrderShipper(int id, User user, Date orderDate, String address, int zip, String city, String phoneNumber, String email, int total, int status, PaymentMethod paymentMethod, List<OrderDetail> orderDetails) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.total = total;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.orderDetails = orderDetails;
    }
}

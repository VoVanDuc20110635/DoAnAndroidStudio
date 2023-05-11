package com.example.cartapplication.model;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable{
    private int id;
    private String accountName;
    private String password;
    private Date createdDate;
    private int roleNumber;
    private int status;
    private String passwordResetToken;

    // Constructor
    public Account(int id, String accountName, String password, Date createdDate, int roleNumber, int status, String passwordResetToken) {
        this.id = id;
        this.accountName = accountName;
        this.password = password;
        this.createdDate = createdDate;
        this.roleNumber = roleNumber;
        this.status = status;
        this.passwordResetToken = passwordResetToken;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getRoleNumber() {
        return roleNumber;
    }

    public void setRoleNumber(int roleNumber) {
        this.roleNumber = roleNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }
}
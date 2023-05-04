package com.example.footapp;

import com.example.footapp.Domain.DetailFood;
import com.example.footapp.Domain.User;

public class DetailMealResponse {
    private String message;
    private boolean error;
    private DetailFood detailFood;

    public DetailFood getDetailFood() {
        return detailFood;
    }

    public void setDetailFood(DetailFood detailFood) {
        this.detailFood = detailFood;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public DetailMealResponse(String message, boolean error, DetailFood detailFood) {
        this.message = message;
        this.error = error;
        this.detailFood = detailFood;
    }
}

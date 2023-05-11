package com.example.footapp.Domain;

import com.example.footapp.remote.ApiService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailFood implements Serializable {
    ApiService apiService;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("meal")
    @Expose
    private String meal;

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("instructions")
    @Expose
    private String instructions;

    @SerializedName("strmealthumb")
    @Expose
    private String strmealthumb;

    @SerializedName("price")
    @Expose
    private Float price;

    public ApiService getApiService() {
        return apiService;
    }

    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getStrmealthumb() {
        return strmealthumb;
    }

    public void setStrmealthumb(String strmealthumb) {
        this.strmealthumb = strmealthumb;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public DetailFood( int id, String meal, String area, String category, String instructions, String strmealthumb, Float price) {
        this.id = id;
        this.meal = meal;
        this.area = area;
        this.category = category;
        this.instructions = instructions;
        this.strmealthumb = strmealthumb;
        this.price = price;
    }

    public DetailFood() {
    }
}

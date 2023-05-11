package com.example.footapp.Domain;

import android.util.Log;

import com.example.footapp.remote.ApiService;
import com.example.footapp.remote.CategoryClient;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDomain implements Serializable {
//    private String title;
//    private String pic;
//    private String description;
//    private Double fee;
//    private int numberIncart;
//
//    public FoodDomain(String title, String pic, String description, Double fee, int numberIncart) {
//        this.title = title;
//        this.pic = pic;
//        this.description = description;
//        this.fee = fee;
//        this.numberIncart = numberIncart;
//    }
//
//    public FoodDomain(String title, String pic, String description, Double fee) {
//        this.title = title;
//        this.pic = pic;
//        this.description = description;
//        this.fee = fee;
//    }
//
//    public FoodDomain() {
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getPic() {
//        return pic;
//    }
//
//    public void setPic(String pic) {
//        this.pic = pic;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Double getFee() {
//        return fee;
//    }
//
//    public void setFee(Double fee) {
//        this.fee = fee;
//    }
//
//    public int getNumberIncart() {
//        return numberIncart;
//    }
//
//    public void setNumberIncart(int numberIncart) {
//        this.numberIncart = numberIncart;
//    }
    ApiService apiService;

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("strMeal")
    @Expose
    private String strMeal;
    @SerializedName("strMealThumb")
    @Expose
    private String strMealThumb;
    @SerializedName("idMeal")
    @Expose
    private int idMeal;
    @SerializedName("idcategory")
    @Expose
    private int idcategory;

    private int numberIncart;

    private CategoryDomain productCategory;

    public FoodDomain(int id, String strMeal, String strMealThumb, int idMeal, int idcategory) {
        this.id = id;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.idMeal = idMeal;
        this.idcategory = idcategory;
        this.numberIncart = 0;
        GetCategoryFromAPI();
    }

    public ApiService getApiService() {
        return apiService;
    }

    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    public int getNumberIncart() {
        return numberIncart;
    }

    public void setNumberIncart(int numberIncart) {
        this.numberIncart = numberIncart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public CategoryDomain getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(CategoryDomain productCategory) {
        this.productCategory = productCategory;
    }
    private void GetCategoryFromAPI(){
        apiService= CategoryClient.getInstance();
        apiService.getCategory().enqueue(new Callback<CategoryDomain>()  {
            @Override
            public void onResponse(Call<CategoryDomain> call, Response<CategoryDomain> response) {
                if(response.isSuccessful()){
                    setProductCategory(response.body());
                }else{
                    int statusCode=response.code();
                }
            }
            @Override
            public void onFailure(Call<CategoryDomain> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
}

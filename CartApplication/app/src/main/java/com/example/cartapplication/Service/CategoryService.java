package com.example.cartapplication.Service;

import com.example.cartapplication.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("api/category/getAll")
    Call<List<Category>> getAllCategories();
}

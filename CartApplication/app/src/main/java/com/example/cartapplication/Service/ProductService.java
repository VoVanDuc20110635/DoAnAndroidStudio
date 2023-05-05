package com.example.cartapplication.Service;

import com.example.cartapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("api/product/getProductList")
    Call<List<Product>> getProductList();
}
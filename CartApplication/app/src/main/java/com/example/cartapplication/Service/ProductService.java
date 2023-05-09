package com.example.cartapplication.Service;

import com.example.cartapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {
    @GET("api/product/getProductList")
    Call<List<Product>> getProductList();

    @GET("api/product/getbycate/{id}")
    Call<List<Product>> getProductCategory(@Path("id") int id);
    @GET("api/product/getbyflavor/{id}")
    Call<List<Product>> getProductFlavor(@Path("id") int id);

}
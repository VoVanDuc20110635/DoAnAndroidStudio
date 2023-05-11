package com.example.cartapplication.Service;

import com.example.cartapplication.model.Account;
import com.example.cartapplication.model.Category;
import com.example.cartapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategoryService {

    @GET("api/category/getAll")
    Call<List<Category>> getAllCategories();

    @GET("api/product/getProductList")
    Call<List<Product>> getProductList();

    @GET("api/account/authenticate")
    Call<Account> authenticate(@Query("accountName") String accountName, @Query("password") String password);

    @GET("api/account/find/{id}")
    Call<Account> getAccount(@Path("id") int accountId);
}

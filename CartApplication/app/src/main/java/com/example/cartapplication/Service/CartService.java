package com.example.cartapplication.Service;

import com.example.cartapplication.model.Cart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CartService {

    @GET("api/cart/getUserCart/{id}")
    Call<Cart> getUserCart(@Path("id") int userId);
}

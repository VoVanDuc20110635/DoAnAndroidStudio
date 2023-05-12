package com.example.cartapplication.Service;

import com.example.cartapplication.model.Cart;
import com.example.cartapplication.model.CartItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CartService {

    @GET("api/cart/getUserCart/{id}")
    Call<Cart> getUserCart(@Path("id") int userId);

    @GET("api/cart/getUserCartItems/{id}")
    Call<List<CartItem>> getUserCartItems (@Path("id") int userId);
}

package com.example.cartapplication.Service;

import com.example.cartapplication.model.CartItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CartItemService {
    @GET("api/cart/getUserCartItems/{id}")
    Call<List<CartItem>> getCartItem(@Path("id") int accountId);

}

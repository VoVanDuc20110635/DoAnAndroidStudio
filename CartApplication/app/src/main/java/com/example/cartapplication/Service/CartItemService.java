package com.example.cartapplication.Service;

import com.example.cartapplication.model.CartItem;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CartItemService {
    @GET("api/cart/getUserCartItems/{id}")
    Call<List<CartItem>> getCartItem(@Path("id") int accountId);

    @DELETE("api/cart/removeCartItem")
    Call<ResponseBody> removeCartItem(
            @Query("userId") int userId,
            @Query("productId") int productId);


}

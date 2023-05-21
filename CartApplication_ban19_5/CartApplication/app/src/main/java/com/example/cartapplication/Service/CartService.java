package com.example.cartapplication.Service;

import com.example.cartapplication.model.Cart;
import com.example.cartapplication.model.CartItem;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CartService {

    @GET("api/cart/getUserCart/{id}")
    Call<Cart> getUserCart(@Path("id") int userId);

    @GET("api/cart/getUserCartItems/{id}")
    Call<List<CartItem>> getUserCartItems (@Path("id") int userId);

    @POST("api/cart/addToCart")
    Call<ResponseBody> addToCart(
            @Query("userId") int userId,
            @Query("productId") int productId,
            @Query("quantity") int quantity);
}

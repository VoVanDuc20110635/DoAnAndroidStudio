package com.example.cartapplication.Service;

import com.example.cartapplication.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderService {
    @GET("api/order/findByUserId/{id}")
    Call<List<Order>>getOrderByUserId(@Path("id") int id);
}

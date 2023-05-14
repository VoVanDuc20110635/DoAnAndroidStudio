package com.example.cartapplication.Service;

import com.example.cartapplication.model.Order;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderService {
    @GET("api/order/findByUserId/{id}")
    Call<List<Order>>getOrderByUserId(@Path("id") int id);

    @POST("api/order/placeOrder")
    Call<ResponseBody>placeOrder(@Query("userId") int userId,
                                 @Query("phone") String phone,
                                 @Query("email") String email,
                                 @Query("address") String address,
                                 @Query("city") String city,
                                 @Query("zip") int zip,
                                 @Query("paymentId") int paymentId);
}

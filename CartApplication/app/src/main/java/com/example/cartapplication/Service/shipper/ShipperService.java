package com.example.cartapplication.Service.shipper;

import com.example.cartapplication.model.ErrorResponse;
import com.example.cartapplication.model.OrderShipper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShipperService {

    @GET("api/order/findByStatusOrder")
    Call<List<OrderShipper>> findByStatusOrder(@Query("status") int status,
                                              @Query("shipperId") int shipperId);

    @POST("api/order/updateStatusOrder")
    Call<ErrorResponse> updateStatusOrder(@Query("orderId") int orderId,
                                          @Query("status") int status,
                                          @Query("shipperId") int shipperId);
}

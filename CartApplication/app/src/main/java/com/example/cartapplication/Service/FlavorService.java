package com.example.cartapplication.Service;

import com.example.cartapplication.model.Category;
import com.example.cartapplication.model.Flavor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlavorService {
    @GET("api/flavor/getAll")
    Call<List<Flavor>> getAllFlavor();
}

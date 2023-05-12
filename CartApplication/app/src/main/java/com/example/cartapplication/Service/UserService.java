package com.example.cartapplication.Service;

import com.example.cartapplication.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("api/user/find/{id}")
    Call<User> getUser(@Path("id") int userId);
}
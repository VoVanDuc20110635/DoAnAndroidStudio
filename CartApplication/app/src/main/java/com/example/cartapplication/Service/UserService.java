package com.example.cartapplication.Service;

import com.example.cartapplication.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @GET("api/user/find/{id}")
    Call<User> getUser(@Path("id") int userId);

    @POST("api/user/editUserInfo")
        Call<ResponseBody> editUserInfo(@Query("userId") int userId,
                                  @Query("name") String name,
                                  @Query("sex") String sex,
                                  @Query("address") String address,
                                  @Query("zipcode") int zipcode,
                                  @Query("email") String email,
                                  @Query("phonenumber") String phonenumber);

    @GET("api/user/find/getAll")
        Call<List<User>> getAllAccount();

    @POST("api/user/editRole")
    Call<ResponseBody> editRole(@Query("userId") int userId,
                                @Query("role") int role);
}
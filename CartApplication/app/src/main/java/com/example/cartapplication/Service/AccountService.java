package com.example.cartapplication.Service;

import com.example.cartapplication.model.Account;
import com.example.cartapplication.model.ErrorResponse;
import com.example.cartapplication.model.User;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountService {
    @GET("api/account/authenticate")
    Call<Account> authenticate(@Query("accountName") String accountName,
                               @Query("password") String password);

    @GET("api/account/find/{id}")
    Call<Account> getAccount(@Path("id") int accountId);

    @GET("api/account/findUserByAccountId")
    Call<User> findUserByAccountId(@Query("accountId") int accountId);

    @POST("api/account/register")
    Call<ErrorResponse> register(
            @Query("userName") String userName,
            @Query("accountName") String accountName,
            @Query("email") String email,
            @Query("password") String password,
            @Query("repeatPassword") String repeatPassword);

    @POST("api/account/forgot-password")
    Call<ErrorResponse> processForgotPassword(
            @Query("email") String email);

    @POST("api/account/reset-password")
    Call<ErrorResponse> processPasswordReset(
            @Query("email") String email,
            @Query("code") String code,
            @Query("password") String password,
            @Query("repeatpassword") String repeatpassword);
}
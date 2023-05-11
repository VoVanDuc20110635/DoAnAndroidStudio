package com.example.cartapplication.Service;

import com.example.cartapplication.model.Account;
import com.example.cartapplication.model.User;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountService {
    @GET("api/account/authenticate")
    Call<Account> authenticate(@Query("accountName") String accountName, @Query("password") String password);

    @GET("api/account/find/{id}")
    Call<Account> getAccount(@Path("id") int accountId);

    @GET("api/account/findUserByAccountId")
    Call<User> findUserByAccountId(@Query("accountId") int accountId);
}
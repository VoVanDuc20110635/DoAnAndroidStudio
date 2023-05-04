package com.example.footapp.remote;

import com.example.footapp.Domain.CategoryDomain;
import com.example.footapp.Domain.Const;
import com.example.footapp.Domain.FoodDomain;
import com.example.footapp.Domain.StateMessage;
import com.example.footapp.Domain.UploadMessage;
import com.example.footapp.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://app.iotstar.vn/appfoods/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("categories.php")
    Call<List<CategoryDomain>> getCategoryAll();
    @GET("category.php")
    Call<CategoryDomain> getCategory();
    @GET("lastproduct.php")
    Call<List<FoodDomain>> getLastProduct();

    @Multipart
    @POST("updateimages.php")
    Call<UploadMessage> uploadImage(@Part(Const.MY_ID) RequestBody id, @Part MultipartBody.Part avt);
    @FormUrlEncoded
    @POST("registrationapi.php?apicall=login")
    Call<LoginResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("newmealdetail.php")
    Call<StateMessage> getDetailMeal(@Field("id") int id);

    @FormUrlEncoded
    @POST("registrationapi.php?apicall=login")
    Call<StateMessage> getAccount(@Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @POST("registrationapi.php?apicall=signup")
    Call<LoginResponse> signup(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("gender") String gender
    );
}

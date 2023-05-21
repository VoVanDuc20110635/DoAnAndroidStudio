package com.example.cartapplication.Service;

import com.example.cartapplication.model.CloudinaryResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface CloudinaryService {
    @Multipart
    @POST("image/upload")
    Call<CloudinaryResponse> uploadImage(@Part MultipartBody.Part file, @PartMap Map<String, String> options);
}
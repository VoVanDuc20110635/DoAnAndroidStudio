package com.example.cartapplication.Service;

import com.example.cartapplication.model.Feedback;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FeedbackService {
        @GET("api/comment/getAll/{id}")
        Call<List<Feedback>> getAllfeedback(@Path("id") int id);

        @POST("api/comment/sentfeedback")
        Call<ResponseBody> sentfeedback(
                @Query("userId") int userId,
                @Query("content") String content,
                @Query("productId") int productId);
}

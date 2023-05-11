package com.example.cartapplication.Service;

import com.example.cartapplication.model.Feedback;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FeedbackService {
        @GET("api/comment/getAll/{id}")
        Call<List<Feedback>> getAllfeedback(@Path("id") int id);
}

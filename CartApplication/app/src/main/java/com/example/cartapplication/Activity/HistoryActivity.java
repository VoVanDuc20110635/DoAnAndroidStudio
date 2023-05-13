package com.example.cartapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.CartItemAdapter;
import com.example.cartapplication.Adapter.OrderAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.CartItemService;
import com.example.cartapplication.Service.OrderService;
import com.example.cartapplication.model.Order;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView orderView;
    ImageView backButton;
    List<Order> listOrder;
    OrderAdapter adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        orderView = findViewById(R.id.recyclerView);
        backButton = findViewById(R.id.back_button);
        listOrder = new ArrayList<>();

        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString("user", "");
        User user = gson.fromJson(userJson, User.class);
        Log.e("dc",user.getName());

        // Khởi tạo Retrofit
        Retrofit retrofit = ApiClient.getApiClient();
        OrderService orderService = retrofit.create(OrderService.class);

        Call<List<Order>> call = orderService.getOrderByUserId(user.getId());
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
//                Log.e("dc","Ko vo dc");
                if(response.isSuccessful()){
                    listOrder = response.body();
                    adapter = new OrderAdapter(listOrder, HistoryActivity.this);


                    // Attach the adapter to the RecyclerView
                    orderView.setAdapter(adapter);
                    orderView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                    adapter.notifyDataSetChanged();
                    Log.e("dc",listOrder.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
//                Log.e("dc","Loi");
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
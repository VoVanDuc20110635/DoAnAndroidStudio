package com.example.cartapplication.Activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.OrderAdapter;
import com.example.cartapplication.Adapter.OrderAdminItemAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.OrderService;
import com.example.cartapplication.model.Order;
import com.example.cartapplication.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryOrderUser extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private OrderAdminItemAdapter mAdapter;
    private List<Order> mOrderList;
    private Button waitorder, deliveryorder, finishorder, cancelorder;
    private ImageView backbutton;


    private void filter(int type, int type2){
        List<Order> orderList = new ArrayList<>();
        for(Order order : mOrderList){
            if(order.getStatus() == type || order.getStatus() == type2){
                orderList.add(order);
            }
        }
        mAdapter = new OrderAdminItemAdapter(orderList,HistoryOrderUser.this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(HistoryOrderUser.this));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order_user);
        mRecyclerView = findViewById(R.id.recyclerView);
        waitorder = findViewById(R.id.waitorder);
        deliveryorder = findViewById(R.id.deliveryorder);
        finishorder = findViewById(R.id.finishorder);
        cancelorder = findViewById(R.id.cancelorder);
        backbutton=findViewById(R.id.back_button);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        waitorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter(1, 1);
            }
        });

        deliveryorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter(2,3);
            }
        });

        finishorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter(4,4);
            }
        });

        cancelorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter(5,6);
            }
        });



        // Thiết lập ActionBar

        getOrdersFromDatabase();
        // Khởi tạo RecyclerView và thiết lập Adapter

//        mAdapter = new OrderAdminItemAdapter(mOrderList,this);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(HistoryOrderUser.this));

        // Lấy danh sách đơn hàng từ cơ sở dữ liệu hoặc API
        // và cập nhật Adapter

    }

    private void getOrdersFromDatabase() {
        // TODO: Lấy danh sách đơn hàng từ cơ sở dữ liệu hoặc API
        // và cập nhật Adapter
        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("User");

        Retrofit retrofit = ApiClient.getApiClient();
        OrderService orderService = retrofit.create(OrderService.class);

        Call<List<Order>> call = orderService.getOrderByUserId(user.getId());
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if(response.isSuccessful()){
                    mOrderList = response.body();
                    //Log.e("dc", response.body().toString());
                    //mAdapter = new OrderAdminItemAdapter(mOrderList,HistoryOrderUser.this);
                    //mRecyclerView.setAdapter(mAdapter);
                    //mRecyclerView.setLayoutManager(new LinearLayoutManager(HistoryOrderUser.this));
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Kết thúc Activity khi người dùng nhấn nút Back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
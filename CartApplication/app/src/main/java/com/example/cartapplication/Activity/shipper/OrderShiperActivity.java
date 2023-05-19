package com.example.cartapplication.Activity.shipper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Activity.Product_Activity;
import com.example.cartapplication.Activity.UserActivity;
import com.example.cartapplication.Adapter.OrderShipperAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.shipper.ShipperService;
import com.example.cartapplication.model.OrderShipper;
import com.example.cartapplication.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderShiperActivity extends AppCompatActivity {

    TextView textName;
    Button OrderChuaGiaoTxt;
    Button OrderYeuCauGiaoTxt;
    Button OrderDangGiaoTxt;
    Button OrderDaGiaoTxt;
    RecyclerView orderDetailView;

    ImageView profileButton;

    private User thisUser;

    List<OrderShipper> orderShippers;

    OrderShipperAdapter adapter;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_shiper);
        thisUser = (User) getIntent().getSerializableExtra("user");
        initView();

        hienThiDonHang(2, thisUser.getId());
        OrderChuaGiaoTxt.setBackgroundColor(Color.GREEN);
        OrderChuaGiaoTxt.setTextColor(Color.WHITE);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến UserActivity và truyền vào user
                Intent intent2;
                intent2 = new Intent(OrderShiperActivity.this, UserActivity.class);
                intent2.putExtra("user", thisUser); // Truyền vào user
                startActivity(intent2);
            }
        });
        OrderChuaGiaoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiDonHang(2, thisUser.getId());
                OrderChuaGiaoTxt.setBackgroundColor(Color.GREEN);
                OrderChuaGiaoTxt.setTextColor(Color.WHITE);
//                OrderYeuCauGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
//                OrderYeuCauGiaoTxt.setTextColor(Color.parseColor("#603913"));
                OrderDangGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
                OrderDangGiaoTxt.setTextColor(Color.parseColor("#603913"));
                OrderDaGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
                OrderDaGiaoTxt.setTextColor(Color.parseColor("#603913"));
            }
        });
//        OrderYeuCauGiaoTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                hienThiDonHang(-3, shipperId);
//                OrderYeuCauGiaoTxt.setBackgroundColor(Color.GREEN);
//                OrderYeuCauGiaoTxt.setTextColor(Color.WHITE);
//                OrderChuaGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
//                OrderChuaGiaoTxt.setTextColor(Color.parseColor("#603913"));
//                OrderDangGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
//                OrderDangGiaoTxt.setTextColor(Color.parseColor("#603913"));
//                OrderDaGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
//                OrderDaGiaoTxt.setTextColor(Color.parseColor("#603913"));
//            }
//        });
        OrderDangGiaoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiDonHang(3, thisUser.getId());
                OrderDangGiaoTxt.setBackgroundColor(Color.GREEN);
                OrderDangGiaoTxt.setTextColor(Color.WHITE);
                OrderChuaGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
                OrderChuaGiaoTxt.setTextColor(Color.parseColor("#603913"));
//                OrderYeuCauGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
//                OrderYeuCauGiaoTxt.setTextColor(Color.parseColor("#603913"));
                OrderDaGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
                OrderDaGiaoTxt.setTextColor(Color.parseColor("#603913"));
            }
        });
        OrderDaGiaoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiDonHang(4, thisUser.getId());
                OrderDaGiaoTxt.setBackgroundColor(Color.GREEN);
                OrderDaGiaoTxt.setTextColor(Color.WHITE);
                OrderChuaGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
                OrderChuaGiaoTxt.setTextColor(Color.parseColor("#603913"));
//                OrderYeuCauGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
//                OrderYeuCauGiaoTxt.setTextColor(Color.parseColor("#603913"));
                OrderDangGiaoTxt.setBackgroundColor(Color.parseColor("#92A8A371"));
                OrderDangGiaoTxt.setTextColor(Color.parseColor("#603913"));
            }
        });

    }
    private void initView(){
        textName = findViewById(R.id.textName);
        OrderChuaGiaoTxt = findViewById(R.id.txt_OrderChuaGiao);
//        OrderYeuCauGiaoTxt = findViewById(R.id.txt_OrderYeuCauGiao);
        OrderDangGiaoTxt = findViewById(R.id.txt_OrderDangGiao);
        OrderDaGiaoTxt = findViewById(R.id.txt_OrderDaGiao);
        orderDetailView = findViewById(R.id.orderProduct_list);
        profileButton = findViewById(R.id.profilebutton);
//        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
//
//        Gson gson = new Gson();
//        String userJson = sharedPreferences.getString("user", "");
//        User user = gson.fromJson(userJson, User.class);
//        Log.e("dc",user.getName());

//        textName.setText(user.getName());
//        shipperId = user.getId();
        textName.setText(thisUser.getName());
//        shipperId=thisUser.getId();
    }
    private void hienThiDonHang(int status, int shipperId){
        Retrofit retrofit = ApiClient.getApiClient();
        ShipperService shipperService = retrofit.create(ShipperService.class);

        Call<List<OrderShipper>> call = shipperService.findByStatusOrder(status, shipperId);
        call.enqueue(new Callback<List<OrderShipper>>() {
            @Override
            public void onResponse(Call<List<OrderShipper>> call, Response<List<OrderShipper>> response) {
                if (response.isSuccessful()){
                    orderShippers = response.body();
                    adapter = new OrderShipperAdapter(orderShippers, OrderShiperActivity.this);
                    orderDetailView.setAdapter(adapter);
                    orderDetailView.setLayoutManager(new LinearLayoutManager(OrderShiperActivity.this));
                    adapter.notifyDataSetChanged();
                    Log.e("dc", orderShippers.toString());
                }
            }

            @Override
            public void onFailure(Call<List<OrderShipper>> call, Throwable t) {
                Log.e("Loi:","khong lay duoc api");
            }
        });
    }
}
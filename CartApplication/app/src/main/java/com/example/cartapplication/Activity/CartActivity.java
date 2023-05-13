package com.example.cartapplication.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.CartItemAdapter;
import com.example.cartapplication.Adapter.CartProductAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.CartItemService;
import com.example.cartapplication.Service.CartService;
import com.example.cartapplication.Service.ProductService;
import com.example.cartapplication.model.CartItem;
import com.example.cartapplication.model.Product;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartProductAdapter productAdapter;
    private List<CartItem> cartItems;
    private CartItemAdapter adapter;
    private List<Product> productList;
    private Button buttonAddToCart;
    private SharedPreferences sharedPreferences;

    private void loadProducts() {
        // Đọc dữ liệu từ JSON và thêm vào productList
        // ...
        ProductService productService = ApiClient.getApiClient().create(ProductService.class);
        Call<List<Product>> call = productService.getProductList();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.addAll(response.body());
                    productAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Lỗi", "Failed to fetch data", t);
            }
        });
        // Cập nhật Adapter
        productAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        // Get the cart items from the intent extras
        cartItems = new ArrayList<>();
//        cartItems.add(new CartItem(3, null, new Product(3, "Loz", 3, 33, 333,
//                null, null, null, 2, 3, null, null), 2, 1222, 333, null, null));

        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString("user", "");
        User user = gson.fromJson(userJson, User.class);
        Log.e("dc",user.getName());

        // Khởi tạo Retrofit
        Retrofit retrofit = ApiClient.getApiClient();

// Tạo một đối tượng service sử dụng Retrofit
        CartItemService cartItemService = retrofit.create(CartItemService.class);

// Gọi API để lấy danh sách CartItem
//        Log.e("dc","dangchay");
        Call<List<CartItem>> call = cartItemService.getCartItem(user.getId());

// Thực hiện request và xử lý kết quả trả về
        call.enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
//                Log.e("dc","vao");
//                Log.e("dc",String.valueOf(user.getId()));
                if (response.isSuccessful()) {
                   cartItems = response.body();
                   if(cartItems != null){
                       Log.e("dc",cartItems.get(0).getProduct().getProductName());
                   }
                    // Create an adapter for the cart items
                    adapter = new CartItemAdapter(cartItems, CartActivity.this);


                    // Attach the adapter to the RecyclerView
                    RecyclerView listViewCartItems = findViewById(R.id.listViewCartItems);
                    listViewCartItems.setAdapter(adapter);
                    Log.e("Loi",response.body().toString());
                    if(response.body() == null){
                        Log.e("dc","bitrong");
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    // Xử lý trường hợp request không thành công
                    String error = null;
                    try {
                        error = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Xử lý lỗi ở đây
                    Log.e("das", "Request failed with error: " + error);

                }
            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
                // Xử lý trường hợp có lỗi xảy ra trong quá trình thực hiện request
            }
        });



//        // Set the layout manager for the RecyclerView
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        listViewCartItems.setLayoutManager(layoutManager);

        // Update the total price TextView
        TextView textViewTotal = findViewById(R.id.textViewTotal);
        int totalPrice = calculateTotalPrice();
        textViewTotal.setText("Tổng tiền: " + totalPrice + "đồng");

        // Set the onClickListener for the checkout button
        Button buttonCheckout = findViewById(R.id.buttonCheckout);
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement checkout functionality
            }
        });
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        if (cartItems == null)
            for (CartItem cartItem : cartItems) {
                totalPrice += cartItem.getTotal();
            }
        return totalPrice;
    }

}

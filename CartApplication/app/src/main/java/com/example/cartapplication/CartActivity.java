package com.example.cartapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.CartProductAdapter;
import com.example.cartapplication.Service.ProductService;
import com.example.cartapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartProductAdapter productAdapter;
    private List<Product> productList;
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
        // Khởi tạo RecyclerView và ProductAdapter
        recyclerView = findViewById(R.id.listViewCartItems);
        productList = new ArrayList<>();
        productAdapter = new CartProductAdapter(productList);

        // Thiết lập LayoutManager và Adapter cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        // Load dữ liệu từ JSON và cập nhật Adapter
        loadProducts();

    }
}
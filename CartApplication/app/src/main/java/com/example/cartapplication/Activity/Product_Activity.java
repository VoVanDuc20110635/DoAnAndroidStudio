package com.example.cartapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.CartProductAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.ProductService;
import com.example.cartapplication.Service.UserService;
import com.example.cartapplication.model.Product;
import com.example.cartapplication.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Product_Activity extends AppCompatActivity {
    //private ImageView imageView;
    private TextView textName;
    private RecyclerView recyclerView;
    private CartProductAdapter productAdapter;
    private List<Product> productList;
    private ImageView imageView3;
    private Intent intent;
    private EditText textSearch;

    private void getUserData() {

        // Gọi API để lấy dữ liệu user
        //User user=null;
        intent=getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("User_id", 0);
            //Log.d("Hệ thống", "User id là "+id);
            //System.out.print("User id là "+id);
            // Sau khi lấy được dữ liệu user, hiển thị lên view
            String name = "not found 404";
            Retrofit retrofit = ApiClient.getApiClient();
            UserService userService = retrofit.create(UserService.class);
            Call<User> call = userService.getUser(id);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User user = response.body();
                        // xử lý dữ liệu user
                        String nameuser= user.getName();
                        textName.setText(nameuser);
                    } else {
                        // xử lý lỗi khi response không thành công
                        Toast.makeText(Product_Activity.this,"User trống",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // xử lý lỗi khi gọi API thất bại
                }
            });


        //String imageUrl = ""; // Thay bằng URL của hình ảnh tương ứng


        // Hiển thị tên của user lên TextView
        textName.setText(name);}
        else{
            Toast.makeText(this,"Intent trống",Toast.LENGTH_SHORT).show();
        }

        // Tải hình ảnh của user lên ImageView
        //Glide.with(this)
        //        .load(imageUrl)
        ///        .into(imageView);
    }
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
    private void searchProducts(String searchText) {
        // Tạo một danh sách mới để lưu các sản phẩm được lọc
        List<Product> filteredList = new ArrayList<>();

        // Duyệt qua tất cả các sản phẩm trong danh sách sản phẩm
        for (Product product : productList) {
            // Nếu tên sản phẩm chứa searchText thì thêm sản phẩm đó vào danh sách mới
            if (product.getProductName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(product);
            }
        }

        // Cập nhật Adapter với danh sách sản phẩm mới
        productAdapter.updateList(filteredList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        recyclerView = findViewById(R.id.product_list);
        productList = new ArrayList<>();
        productAdapter = new CartProductAdapter(productList,this);

        // Thiết lập LayoutManager và Adapter cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        // Load dữ liệu từ JSON và cập nhật Adapter
        loadProducts();
        // Khởi tạo các view
        //imageView = findViewById(R.id.imageView);
        textName = findViewById(R.id.textName);


        // Lấy dữ liệu user từ API
        getUserData();
        // Khởi tạo các view
        imageView3 = findViewById(R.id.imageView3);

        // Xử lý sự kiện khi người dùng nhấn vào ImageView3
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product_Activity.this, CartActivity.class);
                startActivity(intent);
            }
        });


        //Chức năng tìm kiếm
        textSearch = findViewById(R.id.textSearch);
        ImageView searchButton = findViewById(R.id.searchbutton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = textSearch.getText().toString().trim();
                searchProducts(searchText);
            }
        });
    }

}
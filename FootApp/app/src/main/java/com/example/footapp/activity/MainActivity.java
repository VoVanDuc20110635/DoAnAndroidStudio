package com.example.footapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.footapp.Adaptor.CategoryAdaptor;
import com.example.footapp.Adaptor.PoplurarAdaptor;
import com.example.footapp.Domain.CategoryDomain;
import com.example.footapp.Domain.FoodDomain;
import com.example.footapp.Domain.User;
import com.example.footapp.R;
import com.example.footapp.remote.ApiService;
import com.example.footapp.remote.CategoryClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    //rc_category

    private RecyclerView.Adapter adapter2;

    private RecyclerView recyclerViewPopularList;
    //rc_product

    private User user = LoginActivity.user;

    CategoryAdaptor categoryAdapter;
    PoplurarAdaptor productAdapter;
    ApiService apiService;
    List<CategoryDomain> categoryList;
    List<FoodDomain> productList;

    TextView nameTxt;
    ImageView imageAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContent();
        nameTxt.setText(user.getName());
        Glide.with(getApplicationContext())
                .load(user.getImages())
                .into(imageAccount);
        GetCategory();
        GetProduct();
//        recycleViewCategory();
//        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

    }
    private void setContent(){
        recyclerViewCategoryList = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewPopularList  = (RecyclerView) findViewById(R.id.recyclerView2);
        nameTxt = findViewById(R.id.nameTxt);
        imageAccount = findViewById(R.id.imageAccount);

    }
    private void GetCategory(){
        apiService= CategoryClient.getInstance();
        apiService.getCategoryAll().enqueue(new Callback<List<CategoryDomain>>() {
            @Override
            public void onResponse(Call<List<CategoryDomain>> call, Response<List<CategoryDomain>> response) {
                if(response.isSuccessful()){
                    categoryList = response.body();
                    categoryAdapter = new CategoryAdaptor(categoryList,MainActivity.this);
                    recyclerViewCategoryList.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((getApplicationContext()),LinearLayoutManager.HORIZONTAL,false);
                    recyclerViewCategoryList.setLayoutManager(layoutManager);
                    recyclerViewCategoryList.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }else{
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<CategoryDomain>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private void GetProduct() {
        apiService = CategoryClient.getInstance();
        apiService.getLastProduct().enqueue(new Callback<List<FoodDomain>>() {
            @Override
            public void onResponse(Call<List<FoodDomain>> call, Response<List<FoodDomain>> response) {
                if(response.isSuccessful()){
                    productList = response.body();
                    productAdapter = new PoplurarAdaptor(productList,MainActivity.this);
                    recyclerViewPopularList.setHasFixedSize(true);
                    GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
                    recyclerViewPopularList.setLayoutManager(layoutManager);
                    recyclerViewPopularList.setAdapter(productAdapter);
                }else{
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<FoodDomain>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
//    private void recycleViewCategory(){
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewCategoryList = findViewById(R.id.recyclerView);
//        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
//
//        ArrayList<CategoryDomain> category = new ArrayList<>();
//        category.add(new CategoryDomain("Pizza", "cat_1"));
//        category.add(new CategoryDomain("Burger", "cat_2"));
//        category.add(new CategoryDomain("Hotdog", "cat_3"));
//        category.add(new CategoryDomain("Drink", "cat_4"));
//        category.add(new CategoryDomain("Donut", "cat_5"));
//
//        adapter = new CategoryAdaptor(category);
//        recyclerViewCategoryList.setAdapter(adapter);
//    }
//
//    private void recyclerViewPopular(){
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewPopularList = findViewById(R.id.recyclerView2);
//        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
//
//        ArrayList<FoodDomain> foodList = new ArrayList<>();
//        foodList.add(new FoodDomain("Pepperoni pizza", "pop_1", "slices pepperoni, mozzerella cheese, fresh oregano, ground black pepper, pizza sauce", 9.76));
//        foodList.add(new FoodDomain("Cheese Burger", "pop_2", "beef, Gouda Cheese, Special Sauce, Lettuce, tomato", 8.76));
//        foodList.add(new FoodDomain("Vegetable pizza", "pop_3", "olive oil, Vegetable oil, pitted kalamata, cherry tomatoes, basil", 8.97));
//
//        adapter2 = new PoplurarAdaptor(foodList);
//        recyclerViewPopularList.setAdapter(adapter2);
//    }
}
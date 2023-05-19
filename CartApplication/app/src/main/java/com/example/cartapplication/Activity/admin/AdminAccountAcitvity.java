package com.example.cartapplication.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.AccountItemAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.UserService;
import com.example.cartapplication.model.Product;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminAccountAcitvity extends AppCompatActivity {
    private EditText searchEdit;
    private ImageView searchButton, backButton;
    private RecyclerView accountList;
    private List<User> userList;


    private void anhXa(){
        searchEdit = findViewById(R.id.textSearch);
        searchButton = findViewById(R.id.searchbutton);
        backButton = findViewById(R.id.backbutton);
        accountList = findViewById(R.id.product_list);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchAccounts(searchEdit.getText().toString());
            }
        });
    }

    private void callApi(){
        Retrofit retrofit = ApiClient.getApiClient();
        UserService userService = retrofit.create(UserService.class);
        Call<List<User>> call = userService.getAllAccount();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.e("dc",response.body().toString());
                if(response.isSuccessful()){
                    userList = response.body();
                    AccountItemAdapter accountItemAdapter = new AccountItemAdapter(userList,AdminAccountAcitvity.this);
                    accountList.setAdapter(accountItemAdapter);
                    accountList.setLayoutManager(new LinearLayoutManager(AdminAccountAcitvity.this));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account_acitvity);

        anhXa();
        callApi();

    }

    private void searchAccounts(String searchText) {
        // Tạo một danh sách mới để lưu các sản phẩm được lọc
        List<User> filteredList = new ArrayList<>();

        // Duyệt qua tất cả các sản phẩm trong danh sách sản phẩm
        for (User user : userList ) {
            // Nếu tên sản phẩm chứa searchText thì thêm sản phẩm đó vào danh sách mới
            if (user.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(user);
            }
        }

        // Cập nhật Adapter với danh sách sản phẩm mới
        AccountItemAdapter accountItemAdapter = new AccountItemAdapter(filteredList,AdminAccountAcitvity.this);
        accountList.setAdapter(accountItemAdapter);
        
    }

}
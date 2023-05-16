package com.example.cartapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cartapplication.R;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;

public class UserActivity extends AppCompatActivity {
    TextView userName, userSex, userAddress, userZip, userEmail, userPhoneNumber;
    Button editUserButton, historyButton, dangxuatButton;
    private SharedPreferences sharedPreferences;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Lấy thông tin người dùng từ Intent
        Intent intent = getIntent();
//        User user = (User) intent.getSerializableExtra("user");
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString("user", "");
        User user = gson.fromJson(userJson, User.class);
        // Ánh xạ các thành phần giao diện người dùng
        if(user!=null) {
            userName = findViewById(R.id.user_name);
            userSex = findViewById(R.id.user_sex);
            userAddress = findViewById(R.id.user_address);
            userZip = findViewById(R.id.user_zip);
            userEmail = findViewById(R.id.user_email);
            userPhoneNumber = findViewById(R.id.user_phone_number);
            editUserButton = findViewById(R.id.edit_user_button);
            historyButton = findViewById(R.id.history_button);
            dangxuatButton = findViewById(R.id.button_dangxuat);
            backButton = findViewById(R.id.backButtonUser);

            if (user.getAccount().getRoleNumber() == 1){
                historyButton.setVisibility(View.GONE);
            }

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            // Thiết lập thông tin người dùng cho các thành phần giao diện người dùng
            userName.setText(user.getName());
            userSex.setText(user.getSex());
            userAddress.setText(user.getAddress());

            userZip.setText(String.valueOf(user.getZip()));
            userEmail.setText(user.getEmail());
            userPhoneNumber.setText(user.getPhoneNumber());
        }
        // Thiết lập bộ xử lý sự kiện cho nút chỉnh sửa thông tin người dùng
        editUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang EditUserActivity
                Intent intent = new Intent(UserActivity.this, EditUserActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
        dangxuatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}
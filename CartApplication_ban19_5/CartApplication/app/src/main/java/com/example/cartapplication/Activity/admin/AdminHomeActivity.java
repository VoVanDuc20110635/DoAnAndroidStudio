package com.example.cartapplication.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cartapplication.Activity.UserActivity;
import com.example.cartapplication.R;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;

public class AdminHomeActivity extends AppCompatActivity {
    private TextView adminName;
    private ImageView profileButton;
    private LinearLayout qltk, qlsp, qldh;
    private SharedPreferences sharedPreferences;

    private void anhXa(){
        profileButton = findViewById(R.id.profile_buttonadmin);
        adminName = findViewById(R.id.qltk);
        qltk = findViewById(R.id.qltkadmin);
        qlsp = findViewById(R.id.qlspadmin);
        qldh = findViewById(R.id.qldhadmin);

        qltk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAccountAcitvity.class);
                startActivity(intent);
            }
        });

        qlsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminProductActivity.class);
                startActivity(intent);
            }
        });

        qldh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, HistoryOrderActivity.class);
                startActivity(intent);
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        anhXa();
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString("user", "");
        User user = gson.fromJson(userJson, User.class);

        adminName.setText(user.getName());
    }
}
package com.example.footapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.footapp.Domain.User;
import com.example.footapp.R;
import com.example.footapp.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ProfileActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    TextView id, userName, userEmail, gender;
    Button btnBack;
    ImageView imageViewprofile;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*findViewById(R.id.btnlogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });*/

        initView();
        bottomNavigation();
//        Bundle bundleRecieve = getIntent().getExtras();
//        if(bundleRecieve != null){
//            user = (User) bundleRecieve.get("userObject");
//
//            Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT).show();
//            if(user != null){
//                id.setText(String.valueOf(user.getId()));
//                userName.setText(user.getName());
//                userEmail.setText(user.getEmail());
//                gender.setText(user.getGender());
//                Glide.with(getApplicationContext()).load(user.getImages()).into(imageViewprofile);
//            }
//        }
//        else{
//            Toast.makeText(getApplicationContext(), "Chay ko duoc", Toast.LENGTH_SHORT).show();
//        }

        user = LoginActivity.user;
        id.setText(String.valueOf(user.getId()));
        userName.setText(user.getName());
        userEmail.setText(user.getEmail());
        gender.setText(user.getGender());
        Glide.with(getApplicationContext())
                .load(user.getImages())
                .into(imageViewprofile);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageViewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, UpdateImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userObject", user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
            }
        });

    }

    private void initView() {
        id = findViewById(R.id.textViewId);
        userName = findViewById(R.id.textViewUsername);
        userEmail = findViewById(R.id.textViewEmail);
        gender = findViewById(R.id.textViewGender);
        btnBack = findViewById(R.id.btnBack);
        imageViewprofile = findViewById(R.id.imageViewProfile);
    }

}

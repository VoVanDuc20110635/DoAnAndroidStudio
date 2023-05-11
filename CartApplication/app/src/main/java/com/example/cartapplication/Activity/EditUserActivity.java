package com.example.cartapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cartapplication.R;
import com.example.cartapplication.model.User;

public class EditUserActivity extends AppCompatActivity {
    private EditText editUserName;
    private Spinner editUserSexSpinner;
    private EditText editUserAddress;
    private EditText editUserZip;
    private EditText editUserEmail;
    private EditText editUserPhoneNumber;
    private Button saveUserButton;
    private String[] sexArray = {"Không hiển thị", "Nam", "Nữ"};

    private void loaddulieu() {

    }

    private void anhxa() {
        // Lấy thông tin người dùng từ Intent
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        // Ánh xạ các thành phần trong layout
        editUserName = findViewById(R.id.edit_user_name);
        editUserSexSpinner = findViewById(R.id.edit_user_sex_spinner);
        ArrayAdapter<String> sexAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sexArray);
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editUserSexSpinner.setAdapter(sexAdapter);
        editUserAddress = findViewById(R.id.edit_user_address);
        editUserZip = findViewById(R.id.edit_user_zip);
        editUserEmail = findViewById(R.id.edit_user_email);
        editUserPhoneNumber = findViewById(R.id.edit_user_phone_number);
        saveUserButton = findViewById(R.id.save_user_button);
        //Thêm thông tin
        // Thiết lập thông tin người dùng cho các thành phần giao diện người dùng
        editUserName.setText(user.getName());
        //editUserSex.setText(user.getSex());
        if (user.getSex().equals( "Nam"))
            editUserSexSpinner.setSelection(1);
        else if (user.getSex().equals("Nữ"))
            editUserSexSpinner.setSelection(2);
        else editUserSexSpinner.setSelection(0);
        editUserAddress.setText(user.getAddress());

        editUserZip.setText(String.valueOf(user.getZip()));
        editUserEmail.setText(user.getEmail());
        editUserPhoneNumber.setText(user.getPhoneNumber());
    }

    private void themsukienbutton() {
        // Thêm sự kiện click cho saveUserButton
        saveUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy giá trị từ editUserName
                String name = editUserName.getText().toString();
                // Lưu giá trị vào database hoặc thực hiện các thao tác khác
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        anhxa();
        loaddulieu();
        themsukienbutton();
    }
}
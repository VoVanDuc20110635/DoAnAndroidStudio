package com.example.cartapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cartapplication.R;

public class EditUserActivity extends AppCompatActivity {
    private EditText editUserName;
    private Spinner editUserSexSpinner;
    private EditText editUserAddress;
    private EditText editUserZip;
    private EditText editUserEmail;
    private EditText editUserPhoneNumber;
    private Button saveUserButton;
    private void loaddulieu(){

    }
    private void anhxa(){
        // Ánh xạ các thành phần trong layout
        editUserName = findViewById(R.id.edit_user_name);
        editUserSexSpinner = findViewById(R.id.edit_user_sex_spinner);
        editUserAddress = findViewById(R.id.edit_user_address);
        editUserZip = findViewById(R.id.edit_user_zip);
        editUserEmail = findViewById(R.id.edit_user_email);
        editUserPhoneNumber = findViewById(R.id.edit_user_phone_number);
        saveUserButton = findViewById(R.id.save_user_button);
    }
    private void themsukienbutton(){
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
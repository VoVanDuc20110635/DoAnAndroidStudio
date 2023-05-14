package com.example.cartapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.CartService;
import com.example.cartapplication.Service.UserService;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditUserActivity extends AppCompatActivity {
    private EditText editUserName;
    private Spinner editUserSexSpinner;
    private EditText editUserAddress;
    private EditText editUserZip;
    private EditText editUserEmail;
    private EditText editUserPhoneNumber;
    private Button saveUserButton;
    private String[] sexArray = {"Không hiển thị", "Nam", "Nữ"};
    private SharedPreferences sharedPreferences;

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
                Log.e("dc","bam dc nut");
                sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String userJson = sharedPreferences.getString("user", "");
                User user = gson.fromJson(userJson, User.class);

                Retrofit retrofit = ApiClient.getApiClient();
                UserService userService = retrofit.create(UserService.class);

                Call<ResponseBody> call = userService.editUserInfo(user.getId(),
                        editUserName.getText().toString(), editUserSexSpinner.getSelectedItem().toString()
                        , editUserAddress.getText().toString(), Integer.parseInt(editUserZip.getText().toString()),
                        editUserEmail.getText().toString(), editUserPhoneNumber.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
//                            Log.e("dc","cap nhat dc");
                            Toast.makeText(EditUserActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            Call<User> call2 = userService.getUser(user.getId());
                            call2.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {

                                    sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    String userJson = gson.toJson(response.body());
                                    editor.putString("user", userJson);
                                    editor.apply();
                                    //Log.e("dc",userJson);
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {

                                }
                            });
                            Intent intent = new Intent(EditUserActivity.this, UserActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                       // Log.e("dc",call.toString());
                    }
                });
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
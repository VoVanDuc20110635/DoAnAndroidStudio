package com.example.cartapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.AccountService;
import com.example.cartapplication.model.ErrorResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText edtEmail;
    Button continueButton;
    Button loginButton;

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initView();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                Bundle bundle2 = new Bundle();
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callApiProcessForgotPassword();
                Intent intent2 = new Intent(ResetPasswordActivity.this, ResetPasswordStep2.class);
                Bundle bundle2 = new Bundle();
                Log.e("Emaillllllllllllllllllllll111111", email);
                bundle2.putSerializable("email", email);
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });



    }

    private void initView(){
        edtEmail = findViewById(R.id.etEmail);
        loginButton = findViewById(R.id.login_button);
        continueButton = findViewById(R.id.continue_button);
    }

    private void callApiProcessForgotPassword(){
        email = edtEmail.getText().toString();
        Log.e("emaill", email);
//        Retrofit retrofit = ApiClient.getApiClient();
//        AccountService accountService = retrofit.create(AccountService.class);
        AccountService accountService = ApiClient.getApiClient().create(AccountService.class);
        Call<ErrorResponse> call = accountService.processForgotPassword(email);
        call.enqueue(new Callback<ErrorResponse>() {
            @Override
            public void onResponse(Call<ErrorResponse> call, Response<ErrorResponse> response) {
                if (response.isSuccessful()) {
                    int statusCode = response.code();
                    ErrorResponse errorResponse = response.body();
                    if (errorResponse != null) {
                        // xử lý kết quả trả về ở đây
                        Log.e("thong bao thanh cong", response.body().getMessage());
                        Toast.makeText(ResetPasswordActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("thong bao: ", "body khong xac dinh");
                    }
                } else {
                    int statusCode = response.code();
                    String errorMessage = response.message();
                    Log.e("thong bao: ", "response khong thanh cong: " + statusCode + " - " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<ErrorResponse> call, Throwable t) {
                Log.e("thong bao: ", "khong lay duoc api");
            }
        });
    }



}
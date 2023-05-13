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

public class ResetPasswordStep2 extends AppCompatActivity {

    EditText edtEmail;
    Button continueButton;
    Button loginButton;
    EditText edtCode;
    EditText edtPassword;
    EditText edtRepeatPassword;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_step2);

        initView();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ResetPasswordStep2.this, LoginActivity.class);
                Bundle bundle2 = new Bundle();
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPasswordReset();
                Intent intent2 = new Intent(ResetPasswordStep2.this, LoginActivity.class);
                Bundle bundle2 = new Bundle();
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });
    }

    private void initView(){

        edtEmail = findViewById(R.id.etEmail);
        loginButton = findViewById(R.id.login_button);
        continueButton = findViewById(R.id.continue_button);
        edtCode = findViewById(R.id.etCode);
        edtPassword = findViewById(R.id.etPassword);
        edtRepeatPassword = findViewById(R.id.etRepeatPassword);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("email")) {
            email = (String) bundle.getSerializable("email");
            edtEmail.setText(email);
//            System.out.println(email);
            Log.e("Emaillllllllllllllllllllll", email);
            // Sử dụng giá trị email ở đây
        }
    }

    private void processPasswordReset(){
//        String email = edtEmail.getText().toString();
        String code = edtCode.getText().toString();
        String password = edtPassword.getText().toString();
        String repeatpassword = edtRepeatPassword.getText().toString();

        Log.e("email",email);
        Log.e("code",code);
        Log.e("password",password);
        Log.e("repeatpassword",repeatpassword);

        AccountService accountService = ApiClient.getApiClient().create(AccountService.class);
        Call<ErrorResponse> call = accountService.processPasswordReset(email, code, password, repeatpassword);
        call.enqueue(new Callback<ErrorResponse>() {
            @Override
            public void onResponse(Call<ErrorResponse> call, Response<ErrorResponse> response) {
                if (response.isSuccessful()) {
                    int statusCode = response.code();
                    ErrorResponse errorResponse = response.body();
                    if (errorResponse != null) {
                        // xử lý kết quả trả về ở đây
                        Log.e("thong bao thanh cong", response.body().getMessage());
                        Toast.makeText(ResetPasswordStep2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
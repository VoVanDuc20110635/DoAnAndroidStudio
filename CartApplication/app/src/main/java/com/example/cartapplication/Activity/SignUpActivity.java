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


public class SignUpActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtAccountName;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtRepeatPassword;
//    private String userName;
//    private String accountName;
//    private String email;
//    private String password;
//    private String repeatPassword;
    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName= edtUserName.getText().toString();
                final String accountName = edtAccountName.getText().toString();
                final String email = edtEmail.getText().toString();
                final String password = edtPassword.getText().toString();
                final String repeatPassword = edtRepeatPassword.getText().toString();
                Log.e("username", userName);
                Log.e("accountName", accountName);
                Log.e("email", email);
                Log.e("password", password);
                Log.e("repeatPassword", repeatPassword);

                callApiRegister(userName, accountName, email, password, repeatPassword);

                Intent intent2 = new Intent(SignUpActivity.this, LoginActivity.class);
                Bundle bundle2 = new Bundle();
                intent2.putExtras(bundle2);
                startActivity(intent2);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(SignUpActivity.this, LoginActivity.class);
                Bundle bundle2 = new Bundle();
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });


    }
    private void initView(){
        edtUserName = findViewById(R.id.etUsername);
        edtAccountName = findViewById(R.id.etAccountName);
        edtEmail = findViewById(R.id.etEmail);
        edtPassword = findViewById(R.id.etPassword);
        edtRepeatPassword = findViewById(R.id.etRepeatPassword);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.continue_button);



    }

    private void registerAccount(){
        final String userName= edtUserName.getText().toString();
        final String accountName = edtAccountName.getText().toString();
        final String email = edtEmail.getText().toString();
        final String password = edtPassword.getText().toString();
        final String repeatPassword = edtRepeatPassword.getText().toString();
        Log.e("username", userName);
        Log.e("accountName", accountName);
        Log.e("email", email);
        Log.e("password", password);
        Log.e("repeatPassword", repeatPassword);

        callApiRegister(userName, accountName, email, password, repeatPassword);




//        AccountService accountService = ApiClient.getApiClient().create(AccountService.class);
//        Call<String> call = accountService.register(userName, accountName, email, password, repeatPassword);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    String phanHoi;
//                    phanHoi = response.body();
//                    Log.e("phan hoi: ", phanHoi);
//                    Toast.makeText(SignUpActivity.this, phanHoi, Toast.LENGTH_SHORT).show();
//                    Intent intent2 = new Intent(SignUpActivity.this, LoginActivity.class);
//                    Bundle bundle2 = new Bundle();
//                    intent2.putExtras(bundle2);
//                    startActivity(intent2);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.e("Lỗi", "Không kết được API", t);
//            }
//        });
    }
    private void callApiRegister(String userName,String accountName,String email,String password,String repeatPassword){
//        Retrofit retrofit = ApiClient.getApiClient();
//        AccountService accountService = retrofit.create(AccountService.class);
        AccountService accountService = ApiClient.getApiClient().create(AccountService.class);
        Call<ErrorResponse> call = accountService.register(userName, accountName, email, password, repeatPassword);
        call.enqueue(new Callback<ErrorResponse>() {
            @Override
            public void onResponse(Call<ErrorResponse> call, Response<ErrorResponse> response) {
                if (response.isSuccessful()) {
                    int statusCode = response.code();
                    ErrorResponse errorResponse = response.body();
                    if (errorResponse != null) {
                        // xử lý kết quả trả về ở đây
                        Log.e("thong bao thanh cong", response.body().getMessage());
                        Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(SignUpActivity.this, LoginActivity.class);
                        Bundle bundle2 = new Bundle();
                        intent2.putExtras(bundle2);
                        startActivity(intent2);
                    } else {
                        //Log.e("thong bao: ", "body khong xac dinh");

                    }
                } else {
                    int statusCode = response.code();
                    String errorMessage = response.message();
                    Log.e("thong bao: ", "response khong thanh cong: " + statusCode + " - " + errorMessage);
                    Toast.makeText(SignUpActivity.this,"Email đã tồn tại",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ErrorResponse> call, Throwable t) {
                Log.e("thong bao: ", "khong lay duoc api");
            }
        });
    }
}
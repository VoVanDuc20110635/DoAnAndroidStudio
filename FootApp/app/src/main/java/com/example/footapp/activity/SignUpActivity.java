package com.example.footapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.footapp.LoginResponse;
import com.example.footapp.R;
import com.example.footapp.remote.ApiService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private String TAG = LoginActivity.class.getSimpleName();
    TextView btnRedirectLogin;
    EditText etUsername, etPassword, etEmail;
    RadioGroup rdgGender;
    ImageView registerButton;
    ApiService apiService;

    void setContent(){
        etUsername = findViewById(R.id.et_username_regis);
        etPassword = findViewById(R.id.et_password_regis);
        btnRedirectLogin = findViewById(R.id.redic_login);
        registerButton = findViewById(R.id.register_button);
        etEmail = findViewById(R.id.et_email_regis);
        rdgGender = findViewById(R.id.radioGender);
    }
    void doRegister(){
        final String username = etUsername.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        final String gender = ((RadioButton) findViewById(rdgGender.getCheckedRadioButtonId())).getText().toString();
        if(TextUtils.isEmpty(username)){
            etUsername.setError("Please enter username");
            etUsername.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            etPassword.setError("Please enter password");
            etPassword.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(email)){
            etEmail.setError("Please enter email");
            etEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }
//        Log.e("username", username);
//        Log.e("email", email);
//        Log.e("password", password);
//        Log.e("gender", gender);

//        ApiService.apiService.signup(username, password, email, gender);
//        StateMessage stateMessage = response.body();
//        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//        startActivity(intent);


        ApiService.apiService.signup(username, password, email, gender).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Toast.makeText(SignUpActivity.this, "Call User api success", Toast.LENGTH_SHORT).show();
                LoginResponse stateMessage = response.body();

                if(stateMessage != null){
                    Log.e("username", stateMessage.getUser().getUsername());
                    Log.e("email", stateMessage.getUser().getEmail());
//                    Log.e("password", stateMessage.getUser().get);
                    Log.e("gender", stateMessage.getUser().getGender());

                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userObject", stateMessage.getUser());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Call api error", Toast.LENGTH_SHORT).show();
            }
        });
//        apiService = UserClient.getInstance();
//        Call<LoginResponse> call =  apiService.signup(username, password, email, gender);
//        call.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                try {
//                    if(response.isSuccessful()){
//                        Log.i(TAG, "Response: " + response.body());
//                        LoginResponse signUpResponse = response.body();
//                        assert signUpResponse != null;
////                        User user = new User(
////                                signUpResponse.getUser().getId(),
////                                signUpResponse.getUser().getName(),
////                                signUpResponse.getUser().getEmail(),
////                                signUpResponse.getUser().getGender(),
////                                signUpResponse.getUser().getImages()
////                        );
//                        Random random = new Random();
//                        int randomNumber = random.nextInt(100 - 1) + 1;
//                        User user = new User(
//                                randomNumber,
//                                username,
//                                "vovanduc",
//                                email,
//                                gender,
//                                ""
//                        );
//                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
//                        finish();
//                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                    }else {
//                        Toast.makeText(getApplicationContext(),response.message() , Toast.LENGTH_SHORT).show();
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                t.printStackTrace();
//
//                //Response failed
//                Log.e(TAG, "error: " + t.getMessage());
//            }
//        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setContent();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
            }
        });
        btnRedirectLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.footapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.footapp.Domain.StateMessage;
import com.example.footapp.Domain.User;
import com.example.footapp.R;
import com.example.footapp.remote.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
//    private String TAG = LoginActivity.class.getSimpleName();
//    EditText username;
//    EditText password;
//    ImageView loginButton;
//    ApiService apiService;
//    TextView registerButton;
//
//    void setContent(){
//        username = findViewById(R.id.etUsername);
//        password = findViewById(R.id.etPassword);
//        loginButton = findViewById(R.id.login_button);
//        registerButton = findViewById(R.id.register_new);
//    }
//    void doLogin(){
//        if(TextUtils.isEmpty(username.getText().toString())){
//            username.setError("Please enter your username");
//            username.requestFocus();
//            return;
//        }
//        if(TextUtils.isEmpty(password.getText().toString())){
//            password.setError("Please enter your password");
//            password.requestFocus();
//            return;
//        }
//        apiService = UserClient.getInstance();
//        Call<LoginResponse> call =  apiService.login(username.getText().toString().trim(), password.getText().toString().trim());
//        call.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                try {
//                    if(response.isSuccessful()){
//                        if(response.body().isError()==false){
//                            Log.i(TAG, "Response: " + response.body());
//                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
//                            LoginResponse userLogin = response.body();
//                            assert userLogin != null;
////                            User user = new User(
////                                    userLogin.getUser().getId(),
////                                    userLogin.getUser().getName(),
////                                    userLogin.getUser().getEmail(),
////                                    userLogin.getUser().getGender(),
////                                    userLogin.getUser().getImages()
////                            );
//                            User user = new User(
//                                    userLogin.getUser().getId(),
//                                    userLogin.getUser().getUsername(),
//                                    userLogin.getUser().getName(),
//                                    userLogin.getUser().getEmail(),
//                                    userLogin.getUser().getGender(),
//                                    userLogin.getUser().getImages()
//                            );
//                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
//                            finish();
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent);
//                        }
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
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        setContent();
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                doLogin();
//            }
//        });
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
    EditText editTextEmail;
    EditText editTextPassword;
    ImageView btnLogin;

    TextView registerButton;

    public static User user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testLogin();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void testLogin() {
        final String username = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            editTextEmail.setError("Please enter your username");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

//        if (editTextPassword.equals("123")){
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            return;
//        }
        ApiService.apiService.getAccount(username, password).enqueue(new Callback<StateMessage>() {
            @Override
            public void onResponse(Call<StateMessage> call, Response<StateMessage> response) {
                Toast.makeText(LoginActivity.this, "Call User api success", Toast.LENGTH_SHORT).show();
                StateMessage stateMessage = response.body();

                if(stateMessage != null){
                    Log.e("User",stateMessage.getUser().toString());
                    Log.e("Id", Integer.toString(stateMessage.getUser().getId()));
                    Log.e("Username", stateMessage.getUser().getUsername());
                    Log.e("Name", stateMessage.getUser().getName());
                    Log.e("Email", stateMessage.getUser().getEmail());
                    Log.e("Gender", stateMessage.getUser().getGender());
                    Log.e("Images", stateMessage.getUser().getImages());
                    user = new User(stateMessage.getUser().getId(),
                            stateMessage.getUser().getUsername(),
                            stateMessage.getUser().getName(),
                            stateMessage.getUser().getEmail(),
                            stateMessage.getUser().getGender(),
                            stateMessage.getUser().getImages());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userObject", stateMessage.getUser());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Wrong Account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StateMessage> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Call api error", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void initView(){
        editTextEmail = findViewById(R.id.etUsername);
        editTextPassword =  findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_new);
    }

}

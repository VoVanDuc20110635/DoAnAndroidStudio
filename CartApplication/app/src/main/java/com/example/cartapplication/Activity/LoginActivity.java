package com.example.cartapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.AccountService;
import com.example.cartapplication.Service.CartService;
import com.example.cartapplication.Service.UserService;
import com.example.cartapplication.model.Account;
import com.example.cartapplication.model.Cart;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    Button btnLogin;

    TextView registerButton;
    SharedPreferences sharedPreferences;

    public User thisuser = new User();

    public Account account = new Account();

    public Cart cart = new Cart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testLogin();
//                if (account != null)
//                {
//                    Log.e("loi","account ton tai");
//                    getUserFromAccount();
//                }
//                else {
//                    Log.e("loi","account khong ton tai");
//                }
//                addInformationToSharedPreference();
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

        AccountService accountService = ApiClient.getApiClient().create(AccountService.class);
        Call<Account> call = accountService.authenticate(username, password);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    account = response.body();
                    Log.e("id", Integer.toString(account.getId()));
                    Log.e("accountName: ", account.getAccountName());
                    Log.e("password: ", account.getPassword());
                    Log.e("createDate: ", account.getCreatedDate().toString());
                    Log.e("roleNumber:", Integer.toString(account.getRoleNumber()));
                    Log.e("status: ", Integer.toString(account.getStatus()));
                    if (account.getPasswordResetToken() != null) {
                        Log.e("passwordResetToken: ", account.getPasswordResetToken());
                    }
                    Call<User> call2 = accountService.findUserByAccountId(account.getId());
                    call2.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                thisuser = response.body();
                                Log.e("ten user: ", thisuser.getName());
                                CartService cartService = ApiClient.getApiClient().create(CartService.class);
                                Call<Cart> call3 =cartService.getUserCart(thisuser.getId());
                                call3.enqueue(new Callback<Cart>() {
                                    @Override
                                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                                        if (response.isSuccessful() && response.body() != null){
                                            cart = response.body();
                                            try {
                                                Log.e("cart cua userName: ", cart.getUser().getName());
                                            }
                                            catch (Exception e){
                                                Log.e("loi:", "khong lay duoc cart");
                                            }
                                            if (cart == null){
                                                Toast.makeText(LoginActivity.this, "Cart ko lay duoc keke", Toast.LENGTH_SHORT).show();
                                            }
                                            addInformationToSharedPreference();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Cart> call, Throwable t) {
                                        Log.e("Lỗi", "Không kết được API", t);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("Lỗi", "Không kết được API", t);
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e("Lỗi", "Không kết được API", t);
            }
        });

    }
    private void initView(){
        editTextEmail = findViewById(R.id.etUsername);
        editTextPassword =  findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_new);
    }
    private void getUserFromAccount(){
        AccountService accountService = ApiClient.getApiClient().create(AccountService.class);
        Call<User> call = accountService.findUserByAccountId(account.getId());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    thisuser = response.body();
                    Log.e("ten user: ", thisuser.getName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Lỗi", "Không kết được API", t);
            }
        });
//        Log.e("accoutn id ", String.valueOf( account.getId()));
//        Retrofit retrofit = ApiClient.getApiClient();
//        UserService userService = retrofit.create(UserService.class);
//        Call<User> call = userService.getUser(account.getId());
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful()) {
//                    User user = response.body();
//                    // xử lý dữ liệu user
//                    String nameuser = user.getName();
//                    thisuser=user;
//                    Log.e("username", thisuser.getName());
//                } else {
//                    // xử lý lỗi khi response không thành công
//                    Toast.makeText(LoginActivity.this, "User trống", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                // xử lý lỗi khi gọi API thất bại
//            }
//        });
    }

    private void getCartFromUser(){
        CartService cartService = ApiClient.getApiClient().create(CartService.class);
        Call<Cart> call3 =cartService.getUserCart(thisuser.getId());
        call3.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful() && response.body() != null){
                    cart = response.body();
                    try {
                        Log.e("cart cua userName: ", cart.getUser().getName());
                    }
                    catch (Exception e){
                        Log.e("loi:", "khong lay duoc cart");
                    }
                    if (cart == null){
                        Toast.makeText(LoginActivity.this, "Cart ko lay duoc keke", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.e("Lỗi", "Không kết được API", t);
            }
        });
    }
    private void addInformationToSharedPreference(){
        //tương tự như session bên web, sử dụng sharedPreferences
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // vì SharedPreferences chỉ lưu những cặp giá trị chuối String nên phải đổi các Object thành chuỗi json
        // muốn sài thì đổi lại keke
        Gson gson = new Gson();
        String accountJson = gson.toJson(account);
        String userJson = gson.toJson(thisuser);
        String cartJson = gson.toJson(cart);
        editor.putString("account", accountJson);
        editor.putString("user", userJson);
        editor.putString("User_id", String.valueOf(account.getId()));
        editor.putString("cart", cartJson);
        editor.commit();

        if (thisuser != null && account != null){
            Log.e("thong bao", "ca user va account ton tai " + thisuser.getName() + " " + account.getId());
        }

        Intent intent = new Intent(LoginActivity.this, Product_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("account", (Serializable) account);
        bundle.putSerializable("user", (Serializable) thisuser);
        bundle.putSerializable("User_id", account.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
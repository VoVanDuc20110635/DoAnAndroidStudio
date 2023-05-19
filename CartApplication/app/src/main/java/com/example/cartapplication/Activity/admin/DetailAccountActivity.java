package com.example.cartapplication.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.UserService;
import com.example.cartapplication.model.User;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailAccountActivity extends AppCompatActivity {

    private TextView mNameTextView;
    private TextView mSexTextView;
    private TextView mAddressTextView;
    private TextView mZipTextView;
    private TextView mEmailTextView;
    private TextView mPhoneTextView;
    private TextView mRoleTextView;

    private ImageView backButton;
    private Button mHistoryButton;
    private Button mChangeButton;

    private RadioGroup radioGroup;
    private RadioButton shipper, customer;
    private int role = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_account);

        // Khởi tạo các đối tượng TextView và Button
        mNameTextView = findViewById(R.id.user_name);
        mSexTextView = findViewById(R.id.user_sex);
        mAddressTextView = findViewById(R.id.user_address);
        mZipTextView = findViewById(R.id.user_zip);
        mEmailTextView = findViewById(R.id.user_email);
        mPhoneTextView = findViewById(R.id.user_phone_number);
        mRoleTextView = findViewById(R.id.user_role);
        mHistoryButton = findViewById(R.id.history_button);
        mChangeButton = findViewById(R.id.button3);
        backButton = findViewById(R.id.backButtonUser);
        radioGroup = findViewById(R.id.radioGroup);
        customer = findViewById(R.id.Customer);
        shipper = findViewById(R.id.shipper);

        // Lấy Intent và dữ liệu người dùng từ intent
        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("User");

        // Hiển thị dữ liệu người dùng lên TextView và Button
        mNameTextView.setText(user.getName());
        mSexTextView.setText(user.getSex());
        mAddressTextView.setText(user.getAddress());
        mZipTextView.setText(String.valueOf(user.getZip()));
        mEmailTextView.setText(user.getEmail());
        mPhoneTextView.setText(user.getPhoneNumber());
        if (user.getAccount().getRoleNumber() == 1){
            mRoleTextView.setText("Admin");
        }
        else if(user.getAccount().getRoleNumber() == 2){
            mRoleTextView.setText("Shipper");
        }
        else{
            mRoleTextView.setText("Customer");
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // Thiết lập sự kiện click cho nút Lịch sử mua hàng
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Xử lý sự kiện khi click vào nút Lịch sử mua hàng
                Intent intent1 = new Intent(DetailAccountActivity.this, HistoryOrderUser.class);
                intent1.putExtra("User", user);
                startActivity(intent1);
            }
        });

        // Thiết lập sự kiện click cho nút Change
        mChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Xử lý sự kiện khi click vào nút Change
                Retrofit retrofit = ApiClient.getApiClient();
                UserService userService = retrofit.create(UserService.class);



                // Lắng nghe sự kiện khi RadioButton được chọn


// Kiểm tra RadioButton nào đang được chọn khi cần thiết
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == shipper.getId()) {
                    role = 2;
                    // RadioButton "shipper" đang được chọn
                } else if (selectedId == customer.getId()) {
                    // RadioButton "customer" đang được chọn
                    role = 3;
                } else {
                    role = 3;
                }
                Call<ResponseBody> call = userService.editRole(user.getId(), role);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            try {
                                Toast.makeText(DetailAccountActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                                user.getAccount().setRoleNumber(role);
                                intent.putExtra("User", user);
                                recreate();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }
}
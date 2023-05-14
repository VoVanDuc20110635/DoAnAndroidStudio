package com.example.cartapplication.Activity;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.example.cartapplication.APIClient.ApiClient;
        import com.example.cartapplication.R;
        import com.example.cartapplication.Service.OrderService;
        import com.example.cartapplication.Service.UserService;
        import com.example.cartapplication.model.User;
        import com.google.gson.Gson;

        import java.util.ArrayList;

        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;

public class OrderActivity extends AppCompatActivity {

    private EditText addressEditText;
    private EditText zipEditText;
    private EditText cityEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private TextView totalEditText;
    private Spinner paymentSpinner;
    private Button saveButton;
    private String[] paymentmethodsArray = {"Không hiển thị", "Thanh toán COD", "Thanh toán qua thẻ ngân hàng"};
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Lấy các thành phần trong layout
        addressEditText = findViewById(R.id.address_edit_text);
        zipEditText = findViewById(R.id.zip_edit_text);
        cityEditText = findViewById(R.id.city_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        totalEditText = findViewById(R.id.total_edit_text);
        paymentSpinner = findViewById(R.id.payment_spinner);
        saveButton = findViewById(R.id.save_button);

        // Thiết lập danh sách cho Spinner
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentmethodsArray);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentSpinner.setAdapter(paymentAdapter);

        // Xử lý sự kiện nhấn nút Save
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giá trị từ các EditText và Spinner
                String address = addressEditText.getText().toString();
                int zip = Integer.parseInt(zipEditText.getText().toString());
                String city = cityEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                int paymentMethod = (int) paymentSpinner.getSelectedItemId();
                if(paymentMethod == 0){
                    Toast.makeText(OrderActivity.this, "Chưa chọn hình thức thanh toán", Toast.LENGTH_SHORT).show();

                }
                else{
                    sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                    Gson gson = new Gson();
                    String userJson = sharedPreferences.getString("user", "");
                    User user = gson.fromJson(userJson, User.class);

                    Retrofit retrofit = ApiClient.getApiClient();
                    OrderService orderService = retrofit.create(OrderService.class);

                    Call<ResponseBody> call = orderService.placeOrder(user.getId(), phone, email, address, city, zip, paymentMethod);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(OrderActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(OrderActivity.this, Product_Activity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }

                // Lưu trữ dữ liệu vào SharedPreferences


                // Hiển thị thông báo
                Toast.makeText(OrderActivity.this, "Order saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
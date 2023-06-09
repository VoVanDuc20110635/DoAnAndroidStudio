package com.example.cartapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.FeedbackAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.CartService;
import com.example.cartapplication.Service.FeedbackService;
import com.example.cartapplication.model.Feedback;
import com.example.cartapplication.model.Product;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailProductActivity extends AppCompatActivity {
    private ImageView productImageView;
    private TextView productNameTextView;
    private TextView productDescriptionTextView;
    private TextView productPriceTextView;
    private TextView productPriceTextView2;
    private TextView productQuantityTextView;
    private TextView productSoldTextView;
    private RatingBar productRatingBar;
    private ImageButton buttonPlus;
    private ImageButton buttonMinus;
    private EditText quantityEditText;
    private int MAX_QUANTITY = 0;
    private RecyclerView feedbackRecyclerView;
    private FeedbackAdapter feedbackAdapter;
    private List<Feedback> feedbackList;
    private SharedPreferences sharedPreferences;
    private Button addToCartButton;
    private EditText feedbackText;
    private ImageView sendFeedback;
    private ImageView backButton;

    //private ApiClient apiClient;
    //private String phanhoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);


        productImageView = findViewById(R.id.product_image);
        productNameTextView = findViewById(R.id.product_name);
        productDescriptionTextView = findViewById(R.id.product_description);
        productPriceTextView = findViewById(R.id.product_price);
        productPriceTextView2 = findViewById(R.id.price2);
        productQuantityTextView = findViewById(R.id.product_quantity);
        productSoldTextView = findViewById(R.id.product_sold);
        productRatingBar = findViewById(R.id.product_rating);
        addToCartButton = findViewById(R.id.add_to_cart_button);
        sendFeedback = findViewById(R.id.send_feedback_button);
        feedbackText = findViewById(R.id.feedback_edit_text);
        backButton = findViewById(R.id.backButtonDetail);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("Product");
        if (product != null) {
            Picasso.get().load(product.getImage()).into(productImageView);
            productNameTextView.setText(product.getProductName());
            productDescriptionTextView.setText(product.getDescription());
            if (true) {
                productPriceTextView.setText(String.format("%,.0f đồng", product.getPrice() - (product.getDiscount() * product.getPrice() / 100)));
                if (product.getDiscount() > 0) {
                    productPriceTextView2.setText(String.format("%,.0f đồng", product.getPrice()));
                    productPriceTextView2.setPaintFlags(productPriceTextView2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                productQuantityTextView.setText(String.format("Số lượng: %d", product.getQuantity()));
                MAX_QUANTITY = product.getQuantity();
                productSoldTextView.setText(String.format("Đã bán: %d", product.getSold()));
                productRatingBar.setRating(product.getSold() * 5 / (product.getQuantity() + product.getSold()));
                //Toast.makeText(DetailProductActivity.this, "giảm giá:"+product.getDiscount(), Toast.LENGTH_SHORT).show();
            } else {
                productNameTextView.setText("Error 404");
                productDescriptionTextView.setText("Error 404");
            }
        }
        // Ánh xạ các thành phần trong layout
        buttonPlus = findViewById(R.id.button_plus);
        buttonMinus = findViewById(R.id.button_minus);
        quantityEditText = findViewById(R.id.quantity_text_view);

        // Đặt sự kiện click cho nút tăng số lượng sản phẩm
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(String.valueOf(quantityEditText.getText()));
                // Tăng số lượng sản phẩm đến giới hạn tối đa nếu có
                if (quantity < MAX_QUANTITY) {
                    quantity++;
                    quantityEditText.setText(String.valueOf(quantity));
                } else {
                    // Hiển thị thông báo lỗi nếu vượt quá giới hạn tối đa
                    Toast.makeText(DetailProductActivity.this, "Số lượng sản phẩm đã đạt tối đa!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Đặt sự kiện click cho nút giảm số lượng sản phẩm
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Giảm số lượng sản phẩm đến 1 nếu chưa đạt giới hạn tối thiểu
                int quantity = Integer.parseInt(String.valueOf(quantityEditText.getText()));
                if (quantity > 1) {
                    quantity--;
                    quantityEditText.setText(String.valueOf(quantity));
                } else {
                    // Hiển thị thông báo lỗi nếu đã đạt giới hạn tối thiểu
                    Toast.makeText(DetailProductActivity.this, "Số lượng sản phẩm đã đạt tối thiểu!", Toast.LENGTH_SHORT).show();
                }
            }
        });

//lấy feedback
        Retrofit retrofit = ApiClient.getApiClient();
        //String phanhoi="";
        feedbackList=new ArrayList<>();
        //phanhoi="";
        //feedbackList.add(new Feedback(0,"Rỗng"));
        FeedbackService feedbackService = retrofit.create(FeedbackService.class);
        Call call=feedbackService.getAllfeedback(product.getId());
        call.enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    //Log.e("Lỗi:","đã chạy");
                    feedbackList.addAll(response.body());
                    //Log.e("Đầu tiên:",String.valueOf(feedbackList.get(0).getContent()));
                    //Log.d("PHản hồi thông tin",String.valueOf(response.body().toString()));
                    //phanhoi=response.body().toString();
                    // TODO: Xử lý danh sách feedback
                    feedbackRecyclerView = findViewById(R.id.feedback_recycler_view);
                    feedbackRecyclerView.setLayoutManager(new LinearLayoutManager(DetailProductActivity.this));
                    feedbackAdapter = new FeedbackAdapter(feedbackList);

                    // Thiết lập Adapter cho RecyclerView
                    feedbackRecyclerView.setAdapter(feedbackAdapter);
                } else {
                    // TODO: Xử lý lỗi
                    Log.e("Lỗi:","Lỗi");
                    feedbackList.add(new Feedback(0,"Rỗng"));
                    Log.e("Lỗi:","Lỗi x2");
                    //phanhoi="Sai rồi";
                }
            }

            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {
                // TODO: Xử lý lỗi
                Log.e("Lỗi",t.toString());

            }
        });


        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString("user", "");
        User user = gson.fromJson(userJson, User.class);
//        Log.e("dc",user.getName());

        // Khởi tạo Retrofit
//        Retrofit retrofit = ApiClient.getApiClient();
        CartService cartService = retrofit.create(CartService.class);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call1 = cartService.addToCart(user.getId(),product.getId(),Integer.parseInt(quantityEditText.getText().toString()));
                //Toast.makeText(DetailProductActivity.this, String.valueOf(Integer.parseInt(quantityEditText.getText().toString())), Toast.LENGTH_SHORT).show();
                if(Integer.parseInt(quantityEditText.getText().toString()) > 0 && Integer.parseInt(quantityEditText.getText().toString()) < MAX_QUANTITY){
                call1.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(DetailProductActivity.this, "Đã thêm sản phẩm "+product.getProductName(), Toast.LENGTH_SHORT).show();
                            Intent intent = getIntent();
                            if(intent.getBooleanExtra("FromCart",false)){
                                intent = new Intent(DetailProductActivity.this, CartActivity.class);
                            }
                            else{
                                intent = new Intent(DetailProductActivity.this, Product_Activity.class);
                            }
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });}
                else{
                    Toast.makeText(DetailProductActivity.this, "So luong khong cho phep", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call2 = feedbackService.sentfeedback(user.getId(),feedbackText.getText().toString(),product.getId());
                call2.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(DetailProductActivity.this, "Bạn đã bình luận", Toast.LENGTH_SHORT).show();
                            recreate();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

        //Log.d("Thông tin", String.valueOf(feedbackList));
        //Log.d("id",String.valueOf(product.getId()));
        //Log.d("phản hồi",phanhoi);
        // Khởi tạo RecyclerView và Adapter

    }
}
package com.example.cartapplication.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Activity.DetailProductActivity;
import com.example.cartapplication.Adapter.FeedbackAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.FeedbackService;
import com.example.cartapplication.Service.ProductService;
import com.example.cartapplication.model.Feedback;
import com.example.cartapplication.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminDetailProduct extends AppCompatActivity {
    private ImageView backButton, pictureVIew;
    private RecyclerView feedbackRecyclerView;
    private EditText productName, productDes, productPrice, productQuan;
    private TextView productSold;
    private Spinner spinner;
    private Button saveButton;
    private List<String> spinnerList = new ArrayList<>();
    private List<Feedback> feedbackList = new ArrayList<>();

    private Product product;
    private FeedbackAdapter feedbackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_product);

        backButton = findViewById(R.id.backButtonDetail);
        spinner = findViewById(R.id.productstatus);
        productName = findViewById(R.id.product_name);
        productDes = findViewById(R.id.product_description);
        productQuan = findViewById(R.id.product_quantity);
        productPrice = findViewById(R.id.product_price);
        productSold = findViewById(R.id.product_sold);
        pictureVIew = findViewById(R.id.product_image);
        saveButton = findViewById(R.id.saveproductbtn);

        spinnerList.add("Không cho phép bán");
        spinnerList.add("Cho phép bán");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);

// Thiết lập kiểu dropdown cho Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Thiết lập adapter cho Spinner
        spinner.setAdapter(adapter);

        product = (Product)getIntent().getSerializableExtra("Product");

        productName.setText(product.getProductName());
        productDes.setText(product.getDescription());
        productPrice.setText(String.valueOf((int)product.getPrice()));
        productQuan.setText(String.valueOf(product.getQuantity()));
        productSold.setText(String.valueOf(product.getSold()));
        Picasso.get().load(product.getImage()).into(pictureVIew);
        spinner.setSelection(product.getStatus());

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
                    feedbackRecyclerView.setLayoutManager(new LinearLayoutManager(AdminDetailProduct.this));
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
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductService productService = retrofit.create(ProductService.class);
                Call<ResponseBody> call = productService.editProduct(productName.getText().toString(),
                        Integer.parseInt(productQuan.getText().toString()),
                        Integer.parseInt(productPrice.getText().toString()), productDes.getText().toString(), product.getId(),
                        (int)spinner.getSelectedItemId() );
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(AdminDetailProduct.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            product.setProductName(productName.getText().toString());
                            product.setQuantity(Integer.parseInt(productQuan.getText().toString()));
                            product.setPrice(Integer.parseInt(productPrice.getText().toString()));
                            product.setDescription(productDes.getText().toString());
                            product.setStatus((int) spinner.getSelectedItemId());
                            intent.putExtra("Product",product);
                            recreate();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
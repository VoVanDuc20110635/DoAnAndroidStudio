package com.example.cartapplication.Activity.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.CategoryspinnerAdapter;
import com.example.cartapplication.Adapter.FlavorSpinnerAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.CategoryService;
import com.example.cartapplication.Service.FlavorService;
import com.example.cartapplication.Service.ProductService;
import com.example.cartapplication.model.Category;
import com.example.cartapplication.model.Flavor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddNewProductActivity extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView backButton;
    private EditText productName;
    private EditText productDescription;
    private EditText productPrice;
    private EditText productQuantity;
    private Spinner categorySpinner;
    private Spinner flavorSpinner;
    private Button saveProductButton;
    private List<Flavor> flavors;
    private List<Category> categories;
    private Retrofit retrofit;
    private ImageView image;
    private Cloudinary cloudinary;
    private String cloudinaryUrl;
    private String imageurl;
    private ProgressBar middlesc;

    private void laydanhsachflavorvacate() {
        loadcate();
        loadflavor();
    }

    private void loadflavor() {
        FlavorService flavorService = retrofit.create(FlavorService.class);
        Call<List<Flavor>> callflavor = flavorService.getAllFlavor();
        callflavor.enqueue(new Callback<List<Flavor>>() {
            @Override
            public void onResponse(Call<List<Flavor>> call, Response<List<Flavor>> response) {
                flavors = new ArrayList<>();
                flavors = response.body();

                FlavorSpinnerAdapter flavorAdapter = new FlavorSpinnerAdapter(AddNewProductActivity.this, flavors);

                flavorSpinner.setAdapter(flavorAdapter);
                // Xử lý danh sách category ở đây
                //Tạo adaptor cho cate

            }

            @Override
            public void onFailure(Call<List<Flavor>> call, Throwable t) {

            }


        });
    }

    private void loadcate() {
        CategoryService apiInterface = retrofit.create(CategoryService.class);
        Call<List<Category>> call = apiInterface.getAllCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = new ArrayList<>();
                categories = response.body();
                Log.e("danh sách", response.body().toString());

                CategoryspinnerAdapter categoryAdapter = new CategoryspinnerAdapter(AddNewProductActivity.this, categories);
                categorySpinner.setAdapter(categoryAdapter);
                // Xử lý danh sách category ở đây
                //Tạo adaptor cho cate

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }


        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        // Initialize UI components

        backButton = findViewById(R.id.backButtonDetail);
        productName = findViewById(R.id.product_name);
        productDescription = findViewById(R.id.product_description);
        productPrice = findViewById(R.id.product_price);
        productQuantity = findViewById(R.id.product_quantity);
        categorySpinner = findViewById(R.id.categoryspinner);
        flavorSpinner = findViewById(R.id.flavorspinner);
        saveProductButton = findViewById(R.id.saveproductbtn);
        image = findViewById(R.id.product_image);
        middlesc = findViewById(R.id.progressBar);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

//Load spinner
        retrofit = ApiClient.getApiClient();
        laydanhsachflavorvacate();


        // Set up back button listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Set up save product button listener
        //nút thêm product
        Map config = new HashMap();
        config.put("cloud_name", "dtbzxqbkh");
        config.put("api_key", "916471543855334");
        config.put("api_secret", "klSKAQh1cuyOswfFRdq0pghm1Lg");
        cloudinary = new Cloudinary(config);
        saveProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (image.getDrawable() != null) {
                    // Save product data to database
                    String name = productName.getText().toString();
                    String description = productDescription.getText().toString();
                    int price = Integer.parseInt(productPrice.getText().toString());
                    int quantity = Integer.parseInt(productQuantity.getText().toString());
                    int category = ((Category) categorySpinner.getSelectedItem()).getId();
                    int flavor = ((Flavor) flavorSpinner.getSelectedItem()).getId();
                    //String image = "";
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    middlesc.setVisibility(View.VISIBLE);
                    uploadImage();
                    middlesc.setVisibility(View.GONE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Log.e("Up xong", "Chạy tiếp");
                    Retrofit retrofit = ApiClient.getApiClient();
                    ProductService productService = retrofit.create(ProductService.class);
                    Call<ResponseBody> call = productService.addProduct(name, quantity, price, category, flavor, description, imageurl);
//                    Log.e("tên",name);
//                    Log.e("Mô tả",description);
//                    Log.e("Link",imageurl);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.e("Gọi api", "gọi xong");
                            if (response.isSuccessful()) {
                                try {
                                    Toast.makeText(AddNewProductActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                                    Log.e("Gọi api", "lưu được data");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                // Finish activity
                                finish();
                            } else {
                                try {
                                    Log.e("Lỗi", response.raw() + " " + String.valueOf(response.code()));
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("Lỗi", t.toString());
                        }
                    });


                } else {
                    Toast.makeText(AddNewProductActivity.this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    // Khởi chạy activity chọn ảnh
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Xử lý kết quả trả về từ activity chọn ảnh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image.setImageURI(selectedImageUri);
        }
    }

    // Tải lên ảnh lên Cloudinary khi nhấn nút "Thêm"
    private String uploadImage() {
        if (image.getDrawable() != null) {
            Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageData = baos.toByteArray();
            //ProgressDialog progressDialog = new ProgressDialog(AddNewProductActivity.this);
            CountDownLatch latch = new CountDownLatch(1);

            try {

                UploadTask uploadTask = new UploadTask(imageData, latch);
                uploadTask.execute();
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                Log.e("Chờ","Chờ xong rồi");

                //Map uploadResult = cloudinary.uploader().upload(imageData, ObjectUtils.emptyMap());
                cloudinaryUrl = uploadTask.secureUrl;
                return cloudinaryUrl;
                //Toast.makeText(this, "Upload successful", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                //e.printStackTrace();
                Log.e("lỗi úp hình", e.getMessage().toString());
                Toast.makeText(this, "Upload failed", Toast.LENGTH_SHORT).show();
                return "";
            } finally {

            }

        }
        return "";
    }

    private class UploadTask extends AsyncTask<Void, Void, Map<String, Object>> {
        private byte[] imageData;
        protected String secureUrl;
        //protected String url;
        private CountDownLatch latch;

        public UploadTask(byte[] imageData, CountDownLatch latch) {
            this.imageData = imageData;
            this.latch = latch;
        }

        @Override
        protected Map<String, Object> doInBackground(Void... params) {
            try {
                Map<String, Object> options = new HashMap<>();
                //options.put("public_id", "my_image");

                Map<String, Object> response = cloudinary.uploader().upload(imageData, options);
//                Log.e("Thành công", "Up được ảnh");
                latch.countDown();
                imageurl = (String) (response.get("url"));
//                Log.e("Đường dẫn",imageurl);

                return response;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Lỗi", "không up được");
                return null;
            }
        }

        @Override
        protected void onPostExecute(Map<String, Object> uploadResult) {
            super.onPostExecute(uploadResult);
            if (uploadResult != null) {
                secureUrl = (String) uploadResult.get("secure_url");
//                Log.d("Upload success", "Image URL: " + secureUrl);
                // Xử lý kết quả upload ảnh tại đây
            } else {
                // Xử lý lỗi tại đây
//                Log.e("Upload failed", "Error uploading image");
            }

        }
    }


}

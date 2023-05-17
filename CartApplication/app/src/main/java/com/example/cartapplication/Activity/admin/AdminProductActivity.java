package com.example.cartapplication.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.CategoryAdapter;
import com.example.cartapplication.Adapter.FlavorAdapter;
import com.example.cartapplication.Adapter.FlavorSpinnerAdapter;
import com.example.cartapplication.Adapter.ProductAdminAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.CategoryService;
import com.example.cartapplication.Service.FlavorService;
import com.example.cartapplication.Service.ProductService;
import com.example.cartapplication.model.Category;
import com.example.cartapplication.model.Flavor;
import com.example.cartapplication.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminProductActivity extends AppCompatActivity {
    private ProductAdminAdapter productAdapter;
    private List<Product> productList;
    private RecyclerView recyclerView;
    private EditText textName;
    private EditText textSearch;
    private ImageView imageButton;
    private ArrayList<Integer> imageList;
    private int currentImageIndex=-1;
    private View popUpView;
    private ImageView backbutton;
    private Button addproduct;


    private void loadProducts() {
        // Đọc dữ liệu từ JSON và thêm vào productList
        // ...
        ProductService productService = ApiClient.getApiClient().create(ProductService.class);
        Call<List<Product>> call = productService.getProductList();
        call.enqueue(new Callback<List<Product>>() {
//            private ProductAdminAdapter productAdapter;
//            private List<Product> productList;

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.addAll(response.body());
                    productAdapter.updateList(productList);
                    productAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Lỗi", "Failed to fetch data", t);
            }
        });
        // Cập nhật Adapter
        productAdapter.notifyDataSetChanged();
    }

    private void searchProducts(String searchText) {
        // Tạo một danh sách mới để lưu các sản phẩm được lọc
        List<Product> filteredList = new ArrayList<>();

        // Duyệt qua tất cả các sản phẩm trong danh sách sản phẩm
        for (Product product : productList) {
            // Nếu tên sản phẩm chứa searchText thì thêm sản phẩm đó vào danh sách mới
            if (product.getProductName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(product);
            }
        }

        // Cập nhật Adapter với danh sách sản phẩm mới
        productAdapter.updateList(filteredList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        recyclerView = findViewById(R.id.product_list);
        productList = new ArrayList<>();
        productAdapter = new ProductAdminAdapter(productList, this);

//        String userJson = sharedPreferences.getString("user", "");
//        thisUser = getUserFromUserJson(userJson);

        // Thiết lập LayoutManager và Adapter cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);




        // Load dữ liệu từ JSON và cập nhật Adapter
        loadProducts();

        addproduct= findViewById(R.id.addproductbtn);
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminProductActivity.this,AddNewProductActivity.class);
                startActivity(intent);
            }
        });




        //Chức năng tìm kiếm
        textSearch = findViewById(R.id.textSearch);
        ImageView searchButton = findViewById(R.id.searchbutton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = textSearch.getText().toString().trim();
                searchProducts(searchText);
            }
        });

        imageButton = findViewById(R.id.imageButton);
        backbutton =findViewById(R.id.backbutton_admin);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        

        imageList = new ArrayList<>();
        imageList.add(R.drawable.image_fillabc);
        imageList.add(R.drawable.image_fillabc1);
        imageList.add(R.drawable.image_fillprice);
        imageList.add(R.drawable.image_fillprice1);
        imageList.add(R.drawable.image_filldate);
        imageList.add(R.drawable.image_filldate1);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex + 1) % imageList.size();
                imageButton.setImageResource(imageList.get(currentImageIndex));
                switch (currentImageIndex) {
                    case 0:
                        // Sắp xếp sản phẩm theo tên tăng dần
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o1.getProductName().compareTo(o2.getProductName());
                            }
                        });
                        productAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        // Sắp xếp sản phẩm theo tên giảm dần
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o2.getProductName().compareTo(o1.getProductName());
                            }
                        });
                        productAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        // Sắp xếp sản phẩm theo giá giảm dần
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return Double.compare(o2.getPrice(), o1.getPrice());
                            }
                        });
                        productAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        // Sắp xếp sản phẩm theo giá tăng dần
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return Double.compare(o1.getPrice(), o2.getPrice());
                            }
                        });
                        productAdapter.notifyDataSetChanged();
                        break;
                    case 4:
                        // Sắp xếp sản phẩm theo ngày tăng dần
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                Date a1 = o1.getCreatedDate();
                                Date a2 = o2.getCreatedDate();
                                if (a1 == null)
                                    return -1;
                                if (a2 == null)
                                    return 1;
                                return o1.getCreatedDate().compareTo(o2.getCreatedDate());
                            }
                        });
                        productAdapter.notifyDataSetChanged();
                        break;
                    case 5:
                        // Sắp xếp sản phẩm theo ngày giảm dần
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                Date a1 = o1.getCreatedDate();
                                Date a2 = o2.getCreatedDate();
                                if (a1 == null)
                                    return -1;
                                if (a2 == null)
                                    return 1;
                                return o2.getCreatedDate().compareTo(o1.getCreatedDate());
                            }
                        });
                        productAdapter.notifyDataSetChanged();
                        break;
                }
                productAdapter.updateList(productList);
            }
        });
        //cửa số pop up

//        closePopupButton=findViewById(R.id.closepopup);
//        //checkPopupWindowStatus(popupWindow);
//        closePopupButton = findViewById(R.id.closepopup);
//        closePopupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(popupWindow != null && popupWindow.isShowing()) {
//                    popupWindow.dismiss(); // đóng cửa sổ popup
//                    checkPopupWindowStatus(popupWindow);
//                }
//            }
//        });
//        checkPopupWindowStatus(popupWindow);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                // Xử lý khi cửa sổ popup bị tắt
//                checkPopupWindowStatus(popupWindow);
//            }
//        });


        //Danh sách cate và flavor
        List<Category> categoryList = new ArrayList<>();
        List<Flavor> flavorList = new ArrayList<>();
        //// Thêm các đối tượng Category vào danh sách ở đây
        CategoryService apiInterface = ApiClient.getApiClient().create(CategoryService.class);
        FlavorService flavorService = ApiClient.getApiClient().create(FlavorService.class);
        Call<List<Category>> call = apiInterface.getAllCategories();
        Call<List<Flavor>> callflavor = flavorService.getAllFlavor();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categoryList = response.body();
                // Xử lý danh sách category ở đây
                //Tạo adaptor cho cate
                CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }


        });
        callflavor.enqueue(new Callback<List<Flavor>>() {
            @Override
            public void onResponse(Call<List<Flavor>> call, Response<List<Flavor>> response) {
                List<Flavor> flavorlist = response.body();
                // Xử lý danh sách category ở đây
                //Tạo adaptor cho cate
                FlavorAdapter flavorAdapter = new FlavorAdapter(flavorlist);
                //RecyclerView flavorview = popUpView.findViewById(R.id.flavorlist);
                //flavorview.setLayoutManager(new LinearLayoutManager(AdminProductActivity.this));
                //flavorview.setAdapter(flavorAdapter);
            }

            @Override
            public void onFailure(Call<List<Flavor>> call, Throwable t) {

            }


        });





        // Lắng nghe sự kiện thay đổi dữ liệu của adapter
        productAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

            }
        });





        
    }
}
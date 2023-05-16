package com.example.cartapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.CartProductAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.ProductService;
import com.example.cartapplication.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductbyCateorFlavorActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartProductAdapter adapter;

    private List<Product> productList;
    private TextView textView;
    private View imageView3;
    private TextView textSearch;
    private ImageButton imageButton;
    private ArrayList<Object> imageList;
    private int currentImageIndex = 0;
    private ImageView backbutton;
    private TextView emptyView;

    private void checkEmptyView() {
        if (productList == null) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        if (productList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
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
        adapter.updateList(filteredList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productby_cateor_flavor);
        //
        textView = findViewById(R.id.qltk);
        textView.setText(getIntent().getStringExtra("name"));
        String corf = getIntent().getStringExtra("CorF").toString();
        //Toast.makeText(this, corf, Toast.LENGTH_LONG).show();
        // Lấy CategoryId từ Intent
        if (corf.equals("C")) {

            int categoryId = getIntent().getIntExtra("CategoryId", -1);

            // Lấy danh sách sản phẩm theo CategoryId
            ProductService productService = ApiClient.getApiClient().create(ProductService.class);
            Call<List<Product>> call = productService.getProductCategory(categoryId);
            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    productList = response.body();

                    // Thiết lập RecyclerView và adapter
                    recyclerView = findViewById(R.id.product_list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductbyCateorFlavorActivity.this));
                    adapter = new CartProductAdapter(productList, ProductbyCateorFlavorActivity.this);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    // Xử lý lỗi nếu có
                }
            });
        } else {
            // Lấy FlavorId từ Intent
            int flavorId = getIntent().getIntExtra("FlavorId", -1);

            // Lấy danh sách sản phẩm theo FlavorId
            ProductService productService = ApiClient.getApiClient().create(ProductService.class);
            Call<List<Product>> call = productService.getProductFlavor(flavorId);
            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    productList = response.body();

                    // Thiết lập RecyclerView và adapter
                    recyclerView = findViewById(R.id.product_list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductbyCateorFlavorActivity.this));
                    adapter = new CartProductAdapter(productList, ProductbyCateorFlavorActivity.this);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    // Xử lý lỗi nếu có
                }
            });


        }
        imageView3 = findViewById(R.id.imageView3);

        // Xử lý sự kiện khi người dùng nhấn vào ImageView3
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductbyCateorFlavorActivity.this, CartActivity.class);
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
                imageButton.setImageResource((Integer) imageList.get(currentImageIndex));
                switch (currentImageIndex) {
                    case 0:
                        // Sắp xếp sản phẩm theo tên tăng dần
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o1.getProductName().compareTo(o2.getProductName());
                            }
                        });
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        // Sắp xếp sản phẩm theo tên giảm dần
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o2.getProductName().compareTo(o1.getProductName());
                            }
                        });
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        // Sắp xếp sản phẩm theo giá giảm dần
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return Double.compare(o2.getPrice(), o1.getPrice());
                            }
                        });
                        adapter.notifyDataSetChanged();
                        break;
                    case 3:
                        // Sắp xếp sản phẩm theo giá tăng dần
                        Collections.sort(productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return Double.compare(o1.getPrice(), o2.getPrice());
                            }
                        });
                        adapter.notifyDataSetChanged();
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
                        adapter.notifyDataSetChanged();
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
                        adapter.notifyDataSetChanged();
                        break;
                }
                adapter.updateList(productList);
            }
        });
        backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Kết thúc Activity hiện tại và trở về Activity trước đó
            }
        });
        // Kiểm tra danh sách sản phẩm
//        emptyView = findViewById(R.id.empty_view);
//        checkEmptyView();
//
//        // Lắng nghe sự kiện thay đổi dữ liệu của adapter
//        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                checkEmptyView();
//            }
//        });

    }
}
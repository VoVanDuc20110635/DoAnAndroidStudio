package com.example.cartapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.CartProductAdapter;
import com.example.cartapplication.Adapter.CategoryAdapter;
import com.example.cartapplication.Adapter.FlavorAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.CategoryService;
import com.example.cartapplication.Service.FlavorService;
import com.example.cartapplication.Service.ProductService;
import com.example.cartapplication.Service.UserService;
import com.example.cartapplication.model.Account;
import com.example.cartapplication.model.CartItem;
import com.example.cartapplication.model.Category;
import com.example.cartapplication.model.Flavor;
import com.example.cartapplication.model.Product;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Product_Activity extends AppCompatActivity {
    //private ImageView imageView;
    private TextView textName;
    private RecyclerView recyclerView;
    private CartProductAdapter productAdapter;
    private List<Product> productList;
    private ImageView imageView3;
    private Intent intent;
    private EditText textSearch;
    private ImageButton imageButton;
    private List<Integer> imageList;
    private int currentImageIndex = 0;
    private View popUpView;
    private TextView emptyView;
    private boolean isMoreButtonClicked = false;
    private ImageView popupButton;
    private ImageView showMoreButton;
    private ImageView profileButton;
//    private User thisuser;



    private SharedPreferences sharedPreferences;

    private User thisUser;

    private Account account;

    private SoundPool soundPool;
    private Button closebutton;

    private void checkEmptyView() {
        if (productList.isEmpty()||productList==null) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }
    private User getUserFromUserJson(String userJson){
        Gson gson = new Gson();
        User user = gson.fromJson(userJson, User.class);
        return user;
    }


    //private ImageView closePopupButton;
//    public void checkPopupWindowStatus(PopupWindow popupWindow) {
//        if (popupWindow != null && popupWindow.isShowing()) {
//            closePopupButton.setVisibility(View.VISIBLE);
//            //closePopupButton.setVisibility(View.GONE);
//        } else {
//            closePopupButton.setVisibility(View.GONE);
//            //closePopupButton.setVisibility(View.VISIBLE);
//        }
//    }

    private void getUserData() {

        // Gọi API để lấy dữ liệu user
        //User user=null;
        intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("User_id", 0);
            //Log.d("Hệ thống", "User id là "+id);
            //System.out.print("User id là "+id);
            // Sau khi lấy được dữ liệu user, hiển thị lên view
            String name = "not found 404";
            Retrofit retrofit = ApiClient.getApiClient();
            UserService userService = retrofit.create(UserService.class);
            Call<User> call = userService.getUser(id);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User user = response.body();
                        // xử lý dữ liệu user
                        String nameuser = user.getName();
                        thisUser=user;
                        textName.setText(nameuser);
                    } else {
                        // xử lý lỗi khi response không thành công
                        Toast.makeText(Product_Activity.this, "User trống", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // xử lý lỗi khi gọi API thất bại
                }
            });


            //String imageUrl = ""; // Thay bằng URL của hình ảnh tương ứng


            // Hiển thị tên của user lên TextView
            textName.setText(name);
        } else {
            Toast.makeText(this, "Intent trống", Toast.LENGTH_SHORT).show();
        }

        // Tải hình ảnh của user lên ImageView
        //Glide.with(this)
        //        .load(imageUrl)
        ///        .into(imageView);
    }
    private void toggleMoreButtons() {
        isMoreButtonClicked = !isMoreButtonClicked;

        int originalShowMoreButtonMarginBottom = 0;
        ViewGroup.MarginLayoutParams layoutParams =
                (ViewGroup.MarginLayoutParams) showMoreButton.getLayoutParams();

        if (isMoreButtonClicked) {
            AnimationSet animationSet = createAnimationSet();
            imageView3.setVisibility(View.VISIBLE);
            imageView3.startAnimation(animationSet);
            popupButton.setVisibility(View.VISIBLE);
            popupButton.startAnimation(animationSet);
            profileButton.setVisibility(View.VISIBLE);
            profileButton.startAnimation(animationSet);
            showMoreButton.setImageResource(R.drawable.xbutton);

            originalShowMoreButtonMarginBottom = layoutParams.bottomMargin;
            layoutParams.bottomMargin =
                    getResources().getDimensionPixelSize(R.dimen.showMoreButtonMargin);
            showMoreButton.setLayoutParams(layoutParams);

        } else {
            imageView3.setVisibility(View.GONE);
            popupButton.setVisibility(View.GONE);
            profileButton.setVisibility(View.GONE);
            // Đặt lại margin bottom của showmorebutton
            layoutParams.bottomMargin = originalShowMoreButtonMarginBottom;
            showMoreButton.setLayoutParams(layoutParams);
            showMoreButton.setImageResource(R.drawable.uparrow);
        }
    }
    private AnimationSet createAnimationSet() {
        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);
        animationSet.addAnimation(alphaAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(500);
        animationSet.addAnimation(translateAnimation);

        return animationSet;
    }

    private void loadProducts() {
        // Đọc dữ liệu từ JSON và thêm vào productList
        // ...
        ProductService productService = ApiClient.getApiClient().create(ProductService.class);
        Call<List<Product>> call = productService.getProductList();
        call.enqueue(new Callback<List<Product>>() {
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
        setContentView(R.layout.activity_product);
        //View parentView = findViewById(R.layout.activity_product); // Tham chiếu đến View cha của ProductActivity
        recyclerView = findViewById(R.id.product_list);
        productList = new ArrayList<>();
        productAdapter = new CartProductAdapter(productList, this);

//        String userJson = sharedPreferences.getString("user", "");
//        thisUser = getUserFromUserJson(userJson);

        // Thiết lập LayoutManager và Adapter cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);
        textName = findViewById(R.id.qltk);
        profileButton=findViewById(R.id.profilebutton);
        
        LoadData();
        // Load dữ liệu từ JSON và cập nhật Adapter
        loadProducts();
        // Khởi tạo các view
        //imageView = findViewById(R.id.imageView);


        // Lấy dữ liệu user từ API
//        getUserData();
        // Khởi tạo các view
        imageView3 = findViewById(R.id.imageView3);

        // Xử lý sự kiện khi người dùng nhấn vào ImageView3
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product_Activity.this, CartActivity.class);
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
        popUpView = getLayoutInflater().inflate(R.layout.popup_layout, null);
        PopupWindow popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
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
                RecyclerView catelist = popUpView.findViewById(R.id.catelist);
                catelist.setLayoutManager(new LinearLayoutManager(Product_Activity.this));
                catelist.setAdapter(categoryAdapter);
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
                RecyclerView flavorview = popUpView.findViewById(R.id.flavorlist);
                flavorview.setLayoutManager(new LinearLayoutManager(Product_Activity.this));
                flavorview.setAdapter(flavorAdapter);
            }

            @Override
            public void onFailure(Call<List<Flavor>> call, Throwable t) {

            }


        });


        //mở cửa sổ pop up bằng nút
        popupButton = findViewById(R.id.popupcate);
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Hiển thị PopupWindow tại vị trí ImageView
                popupWindow.showAtLocation(getWindow().getDecorView().getRootView(), Gravity.CENTER, 0, 0);
                //popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
                //checkPopupWindowStatus(popupWindow);
            }
        });
        emptyView=findViewById(R.id.empty_view);
        checkEmptyView();

        // Lắng nghe sự kiện thay đổi dữ liệu của adapter
        productAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                checkEmptyView();
            }
        });
        showMoreButton = findViewById(R.id.showmorebutton);

        SoundPool soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .build();
        int soundId = soundPool.load(this, R.raw.bubble3000, 1);
        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
                //soundPool.pause(sound3StreamId);
                //soundPool.autoPause();
                toggleMoreButtons();
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến UserActivity và truyền vào user

                intent = new Intent(Product_Activity.this, UserActivity.class);
                intent.putExtra("user", thisUser); // Truyền vào user
                startActivity(intent);
            }
        });
        closebutton=popUpView.findViewById(R.id.btnclose);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
    }
    private void LoadData(){
        Gson gson = new Gson();
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        String userJson= sharedPreferences.getString("user", "");
        //Log.e("userJson", userJson);
        thisUser = gson.fromJson(userJson, User.class);
        String listCartItemJson = sharedPreferences.getString("cartItemList", "");
        List<CartItem> listCartItem = gson.fromJson(listCartItemJson,new TypeToken<List<CartItem>>(){}.getType());
        //Log.e("list cart item", listCartItem.get(0).getProduct().getProductName());
        String accoutnJson = sharedPreferences.getString("account", "");
        //Log.e("accoutnJson", accoutnJson);
        account = gson.fromJson(accoutnJson, Account.class);


        if (thisUser != null && account != null){
            //Log.e("thong bao ben Product activity", "ca user va account ton tai " + thisUser.getAddress() + " "
                   // + thisUser.getName() + " " + account.getId());
            textName.setText(thisUser.getName());
        }


    }
}
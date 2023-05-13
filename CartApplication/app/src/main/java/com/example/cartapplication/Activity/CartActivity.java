package com.example.cartapplication.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.CartItemAdapter;
import com.example.cartapplication.Adapter.CartProductAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.ProductService;
import com.example.cartapplication.model.CartItem;
import com.example.cartapplication.model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartProductAdapter productAdapter;
    private List<CartItem> cartItems;
    private CartItemAdapter adapter;
    private List<Product> productList;
    private Button buttonAddToCart;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        // Get the cart items from the intent extras
        cartItems = new List<CartItem>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<CartItem> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(CartItem cartItem) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends CartItem> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends CartItem> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(@Nullable Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public CartItem get(int i) {
                return null;
            }

            @Override
            public CartItem set(int i, CartItem cartItem) {
                return null;
            }

            @Override
            public void add(int i, CartItem cartItem) {

            }

            @Override
            public CartItem remove(int i) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<CartItem> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<CartItem> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<CartItem> subList(int i, int i1) {
                return null;
            }
        };
        cartItems = new ArrayList<>();
        cartItems.add(new CartItem(3, null, new Product(3, "Loz", 3, 33, 333,
                null, null, null, 2, 3, null, null), 2, 1222, 333, null, null));

        // Create an adapter for the cart items
        adapter = new CartItemAdapter(cartItems, this);

        // Attach the adapter to the RecyclerView
        RecyclerView listViewCartItems = findViewById(R.id.listViewCartItems);
        listViewCartItems.setAdapter(adapter);

        // Set the layout manager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listViewCartItems.setLayoutManager(layoutManager);

        // Update the total price TextView
        TextView textViewTotal = findViewById(R.id.textViewTotal);
        int totalPrice = calculateTotalPrice();
        textViewTotal.setText("Tổng tiền: " + totalPrice + "đồng");

        // Set the onClickListener for the checkout button
        Button buttonCheckout = findViewById(R.id.buttonCheckout);
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement checkout functionality
            }
        });
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        if (cartItems == null)
            for (CartItem cartItem : cartItems) {
                totalPrice += cartItem.getTotal();
            }
        return totalPrice;
    }

}

package com.example.cartapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Activity.DetailProductActivity;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.CartItemService;
import com.example.cartapplication.Service.CartService;
import com.example.cartapplication.model.CartItem;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private List<CartItem> cartItems;
    Context context;
    private SharedPreferences sharedPreferences;


    public CartItemAdapter(List<CartItem> cartItems,Context t) {
        this.cartItems = cartItems;
        this.context=t;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cartitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.productNameTextView.setText(cartItem.getProduct().getProductName());
        holder.priceTextView.setText("$" + cartItem.getProduct().getPrice());
        holder.totalTextView.setText("$" + cartItem.getTotal());
        holder.quantityTextView.setText(String.valueOf(cartItem.getQuantity()));
        Picasso.get().load(cartItem.getProduct().getImage()).into(holder.productImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("Product", cartItem.getProduct());
                intent.putExtra("FromCart", true);
                context.startActivity(intent);
//                ((Activity)(context)).recreate();
            }
        });
        holder.deleteButton.setOnClickListener(v -> {
            sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String userJson = sharedPreferences.getString("user", "");
            User user = gson.fromJson(userJson, User.class);
            Log.e("dc",user.getName());

            // Khởi tạo Retrofit
            Retrofit retrofit = ApiClient.getApiClient();
            CartItemService cartItemService = retrofit.create(CartItemService.class);
            Call<ResponseBody> call = cartItemService.removeCartItem(user.getId(),cartItem.getProduct().getId());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(context, "Đã xóa sản phẩm "+cartItem.getProduct().getProductName(), Toast.LENGTH_SHORT).show();
                        ((Activity)context).recreate();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView productImageView;
        private final TextView productNameTextView;
        private final TextView priceTextView;
        private final TextView totalTextView;
        private final TextView quantityTextView;
        private final ImageView deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.imageView2);
            productNameTextView = itemView.findViewById(R.id.product_name_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            totalTextView = itemView.findViewById(R.id.total_text_view);
            quantityTextView = itemView.findViewById(R.id.quantity_text_view);
            deleteButton = itemView.findViewById(R.id.button2);
        }
    }
}
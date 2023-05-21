package com.example.cartapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Activity.DetailProductActivity;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.CartService;
import com.example.cartapplication.Service.ProductService;
import com.example.cartapplication.model.Product;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.ResourceBundle;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ProductViewHolder> {

    private Context context;
    private SharedPreferences sharedPreferences;

    public CartProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    private List<Product> productList;
    //private Product currentproduct;
    /*public CartProductAdapter(List<Product> productList) {
        this.productList = productList;
    }*/

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        int id= product.getId();

        //String thutu= String.valueOf(position);
        holder.productNameTextView.setText(product.getProductName());
        holder.priceTextView.setText(String.format("%,.0f đồng", product.getPrice()));
        //holder.priceTextView.setText(thutu);
        //holder.quantityTextView.setText("Số lượng: " + product.getQuantity());
        //holder.buybuton.setText(String.format("%.0f",product.getPrice()-(product.getDiscount()*product.getPrice()/100)));
        holder.buybuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String userJson = sharedPreferences.getString("user", "");
                User user = gson.fromJson(userJson, User.class);
//                Log.e("dc",user.getName());

                // Khởi tạo Retrofit
                Retrofit retrofit = ApiClient.getApiClient();
                CartService cartService = retrofit.create(CartService.class);
                Call< ResponseBody> call = cartService.addToCart(user.getId(),id,1);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, "Đã thêm sản phẩm "+product.getProductName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
        if (false)
            if (product.getDiscount() > 0) {

                holder.Saleimageview.setVisibility(View.VISIBLE);
                holder.priceTextView.setPaintFlags(holder.priceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.priceTextView1.setText(String.format("%,.0f Đồng", product.getrealprice()));
                holder.priceTextView1.setVisibility(View.VISIBLE);
                Drawable drawable;
                if (product.getDiscount() < 20) {
                    drawable = ContextCompat.getDrawable(context, R.drawable.saletag);
                } else {
                    drawable = ContextCompat.getDrawable(context, R.drawable.bigsaleremovebgpreview);
                }
                holder.Saleimageview.setImageDrawable(drawable);
            }
        Picasso.get().load(product.getImage()).into(holder.productImageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("Product", product);
                intent.putExtra("FromCart",false);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (productList == null)
            return 0;
        return productList.size();
    }

    public void updateList(List<Product> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView productNameTextView;
        public TextView priceTextView, priceTextView1;
        public TextView quantityTextView;
        public ImageView productImageView;
        public ImageView Saleimageview;
        public ImageView buybuton;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.product_name);
            priceTextView = itemView.findViewById(R.id.product_price);
            priceTextView1 = itemView.findViewById(R.id.product_price1);
            quantityTextView = itemView.findViewById(R.id.product_quantity);
            productImageView = itemView.findViewById(R.id.product_image);
            Saleimageview = itemView.findViewById(R.id.saleimage);
            buybuton = itemView.findViewById(R.id.buttonbuy);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }
}

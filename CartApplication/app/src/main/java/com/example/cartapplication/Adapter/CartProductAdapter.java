package com.example.cartapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.R;
import com.example.cartapplication.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public CartProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productNameTextView.setText(product.getProductName());
        holder.priceTextView.setText(String.format("%,.0f đồng", product.getPrice()));
        holder.quantityTextView.setText("Số lượng: " + product.getQuantity());
        Picasso.get().load(product.getImage()).into(holder.productImageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView productNameTextView;
        public TextView priceTextView;
        public TextView quantityTextView;
        public ImageView productImageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.product_name);
            priceTextView = itemView.findViewById(R.id.product_price);
            quantityTextView = itemView.findViewById(R.id.product_quantity);
            productImageView = itemView.findViewById(R.id.product_image);
        }
    }
}
package com.example.cartapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.R;
import com.example.cartapplication.model.CartItem;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private List<CartItem> cartItems;
    Context context;

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
        holder.plusButton.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            notifyDataSetChanged();
        });
        holder.minusButton.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                notifyDataSetChanged();
            }
        });
        holder.deleteButton.setOnClickListener(v -> {
            cartItems.remove(cartItem);
            notifyDataSetChanged();
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
        private final ImageButton plusButton;
        private final EditText quantityTextView;
        private final ImageButton minusButton;
        private final ImageView deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.imageView2);
            productNameTextView = itemView.findViewById(R.id.product_name_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            totalTextView = itemView.findViewById(R.id.total_text_view);
            plusButton = itemView.findViewById(R.id.button_plus);
            quantityTextView = itemView.findViewById(R.id.quantity_text_view);
            minusButton = itemView.findViewById(R.id.button_minus);
            deleteButton = itemView.findViewById(R.id.button2);
        }
    }
}
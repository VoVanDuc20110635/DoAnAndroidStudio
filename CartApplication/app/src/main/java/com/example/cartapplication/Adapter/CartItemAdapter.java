package com.example.cartapplication.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.R;
import com.example.cartapplication.model.CartItem;
import com.example.cartapplication.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    // Khai báo một danh sách các sản phẩm
    private List<CartItem> productList;

    // Khai báo một Context để sử dụng các tài nguyên trong layout
    private Context context;

    // Khởi tạo Adapter với danh sách sản phẩm và Context
    public CartItemAdapter(List<CartItem> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    // Định nghĩa lớp ViewHolder để giữ các View trong mỗi item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImageView;
        public TextView productNameTextView;
        public TextView priceTextView;
        public TextView totalTextView;
        public ImageButton buttonPlus;
        public ImageButton buttonMinus;
        public EditText quantityTextView;
        public ImageView deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.imageView2);
            productNameTextView = itemView.findViewById(R.id.product_name_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            totalTextView = itemView.findViewById(R.id.total_text_view);
            buttonPlus = itemView.findViewById(R.id.button_plus);
            buttonMinus = itemView.findViewById(R.id.button_minus);
            quantityTextView = itemView.findViewById(R.id.quantity_text_view);
            deleteButton = itemView.findViewById(R.id.button2);
        }
    }

    // Tạo ViewHolder mới cho mỗi item
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem, parent, false);
        return new ViewHolder(itemView);
    }

    // Đổ dữ liệu vào ViewHolder
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CartItem product = productList.get(position);
//        Log.e("dc",product.getProduct().getProductName());

        Picasso.get()
                .load(product.getProduct().getImage())
                .into(holder.productImageView);
        holder.productNameTextView.setText(product.getProduct().getProductName());
        holder.priceTextView.setText(String.valueOf(product.getPrice()));
        holder.quantityTextView.setText(String.valueOf(product.getQuantity()));

        // Xử lý sự kiện khi người dùng click vào nút Plus
        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(holder.quantityTextView.getText().toString());
                quantity++;
                holder.quantityTextView.setText(String.valueOf(quantity));
                product.setQuantity(quantity);
                updateTotal(holder);
            }
        });

        // Xử lý sự kiện khi người dùng click vào nút Minus
        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(holder.quantityTextView.getText().toString());
                if (quantity > 1) {
                    quantity--;
                    holder.quantityTextView.setText(String.valueOf(quantity));
                    product.setQuantity(quantity);
                    updateTotal(holder);
                }
            }
        });

        // Xử lý sự kiện khi người dùng thay đổi số lượng sản phẩm
        holder.quantityTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int quantity = 1;
                if (!TextUtils.isEmpty(charSequence)) {
                    quantity = Integer.parseInt(charSequence.toString());
                }
                product.setQuantity(quantity);
                updateTotal(holder);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Xử lý sự kiện khi người dùng click vào nút Xóa
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productList.remove(product);
                notifyDataSetChanged();
            }
        });

        // Cập nhật tổng tiền
        updateTotal(holder);
    }

    // Cập nhật tổng tiền cho sản phẩm
    private void updateTotal(ViewHolder holder) {
        int quantity = Integer.parseInt(holder.quantityTextView.getText().toString());
        double price = Double.parseDouble(holder.priceTextView.getText().toString());
        double total = quantity * price;
        holder.totalTextView.setText(String.format(Locale.getDefault(), "%.2f", total));
    }

    // Trả về số lượng item trong danh sách sản phẩm
    @Override
    public int getItemCount() {
        return productList.size();
    }
}
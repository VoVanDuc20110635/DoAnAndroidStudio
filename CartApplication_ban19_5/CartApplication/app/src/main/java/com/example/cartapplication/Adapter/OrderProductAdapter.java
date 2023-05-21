package com.example.cartapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.Activity.DetailProductActivity;
import com.example.cartapplication.R;
import com.example.cartapplication.model.OrderDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderProductAdapter extends RecyclerView.Adapter<OrderProductAdapter.ViewHolder> {
    private List<OrderDetail> orderDetailList;
    Context context;

    public OrderProductAdapter(List<OrderDetail> orderDetailList, Context context) {
        this.orderDetailList = orderDetailList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shipper_orderdetail_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetailList.get(position);
        Picasso.get().load(orderDetail.getProduct().getImage()).into(holder.imageProduct);
        holder.productName.setText(orderDetail.getProduct().getProductName());
        holder.price.setText(String.valueOf(orderDetail.getPrice()));
        holder.quantity.setText(String.valueOf( orderDetail.getQuantity()));
        holder.total.setText(String.valueOf( orderDetail.getTotal()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("Product", orderDetail.getProduct());
                intent.putExtra("FromCart", false);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageProduct;
        private TextView productName;
        private TextView price;
        private TextView quantity;
        private TextView total;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageView2);
            productName = itemView.findViewById(R.id.product_name_text_view);
            price = itemView.findViewById(R.id.price_text_view);
            quantity = itemView.findViewById(R.id.txtQuantity);
            total = itemView.findViewById(R.id.total_text_view);
        }
    }
}

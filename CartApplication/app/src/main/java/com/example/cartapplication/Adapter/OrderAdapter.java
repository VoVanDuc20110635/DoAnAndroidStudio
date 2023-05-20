package com.example.cartapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.R;
import com.example.cartapplication.model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orderList;
    private Context context;

    public OrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderlayout, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderDateTextView.setText("Order Date: " + order.getOrderDate().toString());
        holder.addressTextView.setText("Address: " + order.getAddress());
        holder.cityTextView.setText("City: " + order.getCity());
        holder.phoneNumberTextView.setText("Phone Number: " + order.getPhoneNumber());
        holder.totalTextView.setText("Total: " + order.getTotal());
        holder.statusTextView.setText("Status: " + order.getStatus());
        holder.paymentMethodTextView.setText("Payment Method: " + order.getPaymentMethod().getName());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView orderDateTextView, addressTextView, cityTextView, phoneNumberTextView, totalTextView, statusTextView, paymentMethodTextView;

        public OrderViewHolder(View view) {
            super(view);
            orderDateTextView = view.findViewById(R.id.order_date_textview);
            addressTextView = view.findViewById(R.id.address_textview);
            cityTextView = view.findViewById(R.id.city_textview);
            phoneNumberTextView = view.findViewById(R.id.phone_number_textview);
            totalTextView = view.findViewById(R.id.total_textview);
            statusTextView = view.findViewById(R.id.status_textview);
            paymentMethodTextView = view.findViewById(R.id.payment_method_textview);
        }
    }
}
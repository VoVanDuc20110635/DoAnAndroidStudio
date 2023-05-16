package com.example.cartapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.R;
import com.example.cartapplication.model.Order;

import java.util.List;

public class OrderAdminItemAdapter extends RecyclerView.Adapter<OrderAdminItemAdapter.OrderViewHolder> {

    private List<Order> mOrderList;

    private Context context;

    public OrderAdminItemAdapter(List<Order> orderList, Context context1) {
        mOrderList = orderList;
        context = context1;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderadminitem, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = mOrderList.get(position);

        holder.userNameTextView.setText("Name: " + order.getUser().getName());
        holder.orderDateTextView.setText("Order Date: " + order.getOrderDate());
        holder.addressTextView.setText("Address: " + order.getAddress());
        holder.cityTextView.setText("City: " + order.getCity());
        holder.phoneNumberTextView.setText("Phone Number: " + order.getPhoneNumber());
        holder.totalTextView.setText("Total: " + order.getTotal());
        holder.paymentMethodTextView.setText("Payment Method: " + order.getPaymentMethod().getName());
        holder.statusTextView.setText("Status: " + order.getStatus());

        holder.allowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Xử lý sự kiện khi người dùng nhấn nút "Duyệt"
            }
        });

        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Xử lý sự kiện khi người dùng nhấn nút "Hủy"
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mOrderList == null) return 0;
        return mOrderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView userNameTextView;
        public TextView orderDateTextView;
        public TextView addressTextView;
        public TextView cityTextView;
        public TextView phoneNumberTextView;
        public TextView totalTextView;
        public TextView paymentMethodTextView;
        public TextView statusTextView;
        public Button allowButton;
        public Button cancelButton;

        public OrderViewHolder(View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userodername);
            orderDateTextView = itemView.findViewById(R.id.order_date_textview);
            addressTextView = itemView.findViewById(R.id.address_textview);
            cityTextView = itemView.findViewById(R.id.city_textview);
            phoneNumberTextView = itemView.findViewById(R.id.phone_number_textview);
            totalTextView = itemView.findViewById(R.id.total_textview);
            paymentMethodTextView = itemView.findViewById(R.id.payment_method_textview);
            statusTextView = itemView.findViewById(R.id.statusorder);
            allowButton = itemView.findViewById(R.id.allowbtn);
            cancelButton = itemView.findViewById(R.id.cancel_btn);
        }
    }
}

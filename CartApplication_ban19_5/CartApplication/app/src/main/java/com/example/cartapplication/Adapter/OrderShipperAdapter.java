package com.example.cartapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.Activity.shipper.OrderDetailShipperActivity;
import com.example.cartapplication.R;
import com.example.cartapplication.model.OrderShipper;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;

import java.util.List;

public class OrderShipperAdapter extends RecyclerView.Adapter<OrderShipperAdapter.OrderShipperViewHolder> {
    private List<OrderShipper> orderShipperList;
    private Context context;



    public OrderShipperAdapter(List<OrderShipper> orderShipperList, Context context) {
        this.orderShipperList = orderShipperList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderShipperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_shipper_layout, parent, false);
        return new OrderShipperViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderShipperViewHolder holder, int position) {
        OrderShipper orderShipper = orderShipperList.get(position);
        holder.txtMaDon.setText("Mã đơn: " + orderShipper.getId());
        holder.txtOrderDay.setText("Ngày đặt hàng: "+ orderShipper.getOrderDate());
        holder.txtNameClient.setText("Tên khách hàng: "+ orderShipper.getUser().getName());
        holder.txtAddressDelivery.setText("Địa chỉ giao hàng: "+orderShipper.getAddress());
        holder.txtPhoneNumber.setText("Số điện thoại: "+orderShipper.getPhoneNumber());
        holder.txtTotal.setText("Tổng tiền: "+orderShipper.getTotal());
        holder.txtPaymentMethod.setText("Phương thức thanh toán: "+orderShipper.getPaymentMethod().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, OrderDetailShipperActivity.class);
                intent.putExtra("OrderShipper", orderShipper);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderShipperList.size();
    }

    public class OrderShipperViewHolder extends RecyclerView.ViewHolder{
        public TextView txtMaDon, txtOrderDay, txtNameClient, txtAddressDelivery;
        public TextView txtPhoneNumber, txtTotal, txtPaymentMethod;
        public OrderShipperViewHolder(View view){
            super(view);
            txtMaDon = view.findViewById(R.id.txtMaDon);
            txtOrderDay = view.findViewById(R.id.txtOrderDay);
            txtNameClient = view.findViewById(R.id.txtNameClient);
            txtAddressDelivery = view.findViewById(R.id.txtAddressDelivery);
            txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);
            txtTotal = view.findViewById(R.id.txtTotal);
            txtPaymentMethod = view.findViewById(R.id.txtPaymentMethod);
        }
    }
}

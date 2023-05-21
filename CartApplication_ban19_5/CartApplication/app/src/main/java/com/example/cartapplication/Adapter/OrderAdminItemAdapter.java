package com.example.cartapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.OrderService;
import com.example.cartapplication.model.Order;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderAdminItemAdapter extends RecyclerView.Adapter<OrderAdminItemAdapter.OrderViewHolder> {

    private List<Order> mOrderList;

    private Context context;
    private Retrofit retrofit;
    private OrderService orderService;

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
        switch (order.getStatus()) {
            case 1:
                holder.statusTextView.setText("Chờ xác nhận...");
                holder.allowButton.setVisibility(View.VISIBLE);
                holder.cancelButton.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.statusTextView.setText("Chờ người vận chuyển...");
                break;
            case 3:
                holder.statusTextView.setText("Đang chuyển...");
                break;
            case 4:
                holder.statusTextView.setText("Đã giao");
                break;
            case 5:
                holder.statusTextView.setText("Đã hủy");
                break;
            case 6:
                holder.statusTextView.setText("Đã hủy");
                break;
            default:
                holder.statusTextView.setText("Chờ xác nhận...");
        }
        //holder.statusTextView.setText("Status: " + order.getStatus());

        holder.allowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Xử lý sự kiện khi người dùng nhấn nút "Duyệt"
                retrofit= ApiClient.getApiClient();
                orderService=retrofit.create(OrderService.class);
                Call<ResponseBody> call=orderService.setstatus(order.getId(), 2);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                Toast.makeText(context,response.body().string(),Toast.LENGTH_SHORT).show();
                                Intent intent=((Activity)context).getIntent();
                                intent.putExtra("User",order.getUser());
                                ((Activity) context).recreate();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });

        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Xử lý sự kiện khi người dùng nhấn nút "Hủy"
                retrofit= ApiClient.getApiClient();
                orderService=retrofit.create(OrderService.class);
                Call<ResponseBody> call=orderService.setstatus(order.getId(), 6);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                Toast.makeText(context,response.body().string(),Toast.LENGTH_SHORT).show();
                                Intent intent=((Activity)context).getIntent();
                                intent.putExtra("User",order.getUser());
                                ((Activity) context).recreate();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
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

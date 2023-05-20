package com.example.cartapplication.Activity.shipper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.Adapter.OrderProductAdapter;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.shipper.ShipperService;
import com.example.cartapplication.model.ErrorResponse;
import com.example.cartapplication.model.OrderDetail;
import com.example.cartapplication.model.OrderShipper;
import com.example.cartapplication.model.User;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailShipperActivity extends AppCompatActivity {
    private TextView txtNgayDatHang;
    private TextView txtTenKhachHang;
    private TextView txtDiaChiGiaoHang;
    private TextView txtSoDienThoai;
    private TextView txtTongTien;
    private TextView txtPhuongThucThanhToan;
    private RecyclerView orderProductListView;
    private Button btn_YeuCauGiaoHang;

    private Button btn_XacNhanGiaoHangThanhCong;

    private User thisUser;

    private SharedPreferences sharedPreferences;

    private OrderProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_shipper);
        initview();
        Gson gson = new Gson();
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        String userJson= sharedPreferences.getString("user", "");
        //Log.e("userJson", userJson);
        thisUser = gson.fromJson(userJson, User.class);
        Intent intent = getIntent();
        OrderShipper orderShipper = (OrderShipper) intent.getSerializableExtra("OrderShipper");
        List<OrderDetail> orderDetailList = orderShipper.getOrderDetails();
//        Log.e("vao duoc onCreate OrderdetailShipper", String.valueOf( orderShipper.getId()));
//        Log.e("ngay dat hang:", String.valueOf(orderShipper.getOrderDate()));
//        Log.e("ten khach hang", orderShipper.getUser().getName());
//        Log.e("Dia chi giao hang: ", orderShipper.getAddress());
//        Log.e("So dien thoai: ", orderShipper.getPhoneNumber());
//        Log.e("Tong tien: ",String.valueOf( orderShipper.getTotal()));
//        Log.e("Phuong thuc thanh toan", orderShipper.getPaymentMethod().getName());
        loadDataFromPreviousActity(orderShipper);
        Log.e("thong tin orderDEtaillist", orderDetailList.get(0).getProduct().getProductName());
        loadProductofOrder(orderDetailList);
        if (orderShipper.getStatus() ==2 ){
            btn_XacNhanGiaoHangThanhCong.setVisibility(View.GONE);
            btn_XacNhanGiaoHangThanhCong.setEnabled(false);
        }
        if (orderShipper.getStatus() == 3){
            btn_YeuCauGiaoHang.setVisibility(View.GONE);
            btn_YeuCauGiaoHang.setEnabled(false);
            btn_XacNhanGiaoHangThanhCong.setVisibility(View.VISIBLE);
            btn_XacNhanGiaoHangThanhCong.setEnabled(true);
        }
        if(thisUser.getAccount().getStatus()!=2){
            btn_XacNhanGiaoHangThanhCong.setVisibility(View.GONE);
            btn_YeuCauGiaoHang.setVisibility(View.GONE);
        }
        btn_YeuCauGiaoHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestChangeStatus(orderShipper.getId(), 3, thisUser.getId());
                btn_YeuCauGiaoHang.setVisibility(View.GONE);
                btn_YeuCauGiaoHang.setEnabled(false);
                btn_XacNhanGiaoHangThanhCong.setVisibility(View.VISIBLE);
                btn_XacNhanGiaoHangThanhCong.setEnabled(true);
            }
        });

        if (orderShipper.getStatus() ==3 ){
            btn_YeuCauGiaoHang.setVisibility(View.GONE);
            btn_YeuCauGiaoHang.setEnabled(false);
        }
        btn_XacNhanGiaoHangThanhCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestChangeStatus(orderShipper.getId(), 4, thisUser.getId());
                btn_XacNhanGiaoHangThanhCong.setText("ĐÃ GIAO HÀNG THÀNH CÔNG");
            }
        });
    }

    private void initview(){
        txtNgayDatHang = findViewById(R.id.txtNgayDatHang);
        txtTenKhachHang = findViewById(R.id.txtTenKhachHang);
        txtSoDienThoai = findViewById(R.id.txtSoDienThoai);
        txtDiaChiGiaoHang = findViewById(R.id.txtDiaChiGiaoHang);
        txtTongTien = findViewById(R.id.txtTongTien);
        txtPhuongThucThanhToan = findViewById(R.id.txtPhuongThucThanhToan);
        orderProductListView = findViewById(R.id.orderProduct_list);
        btn_YeuCauGiaoHang = findViewById(R.id.btn_YeuCauGiaoHang);
        btn_XacNhanGiaoHangThanhCong = findViewById(R.id.btn_XacNhanGiaoHangThanhCong);
    }

    private void loadDataFromPreviousActity(OrderShipper orderShipper){
        txtNgayDatHang.setText("Ngày đặt hàng: " + String.valueOf( orderShipper.getOrderDate()));
        txtTenKhachHang.setText("Tên khách hàng: " + orderShipper.getUser().getName());
        txtSoDienThoai.setText("Số điện thoại: " + orderShipper.getPhoneNumber());
        txtDiaChiGiaoHang.setText("Địa chỉ giao hàng: " + orderShipper.getAddress());
        txtTongTien.setText("Tổng tiền: " +  String.valueOf( orderShipper.getTotal()));
        txtPhuongThucThanhToan.setText("Phương thức thanh toán: " +  orderShipper.getPaymentMethod().getName());
    }

    private void loadProductofOrder(List<OrderDetail> orderDetailList){
        adapter = new OrderProductAdapter(orderDetailList, OrderDetailShipperActivity.this);
        orderProductListView.setAdapter(adapter);
        orderProductListView.setLayoutManager(new LinearLayoutManager(OrderDetailShipperActivity.this));
        adapter.notifyDataSetChanged();
    }

    private void sendRequestChangeStatus(int orderId, int status, int shipperId){
        ShipperService shipperService = ApiClient.getApiClient().create(ShipperService.class);
        Call<ErrorResponse> call = shipperService.updateStatusOrder(orderId, status, shipperId);
        call.enqueue(new Callback<ErrorResponse>() {
            @Override
            public void onResponse(Call<ErrorResponse> call, Response<ErrorResponse> response) {
                if (response.isSuccessful()) {
                    int statusCode = response.code();
                    ErrorResponse errorResponse = response.body();
                    if (errorResponse != null) {
                        // xử lý kết quả trả về ở đây
                        Log.e("thong bao thanh cong", response.body().getMessage());
                        Toast.makeText(OrderDetailShipperActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("thong bao: ", "body khong xac dinh");
                    }
                } else {
                    int statusCode = response.code();
                    String errorMessage = response.message();
                    Log.e("thong bao: ", "response khong thanh cong: " + statusCode + " - " + errorMessage);
                }
            }

            @Override
            public void onFailure(Call<ErrorResponse> call, Throwable t) {
                Log.e("thong bao: ", "khong lay duoc api");
            }
        });
    }

}
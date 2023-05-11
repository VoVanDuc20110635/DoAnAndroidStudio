package com.example.cartapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cartapplication.APIClient.ApiClient;
import com.example.cartapplication.R;
import com.example.cartapplication.Service.FeedbackService;
import com.example.cartapplication.model.Feedback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private List<Feedback> feedbackList;

    private void test(){
        Retrofit retrofit = ApiClient.getApiClient();
        //String phanhoi="";
        feedbackList=new ArrayList<>();
        //phanhoi="";
        //feedbackList.add(new Feedback(0,"Rỗng"));
        FeedbackService feedbackService = retrofit.create(FeedbackService.class);
        feedbackService.getAllfeedback(11).enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    feedbackList=response.body();
                    //Log.d("PHản hồi thông tin",String.valueOf(response.body().toString()));
                    //phanhoi=response.body().toString();
                    // TODO: Xử lý danh sách feedback
                } else {
                    // TODO: Xử lý lỗi
                    feedbackList.add(new Feedback(0,"Rỗng"));
                    //phanhoi="Sai rồi";
                }
            }

            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {
                // TODO: Xử lý lỗi
            }
        });

    }


    public static boolean isNumber(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void openProductActivity(String id) {
        Intent intent = new Intent(this, Product_Activity.class);
        intent.putExtra("User_id", Integer.parseInt(id));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editText.getText().toString().trim();
                if (isNumber(id)) {
                    // Do something with the ID
                    openProductActivity(id);
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //test();
        //Log.d("Thông tin", String.valueOf(feedbackList));
        //Log.d("id",String.valueOf(11));
    }
}
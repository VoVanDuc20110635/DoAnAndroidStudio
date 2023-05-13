package com.example.cartapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    public static boolean isNumber(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public void goToCart(String id) {
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("ID", id);
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
                    goToCart(id);
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
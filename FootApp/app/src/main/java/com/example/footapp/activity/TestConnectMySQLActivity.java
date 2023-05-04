package com.example.footapp.activity;

import androidx.annotation.AnyRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footapp.R;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class TestConnectMySQLActivity extends AppCompatActivity {
    TextView text, errorText;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_connect_my_sqlactivity);

        text = (TextView) findViewById(R.id.txtTestMySQL1);
        errorText = (TextView) findViewById(R.id.txtTestMySQL2);
        show = (Button) findViewById(R.id.btnTestMySQL);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
    }

    class Task extends AsyncTask<Void, Void, Void>{
        String records = "", error="";


        @Override
        protected Void doInBackground(Void... voids) {
            try{
//                Log.e(records, "hehe");
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filtro","root", "duc2112002");
                Log.e(records, "hehe");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM testconnection");
                ResultSetMetaData rsmd = resultSet.getMetaData(); // lay ten cot
                while (resultSet.next()){
//                    records += resultSet.getString(1) + "\n";
//                    Log.e(records, records);
                    Toast.makeText(TestConnectMySQLActivity.this, rsmd.getColumnName(1) + ": " + resultSet.getString(1) ,Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                error=  e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            text.setText(records);
            if (error != "")
                errorText.setText(error);
            super.onPostExecute(aVoid);
        }
    }
}
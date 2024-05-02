package com.example.onlinedatabasecurd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import me.rosuh.filepicker.config.FilePickerManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class insertData extends AppCompatActivity {
    TextView tx1,tx2,tx3;
    Button btn,btn_select,btn_upload;
    ArrayList arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        tx1 = findViewById(R.id.tx1);
        tx2 = findViewById(R.id.tx2);
        tx3 = findViewById(R.id.tx3);
        btn = findViewById(R.id.button);
        btn_select = findViewById(R.id.button2);
        btn_upload = findViewById(R.id.button3);
        arrayList = new ArrayList();
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilePickerManager
                        .from(insertData.this)
                        .forResult(FilePickerManager.REQUEST_CODE);
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tx1.getText().toString();
                String email = tx2.getText().toString();
                String password = tx3.getText().toString();
                APIInterface apiInterface = APPClient.getclient().create(APIInterface.class);
                Call<ResultContent> call = apiInterface.insert(name,email,password);
                call.enqueue(new Callback<ResultContent>() {
                    @Override
                    public void onResponse(Call<ResultContent> call, Response<ResultContent> response) {
                        Toast.makeText(insertData.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(insertData.this,DataShow.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<ResultContent> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        arrayList = (ArrayList) FilePickerManager.obtainData();
        Toast.makeText(insertData.this,"Selected File : "+arrayList.get(0),Toast.LENGTH_SHORT).show();
    }
}
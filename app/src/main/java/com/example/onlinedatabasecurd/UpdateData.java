package com.example.onlinedatabasecurd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateData extends AppCompatActivity {
    TextView tx1,tx2,tx3;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        tx1 = findViewById(R.id.tx1);
        tx2 = findViewById(R.id.tx2);
        tx3 = findViewById(R.id.tx3);
        btn = findViewById(R.id.button);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        tx1.setText(name);
        tx2.setText(email);
        tx3.setText(password);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tx1.getText().toString();
                String email = tx2.getText().toString();
                String password = tx3.getText().toString();
                APIInterface apiInterface = APPClient.getclient().create(APIInterface.class);
                Call<ResultContent> call = apiInterface.update(name,email,password,id);
                call.enqueue(new Callback<ResultContent>() {
                    @Override
                    public void onResponse(Call<ResultContent> call, Response<ResultContent> response) {
                        Toast.makeText(UpdateData.this,"Data Updated",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(UpdateData.this,DataShow.class);
                        startActivity(i);
                    }
                    @Override
                    public void onFailure(Call<ResultContent> call, Throwable t) {

                    }
                });
            }
        });
    }
}
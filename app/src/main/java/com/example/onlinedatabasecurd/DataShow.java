package com.example.onlinedatabasecurd;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataShow extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Content> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);
        rv = findViewById(R.id.rv);
        arrayList = new ArrayList<>();
        APIInterface apiInterface = APPClient.getclient().create(APIInterface.class);
        Call<ResultContent> call = apiInterface.getcontect();
        call.enqueue(new Callback<ResultContent>() {
            @Override
            public void onResponse(Call<ResultContent> call, Response<ResultContent> response) {
                arrayList = (ArrayList<Content>) response.body().getContent();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DataShow.this);
                rv.setLayoutManager(linearLayoutManager);
                RVAdapter rvAdapter = new RVAdapter(DataShow.this,arrayList);
                rv.setAdapter(rvAdapter);
            }

            @Override
            public void onFailure(Call<ResultContent> call, Throwable t) {

            }
        });
    }
}
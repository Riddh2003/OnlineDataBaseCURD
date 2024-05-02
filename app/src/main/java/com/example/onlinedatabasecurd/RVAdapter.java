package com.example.onlinedatabasecurd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Myclass> {
    Context context;
    ArrayList<Content> arrayList;
    RVAdapter(Context context, ArrayList<Content> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, @SuppressLint("RecyclerView") int position) {
        holder.t1.setText(arrayList.get(position).getId()+"");
        holder.t2.setText(arrayList.get(position).getName());
        holder.t3.setText(arrayList.get(position).getEmail());
        holder.t4.setText(arrayList.get(position).getPassword());
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuItem m1 = menu.add(0,0,0,"UPDATE");
                MenuItem m2 = menu.add(0,1,0,"DELETE");
                m1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem item) {
                        Intent intent = new Intent(context,UpdateData.class);
                        intent.putExtra("id",arrayList.get(position).getId());
                        intent.putExtra("name",arrayList.get(position).getName());
                        intent.putExtra("email",arrayList.get(position).getEmail());
                        intent.putExtra("password",arrayList.get(position).getPassword());
                        context.startActivity(intent);
                        return false;
                    }
                });
                m2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem item) {
                        APIInterface apiInterface = APPClient.getclient().create(APIInterface.class);
                        Call<ResultContent> call = apiInterface.delete(arrayList.get(position).getId());
                        call.enqueue(new Callback<ResultContent>() {
                            @Override
                            public void onResponse(Call<ResultContent> call, Response<ResultContent> response) {
                                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(context,DataShow.class);
                                context.startActivity(i);
                            }
                            @Override
                            public void onFailure(Call<ResultContent> call, Throwable t) {

                            }
                        });
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myclass extends RecyclerView.ViewHolder{
        TextView t1,t2,t3,t4;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.textView1);
            t2 = itemView.findViewById(R.id.textView2);
            t3 = itemView.findViewById(R.id.textView3);
            t4 = itemView.findViewById(R.id.textView4);
        }
    }
}

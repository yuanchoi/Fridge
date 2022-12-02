package com.teamfive.fridge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private ArrayList<food> arrayList;
    private Context context;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public CustomAdapter(ArrayList<food> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.food_name.setText(arrayList.get(position).getname());
        holder.food_date.setText(arrayList.get(position).getdate());

        //삭제버튼 클릭스 리싸이클 뷰 안에서 삭제 구현 코드
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("foodlist");

        holder.detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // databaseReference.child().removeValue();
                arrayList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, arrayList.size());
            }


        });
    }

    @Override
    public int getItemCount(){
        return (arrayList != null ? arrayList.size() : 0);
    }

    public  class CustomViewHolder extends RecyclerView.ViewHolder{
        // Button detail_btn;
        TextView food_name,food_date;
        Button detail_btn;

        public  CustomViewHolder(@NonNull View itemView){
            super(itemView);

            this.food_name = itemView.findViewById(R.id.food_name);
            this.food_date = itemView.findViewById(R.id.food_date);
            this.detail_btn = itemView.findViewById(R.id.detail_btn);
        }


    }

}

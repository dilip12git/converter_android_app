package com.project.converter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final ArrayList<DataModel> itemList;
    private final OnItemClickListner onItemClickListner;

    public MyAdapter(ArrayList<DataModel> itemList, OnItemClickListner onItemClickListner) {
        this.itemList = itemList;
        this.onItemClickListner = onItemClickListner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel currentItem = itemList.get(position);
        holder.imageView.setImageResource(currentItem.getImg());
        holder.title.setText(currentItem.getTitle());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListner != null) {
                onItemClickListner.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_id);
            title = itemView.findViewById(R.id.title_id);
        }
    }
}

package com.example.easyhotel.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.easyhotel.R;
import com.example.easyhotel.databinding.RoomImgSliderBinding;

import java.util.ArrayList;

public class RoomImgViewpagerAdapter extends RecyclerView.Adapter<RoomImgViewpagerAdapter.ViewHolder> {
    private ArrayList<Integer> imgs;
    private Context context;
    public RoomImgViewpagerAdapter() {
        this.imgs = new ArrayList<>();
        imgs.add(R.drawable.jeju);
        imgs.add(R.drawable.du);
        imgs.add(R.drawable.hoian);
        imgs.add(R.drawable.kur);
        imgs.add(R.drawable.sing);
        imgs.add(R.drawable.background1);
        imgs.add(R.drawable.background2);
        imgs.add(R.drawable.background3);
        imgs.add(R.drawable.background4);
        imgs.add(R.drawable.background5);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        RoomImgSliderBinding binding = RoomImgSliderBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(imgs.get(position))
                .centerCrop().into(holder.binding.imageViewID);
    }

    @Override
    public int getItemCount() {
        return imgs==null?0:imgs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RoomImgSliderBinding binding;
        public ViewHolder(@NonNull RoomImgSliderBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}

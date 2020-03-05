package com.example.easyhotel.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.easyhotel.databinding.RoomImgSliderBinding;
import java.util.List;

public class HotelImgViewpagerAdapter extends RecyclerView.Adapter<HotelImgViewpagerAdapter.ViewHolder> {
    private List<String> imgs;
    private Context context;
    private StringBuilder url = new StringBuilder("http://10.1.42.83/hotel/resource/hotel/");
    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
        notifyDataSetChanged();
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
        Glide.with(context).load(url.toString()+imgs.get(position))
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

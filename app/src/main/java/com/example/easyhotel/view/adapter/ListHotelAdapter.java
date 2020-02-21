package com.example.easyhotel.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyhotel.R;
import com.example.easyhotel.data.model.Hotel;
import com.example.easyhotel.databinding.HotelItemListBinding;

import java.util.List;

public class ListHotelAdapter extends RecyclerView.Adapter {
    private List<Hotel> hotels;
    private HotelItemListBinding binding;


    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.hotel_item_list,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        binding.setData(hotels.get(position));

    }

    @Override
    public int getItemCount() {
        return hotels==null?0:hotels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private  HotelItemListBinding binding;
        public ViewHolder(@NonNull HotelItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

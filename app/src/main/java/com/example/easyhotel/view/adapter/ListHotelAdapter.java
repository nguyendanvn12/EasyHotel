package com.example.easyhotel.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.easyhotel.R;
import com.example.easyhotel.data.model.Hotel;
import com.example.easyhotel.databinding.HotelItemListBinding;
import com.example.easyhotel.view.ListHotelEvent;

import java.util.List;

public class ListHotelAdapter extends RecyclerView.Adapter {
    private List<Hotel> hotels;
    private HotelItemListBinding binding;
    private Context context;
    private ListHotelEvent event;
    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
        notifyDataSetChanged();
    }

    public void setEvent(ListHotelEvent event){
        this.event = event;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.hotel_item_list,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Hotel hotel =hotels.get(position);
        binding.setData(hotel);
        binding.setEvent(event);
        StringBuilder url = new StringBuilder("http://10.0.0.15/hotel/resource/hotel/");
        url.append(hotel.getHotelName()).append("/thumb/").append(hotel.getThumb());
        Log.d("ccc",url.toString());
        Glide.with(context)
                .load(url.toString())
                .centerCrop()
                .into(binding.imageView3);
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

package com.example.easyhotel.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyhotel.R;
import com.example.easyhotel.data.model.SearchModel;
import com.example.easyhotel.databinding.ItemGpsBinding;
import com.example.easyhotel.databinding.ItemHeaderBinding;
import com.example.easyhotel.databinding.ItemSearchHotelBinding;
import com.example.easyhotel.databinding.ItemSearchPlaceBinding;
import com.example.easyhotel.view.Event;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ArrayList<SearchModel> datas;
    private ItemSearchHotelBinding hotelBinding;
    private ItemSearchPlaceBinding placeBinding;
    private ItemGpsBinding gpsBinding;
    private ItemHeaderBinding headerBinding;
    private Event event;

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setDatas(ArrayList<SearchModel> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            gpsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_gps, parent, false);
            return new ViewHolder(gpsBinding);
        } else if (viewType == 1) {
            placeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_search_place, parent, false);
            return new ViewHolder(placeBinding);
        } else if(viewType==2){
            hotelBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_search_hotel, parent, false);
            return new ViewHolder(hotelBinding);
        }
            headerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_header, parent, false);
            return new ViewHolder(headerBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 0:
                break;
            case 1:
                holder.placeBinding.setVar(datas.get(position));
                holder.placeBinding.setEvent(event);
                break;
            case 2:
            holder.hotelBinding.setData(datas.get(position));
            holder.hotelBinding.setEvent(event);
                break;
            default:
                holder.headerBinding.setData(datas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
         ItemSearchHotelBinding hotelBinding;
         ItemSearchPlaceBinding placeBinding;
         ItemHeaderBinding headerBinding;
         ItemGpsBinding gpsBinding;

        public ViewHolder(@NonNull ItemSearchPlaceBinding placeBinding) {
            super(placeBinding.getRoot());
            this.placeBinding = placeBinding;

        }

        public ViewHolder(@NonNull ItemSearchHotelBinding hotelBinding) {
            super(hotelBinding.getRoot());
            this.hotelBinding = hotelBinding;
        }

        public ViewHolder(@NonNull ItemHeaderBinding headerBinding) {
            super(headerBinding.getRoot());
            this.headerBinding = headerBinding;
        }

        public ViewHolder(@NonNull ItemGpsBinding gpsBinding) {
            super(gpsBinding.getRoot());
            this.gpsBinding = gpsBinding;
        }
    }
}

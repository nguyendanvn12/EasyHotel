package com.example.easyhotel.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.RoomItemRowBinding;

public class ListRoomAdapter extends RecyclerView.Adapter {
    private RoomItemRowBinding binding;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.room_item_row,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private RoomItemRowBinding binding;
        public ViewHolder(@NonNull RoomItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.example.easyhotel.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.DurationItemLayoutBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DurationAdapter extends RecyclerView.Adapter<DurationAdapter.ViewHolder> {
    private  DurationItemLayoutBinding itemLayoutBinding ;
    private Date startDate;
    private SimpleDateFormat simpleDateFormat;
    public DurationAdapter(Date date) {
        this.startDate = date;
        simpleDateFormat = new SimpleDateFormat("EE, dd MMMM yyyy");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.duration_item_layout,parent,false);

        return new ViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemLayoutBinding.setDuration(position+1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE,position+1);
        holder.itemLayoutBinding.setTime(simpleDateFormat.format(calendar.getTime()));
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private DurationItemLayoutBinding itemLayoutBinding;
        public ViewHolder(@NonNull DurationItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }
    }
}

package com.example.easyhotel.view.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.easyhotel.R;
import com.example.easyhotel.data.converter.DataConverter;
import com.example.easyhotel.data.model.roominfo.Room;
import com.example.easyhotel.databinding.RoomItemRowBinding;
import com.example.easyhotel.view.Event;
import com.example.easyhotel.view.PickRoom;

import java.util.List;

public class ListRoomAdapter extends RecyclerView.Adapter<ListRoomAdapter.ViewHolder> {
    private RoomItemRowBinding binding;
    private List<Room> rooms;
    private Context context;
    private PickRoom event;

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
        notifyDataSetChanged();
    }

    public void setEvent(PickRoom event) {
        this.event = event;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.room_item_row, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int room = getRealPos(position);
        int rate = getPosInRate(position);
        holder.binding.itemRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.details(room, rate);
            }
        });
        holder.binding.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.book(room, rate);
            }
        });
        try {
            holder.binding.setRoom(rooms.get(room));
            String url = "http://10.1.42.83/hotel/resource/hotel/";
            Glide.with(context).load(url + rooms.get(room).getImgs().get(0))
                    .centerCrop().into(holder.binding.ivRoomThumb);
            holder.binding.tvBreakfast.setText(rooms.get(room).getRates().get(rate).getBreakfast() ? "Bữa ăn: bao gôm bữa ăn sáng" : "Bữa ăn: không bao gồm");
            holder.binding.tvRefund.setText(rooms.get(room).getRates().get(rate).getRefund() ? "Hoàn huỷ: có hoàn huỷ" : "Hoàn huỷ: không hoàn huỷ");
            long startDiscount = rooms.get(room).getRates().get(rate).getStartDiscount();
            long endDiscount = rooms.get(room).getRates().get(rate).getEndDiscount();
            if (System.currentTimeMillis() / 1000 >= startDiscount && System.currentTimeMillis() / 1000 <= endDiscount) {
                int dis = rooms.get(room).getRates().get(rate).getDiscount();
                int orig = rooms.get(room).getRates().get(rate).getOriginPrice();
                int sale = orig - orig * dis / 100;
                if (dis > 0) {
                    holder.binding.tvDiscount.setText(dis + "% off");
                    holder.binding.tvOriginPrice.setText(Html.fromHtml(context.getString(R.string.orifin_price, DataConverter.currency(orig)), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    holder.binding.tvOriginPrice.setVisibility(View.GONE);
                    holder.binding.tvDiscount.setVisibility(View.GONE);
                }
                holder.binding.tvSalePrice.setText(Html.fromHtml(context.getString(R.string.sale, DataConverter.currency(sale)), Html.FROM_HTML_MODE_COMPACT));
            } else {
                int orig = rooms.get(room).getRates().get(rate).getOriginPrice();
                holder.binding.tvDiscount.setVisibility(View.GONE);
                holder.binding.tvOriginPrice.setVisibility(View.GONE);
                holder.binding.tvSalePrice.setText(Html.fromHtml(context.getString(R.string.sale, DataConverter.currency(orig)), Html.FROM_HTML_MODE_COMPACT));
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return rooms == null ? 0 : countRates();
    }

    private int getPosInRate(int pos) {
        int i = 0;
        while (pos >= rooms.get(i).getRates().size()) {
            pos -= rooms.get(i).getRates().size();
            i++;
        }
        return pos;
    }

    private int getRealPos(int pos) {
        int i = 0;
        while (pos >= rooms.get(i).getRates().size()) {
            pos -= rooms.get(i).getRates().size();
            i++;
        }
        if (i > 0) {
            return i - 1;
        }
        return i;
    }

    private int countRates() {
        int rates = 0;
        for (int i = 0; i < rooms.size(); i++) {
            rates += rooms.get(i).getRates().size();
        }
        return rates;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RoomItemRowBinding binding;

        public ViewHolder(@NonNull RoomItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

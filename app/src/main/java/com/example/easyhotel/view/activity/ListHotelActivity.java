package com.example.easyhotel.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.easyhotel.R;
import com.example.easyhotel.data.model.Hotel;
import com.example.easyhotel.databinding.ActivityListHotelBinding;
import com.example.easyhotel.view.ListHotelEvent;
import com.example.easyhotel.view.adapter.ListHotelAdapter;
import com.example.easyhotel.viewmodel.ListHotelViewModel;

import java.util.List;

public class ListHotelActivity extends AppCompatActivity implements ListHotelEvent {
    private ActivityListHotelBinding binding;
    private ListHotelAdapter adapter;
    private ListHotelViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_hotel);
        viewModel = new ViewModelProvider(this).get(ListHotelViewModel.class);
        adapter = new ListHotelAdapter();
        adapter.setEvent(this);
        binding.rvListHotel.setAdapter(adapter);
        binding.rvListHotel.setLayoutManager(new LinearLayoutManager(this));
        binding.setEvent(this);
        int id = 1;

        viewModel.getListHotel(id,null,null,null,null,null).observe(this, new Observer<List<Hotel>>() {
            @Override
            public void onChanged(List<Hotel> hotels) {
                adapter.setHotels(hotels);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void back() {
        onBackPressed();
    }

    @Override
    public void call() {
        String tel = "tel:"+R.string.so_tong_dai;
        Uri uri = Uri.parse(tel);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            ActivityCompat.requestPermissions(ListHotelActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
            return;
        }else {
            startActivity(intent);
        }

    }

    @Override
    public void openSapXep() {

    }

    @Override
    public void openLoc() {

    }

    @Override
    public void openMap() {

    }

    @Override
    public void pickHotel(int hotelId) {
        Log.d("ccc","c");
    Intent intent = new Intent(ListHotelActivity.this,DetailsHotelActivity.class);
    startActivity(intent);
    }
}

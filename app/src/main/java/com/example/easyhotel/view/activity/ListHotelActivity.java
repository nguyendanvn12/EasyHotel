package com.example.easyhotel.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.example.easyhotel.R;
import com.example.easyhotel.data.model.Hotel;
import com.example.easyhotel.data.model.SearchModel;
import com.example.easyhotel.databinding.ActivityListHotelBinding;
import com.example.easyhotel.view.ListHotelEvent;
import com.example.easyhotel.view.adapter.ListHotelAdapter;
import com.example.easyhotel.viewmodel.ListHotelViewModel;

import java.util.List;

public class ListHotelActivity extends AppCompatActivity implements ListHotelEvent {
    private ActivityListHotelBinding binding;
    private ListHotelAdapter adapter;
    private ListHotelViewModel viewModel;
    private long checkIn;
    private int duration;
    private SearchModel location;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_hotel);
        viewModel = new ViewModelProvider(this).get(ListHotelViewModel.class);
        adapter = new ListHotelAdapter();
        adapter.setEvent(this);
        binding.rvListHotel.setAdapter(adapter);
        binding.rvListHotel.setLayoutManager(new LinearLayoutManager(this));
        binding.setEvent(this);
        checkIn= getIntent().getLongExtra("checkin",System.currentTimeMillis());
        duration = getIntent().getIntExtra("duration",1);
        location = (SearchModel) getIntent().getSerializableExtra("location");
        binding.setLocation(location);
        binding.setCheckin(checkIn);
        binding.setDuration(duration);
        viewModel.getListHotel(location.getId(), null, null, null, null, null).observe(this, new Observer<List<Hotel>>() {
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
        String tel = "tel:" + R.string.so_tong_dai;
        Uri uri = Uri.parse(tel);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ListHotelActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        } else {
            startActivity(intent);
        }

    }

    @Override
    public void openSapXep() {

    }

    @Override
    public void openLoc() {
        //todo filter hotel
    }

    @Override
    public void openMap() {
        //todo open Hotels location in Map;
    }

    private long mLastClickTime = 0;

    @Override
    public void pickHotel(int hotelId) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }

        mLastClickTime = SystemClock.elapsedRealtime();
        Intent intent = new Intent(ListHotelActivity.this, DetailsHotelActivity.class);
        intent.putExtra("hotelId",hotelId);
        intent.putExtra("duration",duration);
        intent.putExtra("checkin",checkIn);
        startActivity(intent);
    }
}

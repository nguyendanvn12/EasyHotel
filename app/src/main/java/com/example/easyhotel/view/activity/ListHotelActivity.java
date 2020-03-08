package com.example.easyhotel.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
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
import com.example.easyhotel.view.fragment.ChangeFilterFragment;
import com.example.easyhotel.viewmodel.ListHotelFilterViewModel;
import com.example.easyhotel.viewmodel.ListHotelViewModel;

import java.util.List;

public class ListHotelActivity extends AppCompatActivity implements ListHotelEvent {
    private ActivityListHotelBinding binding;
    private ListHotelAdapter adapter;
    private ListHotelViewModel viewModel;
    private ListHotelFilterViewModel filterViewModel;
    private SearchModel location;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_hotel);
        viewModel = new ViewModelProvider(this).get(ListHotelViewModel.class);
        filterViewModel = new ViewModelProvider(this).get(ListHotelFilterViewModel.class);
        adapter = new ListHotelAdapter();
        adapter.setEvent(this);
        binding.rvListHotel.setAdapter(adapter);
        binding.rvListHotel.setLayoutManager(new LinearLayoutManager(this));
        binding.setEvent(this);
       filterViewModel.setCheckInDate( getIntent().getLongExtra("checkin",System.currentTimeMillis()));
        filterViewModel.setDuration( getIntent().getIntExtra("duration",1));

        Log.d("ccc", "onCreate: "+filterViewModel.getCheckInDate().getValue());

        location = (SearchModel) getIntent().getSerializableExtra("location");
        binding.setLocation(location);
        filterViewModel.getCheckInDate().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                binding.setCheckin(aLong);
            }
        });
        filterViewModel.getDuration().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.setDuration(integer);
            }
        });

        viewModel.getListHotel(location.getId(), null, null, null, null, null).observe(this, new Observer<List<Hotel>>() {
            @Override
            public void onChanged(List<Hotel> hotels) {
                adapter.setHotels(hotels);
            }
        });


    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount()>0){
            fragmentManager.popBackStack();
        }else {
            super.onBackPressed();
        }
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
        intent.putExtra("duration",filterViewModel.getDuration().getValue());
        intent.putExtra("checkin",filterViewModel.getCheckInDate().getValue());
        startActivity(intent);
    }

    @Override
    public void changeFilter() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        Log.d("ccc", "changeFilter: ");
        ChangeFilterFragment fragment = new ChangeFilterFragment();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_down,R.anim.slide_in_up,R.anim.slide_out_up,R.anim.slide_out_down)
                .replace(R.id.fl_container,fragment).addToBackStack(null).commit();
    }



}

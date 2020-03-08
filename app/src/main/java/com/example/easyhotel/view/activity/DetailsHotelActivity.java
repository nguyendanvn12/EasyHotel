package com.example.easyhotel.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.example.easyhotel.R;
import com.example.easyhotel.data.model.hoteldetails.HotelDetails;
import com.example.easyhotel.data.model.roominfo.Room;
import com.example.easyhotel.databinding.ActivityDetailsHotelBinding;
import com.example.easyhotel.view.CheckInDateCallback;
import com.example.easyhotel.view.DetailsHotelEvent;
import com.example.easyhotel.view.DurationCallback;
import com.example.easyhotel.view.PickRoom;
import com.example.easyhotel.view.adapter.HotelImgViewpagerAdapter;
import com.example.easyhotel.view.adapter.ListRoomAdapter;
import com.example.easyhotel.view.fragment.BookingFragment;
import com.example.easyhotel.view.fragment.DetailsRoomFragment;
import com.example.easyhotel.view.fragment.DurationFragment;
import com.example.easyhotel.view.fragment.PickCheckInDateFragment;
import com.example.easyhotel.view.fragment.SearchFragment;
import com.example.easyhotel.view.fragment.SetRoomCountFragment;
import com.example.easyhotel.viewmodel.DetailsHotelViewModel;
import com.example.easyhotel.viewmodel.ListHotelFilterViewModel;
import com.example.easyhotel.viewmodel.ListHotelViewModel;
import com.example.easyhotel.viewmodel.RoomViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailsHotelActivity extends AppCompatActivity implements DetailsHotelEvent, PickRoom, DurationCallback, CheckInDateCallback {
    private ActivityDetailsHotelBinding binding;
    private DetailsHotelViewModel detailsHotelViewModel;
    private ListRoomAdapter roomAdapter;
    private  HotelImgViewpagerAdapter imgAdapter;
    private int hotelId;
    private RoomViewModel roomViewModel;
    private ListHotelFilterViewModel filterViewModel;
    private long mLastClickTime;
    private FragmentManager fragmentManager;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_hotel);
        detailsHotelViewModel = new ViewModelProvider(this).get(DetailsHotelViewModel.class);
        roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);
        filterViewModel = new ViewModelProvider(this).get(ListHotelFilterViewModel.class);
        imgAdapter = new HotelImgViewpagerAdapter();
        binding.setLifecycleOwner(this);
        binding.setViewmodel(detailsHotelViewModel);
        filterViewModel.setCheckInDate(getIntent().getLongExtra("checkin",System.currentTimeMillis()));
       filterViewModel.setDuration(getIntent().getIntExtra("duration",1));

       binding.setFilter(filterViewModel);
        binding.vpSlider.setAdapter(imgAdapter);
        binding.vpSlider.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float offset = 5 * position;
                page.setTranslationX(offset);

                if (position <= -1 || position >= 1) {
                    page.findViewById(R.id.imageViewID).setVisibility(View.VISIBLE);
                } else if (position == 0) {
                    page.findViewById(R.id.imageViewID).setVisibility(View.VISIBLE);
                    page.findViewById(R.id.imageViewID).setTranslationX(0);
                } else {
                    page.findViewById(R.id.imageViewID).setTranslationX(-position * page.getWidth() / 2);

                }
            }

        });
        binding.setEvent(this);
        binding.rvRoomContainer.setLayoutManager(new LinearLayoutManager(this));
        roomAdapter = new ListRoomAdapter();
        binding.rvRoomContainer.setAdapter(roomAdapter);
        hotelId = getIntent().getIntExtra("hotelId",1);
        detailsHotelViewModel.getHotelDetails(hotelId).observe(this, new Observer<HotelDetails>() {
            @Override
            public void onChanged(HotelDetails hotelDetails) {
                imgAdapter.setImgs(hotelDetails.getImgs());
                binding.setHoteldetails(hotelDetails);
            }
        });
        roomViewModel.getRooms(hotelId).observe(this, new Observer<List<Room>>() {
            @Override
            public void onChanged(List<Room> rooms) {
                roomAdapter.setRooms(rooms);
            }
        });
        roomAdapter.setEvent(this);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void back() {
        onBackPressed();
    }

    @Override
    public void like(int hotelId) {
        //todo check user login? -> handle like
    }

    @Override
    public void showMap() {
        //todo show Map
    }

    @Override
    public void showReview() {
        //todo show review details in this hotel
    }

    @Override
    public void callCSKH() {
        String tel = "tel:" + R.string.so_tong_dai;
        Uri uri = Uri.parse(tel);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(DetailsHotelActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void preSlide() {
       int i =  binding.vpSlider.getCurrentItem();
       try {
           binding.vpSlider.setCurrentItem(i-1,true);
       }catch (Exception ex){

       }
    }

    @Override
    public void nextSlide() {
        int i =  binding.vpSlider.getCurrentItem();
        try {
            binding.vpSlider.setCurrentItem(i+1,true);
        }catch (Exception ex){

        }
    }

    @Override
    public void changeCheckIn() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        PickCheckInDateFragment fragment = new PickCheckInDateFragment(filterViewModel.getCheckInDate().getValue(),this::pickDate);
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up,R.anim.slide_in_down,R.anim.slide_out_down,R.anim.slide_out_up).replace(R.id.container,fragment).addToBackStack(null).commit();
    }

    @Override
    public void changeDuration() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        Date date = new Date(filterViewModel.getCheckInDate().getValue());
        int duration = filterViewModel.getDuration().getValue();
        DurationFragment fragment = new DurationFragment(date,duration,this);
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up,R.anim.slide_in_down,R.anim.slide_out_down,R.anim.slide_out_up).replace(R.id.container,fragment).addToBackStack(null).commit();

    }

    @Override
    public void setRoomCount() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        SetRoomCountFragment fragment = new SetRoomCountFragment();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_up,R.anim.slide_in_down,R.anim.slide_out_down,R.anim.slide_out_up).replace(R.id.container,fragment).addToBackStack(null).commit();

    }

    @Override
    public void book(int room, int rate) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        roomViewModel.setActiveRoom(room,rate);
        BookingFragment fragment = new BookingFragment();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_out_left).replace(R.id.container, fragment).addToBackStack(null).commit();

    }

    @Override
    public void details(int room, int rate) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        roomViewModel.setActiveRoom(room,rate);
        DetailsRoomFragment fragment = new DetailsRoomFragment();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_out_left).replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    @Override
    public void pickDate(Long date) {
filterViewModel.setCheckInDate(date);
    }

    @Override
    public void pickDuration(int night) {
filterViewModel.setDuration(night);
    }
}

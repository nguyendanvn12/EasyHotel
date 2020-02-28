package com.example.easyhotel.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.ActivityDetailsHotelBinding;
import com.example.easyhotel.view.adapter.ListRoomAdapter;
import com.example.easyhotel.view.adapter.MainBackgroundAdapter;
import com.example.easyhotel.view.adapter.RoomImgViewpagerAdapter;

import java.util.ArrayList;

public class DetailsHotelActivity extends AppCompatActivity {
private ActivityDetailsHotelBinding binding;
private ListRoomAdapter roomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_hotel);

        binding.vpSlider.setAdapter(new RoomImgViewpagerAdapter());
        binding.vpSlider.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                if (position <= -1 || position >= 1){
                    page.findViewById(R.id.imageViewID).setVisibility(View.VISIBLE);
                }
                else if( position == 0) {
                    page.findViewById(R.id.imageViewID).setVisibility(View.VISIBLE);
                } else {
                    page.findViewById(R.id.imageViewID).setTranslationX(-position * page.getWidth() / 2);
                }
            }
        });
        binding.rvRoomContainer.setLayoutManager(new LinearLayoutManager(this));
        roomAdapter = new ListRoomAdapter();
        binding.rvRoomContainer.setAdapter(roomAdapter);

    }
}

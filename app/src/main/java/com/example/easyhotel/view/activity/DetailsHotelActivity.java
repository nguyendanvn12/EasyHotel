package com.example.easyhotel.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.ActivityDetailsHotelBinding;
import com.example.easyhotel.view.adapter.ListRoomAdapter;
import com.example.easyhotel.view.adapter.MainBackgroundAdapter;
import com.example.easyhotel.view.adapter.RoomImgViewpagerAdapter;
import com.example.easyhotel.viewmodel.DetailsHotelViewModel;

import java.util.ArrayList;

public class DetailsHotelActivity extends AppCompatActivity {
private ActivityDetailsHotelBinding binding;
private DetailsHotelViewModel detailsHotelViewModel;
private ListRoomAdapter roomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_hotel);
        detailsHotelViewModel = new ViewModelProvider(this).get(DetailsHotelViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewmodel(detailsHotelViewModel);
        binding.vpSlider.setAdapter(new RoomImgViewpagerAdapter());
        binding.vpSlider.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                    float offset = 5 * position;
                    page.setTranslationX(offset);

                if (position <= -1 || position >= 1){
                    page.findViewById(R.id.imageViewID).setVisibility(View.VISIBLE);
                }
                else if( position == 0) {
                    page.findViewById(R.id.imageViewID).setVisibility(View.VISIBLE);
                    page.findViewById(R.id.imageViewID).setTranslationX(0);
                } else {
                    Log.d("ccc", "transformPage: "+page.findViewById(R.id.imageViewID).getTranslationX());
                        page.findViewById(R.id.imageViewID).setTranslationX(-position * page.getWidth() / 2);

                }
            }

        });

        binding.rvRoomContainer.setLayoutManager(new LinearLayoutManager(this));
        roomAdapter = new ListRoomAdapter();
        binding.rvRoomContainer.setAdapter(roomAdapter);

    }
}

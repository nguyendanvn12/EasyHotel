package com.example.easyhotel.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.easyhotel.R;
import com.example.easyhotel.databinding.ActivityDetailsHotelBinding;
import com.example.easyhotel.view.adapter.MainBackgroundAdapter;

import java.util.ArrayList;

public class DetailsHotelActivity extends AppCompatActivity {
private ActivityDetailsHotelBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_hotel);

        binding.vpSlider.setAdapter(new MainBackgroundAdapter());
        ViewPager viewPager = new ViewPager(this);
    }
}

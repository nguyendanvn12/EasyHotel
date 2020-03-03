package com.example.easyhotel.view.adapter;

import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.easyhotel.R;

import java.util.ArrayList;

public class MainBackgroundAdapter extends androidx.viewpager.widget.PagerAdapter {
    private ArrayList<Integer> imgs ;

    public MainBackgroundAdapter() {
        this.imgs = new ArrayList<Integer>();
        imgs.add(R.drawable.jeju);
        imgs.add(R.drawable.du);
        imgs.add(R.drawable.hoian);
        imgs.add(R.drawable.kur);
        imgs.add(R.drawable.sing);
        imgs.add(R.drawable.background1);
        imgs.add(R.drawable.background2);
        imgs.add(R.drawable.background3);
        imgs.add(R.drawable.background4);
        imgs.add(R.drawable.background5);

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        int pos = position%imgs.size();
        Glide.with(container.getContext()).load(imgs.get(pos))
                .centerCrop().into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

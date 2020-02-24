package com.example.easyhotel.view;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("text")
    public static void setBackgroundColor(View view,CharSequence text){
        view.getBackground().setColorFilter(Color.parseColor("#999"), PorterDuff.Mode.SRC_ATOP);
    }
}

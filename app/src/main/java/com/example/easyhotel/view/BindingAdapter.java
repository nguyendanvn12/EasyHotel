package com.example.easyhotel.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import com.example.easyhotel.R;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("flexBackground")
    public static void setBackgroundColor(View view,float point){
        GradientDrawable bg = (GradientDrawable) view.getBackground();

        if(point>=9){
            bg.setColor(Color.parseColor("#FD5A2D"));
        }else if(point>=8){
            bg.setColor(Color.parseColor("#428DC5"));
        }else if(point>=7){
            bg.setColor(Color.parseColor("#428DC5"));
        }else {
            bg.setColor(Color.parseColor("#999999"));
        }
    }
    @androidx.databinding.BindingAdapter("textColor")
    public static void setTextColor(TextView view, float point){

        if(point>=9){
            view.setTextColor(Color.parseColor("#FD5A2D"));
            if(point>9.5){
                view.setText("Xuất sắc");
            }else
            view.setText("Tuyệt hảo");
        }else if(point>=8){
            view.setTextColor(Color.parseColor("#428DC5"));
            view.setText("Rất tốt");
        }else if(point>=7){
            view.setTextColor(Color.parseColor("#428DC5"));
            view.setText("Tốt");
        }else {
            view.setTextColor(Color.parseColor("#999999"));
            view.setText("Điểm đánh giá");
        }
    }
    @androidx.databinding.BindingAdapter("star")
    public static void setStar(TextView view,int star){
        switch (star){
            case 1:
                view.setText("\uf005");
                break;
            case 2:
                view.setText("\uf005\uf005");
                break;
            case 3:
                view.setText("\uf005\uf005\uf005");
                break;
            case 4:
                view.setText("\uf005\uf005\uf005\uf005");
                break;
            case 5:
                view.setText("\uf005\uf005\uf005\uf005\uf005");
                break;
        }
    }
    @androidx.databinding.BindingAdapter("hideIfZero")
    public static void hide(View view,int discount){
        view.setVisibility(discount==0?View.GONE:View.VISIBLE);
    }
}

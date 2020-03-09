package com.example.easyhotel.view;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.example.easyhotel.R;
import com.example.easyhotel.data.model.roominfo.Bed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("flexBackground")
    public static void setBackgroundColor(@NonNull View view, float point) {
        GradientDrawable bg = (GradientDrawable) view.getBackground();

        if (point >= 9) {
            bg.setColor(Color.parseColor("#FD5A2D"));
        } else if (point >= 8) {
            bg.setColor(Color.parseColor("#428DC5"));
        } else if (point >= 7) {
            bg.setColor(Color.parseColor("#428DC5"));
        } else {
            bg.setColor(Color.parseColor("#999999"));
        }
    }

    @androidx.databinding.BindingAdapter("textColor")
    public static void setTextColor(TextView view, float point) {

        if (point >= 9) {
            view.setTextColor(Color.parseColor("#FD5A2D"));
            if (point > 9.5) {
                view.setText("Xuất sắc");
            } else
                view.setText("Tuyệt hảo");
        } else if (point >= 8) {
            view.setTextColor(Color.parseColor("#428DC5"));
            view.setText("Rất tốt");
        } else if (point >= 7) {
            view.setTextColor(Color.parseColor("#428DC5"));
            view.setText("Tốt");
        } else if (point == 0) {
            view.setTextColor(Color.parseColor("#999999"));
            view.setText("Chưa có đánh giá");
        } else {
            view.setTextColor(Color.parseColor("#999999"));
            view.setText("Điểm đánh giá");
        }
    }

    @androidx.databinding.BindingAdapter("star")
    public static void setStar(TextView view, int star) {
        switch (star) {
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
    public static void hide(@NonNull View view, int discount) {
        view.setVisibility(discount == 0 ? View.GONE : View.VISIBLE);
    }

    @androidx.databinding.BindingAdapter("mota")
    public static void setMota(TextView view, String s) {
        if (s != null) {
           String x = s.replaceAll("\\\\n","<br/>").replaceAll("\\\\","").toString();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.setText(Html.fromHtml(x, Html.FROM_HTML_MODE_COMPACT));
            } else {
                view.setText(Html.fromHtml(x));
            }
        }
    }
    @androidx.databinding.BindingAdapter("bed")
    public static void setBed(TextView textView, List<Bed> beds){
        String s = "";
        for (Bed bed:beds
             ) {
            if(s.length()!=0){
                s+=" | ";
            }
            s+=bed.getRoomBedCount()+" ";
            s+=bed.getRoomBedName();
        }
        textView.setText(s);
    }
    @androidx.databinding.BindingAdapter("bedDetails")
    public static void setBed1(TextView textView, List<Bed> beds){
        String s = "";
        for (Bed bed:beds
        ) {
            if(s.length()!=0){
                s+=" | ";
            }
            s+=bed.getRoomBedCount()+" ";
            s+=bed.getRoomBedName();
        }
        textView.setText(Html.fromHtml(textView.getContext().getString(R.string.bed,s),Html.FROM_HTML_MODE_COMPACT));
    }
    @androidx.databinding.BindingAdapter(value = {"checkin","duration"},requireAll = true)
    public static void setRentDuration(TextView view,long checkIn,int duration){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        try {
            Date checkInd = new Date(checkIn);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkInd);
            calendar.add(Calendar.DATE,duration);
            view.setText(duration+" đêm( "+dateFormat.format(checkInd)+"-"+dateFormat.format(calendar.getTime())+")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @androidx.databinding.BindingAdapter("checkindetails")
    public static void setCheckIn(TextView view,long checkIn){
        Date date = new Date(checkIn);
        SimpleDateFormat format = new SimpleDateFormat("EE, dd MMM");
        view.setText(format.format(date));
    }
    @androidx.databinding.BindingAdapter({"checkindetails","duration"})
    public static void setCheckOut(TextView view, long checkIn,int duration){
        Date date = new Date(checkIn);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(checkIn);
        calendar.add(Calendar.DATE,duration);
        SimpleDateFormat format = new SimpleDateFormat("EE, dd MMM");
        view.setText(format.format(calendar.getTime())+"("+duration+" đêm)");
    }
    @androidx.databinding.BindingAdapter("radioButtons")
    public static void addRadioButtons(RadioGroup group,List<Bed> beds) {
        RadioButton button;
        try{
            for (Bed bed:beds
            ) {
                button = new RadioButton(group.getContext());
                button.setText(bed.getRoomBedCount()+" "+bed.getRoomBedName());
                button.setBackgroundResource(R.drawable.bg_border_rectangle);
                RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,12,0,12);
                button.setLayoutParams(lp);
                button.setId(bed.getRoomBedId());
                group.addView(button);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @androidx.databinding.BindingAdapter("date")
    public static void setDate(TextView view,long dateL){
        Date date = new Date(dateL);
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy EEEE");
        String text = view.getContext().getString(R.string.date,format.format(date),format1.format(date));
        view.setText(Html.fromHtml(text,Html.FROM_HTML_MODE_COMPACT));
    }
    @androidx.databinding.BindingAdapter("date1")
    public static void setDate1(TextView view,long date1){
        Date date = new Date(date1);
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy");
        view.setText(format.format(date));
    }
    @androidx.databinding.BindingAdapter("hotelThumb")
    public static void setThumb(ImageView view,String url) {
        Glide.with(view.getContext()).load("http://10.1.42.83/hotel/resource/hotel/"+url)
                .centerCrop().into(view);
    }
}

package com.example.easyhotel.data.converter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataConverter {

    @NonNull
    public static String dateStringFormat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE - dd/MM/yyyy");
        Log.d("ccc",date.toString());
        return simpleDateFormat.format(date);
    }

    public static String currency(int money){
         return NumberFormat.getCurrencyInstance(new Locale("vi","VN")).format(money);
    }

}

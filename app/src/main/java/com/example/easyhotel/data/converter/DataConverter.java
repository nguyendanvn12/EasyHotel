package com.example.easyhotel.data.converter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverter {

    @NonNull
    public static String dateStringFormat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE - dd/MM/yyyy");
        Log.d("ccc",date.toString());
        return simpleDateFormat.format(date);
    }
}

package com.example.easyhotel.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainViewModel extends ViewModel {
    private  MutableLiveData<String> _keyword = new MutableLiveData<>("Tìm kiếm điểm đến khách sạn");
    private  MutableLiveData<Date> _date = new MutableLiveData<>(new Date());
    private  MutableLiveData<Integer> _duration = new MutableLiveData<>(1);

    public LiveData<String> keyword = _keyword;
    public LiveData<Integer> duration = _duration;
    public LiveData<Date> date = _date;
    public void set_keyword(String keyword) {
        this._keyword.setValue(keyword);
    }

    public void set_duration(Integer _duration) {
        this._duration.setValue(_duration);
    }

    public void set_date(Date _date) {
        this._date.setValue( _date);
    }

}

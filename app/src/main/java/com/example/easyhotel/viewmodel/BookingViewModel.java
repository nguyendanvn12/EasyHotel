package com.example.easyhotel.viewmodel;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyhotel.BR;

public class BookingViewModel extends ViewModel  {
     public MutableLiveData<Boolean> isCheckIn = new MutableLiveData<>(true);
     public MutableLiveData<Boolean> cmt = new MutableLiveData<>(false);

}

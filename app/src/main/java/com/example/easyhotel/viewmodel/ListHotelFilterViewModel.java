package com.example.easyhotel.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListHotelFilterViewModel extends ViewModel {
private MutableLiveData<Long> checkInDate = new MutableLiveData<>();
private MutableLiveData<Integer> duration = new MutableLiveData<>();

    public LiveData<Long> getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(long checkInDate) {
        this.checkInDate.setValue(checkInDate);
    }

    public LiveData<Integer> getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.setValue(duration);
    }
}

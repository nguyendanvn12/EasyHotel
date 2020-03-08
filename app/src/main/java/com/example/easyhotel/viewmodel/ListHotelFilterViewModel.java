package com.example.easyhotel.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListHotelFilterViewModel extends ViewModel {
private MutableLiveData<Long> checkInDate = new MutableLiveData<>();
private MutableLiveData<Integer> duration = new MutableLiveData<>();
private MutableLiveData<Integer> roomCount = new MutableLiveData<>(1);

    public LiveData<Integer> getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount.setValue(roomCount);
    }

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

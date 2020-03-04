package com.example.easyhotel.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyhotel.data.model.roominfo.Room;
import com.example.easyhotel.data.repository.RoomRepo;

import java.util.Date;
import java.util.List;

public class RoomViewModel extends ViewModel {
    private final RoomRepo repo = new RoomRepo();
    private  MutableLiveData<Date> _date = new MutableLiveData<>();
    private  MutableLiveData<Integer> _duration = new MutableLiveData<>();
    public LiveData<List<Room>> getRooms(int hotelId){
        return repo.getRooms(hotelId);
    }

}

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
    private MutableLiveData<List<Room>> rooms = new MutableLiveData<>();
    private MutableLiveData<Room> _activeRoom = new MutableLiveData<>();
    private MutableLiveData<Integer> _rate = new MutableLiveData<>();
    private MutableLiveData<Integer> _bed = new MutableLiveData<>();


    public LiveData<Room> activeRoom = _activeRoom;
    public LiveData<Integer> rate = _rate;
    public LiveData<Integer> bed = _bed;
    public LiveData<List<Room>> getRooms(int hotelId){
        rooms= repo.getRooms(hotelId);
        return rooms;
    }

    public void selectBed(int _bed) {
        this._bed.setValue(_bed);
    }

    public void setActiveRoom(int room, int rate) {
        this._activeRoom.setValue(rooms.getValue().get(room));
        this._bed.setValue(rooms.getValue().get(room).getBed().get(0).getRoomBedId());
        this._rate.setValue(rate);
    }
}

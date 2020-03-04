package com.example.easyhotel.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyhotel.data.model.Hotel;
import com.example.easyhotel.data.repository.DataRepo;
import com.example.easyhotel.data.repository.HotelRePo;

import java.util.List;

public class ListHotelViewModel extends ViewModel {
    private HotelRePo dataRepo = new HotelRePo();

    public LiveData<List<Hotel>> getListHotel(@Nullable Integer stateId, @Nullable Integer cityId, @Nullable Integer pages, @Nullable Integer min, @Nullable Integer max, @Nullable Integer star){
        return dataRepo.getListHotel(stateId, cityId, pages, min, max, star);
    }
}

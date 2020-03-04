package com.example.easyhotel.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.easyhotel.data.model.roominfo.Room;
import com.example.easyhotel.data.remote.RetrofitClient;
import com.example.easyhotel.data.remote.SearchService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomRepo {
    private final SearchService service = RetrofitClient.getClient();
    private MutableLiveData<List<Room>> list = new MutableLiveData<>();

    public MutableLiveData<List<Room>> getRooms(int hotelId) {
        service.getRoomAvaibility(hotelId).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                try {
                    list.setValue(response.body());
                } catch (Exception e) {
                    Log.d("RoomRepo", "onResponse: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return list;
    }
}

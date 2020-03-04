package com.example.easyhotel.data.repository;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.easyhotel.data.model.Hotel;
import com.example.easyhotel.data.model.hoteldetails.HotelDetails;
import com.example.easyhotel.data.remote.RetrofitClient;
import com.example.easyhotel.data.remote.SearchService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelRePo {
    private final SearchService service = RetrofitClient.getClient();
    private MutableLiveData<List<Hotel>> hotes = new MutableLiveData<>();
    private MutableLiveData<HotelDetails> hotelDetails = new MutableLiveData<>();
    public MutableLiveData<List<Hotel>> getListHotel(@Nullable Integer stateId, @Nullable Integer cityId, @Nullable Integer pages, @Nullable Integer min, @Nullable Integer max, @Nullable Integer star) {
        service.getListHotel(stateId, cityId, pages, min, max, star).enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                hotes.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return hotes;
    }

    public MutableLiveData<HotelDetails> getHotelDetails(int hotelId) {
        service.getDetailsHotel(hotelId).enqueue(new Callback<List<HotelDetails>>() {
            @Override
            public void onResponse(Call<List<HotelDetails>> call, Response<List<HotelDetails>> response) {
                hotelDetails.setValue(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<HotelDetails>> call, Throwable t) {
                t.printStackTrace();
                Log.d("ccc","f");
            }
        });
        return hotelDetails;
    }
}

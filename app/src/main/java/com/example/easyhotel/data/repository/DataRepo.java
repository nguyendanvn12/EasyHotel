package com.example.easyhotel.data.repository;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.easyhotel.data.model.Hotel;
import com.example.easyhotel.data.model.hoteldetails.HotelDetails;
import com.example.easyhotel.data.model.searchresult.SearchResult;
import com.example.easyhotel.data.remote.RetrofitClient;
import com.example.easyhotel.data.remote.SearchService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepo {
    private MutableLiveData<SearchResult> searchResultMutableLiveData = new MutableLiveData<>();
    private final SearchService service = RetrofitClient.getClient();

    public MutableLiveData<SearchResult> getSearchResult(String keyword) {
        service.getSearch(keyword).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.body() != null) {
                    searchResultMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return searchResultMutableLiveData;
    }



}

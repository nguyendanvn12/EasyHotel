package com.example.easyhotel.data.repository;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.easyhotel.data.model.Hotel;
import com.example.easyhotel.data.model.searchresult.SearchResult;
import com.example.easyhotel.data.remote.RetrofitClient;
import com.example.easyhotel.data.remote.SearchService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepo {
    private MutableLiveData<SearchResult> searchResultMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Hotel>> hotes = new MutableLiveData<>();
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

    public MutableLiveData<List<Hotel>> getListHotel(@Nullable Integer stateId, @Nullable Integer cityId, @Nullable Integer pages, @Nullable Integer min, @Nullable Integer max, @Nullable Integer star) {
        service.getListHotel(stateId, cityId, pages, min, max, star).enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                hotes.setValue(response.body());
                Log.d("ccc","ccc12");
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                t.printStackTrace();
                Log.d("ccc","ccc1");
            }
        });
        return hotes;
    }
}

package com.example.easyhotel.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyhotel.data.model.searchresult.SearchResult;
import com.example.easyhotel.data.remote.RetrofitClient;
import com.example.easyhotel.data.remote.SearchService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DataRepo {
      private   MutableLiveData<SearchResult> searchResultMutableLiveData = new MutableLiveData<>();
      private final SearchService service = RetrofitClient.getClient();
      public MutableLiveData<SearchResult> getSearchResult(String keyword){
          service.getSearch(keyword).enqueue(new Callback<SearchResult>() {
              @Override
              public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                  if(response.body()!=null){
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

package com.example.easyhotel.data.remote;

import com.example.easyhotel.data.model.searchresult.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("search")
    Call<SearchResult> getSearch(@Query("keyword")String keyword);
}

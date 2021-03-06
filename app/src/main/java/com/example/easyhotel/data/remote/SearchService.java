package com.example.easyhotel.data.remote;

import com.example.easyhotel.data.model.Hotel;
import com.example.easyhotel.data.model.hoteldetails.HotelDetails;
import com.example.easyhotel.data.model.roominfo.Room;
import com.example.easyhotel.data.model.searchresult.SearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("search")
    Call<SearchResult> getSearch(@Query("keyword")String keyword);

    @GET("list-hotel-availibity")
    Call<List<Hotel>> getListHotel(@Query("state_id")Integer stateId, @Query("city_id")Integer cityId, @Query("pages")Integer pages, @Query("min_price")Integer minPrice, @Query("max_price")Integer maxPrice,
                            @Query("star")Integer star);
    @GET("details-hotel")
    Call<List<HotelDetails>> getDetailsHotel(@Query("hotel_id")Integer hotel_id);

    @GET("room-avaibility")
    Call<List<Room>> getRoomAvaibility(@Query("hotel_id")Integer hotelId);
}
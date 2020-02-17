package com.example.easyhotel.view;

import android.view.View;

import com.example.easyhotel.data.model.SearchModel;

public interface Event {
     void onLocalSearchClick();
     void onPickCheckIn();
     void onPickDuration();
     void onSearchClick();
     void itemPlaceClick(SearchModel model);
     void itemHotelClick();
     void itemGPSClick();
}

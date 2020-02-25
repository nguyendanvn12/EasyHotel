package com.example.easyhotel.view;

import com.example.easyhotel.data.model.Hotel;

public interface ListHotelEvent {
    void back();
    void call();
    void openSapXep();
    void openLoc();
    void openMap();
    void pickHotel(int hotelId);
}

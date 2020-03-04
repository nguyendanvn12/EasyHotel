
package com.example.easyhotel.data.model.roominfo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("room_id")
    @Expose
    private Integer roomId;
    @SerializedName("hotel_id")
    @Expose
    private Integer hotelId;
    @SerializedName("room_name")
    @Expose
    private String roomName;
    @SerializedName("room_count")
    @Expose
    private Integer roomCount;
    @SerializedName("room_size")
    @Expose
    private Float roomSize;
    @SerializedName("room_capacity")
    @Expose
    private Integer roomCapacity;
    @SerializedName("room_description")
    @Expose
    private String roomDescription;
    @SerializedName("room_facilities")
    @Expose
    private String roomFacilities;
    @SerializedName("imgs")
    @Expose
    private List<String> imgs = null;

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    @SerializedName("bed")
    @Expose
    private List<Bed> bed = null;
    @SerializedName("rates")
    @Expose
    private List<Rate> rates = null;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Float getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Float roomSize) {
        this.roomSize = roomSize;
    }

    public Integer getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(String roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    public List<Bed> getBed() {
        return bed;
    }

    public void setBed(List<Bed> bed) {
        this.bed = bed;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

}

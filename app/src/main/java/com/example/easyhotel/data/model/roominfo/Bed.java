
package com.example.easyhotel.data.model.roominfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bed {

    @SerializedName("room_bed_id")
    @Expose
    private Integer roomBedId;
    @SerializedName("room_bed_name")
    @Expose
    private String roomBedName;
    @SerializedName("room_bed_count")
    @Expose
    private Integer roomBedCount;

    public Integer getRoomBedId() {
        return roomBedId;
    }

    public void setRoomBedId(Integer roomBedId) {
        this.roomBedId = roomBedId;
    }

    public String getRoomBedName() {
        return roomBedName;
    }

    public void setRoomBedName(String roomBedName) {
        this.roomBedName = roomBedName;
    }

    public Integer getRoomBedCount() {
        return roomBedCount;
    }

    public void setRoomBedCount(Integer roomBedCount) {
        this.roomBedCount = roomBedCount;
    }
}

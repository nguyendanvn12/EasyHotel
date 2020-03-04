
package com.example.easyhotel.data.model.roominfo;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rate {

    @SerializedName("room_rates_id")
    @Expose
    private Integer roomRatesId;
    @SerializedName("breakfast")
    @Expose
    private Boolean breakfast;
    @SerializedName("origin_price")
    @Expose
    private Integer originPrice;
    @SerializedName("refund")
    @Expose
    private Boolean refund;
    @SerializedName("refund_details")
    @Expose
    private String refundDetails;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("start_discount")
    @Expose
    private Long startDiscount;
    @SerializedName("end_discount")
    @Expose
    private Long endDiscount;

    public Integer getRoomRatesId() {
        return roomRatesId;
    }

    public void setRoomRatesId(Integer roomRatesId) {
        this.roomRatesId = roomRatesId;
    }

    public Boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public Integer getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Integer originPrice) {
        this.originPrice = originPrice;
    }

    public Boolean getRefund() {
        return refund;
    }

    public void setRefund(Boolean refund) {
        this.refund = refund;
    }

    public String getRefundDetails() {
        return refundDetails;
    }

    public void setRefundDetails(String refundDetails) {
        this.refundDetails = refundDetails;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Long getStartDiscount() {
        return startDiscount;
    }

    public void setStartDiscount(Long startDiscount) {
        this.startDiscount = startDiscount;
    }

    public Long getEndDiscount() {
        return endDiscount;
    }

    public void setEndDiscount(Long endDiscount) {
        this.endDiscount = endDiscount;
    }
}

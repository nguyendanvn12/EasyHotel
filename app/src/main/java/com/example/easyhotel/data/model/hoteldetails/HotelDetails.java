
package com.example.easyhotel.data.model.hoteldetails;

import android.util.Log;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelDetails {

    @SerializedName("hotel_id")
    @Expose
    private Integer hotelId;
    @SerializedName("hotel_name")
    @Expose
    private String hotelName;
    @SerializedName("star")
    @Expose
    private Integer star;
    @SerializedName("review_point")
    @Expose
    private Float reviewPoint;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("state_id")
    @Expose
    private Integer stateId;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("imgs")
    @Expose
    private List<String> imgs = null;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("origin_price")
    @Expose
    private String originPrice;
    @SerializedName("sale_price")
    @Expose
    private Integer salePrice;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("tiennghi")
    @Expose
    private String tiennghi;
    @SerializedName("mota")
    @Expose
    private String mota;
    @SerializedName("check_in_time")
    @Expose
    private String checkInTime;
    @SerializedName("check_out_time")
    @Expose
    private String checkOutTime;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Integer getCityId() {
        return cityId;
    }


    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }



    public String getTiennghi() {
        return tiennghi;
    }

    public void setTiennghi(String tiennghi) {
        this.tiennghi = tiennghi;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Float getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(Float reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}

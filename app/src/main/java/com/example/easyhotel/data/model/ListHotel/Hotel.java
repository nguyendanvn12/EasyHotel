
package com.example.easyhotel.data.model.ListHotel;

import android.content.Intent;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotel {

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
    @SerializedName("num_review")
    @Expose
    private Integer numReview;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city_id")
    @Expose
    private Long cityId;
    @SerializedName("state_id")
    @Expose
    private Long stateId;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("imgs")
    @Expose
    private List<String> imgs = null;
    @SerializedName("origin_price")
    @Expose
    private Integer originPrice;
    @SerializedName("sale_price")
    @Expose
    private Integer salePrice;
    @SerializedName("discount")
    @Expose
    private Integer discount;

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

    public Integer getNumReview() {
        return numReview;
    }

    public void setNumReview(Integer numReview) {
        this.numReview = numReview;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public Integer getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Integer originPrice) {
        this.originPrice = originPrice;
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
}

package com.example.easyhotel.data.model;

import java.io.Serializable;

public class SearchModel implements Serializable {
    private int type;
    private int id;
    private String title;
    private String place;

    public SearchModel(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public SearchModel(int type, int id, String title, String place) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.place = place;
    }

    public SearchModel(int type, int id, String title) {
        this.type = type;
        this.id = id;
        this.title = title;
    }

    public SearchModel(int type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

public class ChangeBean {

    @SerializedName("id")
    private Integer id;
    @SerializedName("adult")
    private Boolean adult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }
}

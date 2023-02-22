package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

public class RatingBody {
    @SerializedName("value")
    private Float value;

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}

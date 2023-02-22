package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

public class AlternativeTitles {

    @SerializedName("iso_3166_1")
    private String iso31661;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

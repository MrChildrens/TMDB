package com.goku.tmdb.data.entity.movie;

import com.goku.tmdb.data.entity.AlternativeTitles;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieAlternativeTitles {

    @SerializedName("id")
    private Integer id;
    @SerializedName("titles")
    private List<AlternativeTitles> titles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AlternativeTitles> getTitles() {
        return titles;
    }

    public void setTitles(List<AlternativeTitles> titles) {
        this.titles = titles;
    }
}

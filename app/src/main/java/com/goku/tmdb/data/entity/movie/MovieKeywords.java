package com.goku.tmdb.data.entity.movie;

import com.goku.tmdb.data.entity.Keyword;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieKeywords {
    @SerializedName("id")
    private Integer id;
    @SerializedName("keywords")
    private List<Keyword> keywords;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

}

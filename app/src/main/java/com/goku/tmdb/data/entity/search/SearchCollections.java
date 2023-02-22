package com.goku.tmdb.data.entity.search;

import com.google.gson.annotations.SerializedName;

public class SearchCollections {
    @SerializedName("id")
    private Integer id;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("name")
    private String name;
    @SerializedName("poster_path")
    private String posterPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}

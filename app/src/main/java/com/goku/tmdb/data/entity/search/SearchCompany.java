package com.goku.tmdb.data.entity.search;

import com.google.gson.annotations.SerializedName;

public class SearchCompany {
    @SerializedName("id")
    private Integer id;
    @SerializedName("logo_path")
    private String logoPath;
    @SerializedName("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

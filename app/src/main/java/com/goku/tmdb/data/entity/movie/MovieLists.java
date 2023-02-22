package com.goku.tmdb.data.entity.movie;

import com.google.gson.annotations.SerializedName;

public class MovieLists {

    @SerializedName("description")
    private String description;
    @SerializedName("favorite_count")
    private Integer favoriteCount;
    @SerializedName("id")
    private Integer id;
    @SerializedName("item_count")
    private Integer itemCount;
    @SerializedName("iso_639_1")
    private String iso6391;
    @SerializedName("list_type")
    private String listType;
    @SerializedName("name")
    private String name;
    @SerializedName("poster_path")
    private Object posterPath;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(Object posterPath) {
        this.posterPath = posterPath;
    }
}

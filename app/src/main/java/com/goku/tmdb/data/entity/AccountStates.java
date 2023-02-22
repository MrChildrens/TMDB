package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

public class AccountStates {
    @SerializedName("id")
    private Integer id;
    @SerializedName("favorite")
    private Boolean favorite;
    @SerializedName("rated")
    private Rated rated;
    @SerializedName("watchlist")
    private Boolean watchlist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Rated getRated() {
        return rated;
    }

    public void setRated(Rated rated) {
        this.rated = rated;
    }

    public Boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        this.watchlist = watchlist;
    }

    public static class Rated {
        @SerializedName("value")
        private Float value;

        public Float getValue() {
            return value;
        }

        public void setValue(Float value) {
            this.value = value;
        }
    }


}

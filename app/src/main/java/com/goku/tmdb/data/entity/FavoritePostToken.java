package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

public class FavoritePostToken {
    @SerializedName("media_type")
    private String mediaType;
    @SerializedName("media_id")
    private Long mediaId;
    @SerializedName("favorite")
    private Boolean favorite;
    @SerializedName("watchlist")
    private Boolean watchlist;

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        this.watchlist = watchlist;
    }
}

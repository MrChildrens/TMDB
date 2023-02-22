package com.goku.tmdb.data.entity.tv;

import com.google.gson.annotations.SerializedName;

public class TvScreenedTheatrically {

    @SerializedName("id")
    private Integer id;
    @SerializedName("episode_number")
    private Integer episodeNumber;
    @SerializedName("season_number")
    private Integer seasonNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
}

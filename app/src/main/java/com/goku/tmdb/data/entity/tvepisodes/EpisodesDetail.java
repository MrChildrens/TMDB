package com.goku.tmdb.data.entity.tvepisodes;

import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.Credits;
import com.goku.tmdb.data.entity.Crew;
import com.goku.tmdb.data.entity.ExternalIds;
import com.goku.tmdb.data.entity.GuestStars;
import com.goku.tmdb.data.entity.Translations;
import com.goku.tmdb.data.entity.Video;
import com.goku.tmdb.data.entity.movie.MediaImages;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EpisodesDetail {
    @SerializedName("air_date")
    private String airDate;
    @SerializedName("episode_number")
    private Integer episodeNumber;
    @SerializedName("name")
    private String name;
    @SerializedName("overview")
    private String overview;
    @SerializedName("id")
    private Integer id;
    @SerializedName("production_code")
    private String productionCode;
    @SerializedName("season_number")
    private Integer seasonNumber;
    @SerializedName("still_path")
    private String stillPath;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("crew")
    private List<Crew> crew;
    @SerializedName("guest_stars")
    private List<GuestStars> guestStars;
    @SerializedName("runtime")
    private Integer runtime;

    @SerializedName("credits")
    private Credits mCredits;

    @SerializedName("external_ids")
    private ExternalIds mExternalIds;

    @SerializedName("images")
    private MediaImages mMediaImages;

    @SerializedName("translations")
    private Translations mTranslations;

    @SerializedName("videos")
    private BaseResultBean<Video> mVideoBean;

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getStillPath() {
        return stillPath;
    }

    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public List<GuestStars> getGuestStars() {
        return guestStars;
    }

    public void setGuestStars(List<GuestStars> guestStars) {
        this.guestStars = guestStars;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Credits getCredits() {
        return mCredits;
    }

    public void setCredits(Credits credits) {
        mCredits = credits;
    }

    public ExternalIds getExternalIds() {
        return mExternalIds;
    }

    public void setExternalIds(ExternalIds externalIds) {
        mExternalIds = externalIds;
    }

    public MediaImages getMediaImages() {
        return mMediaImages;
    }

    public void setMediaImages(MediaImages mediaImages) {
        mMediaImages = mediaImages;
    }

    public Translations getTranslations() {
        return mTranslations;
    }

    public void setTranslations(Translations translations) {
        mTranslations = translations;
    }

    public BaseResultBean<Video> getVideoBean() {
        return mVideoBean;
    }

    public void setVideoBean(BaseResultBean<Video> videoBean) {
        mVideoBean = videoBean;
    }

}

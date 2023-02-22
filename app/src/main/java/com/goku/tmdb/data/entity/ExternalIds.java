package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

public class ExternalIds {

    @SerializedName("id")
    private Integer id;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("freebase_mid")
    private String freebaseMid;
    @SerializedName("freebase_id")
    private String freebaseId;
    @SerializedName("tvdb_id")
    private Integer tvdbId;
    @SerializedName("tvrage_id")
    private Integer tvrageId;
    @SerializedName("wikidata_id")
    private String wikidataId;
    @SerializedName("facebook_id")
    private String facebookId;
    @SerializedName("instagram_id")
    private String instagramId;
    @SerializedName("twitter_id")
    private String twitterId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Object getFreebaseMid() {
        return freebaseMid;
    }

    public void setFreebaseMid(String freebaseMid) {
        this.freebaseMid = freebaseMid;
    }

    public Object getFreebaseId() {
        return freebaseId;
    }

    public void setFreebaseId(String freebaseId) {
        this.freebaseId = freebaseId;
    }

    public Integer getTvdbId() {
        return tvdbId;
    }

    public void setTvdbId(Integer tvdbId) {
        this.tvdbId = tvdbId;
    }

    public Integer getTvrageId() {
        return tvrageId;
    }

    public void setTvrageId(Integer tvrageId) {
        this.tvrageId = tvrageId;
    }

    public String getWikidataId() {
        return wikidataId;
    }

    public void setWikidataId(String wikidataId) {
        this.wikidataId = wikidataId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getInstagramId() {
        return instagramId;
    }

    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }
}

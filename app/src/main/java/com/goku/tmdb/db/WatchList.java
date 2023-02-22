package com.goku.tmdb.db;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

@Entity
public class WatchList {
    @Id
    @SerializedName("id")
    private Long id;
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_name")
    private String originalName;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("name")
    private String name;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("genre_ids")
    @Convert(columnType = String.class, converter = TmdbConverter.ListIntegerConverter.class)
    private List<Integer> genreIds;
    @Convert(columnType = String.class, converter = TmdbConverter.ListStringConverter.class)
    @SerializedName("origin_country")
    private List<String> originCountry;

    private Long accountId;
    private int page;
    @Generated(hash = 164904878)
    public WatchList(Long id, Boolean adult, String backdropPath, String originalLanguage,
            String originalName, String overview, Double popularity, String posterPath,
            String firstAirDate, String name, Double voteAverage, Integer voteCount,
            List<Integer> genreIds, List<String> originCountry, Long accountId, int page) {
        this.id = id;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.originalLanguage = originalLanguage;
        this.originalName = originalName;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.firstAirDate = firstAirDate;
        this.name = name;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.genreIds = genreIds;
        this.originCountry = originCountry;
        this.accountId = accountId;
        this.page = page;
    }
    @Generated(hash = 939563304)
    public WatchList() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getAdult() {
        return this.adult;
    }
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }
    public String getBackdropPath() {
        return this.backdropPath;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
    public String getOriginalLanguage() {
        return this.originalLanguage;
    }
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
    public String getOriginalName() {
        return this.originalName;
    }
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
    public String getOverview() {
        return this.overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public Double getPopularity() {
        return this.popularity;
    }
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
    public String getPosterPath() {
        return this.posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public String getFirstAirDate() {
        return this.firstAirDate;
    }
    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getVoteAverage() {
        return this.voteAverage;
    }
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
    public Integer getVoteCount() {
        return this.voteCount;
    }
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
    public List<Integer> getGenreIds() {
        return this.genreIds;
    }
    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }
    public List<String> getOriginCountry() {
        return this.originCountry;
    }
    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }
    public Long getAccountId() {
        return this.accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public int getPage() {
        return this.page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    

}

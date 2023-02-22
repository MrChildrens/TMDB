package com.goku.tmdb.data.entity.movie;

import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.Credits;
import com.goku.tmdb.data.entity.ExternalIds;
import com.goku.tmdb.data.entity.Genres;
import com.goku.tmdb.data.entity.ProductionCompanies;
import com.goku.tmdb.data.entity.ProductionCountries;
import com.goku.tmdb.data.entity.SpokenLanguages;
import com.goku.tmdb.data.entity.Translations;
import com.goku.tmdb.data.entity.Video;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetail {

    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("belongs_to_collection")
    private BelongsToCollection belongsToCollection;
    @SerializedName("budget")
    private Integer budget;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("id")
    private Integer id;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("revenue")
    private Long revenue;
    @SerializedName("runtime")
    private Integer runtime;
    @SerializedName("status")
    private String status;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("title")
    private String title;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("genres")
    private List<Genres> genres;
    @SerializedName("production_companies")
    private List<ProductionCompanies> productionCompanies;
    @SerializedName("production_countries")
    private List<ProductionCountries> productionCountries;
    @SerializedName("spoken_languages")
    private List<SpokenLanguages> spokenLanguages;

    @SerializedName("alternative_titles")
    private MovieAlternativeTitles mAlternativeTitles;

    @SerializedName("credits")
    private Credits mCredits;

    @SerializedName("external_ids")
    private ExternalIds mExternalIds;

    @SerializedName("images")
    private MediaImages mMediaImages;

    @SerializedName("keywords")
    private MovieKeywords mMovieKeywords;

    @SerializedName("release_dates")
    private BaseResultBean<MovieReleaseDates> mMovieReleaseDates;

    @SerializedName("translations")
    private Translations mTranslations;

    @SerializedName("videos")
    private BaseResultBean<Video> mVideoBean;

    @SerializedName("lists")
    private MovieLists mMovieLists;

    public Boolean isAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

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

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
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

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public List<ProductionCompanies> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompanies> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountries> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountries> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<SpokenLanguages> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguages> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public Boolean getAdult() {
        return adult;
    }

    public Boolean getVideo() {
        return video;
    }

    public MovieAlternativeTitles getAlternativeTitles() {
        return mAlternativeTitles;
    }

    public void setAlternativeTitles(MovieAlternativeTitles alternativeTitles) {
        mAlternativeTitles = alternativeTitles;
    }

    public Credits getCreditsBean() {
        return mCredits;
    }

    public void setCreditsBean(Credits credits) {
        mCredits = credits;
    }

    public ExternalIds getExternalIds() {
        return mExternalIds;
    }

    public void setExternalIds(ExternalIds externalIds) {
        mExternalIds = externalIds;
    }

    public MediaImages getMovieImages() {
        return mMediaImages;
    }

    public void setMediaImages(MediaImages mediaImages) {
        mMediaImages = mediaImages;
    }

    public MovieKeywords getMovieKeywords() {
        return mMovieKeywords;
    }

    public void setMovieKeywords(MovieKeywords movieKeywords) {
        mMovieKeywords = movieKeywords;
    }

    public BaseResultBean<MovieReleaseDates> getMovieReleaseDates() {
        return mMovieReleaseDates;
    }

    public void setMovieReleaseDates(BaseResultBean<MovieReleaseDates> movieReleaseDates) {
        mMovieReleaseDates = movieReleaseDates;
    }

    public Translations getTranslationsBean() {
        return mTranslations;
    }

    public void setTranslationsBean(Translations translations) {
        mTranslations = translations;
    }

    public BaseResultBean<Video> getVideoBean() {
        return mVideoBean;
    }

    public void setVideoBean(BaseResultBean<Video> videoBean) {
        mVideoBean = videoBean;
    }

    public MovieLists getMovieLists() {
        return mMovieLists;
    }

    public void setMovieLists(MovieLists movieLists) {
        mMovieLists = movieLists;
    }

    public static class BelongsToCollection {
        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("backdrop_path")
        private String backdropPath;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }
    }

}

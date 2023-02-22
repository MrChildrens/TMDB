package com.goku.tmdb.data.entity.tv;

import com.goku.tmdb.data.entity.AlternativeTitles;
import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.Credits;
import com.goku.tmdb.data.entity.ExternalIds;
import com.goku.tmdb.data.entity.Genres;
import com.goku.tmdb.data.entity.Keyword;
import com.goku.tmdb.data.entity.ProductionCompanies;
import com.goku.tmdb.data.entity.ProductionCountries;
import com.goku.tmdb.data.entity.SpokenLanguages;
import com.goku.tmdb.data.entity.Translations;
import com.goku.tmdb.data.entity.Video;
import com.goku.tmdb.data.entity.movie.MediaImages;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvDetail {
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("id")
    private Integer id;
    @SerializedName("in_production")
    private Boolean inProduction;
    @SerializedName("last_air_date")
    private String lastAirDate;
    @SerializedName("last_episode_to_air")
    private LastEpisodeToAir lastEpisodeToAir;
    @SerializedName("name")
    private String name;
    @SerializedName("next_episode_to_air")
    private LastEpisodeToAir nextEpisodeToAir;
    @SerializedName("number_of_episodes")
    private Integer numberOfEpisodes;
    @SerializedName("number_of_seasons")
    private Integer numberOfSeasons;
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
    @SerializedName("status")
    private String status;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("type")
    private String type;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("created_by")
    private List<CreatedBy> createdBy;
    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime;
    @SerializedName("genres")
    private List<Genres> genres;
    @SerializedName("languages")
    private List<String> languages;
    @SerializedName("networks")
    private List<Networks> networks;
    @SerializedName("origin_country")
    private List<String> originCountry;
    @SerializedName("production_companies")
    private List<ProductionCompanies> productionCompanies;
    @SerializedName("production_countries")
    private List<ProductionCountries> productionCountries;
    @SerializedName("seasons")
    private List<Seasons> seasons;
    @SerializedName("spoken_languages")
    private List<SpokenLanguages> spokenLanguages;

    @SerializedName("aggregate_credits")
    private Credits mAggregateCredits;

    @SerializedName("alternative_titles")
    private BaseResultBean<AlternativeTitles> mAlternativeTitles;

    @SerializedName("content_ratings")
    private BaseResultBean<TvContentRatings> mTvContentRatings;

    @SerializedName("credits")
    private Credits mCredits;

    @SerializedName("external_ids")
    private ExternalIds mExternalIds;

    @SerializedName("episode_groups")
    private BaseResultBean<TvEpisodeGroups> mTvEpisodeGroups;

    @SerializedName("images")
    private MediaImages mMediaImages;

    @SerializedName("keywords")
    private BaseResultBean<Keyword> mKeywords;

    @SerializedName("translations")
    private Translations mTranslations;

    @SerializedName("screened_theatrically")
    private BaseResultBean<TvScreenedTheatrically> mTvScreenedTheatrically;

    @SerializedName("videos")
    private BaseResultBean<Video> mVideoBean;

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

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
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

    public Boolean isInProduction() {
        return inProduction;
    }

    public void setInProduction(Boolean inProduction) {
        this.inProduction = inProduction;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public LastEpisodeToAir getLastEpisodeToAir() {
        return lastEpisodeToAir;
    }

    public void setLastEpisodeToAir(LastEpisodeToAir lastEpisodeToAir) {
        this.lastEpisodeToAir = lastEpisodeToAir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LastEpisodeToAir getNextEpisodeToAir() {
        return nextEpisodeToAir;
    }

    public void setNextEpisodeToAir(LastEpisodeToAir nextEpisodeToAir) {
        this.nextEpisodeToAir = nextEpisodeToAir;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<CreatedBy> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<CreatedBy> createdBy) {
        this.createdBy = createdBy;
    }

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<Networks> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Networks> networks) {
        this.networks = networks;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
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

    public List<Seasons> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Seasons> seasons) {
        this.seasons = seasons;
    }

    public List<SpokenLanguages> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguages> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public Credits getAggregateCredits() {
        return mAggregateCredits;
    }

    public void setAggregateCredits(Credits aggregateCredits) {
        mAggregateCredits = aggregateCredits;
    }

    public BaseResultBean<AlternativeTitles> getAlternativeTitles() {
        return mAlternativeTitles;
    }

    public void setAlternativeTitles(BaseResultBean<AlternativeTitles> alternativeTitles) {
        mAlternativeTitles = alternativeTitles;
    }

    public BaseResultBean<TvContentRatings> getTvContentRatings() {
        return mTvContentRatings;
    }

    public void setTvContentRatings(BaseResultBean<TvContentRatings> tvContentRatings) {
        mTvContentRatings = tvContentRatings;
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

    public BaseResultBean<TvEpisodeGroups> getTvEpisodeGroups() {
        return mTvEpisodeGroups;
    }

    public void setTvEpisodeGroups(BaseResultBean<TvEpisodeGroups> tvEpisodeGroups) {
        mTvEpisodeGroups = tvEpisodeGroups;
    }

    public MediaImages getMediaImages() {
        return mMediaImages;
    }

    public void setMediaImages(MediaImages mediaImages) {
        mMediaImages = mediaImages;
    }

    public BaseResultBean<Keyword> getKeywords() {
        return mKeywords;
    }

    public void setKeywords(BaseResultBean<Keyword> keywords) {
        mKeywords = keywords;
    }

    public Translations getTranslationsBean() {
        return mTranslations;
    }

    public void setTranslationsBean(Translations translations) {
        mTranslations = translations;
    }

    public BaseResultBean<TvScreenedTheatrically> getTvScreenedTheatrically() {
        return mTvScreenedTheatrically;
    }

    public void setTvScreenedTheatrically(BaseResultBean<TvScreenedTheatrically> tvScreenedTheatrically) {
        mTvScreenedTheatrically = tvScreenedTheatrically;
    }

    public BaseResultBean<Video> getVideoBean() {
        return mVideoBean;
    }

    public void setVideoBean(BaseResultBean<Video> videoBean) {
        mVideoBean = videoBean;
    }

    public static class LastEpisodeToAir {
        @SerializedName("air_date")
        private String airDate;
        @SerializedName("episode_number")
        private Integer episodeNumber;
        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("overview")
        private String overview;
        @SerializedName("production_code")
        private String productionCode;
        @SerializedName("runtime")
        private Integer runtime;
        @SerializedName("season_number")
        private Integer seasonNumber;
        @SerializedName("show_id")
        private Integer showId;
        @SerializedName("still_path")
        private String stillPath;
        @SerializedName("vote_average")
        private Double voteAverage;
        @SerializedName("vote_count")
        private Integer voteCount;

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

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getProductionCode() {
            return productionCode;
        }

        public void setProductionCode(String productionCode) {
            this.productionCode = productionCode;
        }

        public Integer getRuntime() {
            return runtime;
        }

        public void setRuntime(Integer runtime) {
            this.runtime = runtime;
        }

        public Integer getSeasonNumber() {
            return seasonNumber;
        }

        public void setSeasonNumber(Integer seasonNumber) {
            this.seasonNumber = seasonNumber;
        }

        public Integer getShowId() {
            return showId;
        }

        public void setShowId(Integer showId) {
            this.showId = showId;
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
    }

    public static class CreatedBy {
        @SerializedName("id")
        private Integer id;
        @SerializedName("credit_id")
        private String creditId;
        @SerializedName("name")
        private String name;
        @SerializedName("gender")
        private Integer gender;
        @SerializedName("profile_path")
        private Object profilePath;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCreditId() {
            return creditId;
        }

        public void setCreditId(String creditId) {
            this.creditId = creditId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public Object getProfilePath() {
            return profilePath;
        }

        public void setProfilePath(Object profilePath) {
            this.profilePath = profilePath;
        }
    }

    public static class Networks {
        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("logo_path")
        private String logoPath;
        @SerializedName("origin_country")
        private String originCountry;

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

        public String getLogoPath() {
            return logoPath;
        }

        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }

        public String getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(String originCountry) {
            this.originCountry = originCountry;
        }
    }

    public static class Seasons {
        @SerializedName("air_date")
        private String airDate;
        @SerializedName("episode_count")
        private Integer episodeCount;
        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("overview")
        private String overview;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("season_number")
        private Integer seasonNumber;

        public String getAirDate() {
            return airDate;
        }

        public void setAirDate(String airDate) {
            this.airDate = airDate;
        }

        public Integer getEpisodeCount() {
            return episodeCount;
        }

        public void setEpisodeCount(Integer episodeCount) {
            this.episodeCount = episodeCount;
        }

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

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public Integer getSeasonNumber() {
            return seasonNumber;
        }

        public void setSeasonNumber(Integer seasonNumber) {
            this.seasonNumber = seasonNumber;
        }
    }
}

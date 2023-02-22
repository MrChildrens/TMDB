package com.goku.tmdb.data.entity.tvseasons;

import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.Credits;
import com.goku.tmdb.data.entity.Crew;
import com.goku.tmdb.data.entity.ExternalIds;
import com.goku.tmdb.data.entity.Translations;
import com.goku.tmdb.data.entity.Video;
import com.goku.tmdb.data.entity.movie.MediaImages;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeasonDetail {
    @SerializedName("_id")
    private String id;
    @SerializedName("air_date")
    private String airDate;
    @SerializedName("name")
    private String name;
    @SerializedName("overview")
    private String overview;
    @SerializedName("id")
    private Integer idInteger;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("season_number")
    private Integer seasonNumber;
    @SerializedName("episodes")
    private List<Episodes> episodes;

    @SerializedName("aggregate_credits")
    private Credits mAggregateCredits;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
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

    public Integer getIdInteger() {
        return idInteger;
    }

    public void setIdInteger(Integer idInteger) {
        this.idInteger = idInteger;
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

    public List<Episodes> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episodes> episodes) {
        this.episodes = episodes;
    }

    public Credits getAggregateCredits() {
        return mAggregateCredits;
    }

    public void setAggregateCredits(Credits aggregateCredits) {
        mAggregateCredits = aggregateCredits;
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

    public static class Episodes {
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
        @SerializedName("crew")
        private List<Crew> crew;
        @SerializedName("guest_stars")
        private List<GuestStars> guestStars;

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

        public static class GuestStars {
            @SerializedName("credit_id")
            private String creditId;
            @SerializedName("order")
            private Integer order;
            @SerializedName("character")
            private String character;
            @SerializedName("adult")
            private Boolean adult;
            @SerializedName("gender")
            private Integer gender;
            @SerializedName("id")
            private Integer id;
            @SerializedName("known_for_department")
            private String knownForDepartment;
            @SerializedName("name")
            private String name;
            @SerializedName("original_name")
            private String originalName;
            @SerializedName("popularity")
            private Double popularity;
            @SerializedName("profile_path")
            private String profilePath;

            public String getCreditId() {
                return creditId;
            }

            public void setCreditId(String creditId) {
                this.creditId = creditId;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }

            public String getCharacter() {
                return character;
            }

            public void setCharacter(String character) {
                this.character = character;
            }

            public Boolean isAdult() {
                return adult;
            }

            public void setAdult(Boolean adult) {
                this.adult = adult;
            }

            public Integer getGender() {
                return gender;
            }

            public void setGender(Integer gender) {
                this.gender = gender;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getKnownForDepartment() {
                return knownForDepartment;
            }

            public void setKnownForDepartment(String knownForDepartment) {
                this.knownForDepartment = knownForDepartment;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOriginalName() {
                return originalName;
            }

            public void setOriginalName(String originalName) {
                this.originalName = originalName;
            }

            public Double getPopularity() {
                return popularity;
            }

            public void setPopularity(Double popularity) {
                this.popularity = popularity;
            }

            public String getProfilePath() {
                return profilePath;
            }

            public void setProfilePath(String profilePath) {
                this.profilePath = profilePath;
            }
        }
    }
}

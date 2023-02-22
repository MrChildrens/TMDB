package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvEpisodesGroupBean {

    @SerializedName("description")
    private String description;
    @SerializedName("episode_count")
    private Integer episodeCount;
    @SerializedName("group_count")
    private Integer groupCount;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("network")
    private Network network;
    @SerializedName("type")
    private Integer type;
    @SerializedName("groups")
    private List<Groups> groups;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public static class Network {
        @SerializedName("id")
        private Integer id;
        @SerializedName("logo_path")
        private String logoPath;
        @SerializedName("name")
        private String name;
        @SerializedName("origin_country")
        private String originCountry;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLogoPath() {
            return logoPath;
        }

        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(String originCountry) {
            this.originCountry = originCountry;
        }
    }

    public static class Groups {
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;
        @SerializedName("order")
        private Integer order;
        @SerializedName("locked")
        private Boolean locked;
        @SerializedName("episodes")
        private List<Episodes> episodes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Boolean isLocked() {
            return locked;
        }

        public void setLocked(Boolean locked) {
            this.locked = locked;
        }

        public List<Episodes> getEpisodes() {
            return episodes;
        }

        public void setEpisodes(List<Episodes> episodes) {
            this.episodes = episodes;
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
            private Object productionCode;
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
            @SerializedName("order")
            private Integer order;

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

            public Object getProductionCode() {
                return productionCode;
            }

            public void setProductionCode(Object productionCode) {
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

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }
    }
}

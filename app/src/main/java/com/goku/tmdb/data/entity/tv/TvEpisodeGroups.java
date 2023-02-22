package com.goku.tmdb.data.entity.tv;

import com.google.gson.annotations.SerializedName;

public class TvEpisodeGroups {

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
}

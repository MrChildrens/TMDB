package com.goku.tmdb.data.entity.tvepisodes;

import com.google.gson.annotations.SerializedName;

public class TvEpisodesAccountStates {
    @SerializedName("id")
    private Integer id;
    @SerializedName("rated")
    private Rated rated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rated getRated() {
        return rated;
    }

    public void setRated(Rated rated) {
        this.rated = rated;
    }

    public static class Rated {
        @SerializedName("value")
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}

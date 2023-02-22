package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FindExternal {

    @SerializedName("movie_results")
    private List<MovieResults> movieResults;
    @SerializedName("person_results")
    private List<MovieResults> personResults;
    @SerializedName("tv_results")
    private List<MovieResults> tvResults;
    @SerializedName("tv_episode_results")
    private List<MovieResults> tvEpisodeResults;
    @SerializedName("tv_season_results")
    private List<MovieResults> tvSeasonResults;

    public List<MovieResults> getMovieResults() {
        return movieResults;
    }

    public void setMovieResults(List<MovieResults> movieResults) {
        this.movieResults = movieResults;
    }

    public List<MovieResults> getPersonResults() {
        return personResults;
    }

    public void setPersonResults(List<MovieResults> personResults) {
        this.personResults = personResults;
    }

    public List<MovieResults> getTvResults() {
        return tvResults;
    }

    public void setTvResults(List<MovieResults> tvResults) {
        this.tvResults = tvResults;
    }

    public List<?> getTvEpisodeResults() {
        return tvEpisodeResults;
    }

    public void setTvEpisodeResults(List<MovieResults> tvEpisodeResults) {
        this.tvEpisodeResults = tvEpisodeResults;
    }

    public List<?> getTvSeasonResults() {
        return tvSeasonResults;
    }

    public void setTvSeasonResults(List<MovieResults> tvSeasonResults) {
        this.tvSeasonResults = tvSeasonResults;
    }

    public static class MovieResults {
        @SerializedName("adult")
        private Boolean adult;
        @SerializedName("backdrop_path")
        private String backdropPath;
        @SerializedName("id")
        private Integer id;
        @SerializedName("title")
        private String title;
        @SerializedName("original_language")
        private String originalLanguage;
        @SerializedName("original_title")
        private String originalTitle;
        @SerializedName("overview")
        private String overview;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("media_type")
        private String mediaType;
        @SerializedName("popularity")
        private Double popularity;
        @SerializedName("release_date")
        private String releaseDate;
        @SerializedName("video")
        private Boolean video;
        @SerializedName("vote_average")
        private Double voteAverage;
        @SerializedName("vote_count")
        private Integer voteCount;
        @SerializedName("genre_ids")
        private List<Integer> genreIds;

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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public Double getPopularity() {
            return popularity;
        }

        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
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

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }
    }
}

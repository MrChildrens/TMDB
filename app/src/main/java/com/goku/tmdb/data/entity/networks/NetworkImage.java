package com.goku.tmdb.data.entity.networks;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NetworkImage {

    @SerializedName("id")
    private Integer id;
    @SerializedName("logos")
    private List<Logos> logos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Logos> getLogos() {
        return logos;
    }

    public void setLogos(List<Logos> logos) {
        this.logos = logos;
    }

    public static class Logos {
        @SerializedName("aspect_ratio")
        private Double aspectRatio;
        @SerializedName("file_path")
        private String filePath;
        @SerializedName("height")
        private Integer height;
        @SerializedName("id")
        private String id;
        @SerializedName("file_type")
        private String fileType;
        @SerializedName("vote_average")
        private Double voteAverage;
        @SerializedName("vote_count")
        private Integer voteCount;
        @SerializedName("width")
        private Integer width;

        public Double getAspectRatio() {
            return aspectRatio;
        }

        public void setAspectRatio(Double aspectRatio) {
            this.aspectRatio = aspectRatio;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
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

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }
    }
}

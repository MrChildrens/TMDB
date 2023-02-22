package com.goku.tmdb.data.entity.movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieReleaseDates {

    @SerializedName("iso_3166_1")
    private String iso31661;
    @SerializedName("release_dates")
    private List<ReleaseDates> releaseDates;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public List<ReleaseDates> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(List<ReleaseDates> releaseDates) {
        this.releaseDates = releaseDates;
    }

    public static class ReleaseDates {
        @SerializedName("certification")
        private String certification;
        @SerializedName("iso_639_1")
        private String iso6391;
        @SerializedName("note")
        private String note;
        @SerializedName("release_date")
        private String releaseDate;
        @SerializedName("type")
        private Integer type;
        @SerializedName("descriptors")
        private List<?> descriptors;

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
        }

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public List<?> getDescriptors() {
            return descriptors;
        }

        public void setDescriptors(List<?> descriptors) {
            this.descriptors = descriptors;
        }
    }
}

package com.goku.tmdb.data.entity.configuration;

import com.goku.tmdb.db.TmdbConverter;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class Configuration {
    @Id(autoincrement = true)
    private long id;

    @Convert(columnType = String.class, converter = TmdbConverter.ImagesConverter.class)
    @SerializedName("images")
    private Images images;

    @Convert(columnType = String.class, converter = TmdbConverter.CountryConverter.class)
    private List<Countries> mCountries;

    @Convert(columnType = String.class, converter = TmdbConverter.LanguagesConverter.class)
    private List<Languages> mLanguages;

    @Convert(columnType = String.class, converter = TmdbConverter.JobsConverter.class)
    private List<Jobs> mJobs;

    @Convert(columnType = String.class, converter = TmdbConverter.TimezonesConverter.class)
    private List<Timezones> mTimezones;

    @Transient
    @SerializedName("change_keys")
    private List<String> changeKeys;


    @Generated(hash = 383766767)
    public Configuration(long id, Images images, List<Countries> mCountries,
            List<Languages> mLanguages, List<Jobs> mJobs, List<Timezones> mTimezones) {
        this.id = id;
        this.images = images;
        this.mCountries = mCountries;
        this.mLanguages = mLanguages;
        this.mJobs = mJobs;
        this.mTimezones = mTimezones;
    }

    @Generated(hash = 365253574)
    public Configuration() {
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Countries> getMCountries() {
        return this.mCountries;
    }

    public void setMCountries(List<Countries> mCountries) {
        this.mCountries = mCountries;
    }

    public List<Languages> getMLanguages() {
        return this.mLanguages;
    }

    public void setMLanguages(List<Languages> mLanguages) {
        this.mLanguages = mLanguages;
    }

    public List<Jobs> getMJobs() {
        return this.mJobs;
    }

    public void setMJobs(List<Jobs> mJobs) {
        this.mJobs = mJobs;
    }

    public List<Timezones> getMTimezones() {
        return this.mTimezones;
    }

    public void setMTimezones(List<Timezones> mTimezones) {
        this.mTimezones = mTimezones;
    }

    public static class Images {
        @SerializedName("base_url")
        private String baseUrl;
        @SerializedName("secure_base_url")
        private String secureBaseUrl;
        @SerializedName("backdrop_sizes")
        private List<String> backdropSizes;
        @SerializedName("logo_sizes")
        private List<String> logoSizes;
        @SerializedName("poster_sizes")
        private List<String> posterSizes;
        @SerializedName("profile_sizes")
        private List<String> profileSizes;
        @SerializedName("still_sizes")
        private List<String> stillSizes;

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getSecureBaseUrl() {
            return secureBaseUrl;
        }

        public void setSecureBaseUrl(String secureBaseUrl) {
            this.secureBaseUrl = secureBaseUrl;
        }

        public List<String> getBackdropSizes() {
            return backdropSizes;
        }

        public void setBackdropSizes(List<String> backdropSizes) {
            this.backdropSizes = backdropSizes;
        }

        public List<String> getLogoSizes() {
            return logoSizes;
        }

        public void setLogoSizes(List<String> logoSizes) {
            this.logoSizes = logoSizes;
        }

        public List<String> getPosterSizes() {
            return posterSizes;
        }

        public void setPosterSizes(List<String> posterSizes) {
            this.posterSizes = posterSizes;
        }

        public List<String> getProfileSizes() {
            return profileSizes;
        }

        public void setProfileSizes(List<String> profileSizes) {
            this.profileSizes = profileSizes;
        }

        public List<String> getStillSizes() {
            return stillSizes;
        }

        public void setStillSizes(List<String> stillSizes) {
            this.stillSizes = stillSizes;
        }
    }
}

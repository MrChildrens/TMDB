package com.goku.tmdb.data.entity.person;

import com.goku.tmdb.data.entity.Credits;
import com.goku.tmdb.data.entity.ExternalIds;
import com.goku.tmdb.data.entity.Translations;
import com.goku.tmdb.data.entity.movie.MediaImages;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonDetail {
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("biography")
    private String biography;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("deathday")
    private String deathday;
    @SerializedName("gender")
    private Integer gender;
    @SerializedName("homepage")
    private Object homepage;
    @SerializedName("id")
    private Integer id;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("known_for_department")
    private String knownForDepartment;
    @SerializedName("name")
    private String name;
    @SerializedName("place_of_birth")
    private String placeOfBirth;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("profile_path")
    private String profilePath;
    @SerializedName("also_known_as")
    private List<String> alsoKnownAs;

    @SerializedName("movie_credits")
    private Credits mMovieCredits;
    @SerializedName("combined_credits")
    private Credits mCombinedCredits;
    @SerializedName("tv_credits")
    private Credits mTvCredits;
    @SerializedName("changes")
    private PersonChanges.Changes mChanges;
    @SerializedName("external_ids")
    private ExternalIds mExternalIds;
    @SerializedName("images")
    private MediaImages mMediaImages;
    @SerializedName("translations")
    private Translations mTranslations;

    public Boolean isAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Object getHomepage() {
        return homepage;
    }

    public void setHomepage(Object homepage) {
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

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
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

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public Credits getMovieCredits() {
        return mMovieCredits;
    }

    public void setMovieCredits(Credits movieCredits) {
        mMovieCredits = movieCredits;
    }

    public Credits getCombinedCredits() {
        return mCombinedCredits;
    }

    public void setCombinedCredits(Credits combinedCredits) {
        mCombinedCredits = combinedCredits;
    }

    public Credits getTvCredits() {
        return mTvCredits;
    }

    public void setTvCredits(Credits tvCredits) {
        mTvCredits = tvCredits;
    }

    public PersonChanges.Changes getChanges() {
        return mChanges;
    }

    public void setChanges(PersonChanges.Changes changes) {
        mChanges = changes;
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
}

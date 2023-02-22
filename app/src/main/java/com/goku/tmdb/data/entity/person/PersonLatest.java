package com.goku.tmdb.data.entity.person;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonLatest {
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("biography")
    private String biography;
    @SerializedName("birthday")
    private Object birthday;
    @SerializedName("deathday")
    private Object deathday;
    @SerializedName("gender")
    private Integer gender;
    @SerializedName("homepage")
    private Object homepage;
    @SerializedName("id")
    private Integer id;
    @SerializedName("imdb_id")
    private Object imdbId;
    @SerializedName("known_for_department")
    private String knownForDepartment;
    @SerializedName("name")
    private String name;
    @SerializedName("place_of_birth")
    private Object placeOfBirth;
    @SerializedName("popularity")
    private Integer popularity;
    @SerializedName("profile_path")
    private Object profilePath;
    @SerializedName("also_known_as")
    private List<?> alsoKnownAs;

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

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public Object getDeathday() {
        return deathday;
    }

    public void setDeathday(Object deathday) {
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

    public Object getImdbId() {
        return imdbId;
    }

    public void setImdbId(Object imdbId) {
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

    public Object getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(Object placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Object getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }

    public List<?> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<?> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }
}

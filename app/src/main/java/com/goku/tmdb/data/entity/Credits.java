package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Credits {
    @SerializedName("id")
    private Integer id;
    @SerializedName("cast")
    private List<Cast> cast;
    @SerializedName("crew")
    private List<Crew> crew;
    @SerializedName("guest_stars")
    private List<GuestStars> guestStars;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
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
}

package com.goku.tmdb.data.entity.configuration;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Timezones {
    @SerializedName("iso_3166_1")
    private String iso31661;
    @SerializedName("zones")
    private List<String> zones;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public List<String> getZones() {
        return zones;
    }

    public void setZones(List<String> zones) {
        this.zones = zones;
    }
}

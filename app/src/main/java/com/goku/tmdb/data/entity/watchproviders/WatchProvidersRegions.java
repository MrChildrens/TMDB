package com.goku.tmdb.data.entity.watchproviders;

import com.google.gson.annotations.SerializedName;

public class WatchProvidersRegions {
    @SerializedName("iso_3166_1")
    private String iso31661;
    @SerializedName("english_name")
    private String englishName;
    @SerializedName("native_name")
    private String nativeName;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}

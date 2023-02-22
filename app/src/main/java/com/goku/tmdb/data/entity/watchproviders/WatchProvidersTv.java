package com.goku.tmdb.data.entity.watchproviders;

import com.google.gson.annotations.SerializedName;

public class WatchProvidersTv {
    @SerializedName("display_priority")
    private Integer displayPriority;
    @SerializedName("logo_path")
    private String logoPath;
    @SerializedName("provider_name")
    private String providerName;
    @SerializedName("provider_id")
    private Integer providerId;

    public Integer getDisplayPriority() {
        return displayPriority;
    }

    public void setDisplayPriority(Integer displayPriority) {
        this.displayPriority = displayPriority;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }
}

package com.goku.tmdb.data.entity.authentication;

import com.google.gson.annotations.SerializedName;

public class PostV4Token {
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

package com.goku.tmdb.data.entity.authentication;

import com.google.gson.annotations.SerializedName;

public class PostToken {
    @SerializedName("request_token")
    private String requestToken;

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }
}

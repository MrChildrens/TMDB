package com.goku.tmdb.data.entity.authentication;

import com.google.gson.annotations.SerializedName;

public class RequestToken {
    @SerializedName("success")
    private Boolean success;
    @SerializedName("expires_at")
    private String expiresAt;
    @SerializedName("request_token")
    private String requestToken;

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    @Override
    public String toString() {
        return "RequestToken{" +
                "success=" + success +
                ", expiresAt='" + expiresAt + '\'' +
                ", requestToken='" + requestToken + '\'' +
                '}';
    }
}

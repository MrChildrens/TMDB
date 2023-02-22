package com.goku.tmdb.data.entity.authentication;

import com.google.gson.annotations.SerializedName;

public class SessionIdBody {
    @SerializedName("session_id")
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

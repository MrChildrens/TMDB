package com.goku.tmdb.data.entity.authentication;

import com.google.gson.annotations.SerializedName;

public class Session {
    @SerializedName("success")
    private Boolean success;

    @SerializedName("session_id")
    private String sessionId;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "Session{" +
                "success=" + success +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}

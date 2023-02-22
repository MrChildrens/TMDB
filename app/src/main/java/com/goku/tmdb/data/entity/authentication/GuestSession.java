package com.goku.tmdb.data.entity.authentication;

import com.google.gson.annotations.SerializedName;

public class GuestSession {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("guest_session_id")
    private String guestSessionId;
    @SerializedName("expires_at")
    private String expiresAt;

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getGuestSessionId() {
        return guestSessionId;
    }

    public void setGuestSessionId(String guestSessionId) {
        this.guestSessionId = guestSessionId;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "GuestSession{" +
                "success=" + success +
                ", guestSessionId='" + guestSessionId + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                '}';
    }
}

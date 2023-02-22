package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

public class StatusBean {

    @SerializedName("status_code")
    private Integer statusCode;
    @SerializedName("status_message")
    private String statusMessage;
    @SerializedName("success")
    private Boolean success;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "StatusBean{" +
                "statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                ", success=" + success +
                '}';
    }
}

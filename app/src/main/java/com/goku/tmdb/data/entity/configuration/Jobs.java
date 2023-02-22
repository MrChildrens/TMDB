package com.goku.tmdb.data.entity.configuration;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Jobs {

    @SerializedName("department")
    private String department;
    @SerializedName("jobs")
    private List<String> jobs;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }
}

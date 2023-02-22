package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResultBean<T> {

    @SerializedName("page")
    private Integer page;
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("results")
    private List<T> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "BaseResultBean{" +
                "page=" + page +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                ", results=" + results +
                '}';
    }
}

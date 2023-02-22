package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("author")
    private String author;
    @SerializedName("author_details")
    private AuthorDetails authorDetails;
    @SerializedName("content")
    private String content;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("id")
    private String id;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("url")
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public AuthorDetails getAuthorDetails() {
        return authorDetails;
    }

    public void setAuthorDetails(AuthorDetails authorDetails) {
        this.authorDetails = authorDetails;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class AuthorDetails {
        @SerializedName("name")
        private String name;
        @SerializedName("username")
        private String username;
        @SerializedName("avatar_path")
        private String avatarPath;
        @SerializedName("rating")
        private Double rating;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatarPath() {
            return avatarPath;
        }

        public void setAvatarPath(String avatarPath) {
            this.avatarPath = avatarPath;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }
    }
}

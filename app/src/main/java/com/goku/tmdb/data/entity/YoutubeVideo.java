package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YoutubeVideo {
    @SerializedName("kind")
    private String kind;
    @SerializedName("etag")
    private String etag;
    @SerializedName("pageInfo")
    private PageInfo pageInfo;
    @SerializedName("items")
    private List<Items> items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public static class PageInfo {
        @SerializedName("totalResults")
        private Integer totalResults;
        @SerializedName("resultsPerPage")
        private Integer resultsPerPage;

        public Integer getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(Integer totalResults) {
            this.totalResults = totalResults;
        }

        public Integer getResultsPerPage() {
            return resultsPerPage;
        }

        public void setResultsPerPage(Integer resultsPerPage) {
            this.resultsPerPage = resultsPerPage;
        }
    }

    public static class Items {
        @SerializedName("kind")
        private String kind;
        @SerializedName("etag")
        private String etag;
        @SerializedName("id")
        private String id;
        @SerializedName("snippet")
        private Snippet snippet;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        public static class Snippet {
            @SerializedName("publishedAt")
            private String publishedAt;
            @SerializedName("channelId")
            private String channelId;
            @SerializedName("title")
            private String title;
            @SerializedName("description")
            private String description;
            @SerializedName("thumbnails")
            private Thumbnails thumbnails;
            @SerializedName("channelTitle")
            private String channelTitle;
            @SerializedName("categoryId")
            private String categoryId;
            @SerializedName("liveBroadcastContent")
            private String liveBroadcastContent;
            @SerializedName("localized")
            private Localized localized;
            @SerializedName("defaultAudioLanguage")
            private String defaultAudioLanguage;

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Thumbnails getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(Thumbnails thumbnails) {
                this.thumbnails = thumbnails;
            }

            public String getChannelTitle() {
                return channelTitle;
            }

            public void setChannelTitle(String channelTitle) {
                this.channelTitle = channelTitle;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getLiveBroadcastContent() {
                return liveBroadcastContent;
            }

            public void setLiveBroadcastContent(String liveBroadcastContent) {
                this.liveBroadcastContent = liveBroadcastContent;
            }

            public Localized getLocalized() {
                return localized;
            }

            public void setLocalized(Localized localized) {
                this.localized = localized;
            }

            public String getDefaultAudioLanguage() {
                return defaultAudioLanguage;
            }

            public void setDefaultAudioLanguage(String defaultAudioLanguage) {
                this.defaultAudioLanguage = defaultAudioLanguage;
            }

            public static class Thumbnails {
                @SerializedName("default")
                private Default defaultX;
                @SerializedName("medium")
                private Medium medium;
                @SerializedName("high")
                private High high;
                @SerializedName("standard")
                private Standard standard;
                @SerializedName("maxres")
                private Maxres maxres;

                public Default getDefaultX() {
                    return defaultX;
                }

                public void setDefaultX(Default defaultX) {
                    this.defaultX = defaultX;
                }

                public Medium getMedium() {
                    return medium;
                }

                public void setMedium(Medium medium) {
                    this.medium = medium;
                }

                public High getHigh() {
                    return high;
                }

                public void setHigh(High high) {
                    this.high = high;
                }

                public Standard getStandard() {
                    return standard;
                }

                public void setStandard(Standard standard) {
                    this.standard = standard;
                }

                public Maxres getMaxres() {
                    return maxres;
                }

                public void setMaxres(Maxres maxres) {
                    this.maxres = maxres;
                }

                public static class Default {
                    @SerializedName("url")
                    private String url;
                    @SerializedName("width")
                    private Integer width;
                    @SerializedName("height")
                    private Integer height;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public Integer getWidth() {
                        return width;
                    }

                    public void setWidth(Integer width) {
                        this.width = width;
                    }

                    public Integer getHeight() {
                        return height;
                    }

                    public void setHeight(Integer height) {
                        this.height = height;
                    }
                }

                public static class Medium {
                    @SerializedName("url")
                    private String url;
                    @SerializedName("width")
                    private Integer width;
                    @SerializedName("height")
                    private Integer height;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public Integer getWidth() {
                        return width;
                    }

                    public void setWidth(Integer width) {
                        this.width = width;
                    }

                    public Integer getHeight() {
                        return height;
                    }

                    public void setHeight(Integer height) {
                        this.height = height;
                    }
                }

                public static class High {
                    @SerializedName("url")
                    private String url;
                    @SerializedName("width")
                    private Integer width;
                    @SerializedName("height")
                    private Integer height;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public Integer getWidth() {
                        return width;
                    }

                    public void setWidth(Integer width) {
                        this.width = width;
                    }

                    public Integer getHeight() {
                        return height;
                    }

                    public void setHeight(Integer height) {
                        this.height = height;
                    }
                }

                public static class Standard {
                    @SerializedName("url")
                    private String url;
                    @SerializedName("width")
                    private Integer width;
                    @SerializedName("height")
                    private Integer height;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public Integer getWidth() {
                        return width;
                    }

                    public void setWidth(Integer width) {
                        this.width = width;
                    }

                    public Integer getHeight() {
                        return height;
                    }

                    public void setHeight(Integer height) {
                        this.height = height;
                    }
                }

                public static class Maxres {
                    @SerializedName("url")
                    private String url;
                    @SerializedName("width")
                    private Integer width;
                    @SerializedName("height")
                    private Integer height;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public Integer getWidth() {
                        return width;
                    }

                    public void setWidth(Integer width) {
                        this.width = width;
                    }

                    public Integer getHeight() {
                        return height;
                    }

                    public void setHeight(Integer height) {
                        this.height = height;
                    }
                }
            }

            public static class Localized {
                @SerializedName("title")
                private String title;
                @SerializedName("description")
                private String description;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }
        }
    }
}

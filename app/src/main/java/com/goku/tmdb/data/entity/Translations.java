package com.goku.tmdb.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Translations {
    @SerializedName("id")
    private Integer id;
    @SerializedName("translations")
    private List<Translation> translations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public static class Translation {
        @SerializedName("iso_3166_1")
        private String iso31661;
        @SerializedName("iso_639_1")
        private String iso6391;
        @SerializedName("name")
        private String name;
        @SerializedName("english_name")
        private String englishName;
        @SerializedName("data")
        private Data data;

        public String getIso31661() {
            return iso31661;
        }

        public void setIso31661(String iso31661) {
            this.iso31661 = iso31661;
        }

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public static class Data {
            @SerializedName("homepage")
            private String homepage;
            @SerializedName("overview")
            private String overview;
            @SerializedName("runtime")
            private Integer runtime;
            @SerializedName("tagline")
            private String tagline;
            @SerializedName("title")
            private String title;

            public String getHomepage() {
                return homepage;
            }

            public void setHomepage(String homepage) {
                this.homepage = homepage;
            }

            public String getOverview() {
                return overview;
            }

            public void setOverview(String overview) {
                this.overview = overview;
            }

            public Integer getRuntime() {
                return runtime;
            }

            public void setRuntime(Integer runtime) {
                this.runtime = runtime;
            }

            public String getTagline() {
                return tagline;
            }

            public void setTagline(String tagline) {
                this.tagline = tagline;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}

package com.goku.tmdb.data.entity.tvseasons;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvSeasonChanges {
    @SerializedName("changes")
    private List<Changes> changes;

    public List<Changes> getChanges() {
        return changes;
    }

    public void setChanges(List<Changes> changes) {
        this.changes = changes;
    }

    public static class Changes {
        @SerializedName("key")
        private String key;
        @SerializedName("items")
        private List<Items> items;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<Items> getItems() {
            return items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public static class Items {
            @SerializedName("id")
            private String id;
            @SerializedName("action")
            private String action;
            @SerializedName("time")
            private String time;
            @SerializedName("value")
            private Value value;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public Value getValue() {
                return value;
            }

            public void setValue(Value value) {
                this.value = value;
            }

            public static class Value {
                @SerializedName("episode_id")
                private Integer episodeId;
                @SerializedName("episode_number")
                private Integer episodeNumber;

                public Integer getEpisodeId() {
                    return episodeId;
                }

                public void setEpisodeId(Integer episodeId) {
                    this.episodeId = episodeId;
                }

                public Integer getEpisodeNumber() {
                    return episodeNumber;
                }

                public void setEpisodeNumber(Integer episodeNumber) {
                    this.episodeNumber = episodeNumber;
                }
            }
        }
    }
}

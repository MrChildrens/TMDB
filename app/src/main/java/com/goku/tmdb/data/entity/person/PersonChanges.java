package com.goku.tmdb.data.entity.person;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonChanges {

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
            @SerializedName("original_value")
            private OriginalValue originalValue;

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

            public OriginalValue getOriginalValue() {
                return originalValue;
            }

            public void setOriginalValue(OriginalValue originalValue) {
                this.originalValue = originalValue;
            }

            public static class OriginalValue {
                @SerializedName("profile")
                private Profile profile;

                public Profile getProfile() {
                    return profile;
                }

                public void setProfile(Profile profile) {
                    this.profile = profile;
                }

                public static class Profile {
                    @SerializedName("file_path")
                    private String filePath;

                    public String getFilePath() {
                        return filePath;
                    }

                    public void setFilePath(String filePath) {
                        this.filePath = filePath;
                    }
                }
            }
        }
    }
}

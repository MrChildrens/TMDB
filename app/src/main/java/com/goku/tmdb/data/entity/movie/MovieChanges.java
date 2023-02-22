package com.goku.tmdb.data.entity.movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieChanges {

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
            @SerializedName("iso_639_1")
            private String iso6391;
            @SerializedName("iso_3166_1")
            private String iso31661;
            @SerializedName("value")
            private Value value;
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

            public String getIso6391() {
                return iso6391;
            }

            public void setIso6391(String iso6391) {
                this.iso6391 = iso6391;
            }

            public String getIso31661() {
                return iso31661;
            }

            public void setIso31661(String iso31661) {
                this.iso31661 = iso31661;
            }

            public Value getValue() {
                return value;
            }

            public void setValue(Value value) {
                this.value = value;
            }

            public OriginalValue getOriginalValue() {
                return originalValue;
            }

            public void setOriginalValue(OriginalValue originalValue) {
                this.originalValue = originalValue;
            }

            public static class Value {
                @SerializedName("backdrop")
                private Backdrop backdrop;

                public Backdrop getBackdrop() {
                    return backdrop;
                }

                public void setBackdrop(Backdrop backdrop) {
                    this.backdrop = backdrop;
                }

                public static class Backdrop {
                    @SerializedName("file_path")
                    private String filePath;
                    @SerializedName("iso_639_1")
                    private String iso6391;

                    public String getFilePath() {
                        return filePath;
                    }

                    public void setFilePath(String filePath) {
                        this.filePath = filePath;
                    }

                    public String getIso6391() {
                        return iso6391;
                    }

                    public void setIso6391(String iso6391) {
                        this.iso6391 = iso6391;
                    }
                }
            }

            public static class OriginalValue {
                @SerializedName("backdrop")
                private BackdropX backdrop;

                public BackdropX getBackdrop() {
                    return backdrop;
                }

                public void setBackdrop(BackdropX backdrop) {
                    this.backdrop = backdrop;
                }

                public static class BackdropX {
                    @SerializedName("file_path")
                    private String filePath;
                    @SerializedName("iso_639_1")
                    private Object iso6391;

                    public String getFilePath() {
                        return filePath;
                    }

                    public void setFilePath(String filePath) {
                        this.filePath = filePath;
                    }

                    public Object getIso6391() {
                        return iso6391;
                    }

                    public void setIso6391(Object iso6391) {
                        this.iso6391 = iso6391;
                    }
                }
            }
        }
    }
}

package com.goku.tmdb.data.entity.tv;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvChanges {
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

            public static class Value {
                @SerializedName("add_to_every_season")
                private Boolean addToEverySeason;
                @SerializedName("credit_id")
                private String creditId;
                @SerializedName("department")
                private String department;
                @SerializedName("job")
                private String job;
                @SerializedName("person_id")
                private Integer personId;
                @SerializedName("season_id")
                private Integer seasonId;

                public Boolean isAddToEverySeason() {
                    return addToEverySeason;
                }

                public void setAddToEverySeason(Boolean addToEverySeason) {
                    this.addToEverySeason = addToEverySeason;
                }

                public String getCreditId() {
                    return creditId;
                }

                public void setCreditId(String creditId) {
                    this.creditId = creditId;
                }

                public String getDepartment() {
                    return department;
                }

                public void setDepartment(String department) {
                    this.department = department;
                }

                public String getJob() {
                    return job;
                }

                public void setJob(String job) {
                    this.job = job;
                }

                public Integer getPersonId() {
                    return personId;
                }

                public void setPersonId(Integer personId) {
                    this.personId = personId;
                }

                public Integer getSeasonId() {
                    return seasonId;
                }

                public void setSeasonId(Integer seasonId) {
                    this.seasonId = seasonId;
                }
            }
        }
    }
}

package com.goku.tmdb.data.entity;

import com.goku.tmdb.db.TmdbConverter;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Account {

    @Convert(columnType = String.class, converter = TmdbConverter.AvatarConverter.class)
    @SerializedName("avatar")
    private Avatar avatar;
    @Id
    @SerializedName("id")
    private Long id;
    @SerializedName("iso_639_1")
    private String iso6391;
    @SerializedName("iso_3166_1")
    private String iso31661;
    @SerializedName("name")
    private String name;
    @SerializedName("include_adult")
    private Boolean includeAdult;
    @SerializedName("username")
    private String username;

    @Generated(hash = 882125521)
    public Account() {
    }

    @Generated(hash = 302505750)
    public Account(Avatar avatar, Long id, String iso6391, String iso31661, String name,
            Boolean includeAdult, String username) {
        this.avatar = avatar;
        this.id = id;
        this.iso6391 = iso6391;
        this.iso31661 = iso31661;
        this.name = name;
        this.includeAdult = includeAdult;
        this.username = username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIncludeAdult() {
        return includeAdult;
    }

    public void setIncludeAdult(Boolean includeAdult) {
        this.includeAdult = includeAdult;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIncludeAdult() {
        return this.includeAdult;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static class Avatar {
        @SerializedName("gravatar")
        private Gravatar gravatar;
        @SerializedName("tmdb")
        private Tmdb tmdb;

        public Gravatar getGravatar() {
            return gravatar;
        }

        public void setGravatar(Gravatar gravatar) {
            this.gravatar = gravatar;
        }

        public Tmdb getTmdb() {
            return tmdb;
        }

        public void setTmdb(Tmdb tmdb) {
            this.tmdb = tmdb;
        }

        public static class Gravatar {
            @SerializedName("hash")
            private String hash;

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }
        }

        public static class Tmdb {
            @SerializedName("avatar_path")
            private String avatarPath;

            public String getAvatarPath() {
                return avatarPath;
            }

            public void setAvatarPath(String avatarPath) {
                this.avatarPath = avatarPath;
            }
        }
    }
}

package com.goku.tmdb.db;

import android.text.TextUtils;

import com.goku.tmdb.data.entity.Account;
import com.goku.tmdb.data.entity.configuration.Configuration;
import com.goku.tmdb.data.entity.configuration.Countries;
import com.goku.tmdb.data.entity.configuration.Jobs;
import com.goku.tmdb.data.entity.configuration.Languages;
import com.goku.tmdb.data.entity.configuration.Timezones;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.List;

public class TmdbConverter{
    public static class ImagesConverter implements PropertyConverter<Configuration.Images, String> {
        @Override
        public Configuration.Images convertToEntityProperty(String databaseValue) {
            if (!TextUtils.isEmpty(databaseValue)) {
                Gson gson = new GsonBuilder().create();
                Type typeToken = new TypeToken<Configuration.Images>(){}.getType();
                return gson.fromJson(databaseValue, typeToken);
            }
            return null;
        }

        @Override
        public String convertToDatabaseValue(Configuration.Images entityProperty) {
            String sb = new Gson().toJson(entityProperty);
            return sb;
        }
    }

    public static class LanguagesConverter implements PropertyConverter<List<Languages>, String> {
        @Override
        public List<Languages> convertToEntityProperty(String databaseValue) {
            if (!TextUtils.isEmpty(databaseValue)) {
                Gson gson = new GsonBuilder().create();
                Type typeToken = new TypeToken<List<Languages>>(){}.getType();
                return gson.fromJson(databaseValue, typeToken);
            }
            return null;
        }

        @Override
        public String convertToDatabaseValue(List<Languages> entityProperty) {
            String sb = new Gson().toJson(entityProperty);
            return sb;
        }
    }

    public static class CountryConverter implements PropertyConverter<List<Countries>, String> {
        @Override
        public List<Countries> convertToEntityProperty(String databaseValue) {
            if (!TextUtils.isEmpty(databaseValue)) {
                Gson gson = new GsonBuilder().create();
                Type typeToken = new TypeToken<List<Countries>>(){}.getType();
                return gson.fromJson(databaseValue, typeToken);
            }
            return null;
        }

        @Override
        public String convertToDatabaseValue(List<Countries> entityProperty) {
            String sb = new Gson().toJson(entityProperty);
            return sb;
        }
    }

    public static class JobsConverter implements PropertyConverter<List<Jobs>, String> {
        @Override
        public List<Jobs> convertToEntityProperty(String databaseValue) {
            if (!TextUtils.isEmpty(databaseValue)) {
                Gson gson = new GsonBuilder().create();
                Type typeToken = new TypeToken<List<Jobs>>(){}.getType();
                return gson.fromJson(databaseValue, typeToken);
            }
            return null;
        }

        @Override
        public String convertToDatabaseValue(List<Jobs> entityProperty) {
            String sb = new Gson().toJson(entityProperty);
            return sb;
        }
    }

    public static class TimezonesConverter implements PropertyConverter<List<Timezones>, String> {
        @Override
        public List<Timezones> convertToEntityProperty(String databaseValue) {
            if (!TextUtils.isEmpty(databaseValue)) {
                Gson gson = new GsonBuilder().create();
                Type typeToken = new TypeToken<List<Timezones>>(){}.getType();
                return gson.fromJson(databaseValue, typeToken);
            }
            return null;
        }

        @Override
        public String convertToDatabaseValue(List<Timezones> entityProperty) {
            String sb = new Gson().toJson(entityProperty);
            return sb;
        }
    }

    public static class AvatarConverter implements PropertyConverter<Account.Avatar, String> {
        @Override
        public Account.Avatar convertToEntityProperty(String databaseValue) {
            if (!TextUtils.isEmpty(databaseValue)) {
                Gson gson = new GsonBuilder().create();
                Type typeToken = new TypeToken<Account.Avatar>(){}.getType();
                return gson.fromJson(databaseValue, typeToken);
            }
            return null;
        }

        @Override
        public String convertToDatabaseValue(Account.Avatar entityProperty) {
            String sb = new Gson().toJson(entityProperty);
            return sb;
        }
    }

    public static class ListIntegerConverter implements PropertyConverter<List<Integer>, String> {
        @Override
        public List<Integer> convertToEntityProperty(String databaseValue) {
            if (!TextUtils.isEmpty(databaseValue)) {
                Gson gson = new GsonBuilder().create();
                Type typeToken = new TypeToken<List<Integer>>(){}.getType();
                return gson.fromJson(databaseValue, typeToken);
            }
            return null;
        }

        @Override
        public String convertToDatabaseValue(List<Integer> entityProperty) {
            String sb = new Gson().toJson(entityProperty);
            return sb;
        }
    }

    public static class ListStringConverter implements PropertyConverter<List<String>, String> {
        @Override
        public List<String> convertToEntityProperty(String databaseValue) {
            if (!TextUtils.isEmpty(databaseValue)) {
                Gson gson = new GsonBuilder().create();
                Type typeToken = new TypeToken<List<String>>(){}.getType();
                return gson.fromJson(databaseValue, typeToken);
            }
            return null;
        }

        @Override
        public String convertToDatabaseValue(List<String> entityProperty) {
            String sb = new Gson().toJson(entityProperty);
            return sb;
        }
    }
}

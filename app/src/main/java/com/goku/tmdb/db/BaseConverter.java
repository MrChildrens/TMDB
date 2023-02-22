package com.goku.tmdb.db;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;

public class BaseConverter<T> implements PropertyConverter<T, String> {
    @Override
    public T convertToEntityProperty(String databaseValue) {
        if (!TextUtils.isEmpty(databaseValue)) {
            Gson gson = new GsonBuilder().create();
            Type typeToken = new TypeToken<T>(){}.getType();
            return gson.fromJson(databaseValue, typeToken);
        }
        return null;
    }

    @Override
    public String convertToDatabaseValue(T entityProperty) {
        String sb = new Gson().toJson(entityProperty);
        return sb;
    }
}

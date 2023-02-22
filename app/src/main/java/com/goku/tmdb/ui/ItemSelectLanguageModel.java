package com.goku.tmdb.ui;

import android.widget.RadioButton;

import androidx.databinding.ObservableField;

public class ItemSelectLanguageModel {
    public ObservableField<Boolean> isSelected = new ObservableField<>(false);
    public ObservableField<String> languages = new ObservableField<>();

    public RadioButton.OnCheckedChangeListener mOnCheckedChangeListener;
    private String mLanguage;
    private String mCountry;

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }
}

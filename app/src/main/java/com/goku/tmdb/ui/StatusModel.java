package com.goku.tmdb.ui;

import android.graphics.Color;
import android.view.View;

import androidx.databinding.ObservableField;

import com.goku.tmdb.R;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.Utils;

public class StatusModel {
    public ObservableField<Integer> dataStatus = new ObservableField<>(Constant.DATA_STATUS_LOADING);
    public View.OnClickListener onErrorClickListener;

    public transient ObservableField<Integer> dominantColor;
    public transient ObservableField<Integer> mutedColor;
    public transient ObservableField<Integer> lightMutedColor;
    public transient ObservableField<Boolean> isDark;

    public StatusModel() {
        if (!Utils.isNightMode()) {
            isDark = new ObservableField<>(false);
            dominantColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.main_bg_color));
            mutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.sub_title_color));
            lightMutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.content_text_color));
        } else {
            isDark = new ObservableField<>(true);
            dominantColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.contrary_main_bg_color));
            mutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.sub_title_color));
            lightMutedColor = new ObservableField<>(Color.WHITE);
        }
    }
}

package com.goku.tmdb.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.goku.tmdb.R;

public class LanguageRadioButton extends androidx.appcompat.widget.AppCompatRadioButton {



    public LanguageRadioButton(Context context) {
        super(context);
        init(context, null, 0);
    }

    public LanguageRadioButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public LanguageRadioButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
//        LayoutInflater.from(context).inflate(context, R.layout.item_selected_language, this));
//        inflate(context, R.layout.item_selected_language, true)
    }
}

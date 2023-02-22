package com.goku.tmdb.ui;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;

import java.util.ArrayList;
import java.util.List;

public class ImagePageViewModel extends BaseContentViewModel {
    public ObservableField<String> page = new ObservableField<>();
    public ObservableField<Boolean> isShowToolbar = new ObservableField<>(true);

    private List<String> mUrls = new ArrayList<>();
    private int mCount;
    private int mPosition;
    private int mItemType;

    public View.OnClickListener onBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    public ImagePageViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
    }

    public List<String> getUrls() {
        return mUrls;
    }

    public void setUrls(List<String> urls) {
        mUrls = urls;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public int getItemType() {
        return mItemType;
    }

    public void setItemType(int itemType) {
        mItemType = itemType;
    }
}

package com.goku.tmdb.ui;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;

public class ImageViewModel extends BaseContentViewModel {

    public MutableLiveData<String> url = new MutableLiveData();
    private String mUrl;
    private int mPosition;
    private int mTargetPosition;
    public ObservableField<Boolean> isShowToolbar = new ObservableField<>(true);

    public MutableLiveData<Boolean> showToolbarEvent = new MutableLiveData<>();

    public View.OnClickListener onImageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isShowToolbar.set(!isShowToolbar.get());
            showToolbarEvent.postValue(isShowToolbar.get());
        }
    };

    public ImageViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public int getTargetPosition() {
        return mTargetPosition;
    }

    public void setTargetPosition(int targetPosition) {
        mTargetPosition = targetPosition;
    }
}
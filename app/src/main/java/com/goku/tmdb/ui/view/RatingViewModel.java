package com.goku.tmdb.ui.view;

import android.app.Application;
import android.view.View;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.goku.tmdb.base.BaseViewModel;
import com.goku.tmdb.data.TmdbRepository;

public class RatingViewModel extends BaseViewModel<TmdbRepository> {
    private static final String TAG = "RatingViewModel";
    public ObservableField<Float> ratings = new ObservableField<>();
    public ObservableField<Float> percent = new ObservableField<>();

    public MutableLiveData<Float> ratingSubmit = new MutableLiveData<>();
    public MutableLiveData<Object> removeSubmit = new MutableLiveData<>();

    public RatingBar.OnRatingBarChangeListener onRatingChangeListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            ratings.set(rating);
            percent.set(rating / 10);
        }
    };

    public View.OnClickListener onSubmitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ratingSubmit.postValue(ratings.get());
        }
    };

    public View.OnClickListener onRemoveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            removeSubmit.postValue(new Object());
        }
    };


    public RatingViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);

    }
}

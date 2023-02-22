package com.goku.tmdb.ui.view;

import android.app.Application;

import androidx.annotation.NonNull;

import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;

public class FliterViewModel extends BaseContentViewModel {
    public FliterViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
    }
}

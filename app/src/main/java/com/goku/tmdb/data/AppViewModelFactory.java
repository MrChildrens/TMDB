package com.goku.tmdb.data;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.goku.tmdb.ui.ContentListViewModel;
import com.goku.tmdb.ui.ContentPageViewModel;
import com.goku.tmdb.ui.ImagePageViewModel;
import com.goku.tmdb.ui.ImageViewModel;
import com.goku.tmdb.ui.LoginViewModel;
import com.goku.tmdb.ui.MainViewModel;
import com.goku.tmdb.ui.SelectLanguageViewModel;
import com.goku.tmdb.ui.detail.DetailViewModel;
import com.goku.tmdb.ui.home.HomeViewModel;
import com.goku.tmdb.ui.search.SearchViewModel;
import com.goku.tmdb.ui.settings.SettingsViewModel;

/**
 * Created by goldze on 2019/3/26.
 */
public class AppViewModelFactory implements ViewModelProvider.Factory {
    @SuppressLint("StaticFieldLeak")
    private static volatile AppViewModelFactory sInstance;
    private final Application mApplication;
    private final TmdbRepository mRepository;

    public static AppViewModelFactory getInstance(Application application) {
        if (sInstance == null) {
            synchronized (AppViewModelFactory.class) {
                if (sInstance == null) {
                    sInstance = new AppViewModelFactory(application, Injection.provideTmbdRepository());
                }
            }
        }
        return sInstance;
    }

    private AppViewModelFactory(Application application, TmdbRepository repository) {
        this.mApplication = application;
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(SettingsViewModel.class)) {
            return (T) new SettingsViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(ContentListViewModel.class)) {
            return (T) new ContentListViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(ImagePageViewModel.class)) {
            return (T) new ImagePageViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(ContentPageViewModel.class)) {
            return (T) new ContentPageViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(ImageViewModel.class)) {
            return (T) new ImageViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(SelectLanguageViewModel.class)) {
            return (T) new SelectLanguageViewModel(mApplication, mRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}

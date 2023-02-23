package com.goku.tmdb.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;

import com.blongho.country_data.World;
import com.goku.tmdb.data.entity.configuration.Configuration;
import com.goku.tmdb.data.entity.configuration.Countries;
import com.goku.tmdb.data.entity.configuration.Jobs;
import com.goku.tmdb.data.entity.configuration.Languages;
import com.goku.tmdb.data.entity.configuration.Timezones;

import java.util.List;

public class TmdbApplication extends Application {
    private static final String TAG = TmdbApplication.class.getSimpleName();
    private static TmdbApplication sInstance;

    private Configuration mConfiguration;
    private List<Countries> mCountries;
    private List<Jobs> mJobs;
    private List<Languages> mLanguages;
    private List<Timezones> mTimezones;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        if (Utils.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Utils.setNightMode(true);
        } else {
            Utils.setNightMode(false);
        }


        MultiLanguageUtil.init(this);
        World.init(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getAppManager().removeActivity(activity);
            }
        });

    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
//    }

    protected boolean isNightMode() {
        int curNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        return curNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }

    public static TmdbApplication getInstance() {
        return sInstance;
    }

    public Configuration getConfiguration() {
        return mConfiguration;
    }

    public void setConfiguration(Configuration configuration) {
        mConfiguration = configuration;
    }

    public List<Countries> getCountries() {
        return mCountries;
    }

    public void setCountries(List<Countries> countries) {
        mCountries = countries;
    }

    public List<Jobs> getJobs() {
        return mJobs;
    }

    public void setJobs(List<Jobs> jobs) {
        mJobs = jobs;
    }

    public List<Languages> getLanguages() {
        return mLanguages;
    }

    public void setLanguages(List<Languages> languages) {
        mLanguages = languages;
    }

    public List<Timezones> getTimezones() {
        return mTimezones;
    }

    public void setTimezones(List<Timezones> timezones) {
        mTimezones = timezones;
    }
}

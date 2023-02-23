package com.goku.tmdb.ui.settings;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.ObservableField;

import com.goku.tmdb.R;
import com.goku.tmdb.app.AppManager;
import com.goku.tmdb.app.MultiLanguageUtil;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;
import com.goku.tmdb.data.entity.authentication.Session;
import com.goku.tmdb.ui.ContentPageActivity;
import com.goku.tmdb.ui.LoginActivity;
import com.goku.tmdb.ui.MainActivity;
import com.goku.tmdb.ui.SelectLanguageActivity;
import com.goku.tmdb.ui.TmdbEvent;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.functions.Consumer;

public class SettingsViewModel extends BaseContentViewModel {
    private static final String TAG = "SettingsViewModel";

    public ObservableField<String> profile = new ObservableField<>();
    public ObservableField<Integer> profilebg = new ObservableField<>();
    public ObservableField<String> accountName = new ObservableField<>();
    public ObservableField<String> btnText = new ObservableField<>();
    public ObservableField<Boolean> adultSwitch = new ObservableField<>(Utils.isAdult());
    public ObservableField<Boolean> nightSwitch = new ObservableField<>(Utils.isNightMode());

    public View.OnClickListener onSignInClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!Utils.isLogin()) {
                startActivity(LoginActivity.class);
            } else {
                logOut();
            }
        }
    };


    public Switch.OnCheckedChangeListener onAudltCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Utils.setAdult(isChecked);
            adultSwitch.set(isChecked);
        }
    };

    public Switch.OnCheckedChangeListener onNightModeCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Utils.setNightMode(isChecked);
            nightSwitch.set(isChecked);
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            initColor();
            refreshColor.postValue(new Object());
            EventBus.getDefault().post(new TmdbEvent(true));
        }
    };

    public View.OnClickListener onWatchListClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Utils.isLogin()) {
                ContentPageActivity.newInstance(AppManager.getAppManager().currentActivity(), getString(R.string.watchlist), PageParams.PAGE_TYPE_WATCHLIST);
            } else {
                Toast.makeText(AppManager.getAppManager().currentActivity(), R.string.login_tip, Toast.LENGTH_SHORT).show();
            }
        }
    };
    public View.OnClickListener onFavouriteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Utils.isLogin()) {
                ContentPageActivity.newInstance(AppManager.getAppManager().currentActivity(), getString(R.string.favourite), PageParams.PAGE_TYPE_FAVORITE);
            } else {
                Toast.makeText(AppManager.getAppManager().currentActivity(), R.string.login_tip, Toast.LENGTH_SHORT).show();
            }
        }
    };
    public View.OnClickListener onRatingClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Utils.isLogin()) {
                ContentPageActivity.newInstance(AppManager.getAppManager().currentActivity(), getString(R.string.watchlist), PageParams.PAGE_TYPE_RATING);
            } else {
                Toast.makeText(AppManager.getAppManager().currentActivity(), R.string.login_tip, Toast.LENGTH_SHORT).show();
            }
        }
    };
    public View.OnClickListener onLanguageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SelectLanguageActivity.newInstance(AppManager.getAppManager().currentActivity());
        }
    };

    public SettingsViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "[Ciel_Debug] onResume: " + mModel.getAccount());
        refresh();
    }

    private void refresh() {
        if (Utils.isLogin()) {
            accountName.set(mModel.getAccount().getUsername());
            btnText.set(Utils.getContext().getString(R.string.logout));
            profile.set(Utils.getGravatarPath() + mModel.getAccount().getAvatar().getTmdb().getAvatarPath());
        } else {
            accountName.set(Utils.getContext().getString(R.string.your_account));
            btnText.set(Utils.getContext().getString(R.string.sign_in_sign_up));
            profile.set("");
            profilebg.set(R.drawable.ic_avatar);
        }
    }

    public void logOut() {
        addSubscribe(mModel.loginOut()
                .subscribe(new Consumer<>() {
                    @Override
                    public void accept(Session session) throws Exception {
                        if (session.getSuccess()) {
                            mModel.delete(mModel.getAccount());
                            Utils.setAccount(null);
                            Utils.setSessionId("");
                            refresh();
                        }
                    }
                }, new Consumer<>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] accept: ");
                    }
                }));
    }
}
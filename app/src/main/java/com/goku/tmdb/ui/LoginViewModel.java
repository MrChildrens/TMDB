package com.goku.tmdb.ui;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goku.tmdb.app.AppManager;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;
import com.goku.tmdb.data.entity.Account;
import com.goku.tmdb.data.entity.authentication.RequestToken;
import com.goku.tmdb.data.entity.authentication.Session;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class LoginViewModel extends BaseContentViewModel {
    private static final String TAG = "LoginViewModel";
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<Boolean> isRemeber = new ObservableField<>(Utils.isRemeberPassword());
    public View.OnClickListener onBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    public View.OnClickListener onLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login(username.get(), password.get());
        }
    };

    public LoginViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
        statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
        if (Utils.isRemeberPassword()) {
            username.set(Utils.getUsername());
            password.set(Utils.getPassword());
        }
    }

    private void login(String username, String password) {
        statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
        addSubscribe(mModel.getRequestToken().flatMap(new Function<RequestToken, Observable<RequestToken>>() {
            @Override
            public Observable<RequestToken> apply(RequestToken newToken) throws Exception {
                return mModel.getTokenWithLogin(username, password, newToken.getRequestToken());
            }
        }).flatMap(new Function<RequestToken, Observable<Session>>() {
            @Override
            public Observable<Session> apply(RequestToken tokenFromLogin) throws Exception {
                return mModel.getSessionWithToken(tokenFromLogin.getRequestToken());
            }
        }).flatMap(new Function<Session, Observable<Account>>() {
            @Override
            public Observable<Account> apply(Session session) throws Exception {
                if (session != null && !TextUtils.isEmpty(session.getSessionId())) {
                    Log.d(TAG, "[Ciel_Debug] accept: " + session);
                    Utils.setSessionId(session.getSessionId());
                }
                return mModel.getAccountDetail();
            }
        }).subscribe(new Consumer<>() {
            @Override
            public void accept(Account account) throws Exception {
                Utils.setAccount(account);
                mModel.insert(account);

                if (isRemeber.get()) {
                    Utils.setRemeberPassword(true);
                    Utils.setUsername(username);
                    Utils.setPassword(password);
                } else {
                    Utils.setRemeberPassword(false);
                    Utils.setUsername("");
                    Utils.setPassword("");
                }
                statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
                finish();
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                Toast.makeText(AppManager.getAppManager().currentActivity(), throwable.toString(), Toast.LENGTH_SHORT).show();
                statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
            }
        }));
    }
}

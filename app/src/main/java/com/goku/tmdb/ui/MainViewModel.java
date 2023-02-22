package com.goku.tmdb.ui;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;
import com.goku.tmdb.data.entity.authentication.GuestSession;
import com.goku.tmdb.data.entity.authentication.RequestToken;
import com.goku.tmdb.data.entity.authentication.Session;
import com.goku.tmdb.data.entity.configuration.Configuration;
import com.goku.tmdb.data.entity.configuration.Countries;
import com.goku.tmdb.data.entity.configuration.Jobs;
import com.goku.tmdb.data.entity.configuration.Languages;
import com.goku.tmdb.data.entity.configuration.Timezones;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainViewModel extends BaseContentViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();
    public final StatusModel statusModel = new StatusModel();

    private final MutableLiveData<Object> mGetConfigFinish = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application, TmdbRepository repository) {
        super(application, repository);
        statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                initSession();
            }
        };
        initSession();
    }

    private void initSession() {
        String sessionId = Utils.getSessionId();
        String guestSessionId = Utils.getGuestSessionId();
        Log.d(TAG, "[Ciel_Debug] initSession: sessionId: " + sessionId);
        Log.d(TAG, "[Ciel_Debug] initSession: guestSessionId: " + guestSessionId);
        if (TextUtils.isEmpty(sessionId) && TextUtils.isEmpty(guestSessionId)) {
            getGuestSession();
        } else if (!TextUtils.isEmpty(sessionId) || !TextUtils.isEmpty(guestSessionId)) {
            initConfiguration();
        } else {
            getGuestSession();
        }
    }

    private void login(String username, String password) {
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
        }).subscribe(new Consumer<>() {
            @Override
            public void accept(Session session) throws Exception {
                if (session != null && !TextUtils.isEmpty(session.getSessionId())) {
                    Log.d(TAG, "[Ciel_Debug] accept: " + session);
                    Utils.setSessionId(session.getSessionId());
                    initConfiguration();
                }
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                statusModel.dataStatus.set(Constant.DATA_STATUS_ERROR);
            }
        }));

    }

    private void getSessionWithToken() {
        addSubscribe(mModel.getRequestToken().flatMap(new Function<RequestToken, Observable<Session>>() {
            @Override
            public Observable<Session> apply(RequestToken requestToken) throws Exception {
                return mModel.getSessionWithToken(requestToken.getRequestToken());
            }
        }).subscribe(new Consumer<>() {
            @Override
            public void accept(Session session) throws Exception {
                if (session != null && !TextUtils.isEmpty(session.getSessionId())) {
                    Log.d(TAG, "[Ciel_Debug] accept: " + session);
                    Utils.setSessionId(session.getSessionId());
                    initConfiguration();
                }
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                statusModel.dataStatus.set(Constant.DATA_STATUS_ERROR);
            }
        }));
    }

    private void getSessionWithV4Token() {
        addSubscribe(mModel.getSessionWithV4Token()
                .subscribe(new Consumer<>() {
                    @Override
                    public void accept(Session session) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] accept: sessionId: " + session.getSessionId());
                        if (session != null && !TextUtils.isEmpty(session.getSessionId())) {
                            Utils.setSessionId(session.getSessionId());
                            initConfiguration();
                        }
                    }
                }, new Consumer<>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                        statusModel.dataStatus.set(Constant.DATA_STATUS_ERROR);
                    }
                }));
    }

    private void getGuestSession() {
        addSubscribe(mModel.getGuestSession()
                .subscribe(new Consumer<>() {
                    @Override
                    public void accept(GuestSession guestSession) throws Exception {
                        if (guestSession != null && !TextUtils.isEmpty(guestSession.getGuestSessionId())) {
                            Log.d(TAG, "[Ciel_Debug] accept: " + guestSession);
                            Utils.setGuestSessionId(guestSession.getGuestSessionId());
                            initConfiguration();
                        }
                    }
                }, new Consumer<>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                        statusModel.dataStatus.set(Constant.DATA_STATUS_ERROR);
                    }
                }));
    }

    private void initConfiguration() {
        Observable<Configuration> configuration = mModel.getApiConfiguration();
        Observable<List<Languages>> configurationLanguages = mModel.getConfigurationLanguages();
        Observable<List<Countries>> configurationCountries = mModel.getConfigurationCountries();
        Observable<List<Jobs>> configurationJobs = mModel.getConfigurationJobs();
        Observable<List<Timezones>> configurationTimezones = mModel.getConfigurationTimezones();
        addSubscribe(configuration.flatMap(new Function<Configuration, Observable<List<Languages>>>() {
            @Override
            public Observable<List<Languages>> apply(Configuration configuration) throws Exception {
                return configurationLanguages;
            }
        }).flatMap(new Function<List<Languages>, Observable<List<Countries>>>() {
            @Override
            public Observable<List<Countries>> apply(List<Languages> listObservable) throws Exception {
                return configurationCountries;
            }
        }).flatMap(new Function<List<Countries>, Observable<List<Jobs>>>() {
            @Override
            public Observable<List<Jobs>> apply(List<Countries> listObservable) throws Exception {
                return configurationJobs;
            }
        }).flatMap(new Function<List<Jobs>, Observable<List<Timezones>>>() {
            @Override
            public Observable<List<Timezones>> apply(List<Jobs> listObservable) throws Exception {
                return configurationTimezones;
            }
        }).subscribe(new Consumer<>() {
            @Override
            public void accept(List<Timezones> listObservable) throws Exception {
                TmdbApplication.getInstance().setConfiguration(mModel.getConfiguration());
                statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
                mGetConfigFinish.postValue(new Object());
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));
    }

    public MutableLiveData<Object> getGetConfigFinish() {
        return mGetConfigFinish;
    }
}

package com.goku.tmdb;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.goku.tmdb.app.Utils;
import com.goku.tmdb.data.Injection;
import com.goku.tmdb.data.TmdbRepository;
import com.goku.tmdb.data.entity.Account;
import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.Movie;
import com.goku.tmdb.data.entity.Tv;
import com.goku.tmdb.db.Favorite;
import com.goku.tmdb.db.WatchList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MyService extends IntentService {
    private static final String TAG = MyService.class.getSimpleName();

    private final TmdbRepository mRepository;
    private final List<Movie> mFavMovies = new ArrayList<>();
    private final List<Tv> mFavTvs = new ArrayList<>();

    private final List<Movie> mWatchlistMovies = new ArrayList<>();
    private final List<Tv> mWatchlistTvs = new ArrayList<>();

    public MyService() {
        super(TAG);
        mRepository = Injection.provideTmbdRepository();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "[Ciel_Debug] onHandleIntent: " + intent.getAction());
//        Bundle extras = intent.getExtras();
        Account account = mRepository.getAccount();
        if (account != null) {
            getFavMovie(account, 1);
            getWatchlistMovie(account, 1);
        }
    }

    private void getFavMovie(Account account, int page) {
        Disposable subscribe = mRepository.getFavoriteMovie(page, "created_at.asc")
                .subscribe(new Consumer<>() {
                    @Override
                    public void accept(BaseResultBean<Movie> movieBaseResultBean) throws Exception {
                        if (movieBaseResultBean.getResults() != null && movieBaseResultBean.getResults().size() > 0) {
                            mFavMovies.addAll(movieBaseResultBean.getResults());
                        }
                        if (movieBaseResultBean.getPage() < movieBaseResultBean.getTotalPages()) {
                            getFavMovie(account, movieBaseResultBean.getPage() + 1);
                        } else {
                            getFavTv(account, 1);
                        }
                    }
                }, new Consumer<>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                        getFavMovie(account, page);
                    }
                });
    }

    private void getFavTv(Account account, int page) {
        Disposable subscribe = mRepository.getFavoriteTv(page, "created_at.asc")
                .subscribe(new Consumer<>() {
                    @Override
                    public void accept(BaseResultBean<Tv> movieBaseResultBean) throws Exception {
                        if (movieBaseResultBean.getResults() != null && movieBaseResultBean.getResults().size() > 0) {
                            mFavTvs.addAll(movieBaseResultBean.getResults());
                        }
                        if (movieBaseResultBean.getPage() < movieBaseResultBean.getTotalPages()) {
                            getFavTv(account, movieBaseResultBean.getPage() + 1);
                        } else {
                            mRepository.deleteAllFavoriteList();
                            for (int i = 0; i < mFavMovies.size(); i++) {
                                Movie movie = mFavMovies.get(i);
                                Favorite favorite = Utils.movieToFavorite(movie);
                                mRepository.insert(favorite);
                            }
                            for (int i = 0; i < mFavTvs.size(); i++) {
                                Tv movie = mFavTvs.get(i);
                                Favorite favorite = Utils.tvToFavorite(movie);
                                mRepository.insert(favorite);
                            }
                        }
                    }
                }, new Consumer<>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                        getFavTv(account, page);
                    }
                });
    }

    private void getWatchlistMovie(Account account, int page) {
        Disposable subscribe = mRepository.getWatchlistMovie(page, "created_at.asc")
                .subscribe(new Consumer<>() {
                    @Override
                    public void accept(BaseResultBean<Movie> movieBaseResultBean) throws Exception {
                        if (movieBaseResultBean.getResults() != null && movieBaseResultBean.getResults().size() > 0) {
                            mWatchlistMovies.addAll(movieBaseResultBean.getResults());
                        }
                        if (movieBaseResultBean.getPage() < movieBaseResultBean.getTotalPages()) {
                            getWatchlistMovie(account, movieBaseResultBean.getPage() + 1);
                        } else {
                            getWatchlistTv(account, 1);
                        }
                    }
                }, new Consumer<>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                        getWatchlistMovie(account, page);
                    }
                });
    }

    private void getWatchlistTv(Account account, int page) {
        Disposable subscribe = mRepository.getWatchlistTv(page, "created_at.asc")
                .subscribe(new Consumer<>() {
                    @Override
                    public void accept(BaseResultBean<Tv> movieBaseResultBean) throws Exception {
                        if (movieBaseResultBean.getResults() != null && movieBaseResultBean.getResults().size() > 0) {
                            mWatchlistTvs.addAll(movieBaseResultBean.getResults());
                        }
                        if (movieBaseResultBean.getPage() < movieBaseResultBean.getTotalPages()) {
                            getWatchlistTv(account, movieBaseResultBean.getPage() + 1);
                        } else {
                            mRepository.deleteAllWatchList();
                            for (int i = 0; i < mWatchlistMovies.size(); i++) {
                                Movie movie = mWatchlistMovies.get(i);
                                WatchList watchList = Utils.movieToWatchList(movie);
                                mRepository.insert(watchList);
                            }
                            for (int i = 0; i < mWatchlistTvs.size(); i++) {
                                Tv movie = mWatchlistTvs.get(i);
                                WatchList watchList = Utils.tvToWatchlist(movie);
                                mRepository.insert(watchList);
                            }
                        }
                    }
                }, new Consumer<>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                        getWatchlistTv(account, page);
                    }
                });
    }
}
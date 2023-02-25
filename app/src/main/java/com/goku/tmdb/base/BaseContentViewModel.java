package com.goku.tmdb.base;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.goku.tmdb.R;
import com.goku.tmdb.app.AppManager;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.data.TmdbRepository;
import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.Cast;
import com.goku.tmdb.data.entity.Credits;
import com.goku.tmdb.data.entity.Crew;
import com.goku.tmdb.data.entity.Movie;
import com.goku.tmdb.data.entity.Person;
import com.goku.tmdb.data.entity.Review;
import com.goku.tmdb.data.entity.Tv;
import com.goku.tmdb.data.entity.collections.CollectionsDetail;
import com.goku.tmdb.data.entity.search.SearchMulti;
import com.goku.tmdb.data.entity.tv.TvDetail;
import com.goku.tmdb.data.entity.tvseasons.SeasonDetail;
import com.goku.tmdb.ui.StatusModel;
import com.goku.tmdb.ui.home.ItemCategoryModel;
import com.goku.tmdb.ui.home.ItemMediaModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public abstract class BaseContentViewModel extends BaseViewModel<TmdbRepository> {

    private static final String TAG = BaseContentViewModel.class.getSimpleName();

    public final StatusModel statusModel = new StatusModel();

    public ObservableField<Integer> dominantColor;
    public ObservableField<Integer> titleTextColor;
    public ObservableField<Integer> bodyTextColor;
    public ObservableField<Boolean> isDark = new ObservableField<>(false);
    public MutableLiveData<Object> refreshColor = new MutableLiveData<>();

    public ObservableField<ItemCategoryModel> ownerModel = new ObservableField<>(new ItemCategoryModel());

    public ObservableField<String> pageStr = new ObservableField<>();
    public ObservableField<String> totalStr = new ObservableField<>();

    protected ItemCategoryModel mItemCategoryModel;
    protected ItemMediaModel mItemMediaModel;

    public BaseContentViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
        initColor();
    }

    public void initColor() {
        if (!Utils.isNightMode()) {
            isDark = new ObservableField<>(false);
            dominantColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.white));
            titleTextColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.sub_title_color));
            bodyTextColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.black));
        } else {
            isDark = new ObservableField<>(true);
            dominantColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.tmdb_primary_color));
            titleTextColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.sub_title_color));
            bodyTextColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.white));
        }
    }

    public void getContents(ItemCategoryModel itemCategoryModel) {
        mItemCategoryModel = itemCategoryModel;
        mItemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_MOVIE_TRENDING:
            case PageParams.CATEGORY_TYPE_MOVIE_NOW_PLAYING:
            case PageParams.CATEGORY_TYPE_MOVIE_POPULAR:
            case PageParams.CATEGORY_TYPE_MOVIE_TOP_RATED:
            case PageParams.CATEGORY_TYPE_MOVIE_UPCOMING:
            case PageParams.CATEGORY_TYPE_MOVIE_RECOMMENDATIONS:
            case PageParams.CATEGORY_TYPE_MOVIE_SIMILAR:
            case PageParams.CATEGORY_TYPE_MOVIE_GENRES:
            case PageParams.CATEGORY_TYPE_SEARCH_MOVIE:
            case PageParams.CATEGORY_TYPE_FAVORITE_MOVIE:
            case PageParams.CATEGORY_TYPE_WATCHLIST_MOVIE:
            case PageParams.CATEGORY_TYPE_RATING_MOVIE:
                getMovies(itemCategoryModel);
                break;
            case PageParams.CATEGORY_TYPE_COLLECTIONS:
                getCollections(itemCategoryModel);
                break;
            case PageParams.CATEGORY_TYPE_TV_TRENDING:
            case PageParams.CATEGORY_TYPE_TV_AIRING_TODAY:
            case PageParams.CATEGORY_TYPE_TV_ON_AIR:
            case PageParams.CATEGORY_TYPE_TV_TOP_RATED:
            case PageParams.CATEGORY_TYPE_TV_RECOMMENDATIONS:
            case PageParams.CATEGORY_TYPE_TV_SIMILAR:
            case PageParams.CATEGORY_TYPE_SEARCH_TV_SHOW:
            case PageParams.CATEGORY_TYPE_TV_GENRES:
            case PageParams.CATEGORY_TYPE_FAVORITE_TV:
            case PageParams.CATEGORY_TYPE_WATCHLIST_TV:
            case PageParams.CATEGORY_TYPE_RATING_TV:
                getTvs(itemCategoryModel);
                break;
            case PageParams.CATEGORY_TYPE_PERSON:
            case PageParams.CATEGORY_TYPE_PERSON_HORI:
            case PageParams.CATEGORY_TYPE_SEARCH_PERSON:
                getPersons(itemCategoryModel);
                break;
            case PageParams.CATEGORY_TYPE_MOVIE_PEOPLE:
            case PageParams.CATEGORY_TYPE_TV_PEOPLE:
                getPersonCombinedCredits(itemCategoryModel);
                break;
            case PageParams.CATEGORY_TYPE_REVIRES_MOVIE:
            case PageParams.CATEGORY_TYPE_REVIRES_TV:
                getReviews(itemCategoryModel);
                break;
            case PageParams.CATEGORY_TYPE_SEASONS:
                getSeasons(itemCategoryModel);
                break;
            case PageParams.CATEGORY_TYPE_EPISODES:
                getEpisodes(itemCategoryModel);
                break;
            case PageParams.CATEGORY_TYPE_CREDITS_MOVIE_CAST:
            case PageParams.CATEGORY_TYPE_CREDITS_MOVIE_CREW:
            case PageParams.CATEGORY_TYPE_CREDITS_TV_CAST:
            case PageParams.CATEGORY_TYPE_CREDITS_TV_CREW:
            case PageParams.CATEGORY_TYPE_CREDITS_SEASONS_CAST:
                getMediaCredits(itemCategoryModel);
                break;
        }
    }

    public void getNextPageContents(ItemCategoryModel itemCategoryModel) {
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_MOVIE_TRENDING:
            case PageParams.CATEGORY_TYPE_MOVIE_NOW_PLAYING:
            case PageParams.CATEGORY_TYPE_MOVIE_POPULAR:
            case PageParams.CATEGORY_TYPE_MOVIE_TOP_RATED:
            case PageParams.CATEGORY_TYPE_MOVIE_UPCOMING:
            case PageParams.CATEGORY_TYPE_MOVIE_RECOMMENDATIONS:
            case PageParams.CATEGORY_TYPE_MOVIE_SIMILAR:
            case PageParams.CATEGORY_TYPE_MOVIE_GENRES:
            case PageParams.CATEGORY_TYPE_SEARCH_MOVIE:
            case PageParams.CATEGORY_TYPE_FAVORITE_MOVIE:
            case PageParams.CATEGORY_TYPE_WATCHLIST_MOVIE:
            case PageParams.CATEGORY_TYPE_RATING_MOVIE:
                nextPageMovies(itemCategoryModel);
                break;
            case PageParams.CATEGORY_TYPE_TV_TRENDING:
            case PageParams.CATEGORY_TYPE_TV_AIRING_TODAY:
            case PageParams.CATEGORY_TYPE_TV_ON_AIR:
            case PageParams.CATEGORY_TYPE_TV_TOP_RATED:
            case PageParams.CATEGORY_TYPE_TV_RECOMMENDATIONS:
            case PageParams.CATEGORY_TYPE_TV_SIMILAR:
            case PageParams.CATEGORY_TYPE_SEARCH_TV_SHOW:
            case PageParams.CATEGORY_TYPE_TV_GENRES:
            case PageParams.CATEGORY_TYPE_FAVORITE_TV:
            case PageParams.CATEGORY_TYPE_WATCHLIST_TV:
            case PageParams.CATEGORY_TYPE_RATING_TV:
                nextPageTvs(itemCategoryModel);
                break;
            case PageParams.CATEGORY_TYPE_PERSON:
            case PageParams.CATEGORY_TYPE_PERSON_HORI:
            case PageParams.CATEGORY_TYPE_SEARCH_PERSON:
                nextPagePersons(itemCategoryModel);
            case PageParams.CATEGORY_TYPE_REVIRES_MOVIE:
            case PageParams.CATEGORY_TYPE_REVIRES_TV:
                nextPageReviews(itemCategoryModel);
                break;
        }
    }

    private void getMovies(ItemCategoryModel itemCategoryModel) {
        Observable<BaseResultBean<Movie>> observable = null;
        itemCategoryModel.statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                getMovies(itemCategoryModel);
            }
        };
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_MOVIE_TRENDING:
                observable = mModel.getMovieTrending(itemCategoryModel.getPage(),
                        itemCategoryModel.isDay.get() ? Constant.PATH_DAY : Constant.PATH_WEEK);
                break;
            case PageParams.CATEGORY_TYPE_MOVIE_NOW_PLAYING:
                observable = mModel.getMovieNowPlaying(itemCategoryModel.getPage(), "");
                break;
            case PageParams.CATEGORY_TYPE_MOVIE_POPULAR:
                observable = mModel.getMoviePopular(itemCategoryModel.getPage(), "");
                break;
            case PageParams.CATEGORY_TYPE_MOVIE_TOP_RATED:
                observable = mModel.getMovieTopRated(itemCategoryModel.getPage(), "");
                break;
            case PageParams.CATEGORY_TYPE_MOVIE_UPCOMING:
                observable = mModel.getMovieUpcoming(itemCategoryModel.getPage(), "");
                break;
            case PageParams.CATEGORY_TYPE_MOVIE_RECOMMENDATIONS:
                observable = mModel.getMovieRecommendations(itemCategoryModel.getId(), itemCategoryModel.getPage());
                break;
            case PageParams.CATEGORY_TYPE_MOVIE_SIMILAR:
                observable = mModel.getMovieSimilar(itemCategoryModel.getId(), itemCategoryModel.getPage());
                break;
            case PageParams.CATEGORY_TYPE_MOVIE_GENRES:
                observable = mModel.getMovieDiscover(itemCategoryModel.getPage(), itemCategoryModel.titles.get(), "popularity.desc", "", "", "", 0.0, 0.0);
                break;
            case PageParams.CATEGORY_TYPE_SEARCH_MOVIE:
                observable = mModel.searchMovie(itemCategoryModel.getSearchQuery(), itemCategoryModel.getPage());
                break;
            case PageParams.CATEGORY_TYPE_FAVORITE_MOVIE:
                observable = mModel.getFavoriteMovie(itemCategoryModel.getPage(), "created_at.asc");
                break;
            case PageParams.CATEGORY_TYPE_WATCHLIST_MOVIE:
                observable = mModel.getWatchlistMovie(itemCategoryModel.getPage(), "created_at.asc");
                break;
            case PageParams.CATEGORY_TYPE_RATING_MOVIE:
                observable = mModel.getRatingMovie(itemCategoryModel.getPage(), "created_at.asc");
                break;
            default:
                break;
        }
        if (observable != null) {
            getMovies(observable, itemCategoryModel);
        }
    }

    private void getMovies(Observable<BaseResultBean<Movie>> observable, ItemCategoryModel itemCategoryModel) {
        Disposable mapDisposable = (Disposable) mMap.get(itemCategoryModel.getCategoryType());
        if (mapDisposable != null && !mapDisposable.isDisposed()) {
            mapDisposable.dispose();
        }
        Disposable disposable = observable.subscribe(new Consumer<>() {
            @Override
            public void accept(BaseResultBean<Movie> movieBeans) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + movieBeans.toString());
                showMovies(movieBeans, itemCategoryModel);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                showError(itemCategoryModel);
//                getMovies(observable, itemCategoryModel);
            }
        });
        mMap.put(String.valueOf(itemCategoryModel.getCategoryType()), disposable);
        addSubscribe(disposable);
    }

    private void getTvs(ItemCategoryModel itemCategoryModel) {
        Observable<BaseResultBean<Tv>> observable = null;
        itemCategoryModel.statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                getTvs(itemCategoryModel);
            }
        };
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_TV_TRENDING:
                observable = mModel.getTvTrending(itemCategoryModel.getPage(),
                        itemCategoryModel.isDay.get() ? Constant.PATH_DAY : Constant.PATH_WEEK);
                break;
            case PageParams.CATEGORY_TYPE_TV_AIRING_TODAY:
                observable = mModel.getTvAiringToday(itemCategoryModel.getPage());
                break;
            case PageParams.CATEGORY_TYPE_TV_ON_AIR:
                observable = mModel.getTvOnTheAir(itemCategoryModel.getPage());
                break;
            case PageParams.CATEGORY_TYPE_TV_TOP_RATED:
                observable = mModel.getTvTopRated(itemCategoryModel.getPage());
                break;
            case PageParams.CATEGORY_TYPE_TV_RECOMMENDATIONS:
                observable = mModel.getTvRecommendations(itemCategoryModel.getId(), itemCategoryModel.getPage());
                break;
            case PageParams.CATEGORY_TYPE_TV_SIMILAR:
                observable = mModel.getTvSimilar(itemCategoryModel.getId(), itemCategoryModel.getPage());
                break;
            case PageParams.CATEGORY_TYPE_SEARCH_TV_SHOW:
                observable = mModel.searchTv(itemCategoryModel.getSearchQuery(), itemCategoryModel.getPage());
                break;
            case PageParams.CATEGORY_TYPE_TV_GENRES:
                observable = mModel.getTvDiscover(itemCategoryModel.getPage(), itemCategoryModel.titles.get(), "popularity.desc", "", "", "", 0.0, 0.0);
                break;
            case PageParams.CATEGORY_TYPE_FAVORITE_TV:
                observable = mModel.getFavoriteTv(itemCategoryModel.getPage(), "created_at.asc");
                break;
            case PageParams.CATEGORY_TYPE_WATCHLIST_TV:
                observable = mModel.getWatchlistTv(itemCategoryModel.getPage(), "created_at.asc");
                break;
            case PageParams.CATEGORY_TYPE_RATING_TV:
                observable = mModel.getRatingTv(itemCategoryModel.getPage(), "created_at.asc");
                break;
            default:
                break;

        }
        if (observable != null) {
            getTvs(observable, itemCategoryModel);
        }
    }

    private void getTvs(Observable<BaseResultBean<Tv>> observable, ItemCategoryModel itemCategoryModel) {
        Disposable mapDisposable = (Disposable) mMap.get(itemCategoryModel.getCategoryType());
        if (mapDisposable != null && !mapDisposable.isDisposed()) {
            mapDisposable.dispose();
        }
        Disposable disposable = observable.subscribe(new Consumer<>() {
            @Override
            public void accept(BaseResultBean<Tv> tvBeans) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + tvBeans.toString());
                showTvs(tvBeans, itemCategoryModel);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
//                getTvs(observable, itemCategoryModel);
                showError(itemCategoryModel);
            }
        });
        mMap.put(String.valueOf(itemCategoryModel.getCategoryType()), disposable);
        addSubscribe(disposable);
    }

    private void getPersons(ItemCategoryModel itemCategoryModel) {
        Observable<BaseResultBean<Person>> observable = null;
        itemCategoryModel.statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                getPersons(itemCategoryModel);
            }
        };
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_PERSON:
            case PageParams.CATEGORY_TYPE_PERSON_HORI:
                observable = mModel.getPersonTrending(itemCategoryModel.getPage(),
                        itemCategoryModel.isDay.get() ? Constant.PATH_DAY : Constant.PATH_WEEK);
                break;
            case PageParams.CATEGORY_TYPE_SEARCH_PERSON:
                observable = mModel.searchPerson(itemCategoryModel.getSearchQuery(), itemCategoryModel.getPage());
                break;
            default:
                break;

        }
        if (observable != null) {
            getPersons(observable, itemCategoryModel);
        }
    }

    private void getPersons(Observable<BaseResultBean<Person>> observable, ItemCategoryModel itemCategoryModel) {
        Disposable mapDisposable = (Disposable) mMap.get(itemCategoryModel.getCategoryType());
        if (mapDisposable != null && !mapDisposable.isDisposed()) {
            mapDisposable.dispose();
        }
        Disposable disposable = observable.subscribe(new Consumer<>() {
            @Override
            public void accept(BaseResultBean<Person> personBeans) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + personBeans.toString());
                showPersons(personBeans, itemCategoryModel);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
//                getPersons(observable, itemCategoryModel);
                showError(itemCategoryModel);
            }
        });
        mMap.put(String.valueOf(itemCategoryModel.getCategoryType()), disposable);
        addSubscribe(disposable);
    }

    private void getPersonCombinedCredits(ItemCategoryModel itemCategoryModel) {
        Observable<Credits> observable = null;
        itemCategoryModel.statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                getPersonCombinedCredits(itemCategoryModel);
            }
        };
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_MOVIE_PEOPLE:
            case PageParams.CATEGORY_TYPE_TV_PEOPLE:
                observable = mModel.getPersonCombinedCredits(itemCategoryModel.getId());
                break;
            default:
                break;

        }
        if (observable != null) {
            getPersonCombinedCredits(observable, itemCategoryModel);
        }
    }

    private void getPersonCombinedCredits(Observable<Credits> observable, ItemCategoryModel itemCategoryModel) {
        Disposable mapDisposable = (Disposable) mMap.get(itemCategoryModel.getCategoryType());
        if (mapDisposable != null && !mapDisposable.isDisposed()) {
            mapDisposable.dispose();
        }
        Disposable disposable = observable.subscribe(new Consumer<>() {
            @Override
            public void accept(Credits credits) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + credits.toString());
                showPersonCombinedCredits(credits, itemCategoryModel);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                showError(itemCategoryModel);
            }
        });
        mMap.put(String.valueOf(itemCategoryModel.getCategoryType()), disposable);
        addSubscribe(disposable);
    }

    private void getReviews(ItemCategoryModel itemCategoryModel) {
        Observable<BaseResultBean<Review>> localObservable = null;
        Observable<BaseResultBean<Review>> enObservable = null;
        itemCategoryModel.statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                getReviews(itemCategoryModel);
            }
        };
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_REVIRES_MOVIE:
                localObservable = mModel.getMovieReviews(itemCategoryModel.getId(), itemCategoryModel.getPage(), Utils.getLanguage());
                enObservable = mModel.getMovieReviews(itemCategoryModel.getId(), itemCategoryModel.getPage(), Locale.ENGLISH.getLanguage());
                break;
            case PageParams.CATEGORY_TYPE_REVIRES_TV:
                localObservable = mModel.getTvReviews(itemCategoryModel.getId(), itemCategoryModel.getPage(), Utils.getLanguage());
                enObservable = mModel.getTvReviews(itemCategoryModel.getId(), itemCategoryModel.getPage(), Locale.ENGLISH.getLanguage());
                break;
            default:
                break;

        }
        if (localObservable != null && enObservable != null) {
            Observable<BaseResultBean<Review>> zipObservable = Observable.zip(localObservable, enObservable, new BiFunction<BaseResultBean<Review>, BaseResultBean<Review>, BaseResultBean<Review>>() {
                @Override
                public BaseResultBean<Review> apply(BaseResultBean<Review> localReviewBean, BaseResultBean<Review> extreReviewBean) throws Exception {
                    if (localReviewBean.getTotalPages() >= extreReviewBean.getTotalPages()) {
                        reviewOverlay(localReviewBean, extreReviewBean);
                    } else {
                        reviewOverlay(extreReviewBean, localReviewBean);
                    }
                    return localReviewBean;
                }
            });
            getReviews(zipObservable, itemCategoryModel);
        }
    }

    private void reviewOverlay(BaseResultBean<Review> originReviewBean, BaseResultBean<Review> extraReviewBean) {
        if (originReviewBean != null && extraReviewBean != null) {
            if (originReviewBean.getResults() == null) {
                originReviewBean.setResults(new ArrayList<>());
            }
            if (originReviewBean.getTotalPages() == null) {
                originReviewBean.setTotalPages(0);
            }
            if (originReviewBean.getTotalResults() == null) {
                originReviewBean.setTotalResults(0);
            }

            if (extraReviewBean.getResults() != null) {
                originReviewBean.getResults().addAll(extraReviewBean.getResults());
            }
            if (extraReviewBean.getTotalResults() != null) {
                originReviewBean.setTotalResults(originReviewBean.getTotalResults() + extraReviewBean.getTotalResults());
            }
        }
    }

    private void getReviews(Observable<BaseResultBean<Review>> observable, ItemCategoryModel itemCategoryModel) {
        Disposable mapDisposable = (Disposable) mMap.get(itemCategoryModel.getCategoryType());
        if (mapDisposable != null && !mapDisposable.isDisposed()) {
            mapDisposable.dispose();
        }

        Disposable disposable = observable.subscribe(new Consumer<>() {
            @Override
            public void accept(BaseResultBean<Review> reviewBeans) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + reviewBeans.toString());
                showReviews(reviewBeans, itemCategoryModel);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                showError(itemCategoryModel);
            }
        });
        mMap.put(String.valueOf(itemCategoryModel.getCategoryType()), disposable);
        addSubscribe(disposable);
    }

    private void getSeasons(ItemCategoryModel itemCategoryModel) {
        Observable<TvDetail> observable = null;
        itemCategoryModel.statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                getSeasons(itemCategoryModel);
            }
        };
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_SEASONS:
                observable = mModel.getTvDetial(itemCategoryModel.getId());
                break;
            default:
                break;

        }
        if (observable != null) {
            getSeasons(observable, itemCategoryModel);
        }
    }

    private void getSeasons(Observable<TvDetail> observable, ItemCategoryModel itemCategoryModel) {
        Disposable mapDisposable = (Disposable) mMap.get(itemCategoryModel.getCategoryType());
        if (mapDisposable != null && !mapDisposable.isDisposed()) {
            mapDisposable.dispose();
        }
        Disposable disposable = observable.subscribe(new Consumer<>() {
            @Override
            public void accept(TvDetail movieDetail) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + movieDetail.toString());
                showSeasons(movieDetail, itemCategoryModel);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                showError(itemCategoryModel);
            }
        });
        mMap.put(String.valueOf(itemCategoryModel.getCategoryType()), disposable);
        addSubscribe(disposable);
    }

    private void getEpisodes(ItemCategoryModel itemCategoryModel) {
        Observable<SeasonDetail> observable = null;
        itemCategoryModel.statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                getEpisodes(itemCategoryModel);
            }
        };
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_EPISODES:
                observable = mModel.getTvSeasonDetail(itemCategoryModel.getTvId(), itemCategoryModel.getSeasonNumber());
                break;
            default:
                break;

        }
        if (observable != null) {
            getEpisodes(observable, itemCategoryModel);
        }
    }

    private void getEpisodes(Observable<SeasonDetail> observable, ItemCategoryModel itemCategoryModel) {
        Disposable mapDisposable = (Disposable) mMap.get(itemCategoryModel.getCategoryType());
        if (mapDisposable != null && !mapDisposable.isDisposed()) {
            mapDisposable.dispose();
        }
        Disposable disposable = observable.subscribe(new Consumer<>() {
            @Override
            public void accept(SeasonDetail seasonDetail) throws Exception {
                showEpisodes(seasonDetail, itemCategoryModel);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                showError(itemCategoryModel);
            }
        });
        mMap.put(String.valueOf(itemCategoryModel.getCategoryType()), disposable);
        addSubscribe(disposable);
    }

    private void getMediaCredits(ItemCategoryModel itemCategoryModel) {
        Observable<Credits> observable = null;
        itemCategoryModel.statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                getMediaCredits(itemCategoryModel);
            }
        };
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_CREDITS_MOVIE_CAST:
            case PageParams.CATEGORY_TYPE_CREDITS_MOVIE_CREW:
                observable = mModel.getMovieCredits(itemCategoryModel.getId());
                break;
            case PageParams.CATEGORY_TYPE_CREDITS_TV_CAST:
            case PageParams.CATEGORY_TYPE_CREDITS_TV_CREW:
                observable = mModel.getTvCredits(itemCategoryModel.getId());
                break;
            case PageParams.CATEGORY_TYPE_CREDITS_SEASONS_CAST:
                observable = mModel.getTvSeasonCredits(itemCategoryModel.getTvId(), itemCategoryModel.getSeasonNumber());
                break;
            default:
                break;

        }
        if (observable != null) {
            getMediaCredits(observable, itemCategoryModel);
        }
    }

    private void getMediaCredits(Observable<Credits> observable, ItemCategoryModel itemCategoryModel) {
        Disposable mapDisposable = (Disposable) mMap.get(itemCategoryModel.getCategoryType());
        if (mapDisposable != null && !mapDisposable.isDisposed()) {
            mapDisposable.dispose();
        }
        Disposable disposable = observable.subscribe(new Consumer<>() {
            @Override
            public void accept(Credits credits) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + credits.toString());
                showMediaCredits(credits, itemCategoryModel);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                showError(itemCategoryModel);
            }
        });
        mMap.put(String.valueOf(itemCategoryModel.getCategoryType()), disposable);
        addSubscribe(disposable);
    }

    private void getSearch(ItemCategoryModel itemCategoryModel) {
        Observable<BaseResultBean<SearchMulti>> observable = null;
        itemCategoryModel.statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                getSearch(itemCategoryModel);
            }
        };
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_SEARCH_MOVIE:
                observable = mModel.searchMulti(itemCategoryModel.getSearchQuery(), itemCategoryModel.getPage());
                break;
            default:
                break;

        }
        if (observable != null) {
            getSearch(observable, itemCategoryModel);
        }
    }

    private void getSearch(Observable<BaseResultBean<SearchMulti>> observable, ItemCategoryModel itemCategoryModel) {
        Disposable mapDisposable = (Disposable) mMap.get(itemCategoryModel.getCategoryType());
        if (mapDisposable != null && !mapDisposable.isDisposed()) {
            mapDisposable.dispose();
        }
        Disposable disposable = observable.subscribe(new Consumer<>() {
            @Override
            public void accept(BaseResultBean<SearchMulti> searchMulti) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + searchMulti.toString());
                showSearch(searchMulti, itemCategoryModel);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                showError(itemCategoryModel);
            }
        });
        mMap.put(String.valueOf(itemCategoryModel.getCategoryType()), disposable);
        addSubscribe(disposable);
    }

    private void getCollections(ItemCategoryModel itemCategoryModel) {
        Observable<CollectionsDetail> observable = null;
        itemCategoryModel.statusModel.onErrorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                getCollections(itemCategoryModel);
            }
        };
        switch (itemCategoryModel.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_COLLECTIONS:
                observable = mModel.getCollections(itemCategoryModel.getId());
                break;
            default:
                break;

        }
        if (observable != null) {
            getCollections(observable, itemCategoryModel);
        }
    }

    private final Map<String, Object> mMap = new HashMap<>();

    private void getCollections(Observable<CollectionsDetail> observable, ItemCategoryModel itemCategoryModel) {
        Disposable mapDisposable = (Disposable) mMap.get(itemCategoryModel.getCategoryType());
        if (mapDisposable != null && !mapDisposable.isDisposed()) {
            mapDisposable.dispose();
        }
        Disposable disposable = observable.subscribe(new Consumer<>() {
            @Override
            public void accept(CollectionsDetail credits) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + credits.toString());
                showCollections(credits, itemCategoryModel);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                showError(itemCategoryModel);
            }
        });
        mMap.put(String.valueOf(itemCategoryModel.getCategoryType()), disposable);
        addSubscribe(disposable);
    }

    private void nextPageMovies(ItemCategoryModel itemCategoryModel) {
        itemCategoryModel.setPage(itemCategoryModel.getPage() + 1);
        getMovies(itemCategoryModel);
    }

    private void nextPageTvs(ItemCategoryModel itemCategoryModel) {
        itemCategoryModel.setPage(itemCategoryModel.getPage() + 1);
        getTvs(itemCategoryModel);
    }

    private void nextPagePersons(ItemCategoryModel itemCategoryModel) {
        itemCategoryModel.setPage(itemCategoryModel.getPage() + 1);
        getPersons(itemCategoryModel);
    }

    private void nextPageReviews(ItemCategoryModel itemCategoryModel) {
        itemCategoryModel.setPage(itemCategoryModel.getPage() + 1);
        getReviews(itemCategoryModel);
    }

    private void showMovies(BaseResultBean<Movie> movieBeans, ItemCategoryModel itemCategoryModel) {
        if (movieBeans != null) {
            itemCategoryModel.count.set(Utils.numberAddComma(movieBeans.getTotalResults()));
            if (movieBeans.getTotalResults() <= 0) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
            } else {
                itemCategoryModel.setPage(movieBeans.getPage());
                itemCategoryModel.setTotalPage(movieBeans.getTotalPages());
                pageStr.set(Utils.getContext().getString(R.string.page_format, movieBeans.getPage(), movieBeans.getTotalPages()));
                totalStr.set(Utils.getContext().getString(R.string.total_format, movieBeans.getTotalResults()));
                itemCategoryModel.itemDatas.get().addAll(Utils.movieBeanToMediaModel(movieBeans.getResults(), itemCategoryModel));
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
            }
            itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
        }
    }

    private void showTvs(BaseResultBean<Tv> tvBeans, ItemCategoryModel itemCategoryModel) {
        if (tvBeans != null) {
            itemCategoryModel.count.set(Utils.numberAddComma(tvBeans.getTotalResults()));
            if (tvBeans.getTotalResults() <= 0) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
            } else {
                itemCategoryModel.setPage(tvBeans.getPage());
                itemCategoryModel.setTotalPage(tvBeans.getTotalPages());
                pageStr.set(Utils.getContext().getString(R.string.page_format, tvBeans.getPage(), tvBeans.getTotalPages()));
                totalStr.set(Utils.getContext().getString(R.string.total_format, tvBeans.getTotalResults()));
                itemCategoryModel.itemDatas.get().addAll(Utils.tvBeanToMediaModel(tvBeans.getResults(), itemCategoryModel));
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
            }
            itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
        }
    }

    private void showPersons(BaseResultBean<Person> personBeans, ItemCategoryModel itemCategoryModel) {
        if (personBeans != null) {
            itemCategoryModel.count.set(Utils.numberAddComma(personBeans.getTotalResults()));
            if (personBeans.getTotalResults() <= 0) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
            } else {
                itemCategoryModel.setPage(personBeans.getPage());
                itemCategoryModel.setTotalPage(personBeans.getTotalPages());
                pageStr.set(Utils.getContext().getString(R.string.page_format, personBeans.getPage(), personBeans.getTotalPages()));
                totalStr.set(Utils.getContext().getString(R.string.total_format, personBeans.getTotalResults()));
                int itemType = PageParams.ITEM_TYPE_PEOPLE;
                if (itemCategoryModel.getCategoryType() == PageParams.CATEGORY_TYPE_PERSON_HORI
                        || itemCategoryModel.getCategoryType() == PageParams.CATEGORY_TYPE_SEARCH_PERSON) {
                    itemType = PageParams.ITEM_TYPE_PEOPLE_HORI;
                }
                itemCategoryModel.itemDatas.get().addAll(Utils.personBeanToMediaModel(personBeans.getResults(), itemType));
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
            }
            itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
        }
    }

    private void showPersonCombinedCredits(Credits credits, ItemCategoryModel itemCategoryModel) {
        if (credits != null) {
            if ((credits.getCast() == null || credits.getCast().size() == 0)
                    && (credits.getCrew() == null || credits.getCrew().size() == 0)) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
            } else {
                int itemType = PageParams.ITEM_TYPE_MOIVE;
                if (itemCategoryModel.getCategoryType() == PageParams.CATEGORY_TYPE_TV_PEOPLE) {
                    itemType = PageParams.ITEM_TYPE_TV_SHOW;
                }
                List<ItemMediaModel> mediaModels = Utils.personCreditsToMediaModel(credits, itemType);
                if (mediaModels.size() == 0) {
                    itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
                } else {
                    itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
                    itemCategoryModel.itemDatas.get().addAll(mediaModels);
                }
                totalStr.set(Utils.getContext().getString(R.string.total_format, mediaModels.size()));
            }
            itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
        }
    }

    private void showReviews(BaseResultBean<Review> reviewBeans, ItemCategoryModel itemCategoryModel) {
        if (reviewBeans != null) {
            itemCategoryModel.count.set(Utils.numberAddComma(reviewBeans.getTotalResults()));
            if (reviewBeans.getTotalResults() <= 0) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
            } else {
                pageStr.set(Utils.getContext().getString(R.string.page_format, reviewBeans.getPage(), reviewBeans.getTotalPages()));
                totalStr.set(Utils.getContext().getString(R.string.total_format, reviewBeans.getTotalResults()));
                itemCategoryModel.setPage(reviewBeans.getPage());
                itemCategoryModel.setTotalPage(reviewBeans.getTotalPages());
                itemCategoryModel.itemDatas.get().addAll(Utils.reviewBeanToItemModel(reviewBeans.getResults()));
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
            }
            itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
        }
    }

    private void showSeasons(TvDetail tvDetail, ItemCategoryModel itemCategoryModel) {
        if (tvDetail != null) {
            List<TvDetail.Seasons> seasons = tvDetail.getSeasons();
            if (seasons != null) {
                List<ItemMediaModel> mediaModels = Utils.seasonBeanToMediaModel(tvDetail.getSeasons(), itemCategoryModel);
                if (mediaModels.size() == 0) {
                    itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
                } else {
                    itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
                    itemCategoryModel.itemDatas.get().addAll(mediaModels);
                }
            } else {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
            }
        }
        itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
    }

    private void showEpisodes(SeasonDetail tvDetail, ItemCategoryModel itemCategoryModel) {
        List<SeasonDetail.Episodes> episodes = tvDetail.getEpisodes();
        if (episodes != null) {
            List<ItemMediaModel> mediaModels = Utils.episodeBeanToMediaModel(episodes, itemCategoryModel);
            if (mediaModels.size() == 0) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
            } else {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
                itemCategoryModel.itemDatas.get().addAll(mediaModels);
            }
        } else {
            itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
        }
        itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
    }

    private void showMediaCredits(Credits credits, ItemCategoryModel itemCategoryModel) {
        if (credits != null) {
            if (itemCategoryModel.getCategoryType() == PageParams.CATEGORY_TYPE_CREDITS_MOVIE_CAST
                    || itemCategoryModel.getCategoryType() == PageParams.CATEGORY_TYPE_CREDITS_TV_CAST
                    || itemCategoryModel.getCategoryType() == PageParams.CATEGORY_TYPE_CREDITS_SEASONS_CAST) {
                List<Cast> casts = credits.getCast();
                if (casts != null) {
                    List<ItemMediaModel> mediaModels = Utils.castBeanToMediaModel(casts, PageParams.ITEM_TYPE_PEOPLE_HORI);
                    if (mediaModels.size() == 0) {
                        itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
                    } else {
                        itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
                        itemCategoryModel.itemDatas.get().addAll(mediaModels);
                    }
                    totalStr.set(Utils.getContext().getString(R.string.total_format, mediaModels.size()));
                } else {
                    itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
                }
            }
            if (itemCategoryModel.getCategoryType() == PageParams.CATEGORY_TYPE_CREDITS_MOVIE_CREW
                    || itemCategoryModel.getCategoryType() == PageParams.CATEGORY_TYPE_CREDITS_TV_CREW) {
                List<Crew> crews = credits.getCrew();
                if (crews != null) {
                    List<ItemMediaModel> mediaModels = Utils.crewBeanToMediaModel(crews, PageParams.ITEM_TYPE_PEOPLE_HORI);
                    if (mediaModels.size() == 0) {
                        itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
                    } else {
                        itemCategoryModel.itemDatas.get().addAll(mediaModels);
                        itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
                    }
                    totalStr.set(Utils.getContext().getString(R.string.total_format, mediaModels.size()));
                } else {
                    itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
                }
            }
        } else {
            itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
        }
        itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
    }

    private void showCollections(CollectionsDetail collectionsDetail, ItemCategoryModel itemCategoryModel) {
        if (collectionsDetail != null) {
            totalStr.set(Utils.getContext().getString(R.string.total_format, collectionsDetail.getParts().size()));
            if (collectionsDetail.getParts().size() <= 0) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
            } else {
                itemCategoryModel.itemDatas.get().addAll(Utils.collectionToMediaModel(collectionsDetail.getParts()));
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
            }
        }
        itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
    }

    private void showSearch(BaseResultBean<SearchMulti> collectionsDetail, ItemCategoryModel itemCategoryModel) {
        if (collectionsDetail != null) {
            if (collectionsDetail.getPage() <= 0) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_NO_DATA);
            } else {
                itemCategoryModel.itemDatas.get().addAll(Utils.searchBeanToMediaModel(collectionsDetail));
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
            }
        }
        itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
    }

    private void showError(ItemCategoryModel itemCategoryModel) {
        if (itemCategoryModel != null) {
            if (itemCategoryModel.getPage() == 1) {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_ERROR);
            } else {
                itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
                itemCategoryModel.setPage(itemCategoryModel.getPage() - 1);
            }
            itemCategoryModel.refreshCategory.postValue(itemCategoryModel);
        }
    }

    protected String getString(int id) {
        return AppManager.getAppManager().currentActivity().getString(id);
    }

    protected Drawable getDrawable(int id) {
        return TmdbApplication.getInstance().getResources().getDrawable(id);
    }

    public ItemCategoryModel getItemCategoryModel() {
        return mItemCategoryModel;
    }

    public void setItemCategoryModel(ItemCategoryModel itemCategoryModel) {
        mItemCategoryModel = itemCategoryModel;
    }

    public ItemMediaModel getItemMediaModel() {
        return mItemMediaModel;
    }

    public void setItemMediaModel(ItemMediaModel itemMediaModel) {
        mItemMediaModel = itemMediaModel;
    }
}

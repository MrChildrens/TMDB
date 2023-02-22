package com.goku.tmdb.data;

import android.util.Log;

import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseModel;
import com.goku.tmdb.data.entity.Account;
import com.goku.tmdb.data.entity.AccountStates;
import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.Credits;
import com.goku.tmdb.data.entity.Movie;
import com.goku.tmdb.data.entity.Person;
import com.goku.tmdb.data.entity.Review;
import com.goku.tmdb.data.entity.StatusBean;
import com.goku.tmdb.data.entity.Tv;
import com.goku.tmdb.data.entity.Video;
import com.goku.tmdb.data.entity.YoutubeVideo;
import com.goku.tmdb.data.entity.authentication.GuestSession;
import com.goku.tmdb.data.entity.authentication.RequestToken;
import com.goku.tmdb.data.entity.authentication.Session;
import com.goku.tmdb.data.entity.collections.CollectionsDetail;
import com.goku.tmdb.data.entity.configuration.Configuration;
import com.goku.tmdb.data.entity.configuration.Countries;
import com.goku.tmdb.data.entity.configuration.Jobs;
import com.goku.tmdb.data.entity.configuration.Languages;
import com.goku.tmdb.data.entity.configuration.Timezones;
import com.goku.tmdb.data.entity.movie.MediaImages;
import com.goku.tmdb.data.entity.movie.MovieAlternativeTitles;
import com.goku.tmdb.data.entity.movie.MovieDetail;
import com.goku.tmdb.data.entity.movie.MovieKeywords;
import com.goku.tmdb.data.entity.person.PersonDetail;
import com.goku.tmdb.data.entity.search.SearchMulti;
import com.goku.tmdb.data.entity.tv.TvDetail;
import com.goku.tmdb.data.entity.tvepisodes.EpisodesDetail;
import com.goku.tmdb.data.entity.tvseasons.SeasonDetail;
import com.goku.tmdb.data.source.HttpDataSource;
import com.goku.tmdb.data.source.LocalDataSource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class TmdbRepository extends BaseModel implements HttpDataSource, LocalDataSource {
    private static final String TAG = TmdbRepository.class.getSimpleName();

    private volatile static TmdbRepository sInstance;

    private final HttpDataSource mHttpDataSource;
    private final LocalDataSource mLocalDataSource;

    public TmdbRepository(HttpDataSource httpDataSource, LocalDataSource localDataSource) {
        mHttpDataSource = httpDataSource;
        mLocalDataSource = localDataSource;
    }

    public static TmdbRepository getInstance(HttpDataSource httpDataSource, LocalDataSource localDataSource) {
        if (sInstance == null) {
            synchronized (TmdbRepository.class) {
                if (sInstance == null) {
                    sInstance = new TmdbRepository(httpDataSource, localDataSource);
                }
            }
        }
        return sInstance;
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieTrending(int page, String timeWindow) {
        return mHttpDataSource.getMovieTrending(page, timeWindow);
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvTrending(int page, String timeWindow) {
        return mHttpDataSource.getTvTrending(page, timeWindow);
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvOnTheAir(int page) {
        return mHttpDataSource.getTvOnTheAir(page);
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvAiringToday(int page) {
        return mHttpDataSource.getTvAiringToday(page);
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvTopRated(int page) {
        return mHttpDataSource.getTvTopRated(page);
    }

    @Override
    public Observable<BaseResultBean<Person>> getPersonTrending(int page, String timeWindow) {
        return mHttpDataSource.getPersonTrending(page, timeWindow);
    }

    @Override
    public Observable<PersonDetail> getPersonDetial(long personId) {
        return mHttpDataSource.getPersonDetial(personId);
    }

    @Override
    public Observable<Credits> getPersonMovieCredits(long personId) {
        return mHttpDataSource.getPersonMovieCredits(personId);
    }

    @Override
    public Observable<Credits> getPersonCombinedCredits(long personId) {
        return mHttpDataSource.getPersonCombinedCredits(personId);
    }

    @Override
    public Observable<YoutubeVideo> getYoutubeVideo(String id, String part) {
        return mHttpDataSource.getYoutubeVideo(id, part);
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieDiscover(int page, String genres, String sortBy, String primaryReleaseYear, String primaryReleaseDateGte, String primaryReleaseDateLte, Double voteAverageGte, Double voteAverageLte) {
        return mHttpDataSource.getMovieDiscover(page, genres, sortBy, primaryReleaseYear, primaryReleaseDateGte, primaryReleaseDateLte, voteAverageGte, voteAverageLte);
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieByCompany(int page, String company, String sortBy, String primaryReleaseYear, String primaryReleaseDateGte, String primaryReleaseDateLte, Double voteAverageGte, Double voteAverageLte) {
        return mHttpDataSource.getMovieDiscover(page, company, sortBy, primaryReleaseYear, primaryReleaseDateGte, primaryReleaseDateLte, voteAverageGte, voteAverageLte);

    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvDiscover(int page, String genres, String sortBy, String primaryReleaseYear, String primaryReleaseDateGte, String primaryReleaseDateLte, Double voteAverageGte, Double voteAverageLte) {
        return mHttpDataSource.getTvDiscover(page, genres, sortBy, primaryReleaseYear, primaryReleaseDateGte, primaryReleaseDateLte, voteAverageGte, voteAverageLte);

    }

    @Override
    public Observable<BaseResultBean<SearchMulti>> searchMulti(String query, int page) {
        return mHttpDataSource.searchMulti(query, page);
    }

    @Override
    public Observable<BaseResultBean<Movie>> searchMovie(String query, int page) {
        return mHttpDataSource.searchMovie(query, page);
    }

    @Override
    public Observable<BaseResultBean<Tv>> searchTv(String query, int page) {
        return mHttpDataSource.searchTv(query, page);
    }

    @Override
    public Observable<BaseResultBean<Person>> searchPerson(String query, int page) {
        return mHttpDataSource.searchPerson(query, page);
    }

    @Override
    public Observable<BaseResultBean<Movie>> getFavoriteMovie(int page, String sortBy) {
        return mHttpDataSource.getFavoriteMovie(page, sortBy)
                /*.flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends BaseResultBean<Movie>> apply(BaseResultBean<Movie> movieBaseResultBean) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] apply: getApiConfiguration http: " + movieBaseResultBean);
                        if (movieBaseResultBean.getResults() != null && movieBaseResultBean.getResults().size() > 0) {
                            for (int i = 0; i < movieBaseResultBean.getResults().size(); i++) {
                                Movie movie = movieBaseResultBean.getResults().get(i);
                                Favorite favorite = Utils.movieToFavorite(movie);
                                if (favorite != null) {
                                    mLocalDataSource.insert(favorite);
                                }
                            }
                        }

                        return Observable.just(movieBaseResultBean);
                    }
                })*/;
    }

    @Override
    public Observable<BaseResultBean<Tv>> getFavoriteTv(int page, String sortBy) {
        return mHttpDataSource.getFavoriteTv(page, sortBy)
                /*.flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends BaseResultBean<Tv>> apply(BaseResultBean<Tv> tvBaseResultBean) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] apply: getApiConfiguration http: " + tvBaseResultBean);
                        if (tvBaseResultBean.getResults() != null && tvBaseResultBean.getResults().size() > 0) {
                            for (int i = 0; i < tvBaseResultBean.getResults().size(); i++) {
                                Tv tv = tvBaseResultBean.getResults().get(i);
                                Favorite favorite = Utils.tvToFavorite(tv);
                                if (favorite != null) {
                                    mLocalDataSource.insert(favorite);
                                }
                            }
                        }

                        return Observable.just(tvBaseResultBean);
                    }
                })*/;
    }

    @Override
    public Observable<StatusBean> favorite(String mediaTpye, long mediaId, boolean isFav) {
        return mHttpDataSource.favorite(mediaTpye, mediaId, isFav);
    }

    @Override
    public Observable<BaseResultBean<Movie>> getWatchlistMovie(int page, String sortBy) {
        return mHttpDataSource.getWatchlistMovie(page, sortBy)
                /*.flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends BaseResultBean<Movie>> apply(BaseResultBean<Movie> movieBaseResultBean) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] apply: getApiConfiguration http: " + movieBaseResultBean);
                        if (movieBaseResultBean.getResults() != null && movieBaseResultBean.getResults().size() > 0) {
                            for (int i = 0; i < movieBaseResultBean.getResults().size(); i++) {
                                Movie movie = movieBaseResultBean.getResults().get(i);
                                WatchList watchList = Utils.movieToWatchList(movie);
                                if (watchList != null) {
                                    mLocalDataSource.insert(watchList);
                                }
                            }
                        }

                        return Observable.just(movieBaseResultBean);
                    }
                })*/;
    }

    @Override
    public Observable<BaseResultBean<Tv>> getWatchlistTv(int page, String sortBy) {
        return mHttpDataSource.getWatchlistTv(page, sortBy)
                /*.flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends BaseResultBean<Tv>> apply(BaseResultBean<Tv> tvBaseResultBean) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] apply: getApiConfiguration http: " + tvBaseResultBean);
                        if (tvBaseResultBean.getResults() != null && tvBaseResultBean.getResults().size() > 0) {
                            for (int i = 0; i < tvBaseResultBean.getResults().size(); i++) {
                                Tv tv = tvBaseResultBean.getResults().get(i);
                                WatchList watchList = Utils.tvToWatchlist(tv);
                                if (watchList != null) {
                                    mLocalDataSource.insert(watchList);
                                }
                            }
                        }

                        return Observable.just(tvBaseResultBean);
                    }
                })*/;
    }

    @Override
    public Observable<BaseResultBean<Movie>> getRatingMovie(int page, String sortBy) {
        return mHttpDataSource.getRatingMovie(page, sortBy);
    }

    @Override
    public Observable<BaseResultBean<Tv>> getRatingTv(int page, String sortBy) {
        return mHttpDataSource.getRatingTv(page, sortBy);
    }

    @Override
    public Observable<StatusBean> watchlist(String mediaTpye, long mediaId, boolean isWatchList) {
        return mHttpDataSource.watchlist(mediaTpye, mediaId, isWatchList);
    }

    @Override
    public Observable<StatusBean> ratingMovie(long movieId, float rating) {
        return mHttpDataSource.ratingMovie(movieId, rating);
    }

    @Override
    public Observable<StatusBean> removeMovieRating(long movieId) {
        return mHttpDataSource.removeMovieRating(movieId);
    }

    @Override
    public Observable<StatusBean> ratingTv(long tvId, float rating) {
        return mHttpDataSource.ratingTv(tvId, rating);
    }

    @Override
    public Observable<StatusBean> removeTvRating(long tvId) {
        return mHttpDataSource.removeTvRating(tvId);
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieNowPlaying(int page, String region) {
        return mHttpDataSource.getMovieNowPlaying(page, region);
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieUpcoming(int page, String region) {
        return mHttpDataSource.getMovieUpcoming(page, region);
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMoviePopular(int page, String region) {
        return mHttpDataSource.getMoviePopular(page, region);
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieTopRated(int page, String region) {
        return mHttpDataSource.getMovieTopRated(page, region);
    }

    @Override
    public Observable<MovieDetail> getMovieDetail(long id) {
        return mHttpDataSource.getMovieDetail(id);
    }

    @Override
    public Observable<AccountStates> getMovieAccountStates(long movieId) {
        return mHttpDataSource.getMovieAccountStates(movieId);
    }

    @Override
    public Observable<Credits> getMovieCredits(long movieId) {
        return mHttpDataSource.getMovieCredits(movieId);
    }

    @Override
    public Observable<MovieAlternativeTitles> getMovieAlternativeTitles(long movieId, String country) {
        return mHttpDataSource.getMovieAlternativeTitles(movieId, country);
    }

    @Override
    public Observable<MovieKeywords> getMovieKeywords(long movieId) {
        return mHttpDataSource.getMovieKeywords(movieId);
    }

    @Override
    public Observable<BaseResultBean<Review>> getMovieReviews(long movieId, int page, String language) {
        return mHttpDataSource.getMovieReviews(movieId, page, language);
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieRecommendations(long movieId, int page) {
        return mHttpDataSource.getMovieRecommendations(movieId, page);
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieSimilar(long movieId, int page) {
        return mHttpDataSource.getMovieSimilar(movieId, page);
    }

    @Override
    public Observable<BaseResultBean<Video>> getMovieVideos(long movieId) {
        return mHttpDataSource.getMovieVideos(movieId);
    }

    @Override
    public Observable<MediaImages> getMovieImages(long movieId, String language) {
        return mHttpDataSource.getMovieImages(movieId, language);
    }

    @Override
    public Observable<CollectionsDetail> getCollections(long collectionId) {
        return mHttpDataSource.getCollections(collectionId);
    }

    @Override
    public Observable<TvDetail> getTvDetial(long tvId) {
        return mHttpDataSource.getTvDetial(tvId);
    }

    @Override
    public Observable<AccountStates> getTvAccountStates(long tvId) {
        return mHttpDataSource.getTvAccountStates(tvId);
    }

    @Override
    public Observable<Credits> getTvCredits(long tvId) {
        return mHttpDataSource.getTvCredits(tvId);
    }

    @Override
    public Observable<BaseResultBean<Video>> getTvVideos(long tvId) {
        return mHttpDataSource.getTvVideos(tvId);
    }

    @Override
    public Observable<MediaImages> getTvImages(long tvId, String language) {
        return mHttpDataSource.getTvImages(tvId, language);
    }

    @Override
    public Observable<BaseResultBean<Review>> getTvReviews(long tvId, int page, String language) {
        return mHttpDataSource.getTvReviews(tvId, page, language);
    }

    @Override
    public Observable<SeasonDetail> getTvSeasonDetail(long tvId, int seasonNumber) {
        return mHttpDataSource.getTvSeasonDetail(tvId, seasonNumber);
    }

    @Override
    public Observable<Credits> getTvSeasonCredits(long tvId, int seasonNumber) {
        return mHttpDataSource.getTvSeasonCredits(tvId, seasonNumber);
    }

    @Override
    public Observable<Credits> getTvEpisodesCredits(long tvId, int seasonNumber, int episodeNumber) {
        return mHttpDataSource.getTvEpisodesCredits(tvId, seasonNumber, episodeNumber);
    }

    @Override
    public Observable<EpisodesDetail> getTvEpisodesDetail(long tvId, int seasonNumber, int episodeNumber) {
        return mHttpDataSource.getTvEpisodesDetail(tvId, seasonNumber, episodeNumber);
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvRecommendations(long tvId, int page) {
        return mHttpDataSource.getTvRecommendations(tvId, page);
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvSimilar(long tvId, int page) {
        return mHttpDataSource.getTvSimilar(tvId, page);
    }

    @Override
    public Observable<GuestSession> getGuestSession() {
        return mHttpDataSource.getGuestSession();
    }

    @Override
    public Observable<RequestToken> getRequestToken() {
        return mHttpDataSource.getRequestToken();
    }

    @Override
    public Observable<RequestToken> getTokenWithLogin(String username, String password, String requestToken) {
        return mHttpDataSource.getTokenWithLogin(username, password, requestToken);
    }

    @Override
    public Observable<Session> getSessionWithToken(String requestToken) {
        return mHttpDataSource.getSessionWithToken(requestToken);
    }

    @Override
    public Observable<Session> getSessionWithV4Token() {
        return mHttpDataSource.getSessionWithV4Token();
    }

    @Override
    public Observable<Session> loginOut() {
        return mHttpDataSource.loginOut();
    }

    @Override
    public Observable<Account> getAccountDetail() {
        return mHttpDataSource.getAccountDetail();
    }

    @Override
    public void insert(Object object) {
        mLocalDataSource.insert(object);
    }

    @Override
    public void delete(Object object) {
        mLocalDataSource.delete(object);
    }

    @Override
    public void update(Object object) {
        mLocalDataSource.update(object);
    }

    @Override
    public Observable<Configuration> getApiConfiguration() {
        Observable<Configuration> httpObservable = mHttpDataSource.getApiConfiguration()
                .flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends Configuration> apply(Configuration configuration) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] apply: getApiConfiguration http: " + configuration.getImages());
                        mLocalDataSource.insert(configuration);
                        return Observable.just(configuration);
                    }
                });
        return mLocalDataSource.getApiConfiguration()
                .flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends Configuration> apply(Configuration configuration) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] apply: getApiConfiguration db: " + configuration.getImages());
                        if (configuration.getImages() == null) {
                            return httpObservable;
                        }
                        return Observable.just(configuration);
                    }
                });
    }

    @Override
    public Observable<List<Countries>> getConfigurationCountries() {
        Observable<List<Countries>> httpObservable = mHttpDataSource.getConfigurationCountries()
                .flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends List<Countries>> apply(List<Countries> countries) throws Exception {
                        Configuration configuration = getConfiguration();
                        configuration.setMCountries(countries);
                        update(configuration);
                        return Observable.just(countries);
                    }
                });
        return mLocalDataSource.getConfigurationCountries()
                .flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends List<Countries>> apply(List<Countries> configuration) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] apply: db: " + configuration);
                        if (configuration.size() == 0) {
                            return httpObservable;
                        }
                        return Observable.just(configuration);
                    }
                });
    }

    @Override
    public Observable<List<Jobs>> getConfigurationJobs() {
        Observable<List<Jobs>> httpObservable = mHttpDataSource.getConfigurationJobs()
                .flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends List<Jobs>> apply(List<Jobs> jobs) throws Exception {
                        Configuration configuration = getConfiguration();
                        configuration.setMJobs(jobs);
                        update(configuration);
                        return Observable.just(jobs);
                    }
                });
        return mLocalDataSource.getConfigurationJobs()
                .flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends List<Jobs>> apply(List<Jobs> jobs) throws Exception {
                        if (jobs.size() == 0) {
                            return httpObservable;
                        }
                        return Observable.just(jobs);
                    }
                });
    }

    @Override
    public Observable<List<Languages>> getConfigurationLanguages() {
        Observable<List<Languages>> httpObservable = mHttpDataSource.getConfigurationLanguages()
                .flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends List<Languages>> apply(List<Languages> languages) throws Exception {
                        Configuration configuration = getConfiguration();
                        configuration.setMLanguages(languages);
                        update(configuration);
                        return Observable.just(languages);
                    }
                });
        return mLocalDataSource.getConfigurationLanguages()
                .flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends List<Languages>> apply(List<Languages> languages) throws Exception {
                        if (languages.size() == 0) {
                            return httpObservable;
                        }
                        return Observable.just(languages);
                    }
                });
    }

    @Override
    public Observable<Response<ResponseBody>> getConfigurationPrimaryTranslations() {
        return mHttpDataSource.getConfigurationPrimaryTranslations();
    }

    @Override
    public Observable<List<Timezones>> getConfigurationTimezones() {
        Observable<List<Timezones>> httpObservable = mHttpDataSource.getConfigurationTimezones()
                .flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends List<Timezones>> apply(List<Timezones> timezones) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] apply: http: " + timezones);
                        Configuration configuration = getConfiguration();
                        configuration.setMTimezones(timezones);
                        update(configuration);
                        return Observable.just(timezones);
                    }
                });
        return mLocalDataSource.getConfigurationTimezones()
                .flatMap(new Function<>() {
                    @Override
                    public ObservableSource<? extends List<Timezones>> apply(List<Timezones> timezones) throws Exception {
                        Log.d(TAG, "[Ciel_Debug] apply: db: " + timezones);
                        if (timezones.size() == 0) {
                            return httpObservable;
                        }
                        return Observable.just(timezones);
                    }
                });
    }

    @Override
    public Configuration getConfiguration() {
        return mLocalDataSource.getConfiguration();
    }

    @Override
    public Account getAccount() {
        return Utils.getAccount();
    }

    @Override
    public List<Movie> getFavoriteList() {
        return mLocalDataSource.getFavoriteList();
    }

    @Override
    public void deleteAllFavoriteList() {
        mLocalDataSource.deleteAllFavoriteList();
    }

    @Override
    public boolean isFavorite(long accountId, long movieId) {
        return mLocalDataSource.isFavorite(accountId, movieId);
    }

    @Override
    public List<Movie> getWatchListMovie() {
        return mLocalDataSource.getWatchListMovie();
    }

    @Override
    public void deleteAllWatchList() {
        mLocalDataSource.deleteAllWatchList();
    }

    @Override
    public boolean isWatchList(long accountId, long movieId) {
        return mLocalDataSource.isWatchList(accountId, movieId);
    }

}

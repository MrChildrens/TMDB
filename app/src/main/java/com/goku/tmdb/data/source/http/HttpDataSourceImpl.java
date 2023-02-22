package com.goku.tmdb.data.source.http;

import android.text.TextUtils;

import com.goku.tmdb.app.AccountStatesTransformer;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.RxTool;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.data.entity.Account;
import com.goku.tmdb.data.entity.AccountStates;
import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.Credits;
import com.goku.tmdb.data.entity.FavoritePostToken;
import com.goku.tmdb.data.entity.Movie;
import com.goku.tmdb.data.entity.Person;
import com.goku.tmdb.data.entity.RatingBody;
import com.goku.tmdb.data.entity.Review;
import com.goku.tmdb.data.entity.StatusBean;
import com.goku.tmdb.data.entity.Tv;
import com.goku.tmdb.data.entity.Video;
import com.goku.tmdb.data.entity.YoutubeVideo;
import com.goku.tmdb.data.entity.authentication.GuestSession;
import com.goku.tmdb.data.entity.authentication.PostToken;
import com.goku.tmdb.data.entity.authentication.PostV4Token;
import com.goku.tmdb.data.entity.authentication.RequestToken;
import com.goku.tmdb.data.entity.authentication.Session;
import com.goku.tmdb.data.entity.authentication.SessionIdBody;
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
import com.goku.tmdb.data.source.http.service.TmdbService;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class HttpDataSourceImpl implements HttpDataSource {
    private volatile static HttpDataSourceImpl sInstance = null;
    private final TmdbService mService;

    public HttpDataSourceImpl(TmdbService tmdbService) {
        mService = tmdbService;
    }

    public static HttpDataSourceImpl getInstance(TmdbService apiService) {
        if (sInstance == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (sInstance == null) {
                    sInstance = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return sInstance;
    }

    public TmdbService getService() {
        return mService;
    }

    @Override
    public Observable<GuestSession> getGuestSession() {
        return mService.getGuestSession()
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<RequestToken> getRequestToken() {
        return mService.getRequestToken()
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<RequestToken> getTokenWithLogin(String username, String password, String requestToken) {
        return mService.getTokenWithLogin(username, password, requestToken)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<Session> getSessionWithToken(String requestToken) {
        PostToken postToken = new PostToken();
        postToken.setRequestToken(requestToken);
        return mService.getSessionWithToken(postToken)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<Session> getSessionWithV4Token() {
        PostV4Token postV4Token = new PostV4Token();
        postV4Token.setAccessToken(Constant.TMDB_V4_ACCESS_TOKEN);
        return mService.getV3SessionWithV4Token(postV4Token)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<Session> loginOut() {
        SessionIdBody postV4Token = new SessionIdBody();
        postV4Token.setSessionId(Utils.getSessionId());
        return mService.loginOut(postV4Token)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<Account> getAccountDetail() {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        return mService.getAccountDetail(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<Configuration> getApiConfiguration() {
        return mService.getApiConfiguration()
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<List<Countries>> getConfigurationCountries() {
        return mService.getConfigurationCountries()
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<List<Jobs>> getConfigurationJobs() {
        return mService.getConfigurationJobs()
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<List<Languages>> getConfigurationLanguages() {
        return mService.getConfigurationLanguages()
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<Response<ResponseBody>> getConfigurationPrimaryTranslations() {
        return mService.getConfigurationPrimaryTranslations()
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<List<Timezones>> getConfigurationTimezones() {
        return mService.getConfigurationTimezones()
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieTrending(int page, String timeWindow) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        return mService.getTrendingMovie(timeWindow, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieNowPlaying(int page, String region) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_REGION, region);
        return mService.getMovieNowPlaying(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieUpcoming(int page, String region) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_REGION, region);
        return mService.getMovieUpcoming(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMoviePopular(int page, String region) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_REGION, region);
        return mService.getMoviePopular(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieTopRated(int page, String region) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_REGION, region);
        return mService.getMovieTopRated(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<MovieDetail> getMovieDetail(long movieId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.APPEND_KEY_ALTERNATIVE_TITLE);
        sb.append(Constant.APPEND_KEY_EXTERNAL_IDS);
        sb.append(Constant.APPEND_KEY_CREDITS);
        sb.append(Constant.APPEND_KEY_IMAGES);
        sb.append(Constant.APPEND_KEY_KEYWORDS);
        sb.append(Constant.APPEND_KEY_RELEASE_DATAS);
        sb.append(Constant.APPEND_KEY_TRANSLATIONS);
        sb.append(Constant.APPEND_KEY_VIDEOS);
        queryParams.put(Constant.QUERY_KEY_APPEND_TO_RESPONSE, sb.toString());
        return mService.getMovieDetail(movieId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<AccountStates> getMovieAccountStates(long movieId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguage());
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        return mService.getMovieAccountStates(movieId, queryParams)
                .compose(RxTool.schedulersTransformer())
                .flatMap(new AccountStatesTransformer());
    }

    @Override
    public Observable<Credits> getMovieCredits(long movieId) {
        HashMap<String, Object> queryParams = new HashMap<>();
//        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguage());
        return mService.getMovieCredits(movieId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<MovieAlternativeTitles> getMovieAlternativeTitles(long movieId, String country) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_COUNTRY, country);
        return mService.getMovieAlternativeTitles(movieId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<MovieKeywords> getMovieKeywords(long movieId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        return mService.getMovieKeywords(movieId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Review>> getMovieReviews(long movieId, int page, String language) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, language);
        return mService.getMovieReviews(movieId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieRecommendations(long movieId, int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getMovieRecommendations(movieId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieSimilar(long movieId, int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        return mService.getMovieSimilar(movieId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Video>> getMovieVideos(long movieId) {
        HashMap<String, Object> queryParams = new HashMap<>();
//        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getMovieVideos(movieId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<MediaImages> getMovieImages(long movieId, String language) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
//        if (!TextUtils.isEmpty(language)) {
        queryParams.put(Constant.QUERY_KEY_INCLUDE_IMAGE_LANGUAGE, language);
//        }
        return mService.getMovieImages(movieId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<CollectionsDetail> getCollections(long collectionId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getCollections(collectionId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<TvDetail> getTvDetial(long tvId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.APPEND_KEY_AGGREGATE_CREDITS);
        sb.append(Constant.APPEND_KEY_ALTERNATIVE_TITLE);
        sb.append(Constant.APPEND_KEY_CONTENT_RATINGS);
        sb.append(Constant.APPEND_KEY_CREDITS);
        sb.append(Constant.APPEND_KEY_EPISODE_GROUPS);
        sb.append(Constant.APPEND_KEY_EXTERNAL_IDS);
        sb.append(Constant.APPEND_KEY_IMAGES);
        sb.append(Constant.APPEND_KEY_KEYWORDS);
        sb.append(Constant.APPEND_KEY_SCREENED_THEATRICALLY);
        sb.append(Constant.APPEND_KEY_TRANSLATIONS);
        sb.append(Constant.APPEND_KEY_VIDEOS);
        queryParams.put(Constant.QUERY_KEY_APPEND_TO_RESPONSE, sb.toString());
        return mService.getTvDetial(tvId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<AccountStates> getTvAccountStates(long tvId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguage());
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        return mService.getTvAccountStates(tvId, queryParams)
                .compose(RxTool.schedulersTransformer())
                .flatMap(new AccountStatesTransformer());
    }

    @Override
    public Observable<Credits> getTvCredits(long tvId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getTvCredits(tvId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Video>> getTvVideos(long tvId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        return mService.getTvVideos(tvId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<MediaImages> getTvImages(long tvId, String language) {
        HashMap<String, Object> queryParams = new HashMap<>();
        if (!TextUtils.isEmpty(language)) {
            queryParams.put(Constant.QUERY_KEY_INCLUDE_IMAGE_LANGUAGE, language);
        }
        return mService.getTvImages(tvId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Review>> getTvReviews(long tvId, int page, String language) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, language);
        return mService.getTvReviews(tvId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<SeasonDetail> getTvSeasonDetail(long tvId, int seasonNumber) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.APPEND_KEY_AGGREGATE_CREDITS);
        sb.append(Constant.APPEND_KEY_CREDITS);
        sb.append(Constant.APPEND_KEY_EXTERNAL_IDS);
        sb.append(Constant.APPEND_KEY_IMAGES);
        sb.append(Constant.APPEND_KEY_TRANSLATIONS);
        sb.append(Constant.APPEND_KEY_VIDEOS);
        queryParams.put(Constant.QUERY_KEY_APPEND_TO_RESPONSE, sb.toString());
        return mService.getTvSeasonDetail(tvId, seasonNumber, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<Credits> getTvSeasonCredits(long tvId, int seasonNumber) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getTvSeasonCredits(tvId, seasonNumber, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<Credits> getTvEpisodesCredits(long tvId, int seasonNumber, int episodeNumber) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getTvEpisodesCredits(tvId, seasonNumber, episodeNumber, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<EpisodesDetail> getTvEpisodesDetail(long tvId, int seasonNumber, int episodeNumber) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.APPEND_KEY_CREDITS);
        sb.append(Constant.APPEND_KEY_EXTERNAL_IDS);
        sb.append(Constant.APPEND_KEY_IMAGES);
        sb.append(Constant.APPEND_KEY_TRANSLATIONS);
        sb.append(Constant.APPEND_KEY_VIDEOS);

        queryParams.put(Constant.QUERY_KEY_APPEND_TO_RESPONSE, sb.toString());
        return mService.getTvEpisodesDetail(tvId, seasonNumber, episodeNumber, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvRecommendations(long tvId, int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getTvRecommendations(tvId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvSimilar(long tvId, int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getTvSimilar(tvId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvTrending(int page, String timeWindow) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getTvTrending(timeWindow, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvOnTheAir(int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getTvOnTheAir(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvAiringToday(int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getTvAiringToday(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvTopRated(int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getTvTopRated(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Person>> getPersonTrending(int page, String timeWindow) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        return mService.getPersonTrending(timeWindow, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<PersonDetail> getPersonDetial(long personId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_APPEND_TO_RESPONSE, "changes,movie_credits,tv_credits,combined_credits,external_ids,images,translations,");
        return mService.getPersonDetial(personId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<Credits> getPersonMovieCredits(long personId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getPersonMovieCredits(personId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<Credits> getPersonCombinedCredits(long personId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        return mService.getPersonCombinedCredits(personId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<YoutubeVideo> getYoutubeVideo(String id, String part) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_ID, id);
        queryParams.put(Constant.QUERY_KEY_PART, part);
        return mService.getYoutubeVideo(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieDiscover(int page, String genres, String sortBy,
                                                              String primaryReleaseYear, String primaryReleaseDateGte, String primaryReleaseDateLte,
                                                              Double voteAverageGte, Double voteAverageLte) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_WITH_GENRES, genres);
        queryParams.put(Constant.QUERY_KEY_SORT_BY, sortBy);
        queryParams.put(Constant.QUERY_KEY_PRIMARY_RELEASE_YEAR, primaryReleaseYear);
//        queryParams.put(Constant.QUERY_KEY_PRIMARY_RELEASE_DATE_GTE, primaryReleaseDateGte);
//        queryParams.put(Constant.QUERY_KEY_PRIMARY_RELEASE_DATE_LTE, primaryReleaseDateLte);
//        queryParams.put(Constant.QUERY_KEY_VOTE_AVERAGE_GTE, voteAverageGte);
//        queryParams.put(Constant.QUERY_KEY_VOTE_AVERAGE_LTE, voteAverageLte);
        return mService.getMovieDiscover(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getMovieByCompany(int page, String company, String sortBy, String primaryReleaseYear, String primaryReleaseDateGte, String primaryReleaseDateLte, Double voteAverageGte, Double voteAverageLte) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_WITH_COMPANIES, company);
        queryParams.put(Constant.QUERY_KEY_SORT_BY, sortBy);
        return mService.getMovieDiscover(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> getTvDiscover(int page, String genres, String sortBy, String primaryReleaseYear, String primaryReleaseDateGte, String primaryReleaseDateLte, Double voteAverageGte, Double voteAverageLte) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_WITH_GENRES, genres);
        queryParams.put(Constant.QUERY_KEY_SORT_BY, sortBy);
        queryParams.put(Constant.QUERY_KEY_PRIMARY_RELEASE_YEAR, primaryReleaseYear);
//        queryParams.put(Constant.QUERY_KEY_PRIMARY_RELEASE_DATE_GTE, primaryReleaseDateGte);
//        queryParams.put(Constant.QUERY_KEY_PRIMARY_RELEASE_DATE_LTE, primaryReleaseDateLte);
//        queryParams.put(Constant.QUERY_KEY_VOTE_AVERAGE_GTE, voteAverageGte);
//        queryParams.put(Constant.QUERY_KEY_VOTE_AVERAGE_LTE, voteAverageLte);
        return mService.getTvDiscover(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<SearchMulti>> searchMulti(String query, int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_QUERY, query);
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_INCLUDE_ADULT, Utils.isAdult());
        return mService.searchMulti(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> searchMovie(String query, int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_QUERY, query);
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_INCLUDE_ADULT, Utils.isAdult());
        queryParams.put(Constant.QUERY_KEY_REGION, Utils.getCountry());
        return mService.searchMovie(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> searchTv(String query, int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_QUERY, query);
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_INCLUDE_ADULT, Utils.isAdult());
        queryParams.put(Constant.QUERY_KEY_REGION, Utils.getCountry());
        return mService.searchTv(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Person>> searchPerson(String query, int page) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_QUERY, query);
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_INCLUDE_ADULT, Utils.isAdult());
        queryParams.put(Constant.QUERY_KEY_REGION, Utils.getCountry());
        return mService.searchPerson(queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getFavoriteMovie(int page, String sortBy) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_SORT_BY, sortBy);
        return mService.getFavoriteMovie(Utils.getAccount().getId(), queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> getFavoriteTv(int page, String sortBy) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_SORT_BY, sortBy);
        return mService.getFavoriteTv(Utils.getAccount().getId(), queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<StatusBean> favorite(String mediaTpye, long mediaId, boolean isFav) {
        HashMap<String, Object> queryParams = new HashMap<>();
        FavoritePostToken favoritePostToken = new FavoritePostToken();
        favoritePostToken.setMediaType(mediaTpye);
        favoritePostToken.setMediaId(mediaId);
        favoritePostToken.setFavorite(isFav);
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        return mService.favorite(Utils.getAccount().getId(), favoritePostToken, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getWatchlistMovie(int page, String sortBy) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_SORT_BY, sortBy);
        return mService.getWatchlistMovie(Utils.getAccount().getId(), queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> getWatchlistTv(int page, String sortBy) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_SORT_BY, sortBy);
        return mService.getWatchlistTv(Utils.getAccount().getId(), queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Movie>> getRatingMovie(int page, String sortBy) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_SORT_BY, sortBy);
        return mService.getRatingMovie(Utils.getAccount().getId(), queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<BaseResultBean<Tv>> getRatingTv(int page, String sortBy) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_LANGUAGE, Utils.getLanguageStr());
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        queryParams.put(Constant.QUERY_KEY_PAGE, page);
        queryParams.put(Constant.QUERY_KEY_SORT_BY, sortBy);
        return mService.getRatingTv(Utils.getAccount().getId(), queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<StatusBean> watchlist(String mediaTpye, long mediaId, boolean isWatchList) {
        HashMap<String, Object> queryParams = new HashMap<>();
        FavoritePostToken favoritePostToken = new FavoritePostToken();
        favoritePostToken.setMediaType(mediaTpye);
        favoritePostToken.setMediaId(mediaId);
        favoritePostToken.setWatchlist(isWatchList);
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        return mService.watchlist(Utils.getAccount().getId(), favoritePostToken, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<StatusBean> ratingMovie(long movieId, float rating) {
        HashMap<String, Object> queryParams = new HashMap<>();
        RatingBody ratingBody = new RatingBody();
        ratingBody.setValue(rating);
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        return mService.ratingMovie(movieId, ratingBody, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<StatusBean> removeMovieRating(long movieId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        return mService.removeMovieRating(movieId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<StatusBean> ratingTv(long tvId, float rating) {
        HashMap<String, Object> queryParams = new HashMap<>();
        RatingBody ratingBody = new RatingBody();
        ratingBody.setValue(rating);
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        return mService.ratingTv(tvId, ratingBody, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

    @Override
    public Observable<StatusBean> removeTvRating(long tvId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(Constant.QUERY_KEY_SESSION_ID, Utils.getSessionId());
        return mService.removeTvRating(tvId, queryParams)
                .compose(RxTool.schedulersTransformer());
    }

}

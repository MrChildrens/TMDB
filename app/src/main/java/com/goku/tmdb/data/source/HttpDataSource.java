package com.goku.tmdb.data.source;

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

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface HttpDataSource {

    Observable<GuestSession> getGuestSession();

    Observable<RequestToken> getRequestToken();

    Observable<RequestToken> getTokenWithLogin(String username, String password, String requestToken);

    Observable<Session> getSessionWithToken(String requestToken);

    Observable<Session> getSessionWithV4Token();

    Observable<Session> loginOut();

    Observable<Account> getAccountDetail();

    Observable<Configuration> getApiConfiguration();

    Observable<List<Countries>> getConfigurationCountries();

    Observable<List<Jobs>> getConfigurationJobs();

    Observable<List<Languages>> getConfigurationLanguages();

    Observable<Response<ResponseBody>> getConfigurationPrimaryTranslations();

    Observable<List<Timezones>> getConfigurationTimezones();

    Observable<BaseResultBean<Movie>> getMovieTrending(int page, String timeWindow);

    Observable<BaseResultBean<Movie>> getMovieNowPlaying(int page, String region);

    Observable<BaseResultBean<Movie>> getMovieUpcoming(int page, String region);

    Observable<BaseResultBean<Movie>> getMoviePopular(int page, String region);

    Observable<BaseResultBean<Movie>> getMovieTopRated(int page, String region);

    Observable<MovieDetail> getMovieDetail(long id);

    Observable<AccountStates> getMovieAccountStates(long movieId);

    Observable<Credits> getMovieCredits(long movieId);

    Observable<MovieAlternativeTitles> getMovieAlternativeTitles(long movieId, String country);

    Observable<MovieKeywords> getMovieKeywords(long movieId);

    Observable<BaseResultBean<Review>> getMovieReviews(long movieId, int page, String language);

    Observable<BaseResultBean<Movie>> getMovieRecommendations(long movieId, int page);

    Observable<BaseResultBean<Movie>> getMovieSimilar(long movieId, int page);

    Observable<BaseResultBean<Video>> getMovieVideos(long movieId);

    Observable<MediaImages> getMovieImages(long movieId, String language);

    Observable<CollectionsDetail> getCollections(long collectionId);

    Observable<TvDetail> getTvDetial(long tvId);

    Observable<AccountStates> getTvAccountStates(long tvId);

    Observable<Credits> getTvCredits(long tvId);

    Observable<BaseResultBean<Video>> getTvVideos(long tvId);

    Observable<MediaImages> getTvImages(long tvId, String language);

    Observable<BaseResultBean<Review>> getTvReviews(long tvId, int page, String language);

    Observable<SeasonDetail> getTvSeasonDetail(long tvId, int seasonNumber);

    Observable<Credits> getTvSeasonCredits(long tvId, int seasonNumber);

    Observable<Credits> getTvEpisodesCredits(long tvId, int seasonNumber, int episodeNumber);

    Observable<EpisodesDetail> getTvEpisodesDetail(long tvId, int seasonNumber, int episodeNumber);

    Observable<BaseResultBean<Tv>> getTvRecommendations(long tvId, int page);

    Observable<BaseResultBean<Tv>> getTvSimilar(long tvId, int page);

    Observable<BaseResultBean<Tv>> getTvTrending(int page, String timeWindow);

    Observable<BaseResultBean<Tv>> getTvOnTheAir(int page);

    Observable<BaseResultBean<Tv>> getTvAiringToday(int page);

    Observable<BaseResultBean<Tv>> getTvTopRated(int page);

    Observable<BaseResultBean<Person>> getPersonTrending(int page, String timeWindow);

    Observable<PersonDetail> getPersonDetial(long personId);

    Observable<Credits> getPersonMovieCredits(long personId);

    Observable<Credits> getPersonCombinedCredits(long personId);

    Observable<YoutubeVideo> getYoutubeVideo(String id, String part);

    Observable<BaseResultBean<Movie>> getMovieDiscover(int page, String genres, String sortBy,
                                                       String primaryReleaseYear, String primaryReleaseDateGte, String primaryReleaseDateLte,
                                                       Double voteAverageGte, Double voteAverageLte);

    Observable<BaseResultBean<Movie>> getMovieByCompany(int page, String company, String sortBy,
                                                       String primaryReleaseYear, String primaryReleaseDateGte, String primaryReleaseDateLte,
                                                       Double voteAverageGte, Double voteAverageLte);

    Observable<BaseResultBean<Tv>> getTvDiscover(int page, String genres, String sortBy,
                                                 String primaryReleaseYear, String primaryReleaseDateGte, String primaryReleaseDateLte,
                                                 Double voteAverageGte, Double voteAverageLte);

    Observable<BaseResultBean<SearchMulti>> searchMulti(String query, int page);

    Observable<BaseResultBean<Movie>> searchMovie(String query, int page);

    Observable<BaseResultBean<Tv>> searchTv(String query, int page);

    Observable<BaseResultBean<Person>> searchPerson(String query, int page);

    Observable<BaseResultBean<Movie>> getFavoriteMovie(int page, String sortBy);

    Observable<BaseResultBean<Tv>> getFavoriteTv(int page, String sortBy);

    Observable<StatusBean> favorite(String mediaTpye, long mediaId, boolean isFav);

    Observable<BaseResultBean<Movie>> getWatchlistMovie(int page, String sortBy);

    Observable<BaseResultBean<Tv>> getWatchlistTv(int page, String sortBy);

    Observable<BaseResultBean<Movie>> getRatingMovie(int page, String sortBy);

    Observable<BaseResultBean<Tv>> getRatingTv(int page, String sortBy);

    Observable<StatusBean> watchlist(String mediaTpye, long mediaId, boolean isWatchList);

    Observable<StatusBean> ratingMovie(long movieId, float rating);

    Observable<StatusBean> removeMovieRating(long movieId);

    Observable<StatusBean> ratingTv(long tvId, float rating);

    Observable<StatusBean> removeTvRating(long tvId);

}

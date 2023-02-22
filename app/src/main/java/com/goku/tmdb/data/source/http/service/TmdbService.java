package com.goku.tmdb.data.source.http.service;

import com.goku.tmdb.data.entity.Account;
import com.goku.tmdb.data.entity.AccountStates;
import com.goku.tmdb.data.entity.AlternativeTitles;
import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.ChangeBean;
import com.goku.tmdb.data.entity.Credits;
import com.goku.tmdb.data.entity.CreditsDetail;
import com.goku.tmdb.data.entity.ExternalIds;
import com.goku.tmdb.data.entity.FavoritePostToken;
import com.goku.tmdb.data.entity.FindExternal;
import com.goku.tmdb.data.entity.Genres;
import com.goku.tmdb.data.entity.Keyword;
import com.goku.tmdb.data.entity.Movie;
import com.goku.tmdb.data.entity.Person;
import com.goku.tmdb.data.entity.RatingBody;
import com.goku.tmdb.data.entity.Review;
import com.goku.tmdb.data.entity.ReviewDetail;
import com.goku.tmdb.data.entity.StatusBean;
import com.goku.tmdb.data.entity.Translations;
import com.goku.tmdb.data.entity.Trending;
import com.goku.tmdb.data.entity.Tv;
import com.goku.tmdb.data.entity.TvEpisodesGroupBean;
import com.goku.tmdb.data.entity.Video;
import com.goku.tmdb.data.entity.YoutubeVideo;
import com.goku.tmdb.data.entity.authentication.GuestSession;
import com.goku.tmdb.data.entity.authentication.PostToken;
import com.goku.tmdb.data.entity.authentication.PostV4Token;
import com.goku.tmdb.data.entity.authentication.RequestToken;
import com.goku.tmdb.data.entity.authentication.Session;
import com.goku.tmdb.data.entity.authentication.SessionIdBody;
import com.goku.tmdb.data.entity.certifications.MovieCertifications;
import com.goku.tmdb.data.entity.certifications.TvCertifications;
import com.goku.tmdb.data.entity.collections.CollectionImages;
import com.goku.tmdb.data.entity.collections.CollectionsDetail;
import com.goku.tmdb.data.entity.companies.Company;
import com.goku.tmdb.data.entity.companies.CompanyAlternativeNames;
import com.goku.tmdb.data.entity.companies.CompanyImages;
import com.goku.tmdb.data.entity.configuration.Configuration;
import com.goku.tmdb.data.entity.configuration.Countries;
import com.goku.tmdb.data.entity.configuration.Jobs;
import com.goku.tmdb.data.entity.configuration.Languages;
import com.goku.tmdb.data.entity.configuration.Timezones;
import com.goku.tmdb.data.entity.guestsessions.RatedTvEpisodes;
import com.goku.tmdb.data.entity.movie.MediaImages;
import com.goku.tmdb.data.entity.movie.MovieAlternativeTitles;
import com.goku.tmdb.data.entity.movie.MovieChanges;
import com.goku.tmdb.data.entity.movie.MovieDetail;
import com.goku.tmdb.data.entity.movie.MovieKeywords;
import com.goku.tmdb.data.entity.movie.MovieLatest;
import com.goku.tmdb.data.entity.movie.MovieLists;
import com.goku.tmdb.data.entity.movie.MovieReleaseDates;
import com.goku.tmdb.data.entity.movie.MovieWatchProviders;
import com.goku.tmdb.data.entity.networks.NetworkAlternativeNames;
import com.goku.tmdb.data.entity.networks.NetworkDetail;
import com.goku.tmdb.data.entity.networks.NetworkImage;
import com.goku.tmdb.data.entity.person.PersonChanges;
import com.goku.tmdb.data.entity.person.PersonDetail;
import com.goku.tmdb.data.entity.person.PersonLatest;
import com.goku.tmdb.data.entity.person.PersonTaggedImages;
import com.goku.tmdb.data.entity.search.SearchCollections;
import com.goku.tmdb.data.entity.search.SearchCompany;
import com.goku.tmdb.data.entity.search.SearchMulti;
import com.goku.tmdb.data.entity.tv.TvChanges;
import com.goku.tmdb.data.entity.tv.TvContentRatings;
import com.goku.tmdb.data.entity.tv.TvDetail;
import com.goku.tmdb.data.entity.tv.TvEpisodeGroups;
import com.goku.tmdb.data.entity.tv.TvLatest;
import com.goku.tmdb.data.entity.tv.TvScreenedTheatrically;
import com.goku.tmdb.data.entity.tv.TvWatchProviders;
import com.goku.tmdb.data.entity.tvepisodes.EpisodesDetail;
import com.goku.tmdb.data.entity.tvepisodes.TvEpisodesAccountStates;
import com.goku.tmdb.data.entity.tvepisodes.TvEpisodesChanges;
import com.goku.tmdb.data.entity.tvepisodes.TvEpisodesImages;
import com.goku.tmdb.data.entity.tvseasons.SeasonDetail;
import com.goku.tmdb.data.entity.tvseasons.TvSeasonChanges;
import com.goku.tmdb.data.entity.tvseasons.TvSeasonImages;
import com.goku.tmdb.data.entity.watchproviders.WatchProvidersMovie;
import com.goku.tmdb.data.entity.watchproviders.WatchProvidersRegions;
import com.goku.tmdb.data.entity.watchproviders.WatchProvidersTv;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface TmdbService {

    /******Authentication******/
    //获取访客的guest_session，有效期24小时
    @GET("authentication/guest_session/new")
    Observable<GuestSession> getGuestSession();

    //获取token
    @GET("authentication/token/new")
    Observable<RequestToken> getRequestToken();

    //通过TMBD用户名和密码，request_token请求新的token
    @FormUrlEncoded
    @POST("authentication/token/validate_with_login")
    Observable<RequestToken> getTokenWithLogin(@Field("username") String username, @Field("password") String password, @Field("request_token") String requestToken);

    //通过token获取Session id
    @POST("authentication/session/new")
    Observable<Session> getSessionWithToken(@Body PostToken postToken);

    //通过v4的access_token直接获取session id
    @POST("authentication/session/convert/4")
    Observable<Session> getV3SessionWithV4Token(@Body PostV4Token accessToken);

    //退出登录
    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    Observable<Session> loginOut(@Body SessionIdBody requestBody);
    /*Authentication*/

    /******Account******/
    @GET("account")
    Observable<Account> getAccountDetail(@QueryMap HashMap<String, Object> queryParams);
    /*****Account******/

    /*****Configuration*****/
    @GET("configuration")
    Observable<Configuration> getApiConfiguration();

    @GET("configuration/countries")
    Observable<List<Countries>> getConfigurationCountries();

    @GET("configuration/jobs")
    Observable<List<Jobs>> getConfigurationJobs();

    @GET("configuration/languages")
    Observable<List<Languages>> getConfigurationLanguages();

    @GET("configuration/primary_translations")
    Observable<Response<Response<ResponseBody>>> getConfigurationPrimaryTranslations();

    @GET("configuration/timezones")
    Observable<List<Timezones>> getConfigurationTimezones();
    /****Configuration*****/

    /*****Certifications*****/
    @GET("certification/movie/list")
    Observable<MovieCertifications> getMovieCertifications();

    @GET("certification/tv/list")
    Observable<TvCertifications> getTvCertifications();
    /****Certifications*****/

    /******Change******/
    @GET("movie/changes")
    Observable<BaseResultBean<ChangeBean>> getMovieChange(@QueryMap HashMap<String, Object> queryParams);

    @GET("tv/changes")
    Observable<BaseResultBean<ChangeBean>> getTvChange(@QueryMap HashMap<String, Object> queryParams);

    @GET("people/changes")
    Observable<BaseResultBean<ChangeBean>> getPeopleChange(@QueryMap HashMap<String, Object> queryParams);
    /*****Change******/

    /******Collections******/
    @GET("collection/{collection_id}")
    Observable<CollectionsDetail> getCollections(@Path("collection_id") long collectionId, @QueryMap HashMap<String, Object> queryParams);

    /******Collections******/

    @GET("collection/{collection_id}/images")
    Observable<CollectionImages> getCollectionImage(@Path("collection_id") long collectionId, @QueryMap HashMap<String, Object> queryParams);

    @GET("collection/{collection_id}/translations")
        //1.collection_id
    Observable<Translations> getCollectionTranslations(@Path("collection_id") long collectionId, @QueryMap HashMap<String, Object> queryParams);
    /*****Collections******/

    /******Companies******/
    @GET("company/{company_id}")
    //1.company_id
    Observable<Company> getCompanyDetail(@Path("company_id") long companyId, @QueryMap HashMap<String, Object> queryParams);

    @GET("company/{company_id}/alternative_names")
        //1.company_id
    Observable<BaseResultBean<CompanyAlternativeNames>> getCompanyAlternativeNames(@Path("company_id") long companyId, @QueryMap HashMap<String, Object> queryParams);

    @GET("company/{company_id}/images")
        //1.company_id
    Observable<CompanyImages> getCompanyImage(@Path("company_id") long companyId, @QueryMap HashMap<String, Object> queryParams);
    /*****Companies******/

    /******Credits******/
    @GET("credit/{credit_id}")
    //1.credit_id
    Observable<CreditsDetail> getCredits(@Path("credit_id") long creditId, @QueryMap HashMap<String, Object> queryParams);
    /*****Credits******/

    /******Discover******/
    @GET("discover/movie")
    Observable<BaseResultBean<Movie>> getMovieDiscover(@QueryMap HashMap<String, Object> queryParams);

    @GET("discover/tv")
    Observable<BaseResultBean<Tv>> getTvDiscover(@QueryMap HashMap<String, Object> queryParams);
    /*****Discover******/

    /******Find******/
    @GET("find/{external_id}")
    Observable<FindExternal> findExternal(@Path("external_id") long externalId, @QueryMap HashMap<String, Object> queryParams);
    /*****Find******/

    /******Genres******/
    @GET("genre/movie/list")
    Observable<List<Genres>> getMovieGenres(@QueryMap HashMap<String, Object> queryParams);

    @GET("genre/tv/list")
    Observable<List<Genres>> getTvGenres(@QueryMap HashMap<String, Object> queryParams);
    /*****Genres******/

    /******Guest Sessions******/
    @GET("guest_session/{guest_session_id}/rated/movies")
    //1.guest_session_id  2.sort_by(created_at.asc, created_at.desc)
    Observable<BaseResultBean<Movie>> getRatedMovies(@Path("guest_session_id") long guestSessionId, @QueryMap HashMap<String, Object> queryParams);

    @GET("guest_session/{guest_session_id}/rated/tv")
        //1.guest_session_id  2.sort_by(created_at.asc, created_at.desc)
    Observable<BaseResultBean<Tv>> getRatedTv(@Path("guest_session_id") long guestSessionId, @QueryMap HashMap<String, Object> queryParams);

    @GET("guest_session/{guest_session_id}/rated/tv/episodes")
        //1.guest_session_id  2.sort_by(created_at.asc, created_at.desc)
    Observable<BaseResultBean<RatedTvEpisodes>> getRatedTvEpisodes(@Path("guest_session_id") long guestSessionId, @QueryMap HashMap<String, Object> queryParams);
    /*****Guest Sessions******/

    /******Keywords******/
    @GET("keyword/{keyword_id}")
    //1.keyword_id
    Observable<Keyword> getKeywordDetail(@Path("keyword_id") long keywordId, @QueryMap HashMap<String, Object> queryParams);

    @GET("keyword/{keyword_id}/movies")
        //1.keyword_id
    Observable<BaseResultBean<Movie>> getMovieWithKeyword(@Path("keyword_id") long keywordId, @QueryMap HashMap<String, Object> queryParams);
    /*****Keywords******/

    /******Networks******/
    @GET("network/{network_id}")
    //1.network_id
    Observable<NetworkDetail> getNetworkDetails(@Path("network_id") long networkId, @QueryMap HashMap<String, Object> queryParams);

    @GET("network/{network_id}/alternative_names")
        //1.network_id
    Observable<BaseResultBean<NetworkAlternativeNames>> getNetworkAlternativeNames(@Path("network_id") long networkId, @QueryMap HashMap<String, Object> queryParams);

    @GET("network/{network_id}/images")
        //1.network_id
    Observable<NetworkImage> getNetworkImage(@Path("network_id") long networkId, @QueryMap HashMap<String, Object> queryParams);
    /*****Networks******/

    /******Reviews******/
    @GET("review/{review_id}")
    //1.review_id
    Observable<ReviewDetail> getReviewDetail(@Path("review_id") long reviewId, @QueryMap HashMap<String, Object> queryParams);
    /*****Reviews******/

    /******Search******/
    @GET("search/company")
    //1.query  2.page
    Observable<BaseResultBean<SearchCompany>> searchCompany(@QueryMap HashMap<String, Object> queryParams);

    @GET("search/collection")
        //1.query  2.page
    Observable<BaseResultBean<SearchCollections>> searchCollection(@QueryMap HashMap<String, Object> queryParams);

    @GET("search/keyword")
        //1.query  2.page
    Observable<BaseResultBean<Keyword>> searchKeyword(@QueryMap HashMap<String, Object> queryParams);

    @GET("search/movie")
        //1.query  2.page  3.include_adult  4.region  5.year  6.primary_release_year
    Observable<BaseResultBean<Movie>> searchMovie(@QueryMap HashMap<String, Object> queryParams);

    @GET("search/multi")
        //1.query  2.page  3.include_adult  4.region
    Observable<BaseResultBean<SearchMulti>> searchMulti(@QueryMap HashMap<String, Object> queryParams);

    @GET("search/person")
        //1.query  2.page  3.include_adult  4.region
    Observable<BaseResultBean<Person>> searchPerson(@QueryMap HashMap<String, Object> queryParams);

    @GET("search/tv")
        //1.query  2.page  3.include_adult  4.first_air_date_year
    Observable<BaseResultBean<Tv>> searchTv(@QueryMap HashMap<String, Object> queryParams);
    /*****Search******/

    /******TV Seasons******/
    @GET("tv/{tv_id}/season/{season_number}")
    Observable<SeasonDetail> getTvSeasonDetail(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/account_states")
    Observable<AccountStates> getAccountStates(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/aggregate_credits")
    Observable<Credits> getAggregateCredits(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/season/{season_id}/changes")
    Observable<TvSeasonChanges> getTvSeasonChanges(@Path("season_number") long seasonId, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/credits")
    Observable<Credits> getTvSeasonCredits(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/external_ids")
    Observable<ExternalIds> getTvSeasonExternalIds(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/images")
    Observable<TvSeasonImages> getTvSeasonImages(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/translations")
    Observable<Translations> getTvSeasonTranslations(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/videos")
    Observable<BaseResultBean<Video>> getTvSeasonVideos(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber, @QueryMap HashMap<String, Object> queryParams);
    /*****TV Seasons******/

    /******TV Episodes******/
    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    Observable<EpisodesDetail> getTvEpisodesDetail(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber,
                                                   @Path("episode_number") int episodeNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/account_states")
    Observable<TvEpisodesAccountStates> getTvEpisodesAccountStates(@Path("tv_id") int tvId, @Path("season_number") int seasonNumber,
                                                                   @Path("episode_number") int episodeNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/episode/{episode_id}/changes")
//    1.start_date  2.end_date  3.page
    Observable<TvEpisodesChanges> getTvEpisodesChanges(@Path("episode_id") long episodeId, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/credits")
    Observable<Credits> getTvEpisodesCredits(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber,
                                             @Path("episode_number") int episodeNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/external_ids")
    Observable<ExternalIds> getTvEpisodesExternalIds(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber,
                                                     @Path("episode_number") int episodeNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/images")
    Observable<TvEpisodesImages> getTvEpisodesImages(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber,
                                                     @Path("episode_number") int episodeNumber, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/translations")
    Observable<Translations> getTvEpisodesTranslations(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber,
                                                       @Path("episode_number") int episodeNumber, @QueryMap HashMap<String, Object> queryParams);

    /*{
        "value": 8.5
    }*/
    @POST("tv/{tv_id}/season/{season_number}/episode/{episode_number}/rating")
    //1.guest_session_id  2.session_id
    Observable<StatusBean> postTvEpisodesRating(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber,
                                                @Path("episode_number") int episodeNumber, @QueryMap HashMap<String, Object> queryParams, @Body RequestBody requestBody);


    @DELETE("tv/{tv_id}/season/{season_number}/episode/{episode_number}/rating")
        //1.guest_session_id  2.session_id
    Observable<StatusBean> deleteTvEpisodesRating(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber,
                                                  @Path("episode_number") int episodeNumber, @QueryMap HashMap<String, Object> queryParams, @Body RequestBody requestBody);

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}/videos")
    Observable<BaseResultBean<Video>> getTvEpisodesVideos(@Path("tv_id") long tvId, @Path("season_number") int seasonNumber,
                                                          @Path("episode_number") int episodeNumber, @QueryMap HashMap<String, Object> queryParams);
    /*****TV Episodes******/

    /******TV Episode Groups******/
    @GET("tv/episode_group/{id}")
    Observable<TvEpisodesGroupBean> getTvEpisodesGroupDetail(@Path("id") long id, @QueryMap HashMap<String, Object> queryParams);
    /*****TV Episode Groups******/

    /******Watch Providers******/
    //    1.watch_region
    @GET("watch/providers/regions")
    Observable<BaseResultBean<WatchProvidersRegions>> getWatchProvidersRegions(@QueryMap HashMap<String, Object> queryParams);

    //    1.watch_region
    @GET("watch/providers/movie")
    Observable<BaseResultBean<WatchProvidersMovie>> getWatchProvidersMovie(@QueryMap HashMap<String, Object> queryParams);

    //    1.watch_region
    @GET("watch/providers/tv")
    Observable<BaseResultBean<WatchProvidersTv>> getWatchProvidersTv(@QueryMap HashMap<String, Object> queryParams);

    /******Watch Providers******/


    @GET("trending/{MediaTypes}/{TimeWindow}")
    //MediaTypes: 1.all 2.movie 3.tv 4.person
    //TimeWindow: 1.day 2.week
    Observable<BaseResultBean<Trending>> getTrending(@Path("MediaTypes") String mediaType, @Path("TimeWindow") String timeWindow, @QueryMap HashMap<String, Object> queryParams);

    /******Person******/
    @GET("trending/person/{TimeWindow}")
    //MediaTypes: 1.all 2.movie 3.tv 4.person
    //TimeWindow: 1.day 2.week
    Observable<BaseResultBean<com.goku.tmdb.data.entity.Person>> getPersonTrending(@Path("TimeWindow") String timeWindow, @QueryMap HashMap<String, Object> queryParams);

    @GET("person/{person_id}")
        //1.append_to_response String
    Observable<PersonDetail> getPersonDetial(@Path("person_id") long personId, @QueryMap HashMap<String, Object> queryParams);

    @GET("person/{person_id}")
        //1.end_date String  2.page int  3.start_date String
    Observable<PersonChanges> getPersonChanges(@Path("person_id") long personId, @QueryMap HashMap<String, Object> queryParams);

    @GET("person/{person_id}/movie_credits")
    Observable<Credits> getPersonMovieCredits(@Path("person_id") long personId, @QueryMap HashMap<String, Object> queryParams);

    @GET("person/{person_id}/tv_credits")
    Observable<Credits> getPersonTvCredits(@Path("person_id") long personId, @QueryMap HashMap<String, Object> queryParams);

    @GET("person/{person_id}/combined_credits")
    Observable<Credits> getPersonCombinedCredits(@Path("person_id") long personId, @QueryMap HashMap<String, Object> queryParams);

    @GET("person/{person_id}/external_ids")
    Observable<ExternalIds> getPersonExternalIds(@Path("person_id") long personId, @QueryMap HashMap<String, Object> queryParams);

    @GET("person/{person_id}/images")
    Observable<MediaImages> getPersonImages(@Path("person_id") long personId, @QueryMap HashMap<String, Object> queryParams);

    @GET("person/{person_id}/tagged_images")
        //1.page int
    Observable<BaseResultBean<PersonTaggedImages>> getPersonTaggedImages(@Path("person_id") long personId, @QueryMap HashMap<String, Object> queryParams);

    @GET("person/{person_id}/translations")
    Observable<Translations> getPersonTranslations(@Path("person_id") long personId, @QueryMap HashMap<String, Object> queryParams);

    @GET("person/latest")
    Observable<PersonLatest> getPersonTranslations(@QueryMap HashMap<String, Object> queryParams);

    @GET("person/popular")
        //1.page int
    Observable<BaseResultBean<Person>> getPersonPopular(@QueryMap HashMap<String, Object> queryParams);
    /*****Person******/

    /******TV Show******/
    @GET("trending/tv/{TimeWindow}")
    Observable<BaseResultBean<Tv>> getTvTrending(@Path("TimeWindow") String timeWindow, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}")
        //1.append_to_response String
    Observable<TvDetail> getTvDetial(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/account_states")
    Observable<ResponseBody> getTvAccountStates(@Path("tv_id") long tvId, @QueryMap HashMap<String, Object> queryParams);


    @GET("tv/{tv_id}/aggregate_credits")
    Observable<Credits> getTvAggregateCredits(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/alternative_titles")
    Observable<BaseResultBean<AlternativeTitles>> getTvAlternativeTitles(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/changes")
        //1.start_date String  2.end_date String  3.page int
    Observable<TvChanges> getTvChanges(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/content_ratings")
    Observable<BaseResultBean<TvContentRatings>> getTvContentRatings(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/credits")
    Observable<Credits> getTvCredits(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/episode_groups")
    Observable<BaseResultBean<TvEpisodeGroups>> getTvEpisodeGroups(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/external_ids")
    Observable<ExternalIds> getTvExternalIds(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/images")
    Observable<MediaImages> getTvImages(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/keywords")
    Observable<BaseResultBean<Keyword>> getTvKeywords(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/recommendations")
        //1.page int
    Observable<BaseResultBean<Tv>> getTvRecommendations(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/reviews")
        //1.page int
    Observable<BaseResultBean<Review>> getTvReviews(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/screened_theatrically")
    Observable<BaseResultBean<TvScreenedTheatrically>> getTvScreenedTheatrically(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/similar")
        //1.page int
    Observable<BaseResultBean<Tv>> getTvSimilar(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/translations")
    Observable<Translations> getTvTranslations(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/videos")
    Observable<BaseResultBean<Video>> getTvVideos(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/{tv_id}/watch/providers")
    Observable<BaseResultBean<TvWatchProviders>> getTvWatchProviders(@Path("tv_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @POST("movie/{movie_id}/rating")
    Observable<StatusBean> ratingMovie(@Path("movie_id") long movieId, @Body RatingBody ratingBody, @QueryMap HashMap<String, Object> queryParams);

    @HTTP(method = "DELETE", path = "movie/{movie_id}/rating", hasBody = true)
    Observable<StatusBean> removeMovieRating(@Path("movie_id") long movieId, @QueryMap HashMap<String, Object> queryParams);

    @POST("tv/{tv_id}/rating")
    Observable<StatusBean> ratingTv(@Path("tv_id") long tvId, @Body RatingBody ratingBody, @QueryMap HashMap<String, Object> queryParams);

    @HTTP(method = "DELETE", path = "tv/{tv_id}/rating", hasBody = true)
    Observable<StatusBean> removeTvRating(@Path("tv_id") long tvId, @QueryMap HashMap<String, Object> queryParams);

    @GET("tv/latest")
    Observable<TvLatest> getTvLatest(@QueryMap HashMap<String, Object> queryParams);

    @GET("tv/airing_today")
        //1.page int
    Observable<BaseResultBean<Tv>> getTvAiringToday(@QueryMap HashMap<String, Object> queryParams);

    @GET("tv/on_the_air")
        //1.page int
    Observable<BaseResultBean<Tv>> getTvOnTheAir(@QueryMap HashMap<String, Object> queryParams);

    @GET("tv/popular")
        //1.page int
    Observable<BaseResultBean<Tv>> getTvPopular(@QueryMap HashMap<String, Object> queryParams);

    @GET("tv/top_rated")
        //1.page int
    Observable<BaseResultBean<Tv>> getTvTopRated(@QueryMap HashMap<String, Object> queryParams);

    /*****TV Show******/

    /******Movie******/
    @GET("trending/movie/{TimeWindow}")
    Observable<BaseResultBean<Movie>> getTrendingMovie(@Path("TimeWindow") String timeWindow, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}")
        //1.append_to_response
    Observable<MovieDetail> getMovieDetail(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    //GET movie/{movie_id}/account_states
    //1.guest_session_id String  2.session_id String
    @GET("movie/{movie_id}/alternative_titles")
    //1.country String
    Observable<MovieAlternativeTitles> getMovieAlternativeTitles(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/account_states")
    Observable<ResponseBody> getMovieAccountStates(@Path("movie_id") long movieId, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/changes")
        //1.start_date String  2.end_date String  3.page int
    Observable<MovieChanges> getMovieChanges(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/credits")
    Observable<Credits> getMovieCredits(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/external_ids")
    Observable<ExternalIds> getMovieExternalIds(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/images")
        //1.include_image_language
    Observable<MediaImages> getMovieImages(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/keywords")
    Observable<MovieKeywords> getMovieKeywords(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/lists")
        //1.page int
    Observable<BaseResultBean<MovieLists>> getMovieLists(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/recommendations")
        //1.page int
    Observable<BaseResultBean<Movie>> getMovieRecommendations(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/release_dates")
    Observable<BaseResultBean<MovieReleaseDates>> getMovieReleaseDates(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/reviews")
        //1.page int
    Observable<BaseResultBean<Review>> getMovieReviews(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/similar")
        //1.page int
    Observable<BaseResultBean<Movie>> getMovieSimilar(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/translations")
    Observable<Translations> getMovieTranslations(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/videos")
    Observable<BaseResultBean<Video>> getMovieVideos(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    @GET("movie/{movie_id}/watch/providers")
    Observable<BaseResultBean<MovieWatchProviders>> getMovieWatchProviders(@Path("movie_id") long id, @QueryMap HashMap<String, Object> queryParams);

    //POST /movie/{movie_id}/rating
    //1.guest_session_id String  2.session_id String

    //DELETE /movie/{movie_id}/rating
    //1.guest_session_id String  2.session_id String

    @GET("movie/latest")
    Observable<MovieLatest> getMovieLatest(@QueryMap HashMap<String, Object> queryParams);

    @GET("movie/now_playing")
        //1.page int  2.region String
    Observable<BaseResultBean<Movie>> getMovieNowPlaying(@QueryMap HashMap<String, Object> queryParams);

    @GET("movie/popular")
        //1.page int  2.region String
    Observable<BaseResultBean<Movie>> getMoviePopular(@QueryMap HashMap<String, Object> queryParams);

    @GET("movie/top_rated")
        //1.page int  2.region String
    Observable<BaseResultBean<Movie>> getMovieTopRated(@QueryMap HashMap<String, Object> queryParams);

    @GET("movie/upcoming")
        //1.page int  2.region String
    Observable<BaseResultBean<Movie>> getMovieUpcoming(@QueryMap HashMap<String, Object> queryParams);

    /*****Movie******/

    /******Favorite******/
    @GET("account/{account_id}/favorite/movies")
    Observable<BaseResultBean<Movie>> getFavoriteMovie(@Path("account_id") long accountId, @QueryMap HashMap<String, Object> queryParams);

    @GET("account/{account_id}/favorite/tv")
    Observable<BaseResultBean<Tv>> getFavoriteTv(@Path("account_id") long accountId, @QueryMap HashMap<String, Object> queryParams);

    @POST("account/{account_id}/favorite")
    Observable<StatusBean> favorite(@Path("account_id") long accountId, @Body FavoritePostToken postToken, @QueryMap HashMap<String, Object> queryParams);
    /*****Favorite******/

    /******Watchlist******/
    @GET("account/{account_id}/watchlist/movies")
    Observable<BaseResultBean<Movie>> getWatchlistMovie(@Path("account_id") long accountId, @QueryMap HashMap<String, Object> queryParams);

    @GET("account/{account_id}/watchlist/tv")
    Observable<BaseResultBean<Tv>> getWatchlistTv(@Path("account_id") long accountId, @QueryMap HashMap<String, Object> queryParams);

    @POST("account/{account_id}/watchlist")
    Observable<StatusBean> watchlist(@Path("account_id") long accountId, @Body FavoritePostToken postToken, @QueryMap HashMap<String, Object> queryParams);

    /*****Watchlist******/

    /******Rating******/
    @GET("account/{account_id}/rated/movies")
    Observable<BaseResultBean<Movie>> getRatingMovie(@Path("account_id") long accountId, @QueryMap HashMap<String, Object> queryParams);

    @GET("account/{account_id}/rated/tv")
    Observable<BaseResultBean<Tv>> getRatingTv(@Path("account_id") long accountId, @QueryMap HashMap<String, Object> queryParams);

    /******Rating******/

    @GET("https://www.googleapis.com/youtube/v3/videos?key=AIzaSyCM993YhCfNQjlw0UWZQIXQNizWPCejMM8&id=Ox8_HtUicHk")
    Observable<YoutubeVideo> getYoutubeVideo(@QueryMap HashMap<String, Object> queryParams);
}

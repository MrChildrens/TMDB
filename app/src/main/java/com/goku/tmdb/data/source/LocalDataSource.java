package com.goku.tmdb.data.source;

import com.goku.tmdb.data.entity.Account;
import com.goku.tmdb.data.entity.Movie;
import com.goku.tmdb.data.entity.configuration.Configuration;
import com.goku.tmdb.data.entity.configuration.Countries;
import com.goku.tmdb.data.entity.configuration.Jobs;
import com.goku.tmdb.data.entity.configuration.Languages;
import com.goku.tmdb.data.entity.configuration.Timezones;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface LocalDataSource {
    void insert(Object object);

    void delete(Object object);

    void update(Object object);

    Observable<Configuration> getApiConfiguration();

    Observable<List<Countries>> getConfigurationCountries();

    Observable<List<Jobs>> getConfigurationJobs();

    Observable<List<Languages>> getConfigurationLanguages();

    Observable<Response<ResponseBody>> getConfigurationPrimaryTranslations();

    Observable<List<Timezones>> getConfigurationTimezones();

    Configuration getConfiguration();

    Account getAccount();

    List<Movie> getFavoriteList();

    void deleteAllFavoriteList();

    boolean isFavorite(long accountId, long movieId);

    List<Movie> getWatchListMovie();

    void deleteAllWatchList();

    boolean isWatchList(long accountId, long movieId);

}

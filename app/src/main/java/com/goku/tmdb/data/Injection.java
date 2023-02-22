package com.goku.tmdb.data;

import com.goku.tmdb.data.source.HttpDataSource;
import com.goku.tmdb.data.source.LocalDataSource;
import com.goku.tmdb.data.source.http.HttpDataSourceImpl;
import com.goku.tmdb.data.source.http.RetrofitTool;
import com.goku.tmdb.data.source.http.service.TmdbService;
import com.goku.tmdb.data.source.local.LocalDataSourceImpl;

public class Injection {
    public static TmdbRepository provideTmbdRepository() {
        TmdbService tmdbService = RetrofitTool.getInstance().getTmdbService();
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(tmdbService);
        LocalDataSource localDataSource = LocalDataSourceImpl.getInstance();
        return TmdbRepository.getInstance(httpDataSource, localDataSource);
    }
}

package com.goku.tmdb.data.source.local;

import android.database.sqlite.SQLiteDatabase;

import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.data.entity.Account;
import com.goku.tmdb.data.entity.Movie;
import com.goku.tmdb.data.entity.configuration.Configuration;
import com.goku.tmdb.data.entity.configuration.Countries;
import com.goku.tmdb.data.entity.configuration.Jobs;
import com.goku.tmdb.data.entity.configuration.Languages;
import com.goku.tmdb.data.entity.configuration.Timezones;
import com.goku.tmdb.data.greendao.AccountDao;
import com.goku.tmdb.data.greendao.ConfigurationDao;
import com.goku.tmdb.data.greendao.DaoMaster;
import com.goku.tmdb.data.greendao.DaoSession;
import com.goku.tmdb.data.greendao.FavoriteDao;
import com.goku.tmdb.data.greendao.WatchListDao;
import com.goku.tmdb.data.source.LocalDataSource;
import com.goku.tmdb.db.Favorite;
import com.goku.tmdb.db.WatchList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author: Ciel
 * @date: 2020/3/10 11:56
 */
public class LocalDataSourceImpl implements LocalDataSource {
    private static final String DB_NAME = "tmdb.db";
    private static final String TAG = "LocalDataSourceImpl";
    private static volatile LocalDataSourceImpl sLocalDataSourceImpl;

    private final DaoMaster.DevOpenHelper mDevOpenHelper;
    private final SQLiteDatabase mLiteDatabase;
    private final DaoMaster mDaoMaster;
    private final DaoSession mDaoSession;

    private final ConfigurationDao mConfigurationDao;
    private final AccountDao mAccountDao;
    private final FavoriteDao mFavoriteDao;
    private final WatchListDao mWatchListDao;

    public static LocalDataSourceImpl getInstance() {
        if (sLocalDataSourceImpl == null) {
            synchronized (LocalDataSourceImpl.class) {
                if (sLocalDataSourceImpl == null) {
                    sLocalDataSourceImpl = new LocalDataSourceImpl();
                }
            }
        }
        return sLocalDataSourceImpl;
    }

    public LocalDataSourceImpl() {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(TmdbApplication.getInstance(), DB_NAME, null);
        mLiteDatabase = mDevOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mLiteDatabase);
        mDaoSession = mDaoMaster.newSession();

        mConfigurationDao = mDaoSession.getConfigurationDao();
        mAccountDao = mDaoSession.getAccountDao();
        mFavoriteDao = mDaoSession.getFavoriteDao();
        mWatchListDao = mDaoSession.getWatchListDao();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getLiteDatabase() {
        return mLiteDatabase;
    }

    @Override
    public void insert(Object object) {
        if (object instanceof Configuration) {
            mConfigurationDao.insertOrReplace((Configuration) object);
        } else if (object instanceof Account) {
            mAccountDao.insertOrReplace((Account) object);
        } else if (object instanceof Favorite) {
            mFavoriteDao.insertOrReplace((Favorite) object);
        }else if (object instanceof WatchList) {
            mWatchListDao.insertOrReplace((WatchList) object);
        }
    }

    @Override
    public void delete(Object object) {
        if (object instanceof Configuration) {
            mConfigurationDao.delete((Configuration) object);
        } else if (object instanceof Account) {

        } else if (object instanceof Favorite) {
            mFavoriteDao.delete((Favorite) object);
        }else if (object instanceof WatchList) {
            mWatchListDao.delete((WatchList) object);
        }
    }

    @Override
    public void update(Object object) {
        if (object instanceof Configuration) {
            mConfigurationDao.update((Configuration) object);
        }
    }

    @Override
    public Observable<List<Countries>> getConfigurationCountries() {
        Configuration configuration = mConfigurationDao.queryBuilder().unique();
        if (configuration == null) {
            configuration = new Configuration();
            configuration.setMCountries(new ArrayList<>());
        } else if (configuration.getMCountries() == null) {
            configuration.setMCountries(new ArrayList<>());
        }
        return Observable.just(configuration.getMCountries());
    }

    @Override
    public Observable<List<Jobs>> getConfigurationJobs() {
        Configuration configuration = mConfigurationDao.queryBuilder().unique();
        if (configuration == null) {
            configuration = new Configuration();
            configuration.setMJobs(new ArrayList<>());
        } else if (configuration.getMJobs() == null) {
            configuration.setMJobs(new ArrayList<>());
        }
        return Observable.just(configuration.getMJobs());
    }

    @Override
    public Observable<List<Languages>> getConfigurationLanguages() {
        Configuration configuration = mConfigurationDao.queryBuilder().unique();
        if (configuration == null) {
            configuration = new Configuration();
            configuration.setMLanguages(new ArrayList<>());
        } else if (configuration.getMLanguages() == null) {
            configuration.setMLanguages(new ArrayList<>());
        }
        return Observable.just(configuration.getMLanguages());
    }

    @Override
    public Observable<Response<ResponseBody>> getConfigurationPrimaryTranslations() {
        return null;
    }

    @Override
    public Observable<List<Timezones>> getConfigurationTimezones() {
        Configuration configuration = mConfigurationDao.queryBuilder().unique();
        if (configuration == null) {
            configuration = new Configuration();
            configuration.setMTimezones(new ArrayList<>());
        } else if (configuration.getMTimezones() == null) {
            configuration.setMTimezones(new ArrayList<>());
        }
        return Observable.just(configuration.getMTimezones());
    }

    @Override
    public Configuration getConfiguration() {
        return mConfigurationDao.queryBuilder().unique();
    }

    @Override
    public Account getAccount() {
        return mAccountDao.queryBuilder().unique();
    }

    @Override
    public List<Movie> getFavoriteList() {
        List<Movie> movies = new ArrayList<>();
        List<Favorite> list = mFavoriteDao.queryBuilder().list();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Favorite favorite = list.get(i);
                Movie movie = Utils.favoriteToMovie(favorite);
                if (movie != null) {
                    movies.add(movie);
                }
            }
        }
        return movies;
    }

    @Override
    public void deleteAllFavoriteList() {
        mFavoriteDao.deleteAll();
    }

    @Override
    public boolean isFavorite(long accountId, long movieId) {
        Favorite favorite = mFavoriteDao.queryBuilder().where(FavoriteDao.Properties.AccountId.eq(accountId), FavoriteDao.Properties.Id.eq(movieId)).unique();
        return favorite != null;
    }

    @Override
    public List<Movie> getWatchListMovie() {
        List<Movie> movies = new ArrayList<>();
        List<WatchList> list = mWatchListDao.queryBuilder().list();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                WatchList favorite = list.get(i);
                Movie movie = Utils.watchlistToMovie(favorite);
                if (movie != null) {
                    movies.add(movie);
                }
            }
        }
        return movies;
    }

    @Override
    public void deleteAllWatchList() {
        mWatchListDao.deleteAll();
    }

    @Override
    public boolean isWatchList(long accountId, long movieId) {
        WatchList watchList = mWatchListDao.queryBuilder().where(WatchListDao.Properties.AccountId.eq(accountId), WatchListDao.Properties.Id.eq(movieId)).unique();
        return watchList != null;
    }

    @Override
    public Observable<Configuration> getApiConfiguration() {
        Configuration configuration = mConfigurationDao.queryBuilder().unique();
        if (configuration == null) {
            configuration = new Configuration();
        }
        return Observable.just(configuration);
    }

}

package com.goku.tmdb.data.source.http;

import android.util.Log;

import com.goku.tmdb.app.Constant;
import com.goku.tmdb.data.source.http.service.TmdbService;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTool {

    private static final String TAG = RetrofitTool.class.getSimpleName();

    private static volatile RetrofitTool sRetrofitTool;

    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private TmdbService mTmdbService;

    public static RetrofitTool getInstance() {
        if (sRetrofitTool == null) {
            synchronized (RetrofitTool.class) {
                if (sRetrofitTool == null) {
                    sRetrofitTool = new RetrofitTool();
                }
            }
        }
        return sRetrofitTool;
    }

    public RetrofitTool() {
        initOkHttpClient();
        initRetrofit();
    }

    private void initOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG, "[Ciel_Debug] log: " + message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder();
                HttpUrl originalHttpUrl = chain.request().url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", Constant.TMDB_API_KEY)
//                        .addQueryParameter("language", Utils.getLanguage())
                        .build();
                request.url(url);
                Response response = chain.proceed(request.build());
                return response;
            }
        };
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor)
                .build();
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTmdbService = mRetrofit.create(TmdbService.class);
    }

    public TmdbService getTmdbService() {
        return mTmdbService;
    }

    public void setTmdbService(TmdbService tmdbService) {
        mTmdbService = tmdbService;
    }
}

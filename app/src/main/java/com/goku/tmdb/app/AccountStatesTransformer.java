package com.goku.tmdb.app;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.goku.tmdb.data.entity.AccountStates;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AccountStatesTransformer implements io.reactivex.functions.Function<ResponseBody, Observable<AccountStates>> {


    @Override
    public Observable<AccountStates> apply(ResponseBody responseBody) throws Exception {
        AccountStates accountStates = new AccountStates();
        if (responseBody != null) {
            byte[] bytes = new byte[0];
            try {
                bytes = responseBody.bytes();
                if (bytes.length > 0) {
                    try {
                        String json = new String(bytes);
                        JSONObject jsonObject = new JSONObject(json);
                        Boolean isFav = jsonObject.optBoolean("favorite");
                        accountStates.setFavorite(isFav);
                        Boolean isWatchlist = jsonObject.optBoolean("watchlist");
                        accountStates.setWatchlist(isWatchlist);
                        JSONObject ratingObject = jsonObject.getJSONObject("rated");
                        double value = ratingObject.optDouble("value");
                        AccountStates.Rated rated = new AccountStates.Rated();
                        rated.setValue((float) value);
                        accountStates.setRated(rated);
                    } catch (JSONException exception) {
                        return Observable.just(accountStates);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return Observable.just(accountStates);
    }
}

package com.goku.tmdb.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.goku.tmdb.R;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseContentViewModel {
    private static final String TAG = HomeViewModel.class.getSimpleName();
    private final List<ItemCategoryModel> mData = new ArrayList<>();
    public final MutableLiveData<List<ItemCategoryModel>> refreshCategory = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
    }

    public void init() {
        addItem(Utils.getContext().getString(R.string.trending), Utils.getContext().getString(R.string.movie).toUpperCase(), PageParams.CATEGORY_TYPE_MOVIE_TRENDING, PageParams.DIRECTION_VERTICAL);

        addItem(Utils.getContext().getString(R.string.now_playing), Utils.getContext().getString(R.string.movie).toUpperCase(), PageParams.CATEGORY_TYPE_MOVIE_NOW_PLAYING, PageParams.DIRECTION_VERTICAL);

        addItem(Utils.getContext().getString(R.string.upcoming), Utils.getContext().getString(R.string.movie).toUpperCase(), PageParams.CATEGORY_TYPE_MOVIE_UPCOMING, PageParams.DIRECTION_VERTICAL);

        addItem(Utils.getContext().getString(R.string.popular), Utils.getContext().getString(R.string.movie).toUpperCase(), PageParams.CATEGORY_TYPE_MOVIE_POPULAR, PageParams.DIRECTION_VERTICAL);

        addItem(Utils.getContext().getString(R.string.top_rated), Utils.getContext().getString(R.string.movie).toUpperCase(), PageParams.CATEGORY_TYPE_MOVIE_TOP_RATED, PageParams.DIRECTION_VERTICAL);

        addItem(Utils.getContext().getString(R.string.celebrities), "", PageParams.CATEGORY_TYPE_PERSON, PageParams.DIRECTION_VERTICAL);

        addItem(Utils.getContext().getString(R.string.trending), Utils.getContext().getString(R.string.tv_show).toUpperCase(), PageParams.CATEGORY_TYPE_TV_TRENDING, PageParams.DIRECTION_VERTICAL);

        addItem(Utils.getContext().getString(R.string.airing_today), Utils.getContext().getString(R.string.tv_show).toUpperCase(), PageParams.CATEGORY_TYPE_TV_AIRING_TODAY, PageParams.DIRECTION_VERTICAL);

        addItem(Utils.getContext().getString(R.string.on_tv), Utils.getContext().getString(R.string.tv_show).toUpperCase(), PageParams.CATEGORY_TYPE_TV_ON_AIR, PageParams.DIRECTION_VERTICAL);

        addItem(Utils.getContext().getString(R.string.top_rated), Utils.getContext().getString(R.string.tv_show).toUpperCase(), PageParams.CATEGORY_TYPE_TV_TOP_RATED, PageParams.DIRECTION_VERTICAL);

        refreshCategory.postValue(mData);
    }

    private void addItem(String category, String type, int viewType, int itemDirection) {
        ItemCategoryModel mutiItemCategoryModel = new ItemCategoryModel();
        mutiItemCategoryModel.titles.set(category);
        mutiItemCategoryModel.subTitles.set(type);
        mutiItemCategoryModel.setCategoryType(viewType);
        mutiItemCategoryModel.setItemDirection(itemDirection);
        mutiItemCategoryModel.itemDatas.set(new ArrayList<>());
        if (viewType == PageParams.CATEGORY_TYPE_MOVIE_TRENDING
                || viewType == PageParams.CATEGORY_TYPE_TV_TRENDING
                || viewType == PageParams.CATEGORY_TYPE_PERSON) {
            mutiItemCategoryModel.isShowTimeSwitch.set(true);
        }
        mData.add(mutiItemCategoryModel);
    }

    public List<ItemCategoryModel> getData() {
        return mData;
    }
}
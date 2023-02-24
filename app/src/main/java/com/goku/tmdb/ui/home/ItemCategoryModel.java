package com.goku.tmdb.ui.home;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.goku.tmdb.R;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.ui.StatusModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemCategoryModel implements Serializable {
    public transient final StatusModel statusModel = new StatusModel();
    public ObservableField<List<ItemMediaModel>> itemDatas = new ObservableField<>(new ArrayList<>());
    public transient final MutableLiveData<ItemCategoryModel> refreshCategory = new MutableLiveData<>();
    public transient final MutableLiveData<ItemCategoryModel> changeDayOrWeek = new MutableLiveData<>();

    public transient ObservableField<Integer> dominantColor;
    public transient ObservableField<Integer> mutedColor;
    public transient ObservableField<Integer> lightMutedColor;
    public transient ObservableField<Boolean> isDark;

    public ObservableField<String> titles = new ObservableField<>();
    public ObservableField<String> subTitles = new ObservableField<>();
    public transient ObservableField<String> content = new ObservableField<>();
    public transient ObservableField<String> images = new ObservableField<>();
    public transient ObservableField<String> count = new ObservableField<>();
    public transient ObservableField<Boolean> isShowCount = new ObservableField<>(true);
    public ObservableField<Boolean> isDay = new ObservableField<>(false);
    public transient ObservableField<Boolean> isShowTimeSwitch = new ObservableField<>(false);

    public transient View.OnClickListener onLeftClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!isDay.get()) {
                isDay.set(true);
                changeDayOrWeek.postValue(ItemCategoryModel.this);
            }
        }
    };

    public transient View.OnClickListener onRightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isDay.get()) {
                isDay.set(false);
                changeDayOrWeek.postValue(ItemCategoryModel.this);
            }
        }
    };

    private int mCategoryType;
    private long mId;
    private int mPage = 1;
    private int mTotalPage = 1;

    private long mTvId;
    private String mTvTitle;
    private String mPosterPath;
    private String mBackdropPath;
    private int mEpisodeCount = 1;
    private int mSeasonNumber = 1;

    private String mSearchQuery;

    private int mItemDirection = PageParams.DIRECTION_VERTICAL;

    public ItemCategoryModel() {
        if (!Utils.isNightMode()) {
            isDark = new ObservableField<>(false);
            dominantColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.white));
            mutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.sub_title_color));
            lightMutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.black));
        } else {
            isDark = new ObservableField<>(true);
            dominantColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.tmdb_primary_color));
            mutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.sub_title_color));
            lightMutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.white));
        }
    }

    public int getCategoryType() {
        return mCategoryType;
    }

    public void setCategoryType(int categoryType) {
        mCategoryType = categoryType;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getTotalPage() {
        return mTotalPage;
    }

    public void setTotalPage(int totalPage) {
        mTotalPage = totalPage;
    }

    public long getTvId() {
        return mTvId;
    }

    public void setTvId(long tvId) {
        mTvId = tvId;
    }

    public String getTvTitle() {
        return mTvTitle;
    }

    public void setTvTitle(String tvTitle) {
        mTvTitle = tvTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public int getEpisodeCount() {
        return mEpisodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        mEpisodeCount = episodeCount;
    }

    public int getSeasonNumber() {
        return mSeasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        mSeasonNumber = seasonNumber;
    }

    public String getSearchQuery() {
        return mSearchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        mSearchQuery = searchQuery;
    }

    public int getItemDirection() {
        return mItemDirection;
    }

    public void setItemDirection(int itemDirection) {
        mItemDirection = itemDirection;
    }

}

package com.goku.tmdb.ui;

public class TmdbEvent {

    private boolean mIsRefreshColor;

    public TmdbEvent(boolean isRefreshColor) {
        mIsRefreshColor = isRefreshColor;
    }

    public boolean isRefreshColor() {
        return mIsRefreshColor;
    }

    public void setRefreshColor(boolean refreshColor) {
        mIsRefreshColor = refreshColor;
    }
}

package com.goku.tmdb.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class HorizontalRecyclerView extends RecyclerView {
    private static final String TAG = HorizontalRecyclerView.class.getSimpleName();
    private boolean loadingMoreEnabled = true;

    public HorizontalRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public HorizontalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (/*newState == RecyclerView.SCROLL_STATE_IDLE && */!mIsLoading) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
                    int lastVisibleItemPosition = 0;
                    LayoutManager layoutManager = getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager) {
                        lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
//                        lastVisibleItemPosition = findMax(into);
                    } else {
                        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    }
                    Log.d(TAG, "[Ciel_Debug] -----------------------------------------------");
                    Log.d(TAG, "[Ciel_Debug] onScrollStateChanged linearLayoutManager.getChildCount(): " + linearLayoutManager.getChildCount());
                    Log.d(TAG, "[Ciel_Debug] onScrollStateChanged lastVisibleItemPosition: " + lastVisibleItemPosition);
                    Log.d(TAG, "[Ciel_Debug] onScrollStateChanged linearLayoutManager.getItemCount(): " + linearLayoutManager.getItemCount());
                    if (linearLayoutManager.getChildCount() > 0
                            && lastVisibleItemPosition > linearLayoutManager.getItemCount() - 100
                            && linearLayoutManager.getItemCount() > linearLayoutManager.getChildCount()) {
                        if (mOnLoadingListener != null && loadingMoreEnabled) {
                            mIsLoading = true;
                            mOnLoadingListener.onLoadMore();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public boolean isLoadingMoreEnabled() {
        return loadingMoreEnabled;
    }

    public void setLoadingMoreEnabled(boolean loadingMoreEnabled) {
        this.loadingMoreEnabled = loadingMoreEnabled;
    }

    public boolean mIsLoading = false;

    public void loadmoreComplete() {
        mIsLoading = false;
    }

    private OnLoadingListener mOnLoadingListener;

    public void setOnLoadingListener(OnLoadingListener onLoadingListener) {
        mOnLoadingListener = onLoadingListener;
    }

    public interface OnLoadingListener {
        void onLoadMore();
    }
}

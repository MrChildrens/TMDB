package com.goku.tmdb.ui.search;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.goku.tmdb.R;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.ui.ContentListFragment;

import java.util.ArrayList;
import java.util.List;

public class ContentPageAdapter extends FragmentPagerAdapter {
    private static final String TAG = ContentPageAdapter.class.getSimpleName();
    private static final Integer[] SEARCH_CATEGORYS = new Integer[]{R.string.movie, R.string.tv_show, R.string.celebrities};
    private static final Integer[] FAVORITE_CATEGORYS = new Integer[]{R.string.movie, R.string.tv_show};

    private final List<String> mCategorys = new ArrayList<>();
    private final List<ContentListFragment> mFragments = new ArrayList<>();

    public ContentPageAdapter(Context context, @NonNull FragmentManager fm, int categoryType) {
        super(fm);
        init(context, categoryType);
    }

    private void init(Context context, int categoryType) {
        if (categoryType == PageParams.PAGE_TYPE_SEARCH) {
            for (int i = 0; i < SEARCH_CATEGORYS.length; i++) {
                mCategorys.add(context.getResources().getString(SEARCH_CATEGORYS[i]));
                String category = mCategorys.get(i);
                if (TextUtils.equals(category, context.getResources().getString(R.string.movie))) {
                    mFragments.add(ContentListFragment.newInstance(PageParams.CATEGORY_TYPE_SEARCH_MOVIE, i));
                } else if (TextUtils.equals(category, context.getResources().getString(R.string.tv_show))) {
                    mFragments.add(ContentListFragment.newInstance(PageParams.CATEGORY_TYPE_SEARCH_TV_SHOW, i));
                } else if (TextUtils.equals(category, context.getResources().getString(R.string.celebrities))) {
                    mFragments.add(ContentListFragment.newInstance(PageParams.CATEGORY_TYPE_SEARCH_PERSON, i));
                }
            }
        } else if (categoryType == PageParams.PAGE_TYPE_FAVORITE) {
            for (int i = 0; i < FAVORITE_CATEGORYS.length; i++) {
                mCategorys.add(context.getResources().getString(FAVORITE_CATEGORYS[i]));
                String category = mCategorys.get(i);
                if (TextUtils.equals(category, context.getResources().getString(R.string.movie))) {
                    mFragments.add(ContentListFragment.newInstance(PageParams.CATEGORY_TYPE_FAVORITE_MOVIE, i));
                } else if (TextUtils.equals(category, context.getResources().getString(R.string.tv_show))) {
                    mFragments.add(ContentListFragment.newInstance(PageParams.CATEGORY_TYPE_FAVORITE_TV, i));
                }
            }
        } else if (categoryType == PageParams.PAGE_TYPE_WATCHLIST) {
            for (int i = 0; i < FAVORITE_CATEGORYS.length; i++) {
                mCategorys.add(context.getResources().getString(FAVORITE_CATEGORYS[i]));
                String category = mCategorys.get(i);
                if (TextUtils.equals(category, context.getResources().getString(R.string.movie))) {
                    mFragments.add(ContentListFragment.newInstance(PageParams.CATEGORY_TYPE_WATCHLIST_MOVIE, i));
                } else if (TextUtils.equals(category, context.getResources().getString(R.string.tv_show))) {
                    mFragments.add(ContentListFragment.newInstance(PageParams.CATEGORY_TYPE_WATCHLIST_TV, i));
                }
            }
        } else if (categoryType == PageParams.PAGE_TYPE_RATING) {
            for (int i = 0; i < FAVORITE_CATEGORYS.length; i++) {
                mCategorys.add(context.getResources().getString(FAVORITE_CATEGORYS[i]));
                String category = mCategorys.get(i);
                if (TextUtils.equals(category, context.getResources().getString(R.string.movie))) {
                    mFragments.add(ContentListFragment.newInstance(PageParams.CATEGORY_TYPE_RATING_MOVIE, i));
                } else if (TextUtils.equals(category, context.getResources().getString(R.string.tv_show))) {
                    mFragments.add(ContentListFragment.newInstance(PageParams.CATEGORY_TYPE_RATING_TV, i));
                }
            }
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public List<String> getCategorys() {
        return mCategorys;
    }

    public List<ContentListFragment> getFragments() {
        return mFragments;
    }
}

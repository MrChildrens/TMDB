package com.goku.tmdb.ui.detail;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.goku.tmdb.R;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.ui.ContentListFragment;
import com.goku.tmdb.ui.home.ItemMediaModel;

import java.util.ArrayList;
import java.util.List;

public class DetailFragmentPageAdapter extends FragmentPagerAdapter {
    private static final String TAG = DetailFragmentPageAdapter.class.getSimpleName();
    private static final Integer[] MOVIE_DETAIL_CATEGORYS = new Integer[]{R.string.about, R.string.cast, R.string.crew, R.string.reviews, R.string.recommendations, R.string.similar};

    private static final Integer[] TV_DETAIL_CATEGORYS = new Integer[]{R.string.about, R.string.seasons, R.string.cast, R.string.crew, R.string.reviews, R.string.recommendations, R.string.similar};

    private static final Integer[] PEOPLE_CATEGORYS = new Integer[]{R.string.about, R.string.movie, R.string.tv_show};

    private static final Integer[] SEASONS_CATEGORYS = new Integer[]{R.string.about, R.string.episode, R.string.cast};

    private final List<String> mCategorys = new ArrayList<>();
    private final List<Fragment> mFragments = new ArrayList<>();

    public DetailFragmentPageAdapter(Context context, @NonNull FragmentManager fm, ItemMediaModel mediaModel) {
        super(fm);
        init(context, mediaModel, null);
    }

    public DetailFragmentPageAdapter(Context context, @NonNull FragmentManager fm, ItemMediaModel mediaModel, List<ItemMediaModel> mediaModels) {
        super(fm);
        init(context, mediaModel, mediaModels);
    }

    private void init(Context context, ItemMediaModel mediaModel, List<ItemMediaModel> mediaModels) {
        Log.d(TAG, "[Ciel_Debug] init: " + mediaModels.size());
        switch (mediaModel.getItemType()) {
            case PageParams.ITEM_TYPE_MOIVE:
            case PageParams.ITEM_TYPE_MOIVE_HORI:
                for (Integer movieDetailCategory : MOVIE_DETAIL_CATEGORYS) {
                    mCategorys.add(context.getResources().getString(movieDetailCategory));
                }
                break;
            case PageParams.ITEM_TYPE_TV_SHOW:
            case PageParams.ITEM_TYPE_TV_SHOW_HORI:
                for (Integer tvDetailCategory : TV_DETAIL_CATEGORYS) {
                    mCategorys.add(context.getResources().getString(tvDetailCategory));
                }
                break;
            case PageParams.ITEM_TYPE_PEOPLE:
            case PageParams.ITEM_TYPE_PEOPLE_HORI:
                for (Integer peopleCategory : PEOPLE_CATEGORYS) {
                    mCategorys.add(context.getResources().getString(peopleCategory));
                }
                break;
            case PageParams.ITEM_TYPE_SEASONS:
                for (Integer seasonsCategory : SEASONS_CATEGORYS) {
                    mCategorys.add(context.getResources().getString(seasonsCategory));
                }
                break;
            case PageParams.ITEM_TYPE_EPISODES:
                if (mediaModels != null) {
                    for (int i = 0; i < mediaModels.size(); i++) {
                        mCategorys.add(Utils.formatEpisodeName(mediaModels.get(i).getEpisodeNumber()));
                    }
                }
                break;
        }
        for (int i = 0; i < mCategorys.size(); i++) {
            String category = mCategorys.get(i);
            Log.d(TAG, "[Ciel_Debug] init: " + category);
            Log.d(TAG, "[Ciel_Debug] init: " + mediaModel.getItemType());
            if (mediaModels.size() > 0) {
                Log.d(TAG, "[Ciel_Debug] init: " + Utils.formatEpisodeName(mediaModels.get(i).getEpisodeNumber()));
            }
            if (TextUtils.equals(category, context.getResources().getString(R.string.about))) {
                mFragments.add(DetailAboutFragment.newInstance(mediaModel));
            } else if (TextUtils.equals(category, context.getResources().getString(R.string.crew))) {
                if (mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                        || mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_CREDITS_MOVIE_CREW, i));
                } else if (mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                        || mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_CREDITS_TV_CREW, i));
                }
            } else if (TextUtils.equals(category, context.getResources().getString(R.string.cast))) {
                if (mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                        || mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_CREDITS_MOVIE_CAST, i));
                } else if (mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                        || mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_CREDITS_TV_CAST, i));
                } else if (mediaModel.getItemType() == PageParams.ITEM_TYPE_SEASONS) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_CREDITS_SEASONS_CAST, i));
                }
            } else if (TextUtils.equals(category, context.getResources().getString(R.string.seasons))) {
                mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_SEASONS, i));
            } else if (TextUtils.equals(category, context.getResources().getString(R.string.comments))) {
                mFragments.add(DetailAboutFragment.newInstance(mediaModel));
            } else if (TextUtils.equals(category, context.getResources().getString(R.string.reviews))) {
                if (mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                        || mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_REVIRES_MOVIE, i));
                } else if (mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                        || mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_REVIRES_TV, i));
                }
            } else if (TextUtils.equals(category, context.getResources().getString(R.string.recommendations))) {
                if (mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                        || mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_MOVIE_RECOMMENDATIONS, i));
                } else if (mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                        || mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_TV_RECOMMENDATIONS, i));
                }
            } else if (TextUtils.equals(category, context.getResources().getString(R.string.similar))) {
                if (mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                        || mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_MOVIE_SIMILAR, i));
                } else if (mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                        || mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
                    mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_TV_SIMILAR, i));
                }
            } else if (TextUtils.equals(category, context.getResources().getString(R.string.movie))) {
                mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_MOVIE_PEOPLE, i));
            } else if (TextUtils.equals(category, context.getResources().getString(R.string.tv_show))) {
                mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_TV_PEOPLE, i));
            } else if (TextUtils.equals(category, context.getResources().getString(R.string.episode))) {
                mFragments.add(ContentListFragment.newInstance(mediaModel, PageParams.CATEGORY_TYPE_EPISODES, i));
            } else if (TextUtils.equals(category, Utils.formatEpisodeName(mediaModels.get(i).getEpisodeNumber()))) {
                mFragments.add(DetailAboutFragment.newInstance(mediaModels.get(i)));
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
}

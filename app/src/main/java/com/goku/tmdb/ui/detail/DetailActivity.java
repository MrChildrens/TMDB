package com.goku.tmdb.ui.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.goku.tmdb.R;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseActivity;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.data.Injection;
import com.goku.tmdb.databinding.ActivityDetailBinding;
import com.goku.tmdb.ui.home.ItemCategoryModel;
import com.goku.tmdb.ui.home.ItemMediaModel;
import com.goku.tmdb.ui.view.RatingDialog;
import com.goku.tmdb.ui.view.RatingViewModel;
import com.google.android.material.appbar.AppBarLayout;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.io.Serializable;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailViewModel> {
    private static final String TAG = DetailActivity.class.getSimpleName();
    private ViewPager mViewPager;
    private DetailFragmentPageAdapter mPageAdapter;
    private MagicIndicator mMagicIndicator;
    private CommonNavigator mCommonNavigator;
    private float mAppbarAphla = 0;

    public static void newInstance(Context context, ItemMediaModel model) {
        newInstance(context, model, null);
    }

    public static void newInstance(Context context, ItemMediaModel model, List<ItemMediaModel> mediaModels) {
        Intent intent = new Intent();
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_ITEM_MODEL, model);
        if (mediaModels != null) {
            bundle.putSerializable(Constant.KEY_ITEM_MODELS, (Serializable) mediaModels);
        }
        intent.putExtras(bundle);
        intent.setClass(context, DetailActivity.class);
        context.startActivity(intent);
    }

    public static void newInstance(Context context, ItemCategoryModel categoryModel) {
        Intent intent = new Intent();
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_CATEGORY_MODEL, categoryModel);
        intent.putExtras(bundle);
        intent.setClass(context, DetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initPaltte(true);
    }

    private void initAdapter() {
        if (mViewModel.getMediaModels() == null) {
            mPageAdapter = new DetailFragmentPageAdapter(this, getSupportFragmentManager(), mViewModel.getItemMediaModel());
        } else {
            mPageAdapter = new DetailFragmentPageAdapter(this, getSupportFragmentManager(), mViewModel.getItemMediaModel(), mViewModel.getMediaModels());
        }
    }

    private void initData() {
        List<ItemMediaModel> mediaModels = null;
        ItemMediaModel mediaModel = null;
        ItemCategoryModel categoryModel = null;
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                mediaModel = (ItemMediaModel) bundle.getSerializable(Constant.KEY_ITEM_MODEL);
                mediaModels = (List<ItemMediaModel>) bundle.getSerializable(Constant.KEY_ITEM_MODELS);
                categoryModel = (ItemCategoryModel) bundle.getSerializable(Constant.KEY_CATEGORY_MODEL);
            }
        }
        if (mediaModel != null) {
            if (mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                    || mediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
                mViewModel.initMovieDetail(mediaModel);
            } else if (mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                    || mediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
                mViewModel.initTvDetail(mediaModel);
            } else if (mediaModel.getItemType() == PageParams.ITEM_TYPE_SEASONS) {
                mViewModel.initSeasonDetail(mediaModel);
            } else if (mediaModel.getItemType() == PageParams.ITEM_TYPE_EPISODES) {
                mViewModel.initEpisodeDetail(mediaModel);
            } else if (mediaModel.getItemType() == PageParams.ITEM_TYPE_PEOPLE
                    || mediaModel.getItemType() == PageParams.ITEM_TYPE_PEOPLE_HORI) {
                mViewModel.initPersonDetail(mediaModel);
            }
        }
        if (mediaModels != null) {
            mViewModel.setMediaModels(mediaModels);
        }
        if (categoryModel != null) {
            ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
            itemCategoryModel.setCategoryType(categoryModel.getCategoryType());
            itemCategoryModel.setTvId(categoryModel.getTvId());
            itemCategoryModel.setTvTitle(categoryModel.getTvTitle());
            itemCategoryModel.setSeasonNumber(categoryModel.getSeasonNumber());
            itemCategoryModel.setEpisodeNumber(categoryModel.getEpisodeNumber());
            itemCategoryModel.setBackdropPath(categoryModel.getBackdropPath());
            mViewModel.setItemCategoryModel(itemCategoryModel);
        }
    }

    private void initPaltte(boolean needInitView) {
        if (mViewModel != null) {
            String url = null;
            if (!TextUtils.isEmpty(mViewModel.poster.get())) {
                url = mViewModel.poster.get();
            } else if (!TextUtils.isEmpty(mViewModel.backdrop.get())) {
                url = mViewModel.backdrop.get();
            } else if (needInitView) {
                initView();
                return;
            }
            Glide.with(TmdbApplication.getInstance())
                    .asBitmap()
                    .load(url)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(@Nullable Palette palette) {
                                    if (palette == null && needInitView) {
                                        initView();
                                        return;
                                    }

                                    mViewModel.dominantColor.set(palette.getDominantColor(Color.WHITE));

                                    boolean isDark = Utils.isNightMode();
                                    Palette.Swatch mostPopularSwatch = null;
                                    for (Palette.Swatch swatch : palette.getSwatches()) {
                                        if (mostPopularSwatch == null
                                                || swatch.getPopulation() > mostPopularSwatch.getPopulation()) {
                                            mostPopularSwatch = swatch;
                                        }
                                    }
                                    if (mostPopularSwatch != null) {
                                        double luminance = ColorUtils.calculateLuminance(mostPopularSwatch.getRgb());
                                        // 当luminance小于0.5时，我们认为这是一个深色值.
                                        isDark = luminance < 0.5;
                                    }

                                    Window window = getWindow();
                                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                                    Drawable drawable;
                                    if (isDark) {
                                        mViewModel.isDark.set(true);
                                        mViewModel.bodyTextColor.set(Color.WHITE);
                                        mViewModel.titleTextColor.set(getResources().getColor(R.color.sub_title_color));
                                        drawable = ContextCompat.getDrawable(DetailActivity.this, R.drawable.ic_audience_night);
                                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                                    } else {
                                        mViewModel.isDark.set(false);
                                        mViewModel.bodyTextColor.set(Color.BLACK);
                                        mViewModel.titleTextColor.set(getResources().getColor(R.color.sub_title_color));
                                        drawable = ContextCompat.getDrawable(DetailActivity.this, R.drawable.ic_audience_black);

                                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                                    }
                                    mViewModel.dominantColor.set(mostPopularSwatch.getRgb());
//                                    mViewModel.bodyTextColor.set(mostPopularSwatch.getBodyTextColor());
                                    mViewModel.titleTextColor.set(mostPopularSwatch.getTitleTextColor());
                                    if (mPageAdapter != null && mCommonNavigator != null) {
                                        LinePagerIndicator indicator = (LinePagerIndicator) mCommonNavigator.getPagerIndicator();
                                        indicator.setColors(mViewModel.titleTextColor.get());
                                        indicator.getPaint().setColor(mViewModel.titleTextColor.get());
                                        indicator.invalidate();
                                        for (int i = 0; i < mPageAdapter.getCount(); i++) {
                                            SimplePagerTitleView simplePagerTitleView = (SimplePagerTitleView) mCommonNavigator.getTitleContainer().getChildAt(i);
                                            simplePagerTitleView.setSelectedColor(mViewModel.titleTextColor.get());
                                            simplePagerTitleView.setNormalColor(mViewModel.bodyTextColor.get());
                                            if (i != mViewPager.getCurrentItem()) {
                                                simplePagerTitleView.setTextColor(mViewModel.bodyTextColor.get());
                                            } else {
                                                mCommonNavigator.onPageSelected(i);
                                                simplePagerTitleView.setTextColor(mViewModel.titleTextColor.get());
                                            }
                                        }
                                    }

                                    if (drawable != null) {
                                        drawable.setBounds(0, 0, getResources().getDimensionPixelOffset(R.dimen.button_drawable_width),
                                                getResources().getDimensionPixelOffset(R.dimen.button_drawable_width));
                                        mViewBinding.layoutMediaTop.tvVoteCount.setCompoundDrawablesRelative(drawable, null, null, null);
                                    }

                                    window.setStatusBarColor(mViewModel.dominantColor.get());

                                    mViewBinding.toolbar.setBackgroundColor(mViewModel.dominantColor.get());
                                    mViewBinding.toolbar.getBackground().setAlpha((int) (mAppbarAphla * 255));
                                    if (needInitView) {
                                        initView();
                                    }
                                    mViewBinding.setViewModel(mViewModel);
                                }
                            });
                        }

                        @Override
//                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        public void onLoadFailed(Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                            if (needInitView) {
                                initView();
                            }
                        }
                    });
        }
    }

    private void initView() {

        mViewBinding.toolbar.setBackgroundColor(mViewModel.dominantColor.get());

        setSupportActionBar(mViewBinding.toolbar);
        mViewBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initRatingDialog();
        if (mViewModel.getItemCategoryModel() != null
                && mViewModel.getItemCategoryModel().getCategoryType() == PageParams.CATEGORY_TYPE_EPISODE) {
            mViewModel.getContents(mViewModel.getItemCategoryModel());
            mViewModel.getItemCategoryModel().refreshCategory.observe(this, new Observer<ItemCategoryModel>() {
                @Override
                public void onChanged(ItemCategoryModel categoryModel) {
                    mViewModel.setMediaModels(categoryModel.itemDatas.get());
                    for (int i = 0; i < categoryModel.itemDatas.get().size(); i++) {
                        Log.d(TAG, "[Ciel_Debug] onChanged: " + categoryModel.itemDatas.get().get(i).getEpisodeNumber() + ',' + mViewModel.getItemCategoryModel().getEpisodeNumber());
                        if (categoryModel.itemDatas.get().get(i).getEpisodeNumber() == mViewModel.getItemCategoryModel().getEpisodeNumber()) {
                            mViewModel.initEpisodeDetail(categoryModel.itemDatas.get().get(i));
                            break;
                        }
                    }
                    mViewModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
                    initAppbarLayout();
                    initAdapter();
                    initViewPager();
                    initMagicIndicator();
                }
            });
        } else {
            mViewModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
            initAppbarLayout();
            initAdapter();
            initViewPager();
            initMagicIndicator();
        }
    }

    private void initRatingDialog() {
        RatingViewModel ratingViewModel = new RatingViewModel(getApplication(), Injection.provideTmbdRepository());

        RatingDialog ratingDialog = new RatingDialog(DetailActivity.this, ratingViewModel);
        mViewModel.showRatingDialog.observe(this, new Observer<DetailViewModel>() {
            @Override
            public void onChanged(DetailViewModel detailViewModel) {
                if (mViewModel.userRating != null && mViewModel.userRating.get() != null) {
                    Log.d(TAG, "[Ciel_Debug] initRatingDialog: " + mViewModel.userRating.get());
                    ratingViewModel.ratings.set(mViewModel.userRating.get());
                } else {
                    ratingViewModel.ratings.set(null);
                }

                int x = 0;
                int y = 0;
                ratingDialog.showAtLocation(Gravity.CENTER, x, y);
            }
        });
        ratingViewModel.ratingSubmit.observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float rating) {
                mViewModel.rating(rating);
                ratingDialog.dismiss();
            }
        });
        ratingViewModel.removeSubmit.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                mViewModel.removeRating();
                ratingDialog.dismiss();
            }
        });

    }

    private void initAppbarLayout() {
        switch (mViewModel.getItemMediaModel().getItemType()) {
            case PageParams.ITEM_TYPE_MOIVE:
            case PageParams.ITEM_TYPE_MOIVE_HORI:
            case PageParams.ITEM_TYPE_TV_SHOW:
            case PageParams.ITEM_TYPE_TV_SHOW_HORI:
                mViewModel.isHideVote.set(false);
                mMagicIndicator = mViewBinding.layoutMediaTop.indicatorMovieDetail;
                break;
            case PageParams.ITEM_TYPE_SEASONS:
                mViewModel.isHideVote.set(true);
                mMagicIndicator = mViewBinding.layoutMediaTop.indicatorMovieDetail;
                break;
            case PageParams.ITEM_TYPE_EPISODES:
                mViewBinding.layoutEpisodeTop.getRoot().setVisibility(View.VISIBLE);
                mViewModel.isHideVote.set(true);
                mMagicIndicator = mViewBinding.layoutEpisodeTop.indicatorMovieDetail;
                break;
            case PageParams.ITEM_TYPE_PEOPLE:
            case PageParams.ITEM_TYPE_PEOPLE_HORI:
                mViewModel.isHideVote.set(true);
                mMagicIndicator = mViewBinding.layoutPeopleTop.indicatorMovieDetail;
                break;
        }

        mViewBinding.collapsing.setExpandedTitleTextAppearance(R.style.expandedTitle);
        mViewBinding.appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (-verticalOffset > 0) {
                    mAppbarAphla = (float) -verticalOffset / (float) (mViewBinding.appbarlayout.getHeight() -
                            getResources().getDimensionPixelOffset(R.dimen.indicator_height)
                            - getResources().getDimensionPixelOffset(R.dimen.toolbar_height));
                } else {
                    mAppbarAphla = 0;
                }

                refreshToolbarView();
                if (-verticalOffset >= (mViewBinding.appbarlayout.getHeight()
                        - getResources().getDimensionPixelOffset(R.dimen.indicator_height)
                        - getResources().getDimensionPixelOffset(R.dimen.toolbar_height)) * 0.5) {
                    mViewBinding.tvTitle.setText(mViewModel.title.get());
                } else {
                    mViewBinding.tvTitle.setText("");
                }
            }
        });
    }

    private void refreshToolbarView() {
        String aphlaHex = Integer.toHexString((int) (mAppbarAphla * 255));
        if (aphlaHex.length() == 1) {
            aphlaHex = "0" + aphlaHex;
        }
        Drawable toolbarTackground = mViewBinding.toolbar.getBackground();
        if (toolbarTackground != null) {
            toolbarTackground.setAlpha((int) (mAppbarAphla * 255));
        }
        Drawable indicatorBackground = mMagicIndicator.getBackground();
        if (indicatorBackground != null) {
            indicatorBackground.setAlpha((int) (mAppbarAphla * 255));
        }
        Drawable backBackground = mViewBinding.ivBack.getBackground();
        if (backBackground != null) {
            backBackground.setAlpha(255 - (int) (mAppbarAphla * 255));
        }
        Drawable homeBackground = mViewBinding.ivHome.getBackground();
        if (homeBackground != null) {
            homeBackground.setAlpha(255 - (int) (mAppbarAphla * 255));
        }
        Drawable favBackground = mViewBinding.ivFav.getBackground();
        if (favBackground != null) {
            favBackground.setAlpha(255 - (int) (mAppbarAphla * 255));
        }
        Drawable watchlistBackground = mViewBinding.ivWatchlist.getBackground();
        if (watchlistBackground != null) {
            watchlistBackground.setAlpha(255 - (int) (mAppbarAphla * 255));
        }
//        Drawable barDateBackground = mViewBinding.tvBarDate.getBackground();
//        if (barDateBackground != null) {
//            barDateBackground.setAlpha((int) (mAppbarAphla * 255));
//        }
//        mViewBinding.tvBarDate.setTextColor(Color.parseColor("#" + aphlaHex + "FFFFFF"));
//        Drawable[] barDateCompoundDrawables = mViewBinding.tvBarDate.getCompoundDrawables();
//        if (barDateCompoundDrawables != null) {
//            for (int i = 0; i < barDateCompoundDrawables.length; i++) {
//                if (barDateCompoundDrawables[i] != null) {
//                    barDateCompoundDrawables[i].setAlpha((int) (mAppbarAphla * 255));
//                }
//            }
//        }
//        Drawable barRuntimeBackground = mViewBinding.tvBarRuntime.getBackground();
//        if (barRuntimeBackground != null) {
//            barRuntimeBackground.setAlpha((int) (mAppbarAphla * 255));
//        }
//        mViewBinding.tvBarRuntime.setTextColor(Color.parseColor("#" + aphlaHex + "FFFFFF"));
//        Drawable[] barRuntimeCompoundDrawables = mViewBinding.tvBarRuntime.getCompoundDrawables();
//        if (barRuntimeCompoundDrawables != null) {
//            for (int i = 0; i < barRuntimeCompoundDrawables.length; i++) {
//                if (barRuntimeCompoundDrawables[i] != null) {
//                    barRuntimeCompoundDrawables[i].setAlpha((int) (mAppbarAphla * 255));
//                }
//            }
//        }
//        Drawable barLanguageBackground = mViewBinding.tvBarLanguage.getBackground();
//        if (barLanguageBackground != null) {
//            barLanguageBackground.setAlpha((int) (mAppbarAphla * 255));
//        }
//        mViewBinding.tvBarLanguage.setTextColor(Color.parseColor("#" + aphlaHex + "FFFFFF"));
//        Drawable[] barLanguageCompoundDrawables = mViewBinding.tvBarLanguage.getCompoundDrawables();
//        if (barLanguageCompoundDrawables != null) {
//            for (int i = 0; i < barLanguageCompoundDrawables.length; i++) {
//                if (barLanguageCompoundDrawables[i] != null) {
//                    barLanguageCompoundDrawables[i].setAlpha((int) (mAppbarAphla * 255));
//                }
//            }
//        }
    }

    @Override
    public int initContentView() {
        return R.layout.activity_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public DetailViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return new ViewModelProvider(this, factory).get(DetailViewModel.class);
    }

    private void initViewPager() {
        mViewPager = mViewBinding.viewpagerMovieDetail;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
                if (mViewModel.getMediaModels() != null && mViewModel.getMediaModels().size() > position) {
                    mViewModel.initEpisodeDetail(mViewModel.getMediaModels().get(position));
                    initPaltte(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setOffscreenPageLimit(mPageAdapter.getCount());
        mViewPager.setAdapter(mPageAdapter);
        mViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                int position = 0;
                if (mViewModel.getItemMediaModel() != null
                        && mViewModel.getItemMediaModel().getEpisodeNumber() != null
                        && mViewModel.getItemMediaModel().getEpisodeNumber() != null
                        && mViewModel.getMediaModels() != null) {
                    for (int i = 0; i < mViewModel.getMediaModels().size(); i++) {
                        if (mViewModel.getItemMediaModel().getEpisodeNumber().equals(mViewModel.getMediaModels().get(i).getEpisodeNumber())) {
                            position = i;
                            break;
                        }
                    }
                    mViewPager.setCurrentItem(position);
                }
            }
        }, 0);
    }

    private void initMagicIndicator() {
        int normalColor = mViewModel.bodyTextColor.get();
        int selectedColor = mViewModel.titleTextColor.get();
//        int selectedColor = getResources().getColor(R.color.tmdb_secondary_color);
        mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mPageAdapter != null ? mPageAdapter.getCategorys().size() : 0;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setTextAppearance(DetailActivity.this, R.style.sub_title_style);
                simplePagerTitleView.setText(mPageAdapter.getCategorys().get(index));
                simplePagerTitleView.setNormalColor(mViewModel.bodyTextColor.get());
                simplePagerTitleView.setSelectedColor(mViewModel.titleTextColor.get());
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mViewPager != null) {
                            mViewPager.setCurrentItem(index);
                        }
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(mViewModel.titleTextColor.get());
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(mCommonNavigator);
        if (mViewPager != null) {
            ViewPagerHelper.bind(mMagicIndicator, mViewPager);
        }
    }

}
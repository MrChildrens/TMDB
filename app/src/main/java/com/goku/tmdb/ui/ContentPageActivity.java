package com.goku.tmdb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.goku.tmdb.R;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.base.BaseActivity;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.ActivityContentPageBinding;
import com.goku.tmdb.ui.search.ContentPageAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import me.tatarka.bindingcollectionadapter2.BR;

public class ContentPageActivity extends BaseActivity<ActivityContentPageBinding, ContentPageViewModel> {
    private static final String TAG = ContentPageActivity.class.getSimpleName();
    private ViewPager mViewPager;
    private ContentPageAdapter mPageAdapter;
    private MagicIndicator mMagicIndicator;
    private int mCategoryType;

    public static void newInstance(Context context, String category, int categoryType) {
        Intent intent = new Intent();
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_CATEGORY, category);
        bundle.putInt(Constant.KEY_CATEGORY_TYPE, categoryType);
        intent.putExtras(bundle);
        intent.setClass(context, ContentPageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        initAdapter();
        initViewPager();
        initMagicIndicator();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String category = "";
        if (extras != null) {
            mCategoryType = extras.getInt(Constant.KEY_CATEGORY_TYPE);
            category = extras.getString(Constant.KEY_CATEGORY);
        }
        mViewModel.title.set(category);
    }

    @Override
    public int initContentView() {
        return R.layout.activity_content_page;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ContentPageViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return new ViewModelProvider(this, factory).get(ContentPageViewModel.class);
    }

    private void initView() {
        mMagicIndicator = mViewBinding.indicatorContentPage;
        mViewPager = mViewBinding.viewpagerContentPage;
    }

    private void initAdapter() {
        mPageAdapter = new ContentPageAdapter(this, getSupportFragmentManager(), mCategoryType);
    }

    private void initViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setOffscreenPageLimit(mPageAdapter.getCount());
        mViewPager.setAdapter(mPageAdapter);
    }

    private void initMagicIndicator() {
        int normalColor = mViewModel.lightMutedColor.get();
//        int selectedColor = mViewModel.mutedColor.get();
        int selectedColor = getResources().getColor(R.color.tmdb_secondary_color);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mPageAdapter != null ? mPageAdapter.getCategorys().size() : 0;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setTextAppearance(ContentPageActivity.this, R.style.sub_title_style);
                simplePagerTitleView.setText(mPageAdapter.getCategorys().get(index));
                simplePagerTitleView.setNormalColor(normalColor);
                simplePagerTitleView.setSelectedColor(selectedColor);
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
                indicator.setColors(selectedColor);
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        if (mViewPager != null) {
            ViewPagerHelper.bind(mMagicIndicator, mViewPager);
        }
    }
}
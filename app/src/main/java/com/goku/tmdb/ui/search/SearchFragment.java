package com.goku.tmdb.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.goku.tmdb.BR;
import com.goku.tmdb.R;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.base.BaseFragment;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.FragmentSearchBinding;
import com.goku.tmdb.ui.ContentListFragment;
import com.goku.tmdb.ui.ContentListViewModel;
import com.goku.tmdb.ui.TmdbEvent;
import com.goku.tmdb.ui.home.ItemCategoryModel;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel> {
    private static final String TAG = SearchFragment.class.getSimpleName();
    private ViewPager mViewPager;
    private ContentPageAdapter mPageAdapter;
    private MagicIndicator mMagicIndicator;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initAdapter();
        initViewPager();
        initMagicIndicator();
    }

    private void initView() {
        mMagicIndicator = mViewBinding.indicatorSearch;
        mViewPager = mViewBinding.viewpagerSearch;
        mViewBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    List<ContentListFragment> fragments = mPageAdapter.getFragments();
                    for (int i = 0; i < fragments.size(); i++) {
                        ContentListFragment contentListFragment = fragments.get(i);
                        contentListFragment.clear();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mViewBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = mViewModel.searchText.get();
                if (!TextUtils.isEmpty(query)) {
                    ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
                    itemCategoryModel.setCategoryType(PageParams.CATEGORY_TYPE_SEARCH_MOVIE);
                    itemCategoryModel.setSearchQuery(query);
                    mViewModel.getContents(itemCategoryModel);
                    itemCategoryModel.refreshCategory.observe(getViewLifecycleOwner(), new Observer<>() {
                        @Override
                        public void onChanged(ItemCategoryModel itemCategoryModel) {
                            for (int i = 0; i < mPageAdapter.getFragments().size(); i++) {
                                ContentListFragment fragment = mPageAdapter.getFragments().get(i);
                                AppViewModelFactory factory = AppViewModelFactory.getInstance(TmdbApplication.getInstance());
                                if (!fragment.isDetached()) {
                                    ContentListViewModel searchViewModel = new ViewModelProvider(fragment, factory).get(ContentListViewModel.class);
                                    searchViewModel.getItemCategoryModel().setSearchQuery(query);
                                    fragment.clear();
                                    fragment.getDatas(searchViewModel.getItemCategoryModel());
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private void initAdapter() {
        mPageAdapter = new ContentPageAdapter(getContext(), getFragmentManager(), PageParams.PAGE_TYPE_SEARCH);
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
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mPageAdapter != null ? mPageAdapter.getCategorys().size() : 0;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setTextAppearance(getContext(), R.style.sub_title_style);
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


    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_search;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public SearchViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(TmdbApplication.getInstance());
        return new ViewModelProvider(this, factory).get(SearchViewModel.class);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (mPageAdapter != null && mPageAdapter.getFragments() != null) {
                mViewModel.searchText.set("");
                for (int i = 0; i < mPageAdapter.getFragments().size(); i++) {
                    ContentListFragment fragment = mPageAdapter.getFragments().get(i);
                    if (fragment != null) {
                        fragment.clear();
                    }
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTmdbEvent(TmdbEvent event) {
        if (event.isRefreshColor()) {
            mViewModel.initColor();
            mViewBinding.setViewModel(mViewModel);
            initMagicIndicator();

            List<ContentListFragment> fragments = mPageAdapter.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                ContentListFragment contentListFragment = fragments.get(i);
                ContentListViewModel detailViewModel = new ViewModelProvider(contentListFragment,
                        AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(ContentListViewModel.class);
                detailViewModel.dominantColor.set(mViewModel.dominantColor.get());
                detailViewModel.mutedColor.set(mViewModel.mutedColor.get());
                detailViewModel.lightMutedColor.set(mViewModel.lightMutedColor.get());
                detailViewModel.isDark.set(mViewModel.isDark.get());

                detailViewModel.getItemCategoryModel().statusModel.dominantColor.set(mViewModel.dominantColor.get());
                detailViewModel.getItemCategoryModel().statusModel.mutedColor.set(mViewModel.mutedColor.get());
                detailViewModel.getItemCategoryModel().statusModel.lightMutedColor.set(mViewModel.lightMutedColor.get());
                detailViewModel.getItemCategoryModel().statusModel.isDark.set(mViewModel.isDark.get());
            }

        }
    }
}
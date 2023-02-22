package com.goku.tmdb.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goku.tmdb.R;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseFragment;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.FragmentContentListBinding;
import com.goku.tmdb.ui.detail.DetailActivity;
import com.goku.tmdb.ui.detail.DetailViewModel;
import com.goku.tmdb.ui.home.ItemCategoryModel;
import com.goku.tmdb.ui.home.ItemMediaModel;
import com.goku.tmdb.ui.search.SearchFragment;
import com.goku.tmdb.ui.search.SearchViewModel;
import com.goku.tmdb.ui.view.HorizontalRecyclerView;

import me.tatarka.bindingcollectionadapter2.BR;

public class ContentListFragment extends BaseFragment<FragmentContentListBinding, ContentListViewModel> {
    private static final String TAG = ContentListFragment.class.getSimpleName();
    private HorizontalRecyclerView mRecyclerViewContentList;
    private ContentListAdapter mAdapterContentList;
    private boolean mIsReuqestFinish = false;
    private int mCategoryType;
    private int mPagePosition;

    public static ContentListFragment newInstance(int categoryType, int position) {
        return newInstance(null, categoryType, position);
    }

    public static ContentListFragment newInstance(ItemMediaModel mediaModel, int categoryType, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.KEY_CATEGORY_TYPE, categoryType);
        bundle.putInt(Constant.KEY_POSITION, position);
        if (mediaModel != null) {
            bundle.putSerializable(Constant.KEY_ITEM_MODEL, mediaModel);
        }

        ContentListFragment fragment = new ContentListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_content_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ContentListViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(TmdbApplication.getInstance());
        return new ViewModelProvider(this, factory).get(ContentListViewModel.class);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        if (mPagePosition == 0
                && mCategoryType != PageParams.CATEGORY_TYPE_SEARCH_MOVIE
                && mCategoryType != PageParams.CATEGORY_TYPE_SEARCH_TV_SHOW
                && mCategoryType != PageParams.CATEGORY_TYPE_SEARCH_PERSON) {
            //如果Fragment在ViewPager的位置是0，且Fragment不属于搜索页面
            //直接获取数据
            getDatas(mViewModel.getItemCategoryModel());
        }
    }

    private void initData() {
        ItemMediaModel mediaModel = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            mediaModel = (ItemMediaModel) bundle.getSerializable(Constant.KEY_ITEM_MODEL);
            mCategoryType = bundle.getInt(Constant.KEY_CATEGORY_TYPE);
            mPagePosition = bundle.getInt(Constant.KEY_POSITION);
        }
        Log.d(TAG, "[Ciel_Debug] initData: mCategoryType: " + mCategoryType);
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
        if (mediaModel != null) {
            mViewModel.setItemMediaModel(mediaModel);
            itemCategoryModel.setId(mediaModel.getId());
            if (mCategoryType == PageParams.CATEGORY_TYPE_SEASONS) {
                itemCategoryModel.setTvId(mediaModel.getId());
                itemCategoryModel.setTvTitle(mediaModel.getTitle());
                itemCategoryModel.setPosterPath(mediaModel.getPosterPath());
                itemCategoryModel.setBackdropPath(mediaModel.getBackdropPath());
                if (mediaModel.getEpisodeCount() != null) {
                    itemCategoryModel.setEpisodeCount(mediaModel.getEpisodeCount());
                }
                if (mediaModel.getSeasonNumber() != null) {
                    itemCategoryModel.setSeasonNumber(mediaModel.getSeasonNumber());
                }
                Log.d(TAG, "[Ciel_Debug] initData: getSeasonNumber: " + mediaModel.getSeasonNumber());
            }
            if (mCategoryType == PageParams.CATEGORY_TYPE_EPISODES
                    || mCategoryType == PageParams.CATEGORY_TYPE_CREDITS_SEASONS_CAST) {
                itemCategoryModel.setTvId(mediaModel.getTvId());
                itemCategoryModel.setTvTitle(mediaModel.getTvTitle());
                itemCategoryModel.setPosterPath(mediaModel.getPosterPath());
                itemCategoryModel.setBackdropPath(mediaModel.getBackdropPath());
                if (mediaModel.getSeasonNumber() != null) {
                    itemCategoryModel.setSeasonNumber(mediaModel.getSeasonNumber());
                }
            }

        }
        //搜索页面初始不显示Loading
        if (mCategoryType == PageParams.CATEGORY_TYPE_SEARCH_MOVIE
                || mCategoryType == PageParams.CATEGORY_TYPE_SEARCH_TV_SHOW
                || mCategoryType == PageParams.CATEGORY_TYPE_SEARCH_PERSON) {
            itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_WAIT_SEARCH);
        }

        itemCategoryModel.setCategoryType(mCategoryType);

        mViewModel.setItemCategoryModel(itemCategoryModel);
        mViewBinding.setCategoryModel(itemCategoryModel);
    }

    private void initView() {
        mRecyclerViewContentList = mViewBinding.recyclerviewMedia;
        //根据类型初始化
        switch (mCategoryType) {
            case PageParams.CATEGORY_TYPE_SEASONS:
                mRecyclerViewContentList.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerViewContentList.setLoadingMoreEnabled(false);
                mViewModel.isShowToolbar.set(false);
                break;
            case PageParams.CATEGORY_TYPE_PERSON_HORI:
            case PageParams.CATEGORY_TYPE_CREDITS_MOVIE_CAST:
            case PageParams.CATEGORY_TYPE_CREDITS_MOVIE_CREW:
            case PageParams.CATEGORY_TYPE_CREDITS_TV_CAST:
            case PageParams.CATEGORY_TYPE_CREDITS_TV_CREW:
            case PageParams.CATEGORY_TYPE_CREDITS_SEASONS_CAST:
            case PageParams.CATEGORY_TYPE_SEARCH_PERSON:
                mRecyclerViewContentList.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerViewContentList.setLoadingMoreEnabled(false);
                break;
            case PageParams.CATEGORY_TYPE_MOVIE_RECOMMENDATIONS:
            case PageParams.CATEGORY_TYPE_TV_RECOMMENDATIONS:
            case PageParams.CATEGORY_TYPE_MOVIE_SIMILAR:
            case PageParams.CATEGORY_TYPE_TV_SIMILAR:
            case PageParams.CATEGORY_TYPE_SEARCH_MOVIE:
            case PageParams.CATEGORY_TYPE_SEARCH_TV_SHOW:
            case PageParams.CATEGORY_TYPE_FAVORITE_MOVIE:
            case PageParams.CATEGORY_TYPE_FAVORITE_TV:
            case PageParams.CATEGORY_TYPE_WATCHLIST_MOVIE:
            case PageParams.CATEGORY_TYPE_WATCHLIST_TV:
            case PageParams.CATEGORY_TYPE_RATING_MOVIE:
            case PageParams.CATEGORY_TYPE_RATING_TV:
                if (Utils.isGridMode()) {
                    mRecyclerViewContentList.setLayoutManager(new GridLayoutManager(getContext(), Constant.GIRD_LINE_COUNT));
                } else {
                    mRecyclerViewContentList.setLayoutManager(new LinearLayoutManager(getContext()));
                }
                mRecyclerViewContentList.setLoadingMoreEnabled(true);
                break;
            case PageParams.CATEGORY_TYPE_MOVIE_PEOPLE:
            case PageParams.CATEGORY_TYPE_TV_PEOPLE:
                if (Utils.isGridMode()) {
                    mRecyclerViewContentList.setLayoutManager(new GridLayoutManager(getContext(), Constant.GIRD_LINE_COUNT));
                } else {
                    mRecyclerViewContentList.setLayoutManager(new LinearLayoutManager(getContext()));
                }
                mRecyclerViewContentList.setLoadingMoreEnabled(false);
                break;
            case PageParams.CATEGORY_TYPE_EPISODES:
                mViewModel.isShowToolbar.set(false);
                mRecyclerViewContentList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                mRecyclerViewContentList.setLoadingMoreEnabled(false);
                break;
            case PageParams.CATEGORY_TYPE_REVIRES_MOVIE:
            case PageParams.CATEGORY_TYPE_REVIRES_TV:
                mRecyclerViewContentList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                mRecyclerViewContentList.setLoadingMoreEnabled(true);
                break;
        }
        mAdapterContentList = new ContentListAdapter(getContext(), Utils.isGridMode());
        mRecyclerViewContentList.setAdapter(mAdapterContentList);

        //获取父Activity->DetailActivity初始化的颜色
        if (getActivity() instanceof DetailActivity) {
            DetailViewModel detailViewModel = new ViewModelProvider(getActivity(),
                    AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(DetailViewModel.class);
            mViewModel.dominantColor.set(detailViewModel.dominantColor.get());
            mViewModel.mutedColor.set(detailViewModel.mutedColor.get());
            mViewModel.lightMutedColor.set(detailViewModel.lightMutedColor.get());
            mViewModel.isDark.set(detailViewModel.isDark.get());
            mViewModel.isGridMode.set(Utils.isGridMode());
        }

        //监听父Activity->ContentPageActivity点击切换表格模式按钮
        if (getActivity() instanceof ContentPageActivity) {
            ContentPageViewModel contentPageViewModel = new ViewModelProvider(getActivity(),
                    AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(ContentPageViewModel.class);
            contentPageViewModel.changeGridMode.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isGridMode) {
                    changeGridMode(isGridMode);
                }
            });
        }

        //监听父Fragment->SearchFragment点击切换表格模式按钮
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fl_fragment_container);
        if (fragment instanceof SearchFragment) {
            SearchViewModel searchViewModel = new ViewModelProvider(fragment,
                    AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(SearchViewModel.class);
            searchViewModel.changeGridMode.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isGridMode) {
                    changeGridMode(isGridMode);
                }
            });
        }
    }

    private void changeGridMode(Boolean isGridMode) {
        if (mCategoryType != PageParams.CATEGORY_TYPE_PERSON_HORI
                && mCategoryType != PageParams.CATEGORY_TYPE_SEARCH_PERSON) {
            mAdapterContentList.setGridMode(isGridMode);
            if (isGridMode) {
                mRecyclerViewContentList.setLayoutManager(new GridLayoutManager(getContext(), Constant.GIRD_LINE_COUNT));
            } else {
                mRecyclerViewContentList.setLayoutManager(new LinearLayoutManager(getContext()));
            }
            mRecyclerViewContentList.setAdapter(mAdapterContentList);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mPagePosition != 0
                && mCategoryType != PageParams.CATEGORY_TYPE_SEARCH_MOVIE
                && mCategoryType != PageParams.CATEGORY_TYPE_SEARCH_TV_SHOW
                && mCategoryType != PageParams.CATEGORY_TYPE_SEARCH_PERSON) {
            //如果Fragment不在ViewPager的位置0，且Fragment不属于搜索页面
            //isVisibleToUser为true的时候获取数据
            if (isVisibleToUser && !mIsReuqestFinish) {
                getDatas(mViewModel.getItemCategoryModel());
            }
        }
    }

    public void getDatas(ItemCategoryModel itemCategoryModel) {
        if (mViewModel == null || itemCategoryModel == null || mRecyclerViewContentList == null || mAdapterContentList == null) {
            Log.d(TAG, "[Ciel_Debug] getDatas: mViewModel: " + mViewModel);
            Log.d(TAG, "[Ciel_Debug] getDatas: itemCategoryModel: " + itemCategoryModel);
            Log.d(TAG, "[Ciel_Debug] getDatas: mRecyclerViewMediaList: " + mRecyclerViewContentList);
            Log.d(TAG, "[Ciel_Debug] getDatas: mAdapterMediaList: " + mAdapterContentList);
            return;
        }
        itemCategoryModel.setPage(1);
        itemCategoryModel.setTotalPage(1);
        itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
        if (mCategoryType != PageParams.CATEGORY_TYPE_SEASONS && mCategoryType != PageParams.CATEGORY_TYPE_EPISODES) {
            mViewModel.isShowToolbar.set(true);
        }
        mRecyclerViewContentList.setLoadingMoreEnabled(true);
        mViewModel.getContents(itemCategoryModel);

        itemCategoryModel.refreshCategory.observe(getViewLifecycleOwner(), new Observer<>() {
            @Override
            public void onChanged(ItemCategoryModel categoryModel) {
                mIsReuqestFinish = true;
                if (getActivity() instanceof DetailActivity) {
                    DetailViewModel detailViewModel = new ViewModelProvider(getActivity(),
                            AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(DetailViewModel.class);
                    for (int i = 0; i < categoryModel.itemDatas.get().size(); i++) {
                        Utils.initMediaModelPalette(detailViewModel.dominantColor.get(), detailViewModel.mutedColor.get(),
                                detailViewModel.lightMutedColor.get(), detailViewModel.isDark.get(), categoryModel.itemDatas.get().get(i));
                    }
                    itemCategoryModel.statusModel.dominantColor.set(detailViewModel.dominantColor.get());
                    itemCategoryModel.statusModel.mutedColor.set(detailViewModel.mutedColor.get());
                    itemCategoryModel.statusModel.lightMutedColor.set(detailViewModel.lightMutedColor.get());
                    itemCategoryModel.statusModel.isDark.set(detailViewModel.isDark.get());
                    mViewBinding.setViewModel(mViewModel);
                }
                if (getActivity() instanceof ContentPageActivity) {
                    ContentPageViewModel contentPageViewModel = new ViewModelProvider(getActivity(),
                            AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(ContentPageViewModel.class);
                    for (int i = 0; i < categoryModel.itemDatas.get().size(); i++) {
                        Utils.initMediaModelPalette(contentPageViewModel.dominantColor.get(), contentPageViewModel.mutedColor.get(),
                                contentPageViewModel.lightMutedColor.get(), contentPageViewModel.isDark.get(), categoryModel.itemDatas.get().get(i));
                    }
                }
                Fragment fragment = getFragmentManager().findFragmentById(R.id.fl_fragment_container);
                if (fragment instanceof SearchFragment) {
                    SearchViewModel searchViewModel = new ViewModelProvider(fragment,
                            AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(SearchViewModel.class);
                    for (int i = 0; i < categoryModel.itemDatas.get().size(); i++) {
                        Utils.initMediaModelPalette(searchViewModel.dominantColor.get(), searchViewModel.mutedColor.get(),
                                searchViewModel.lightMutedColor.get(), searchViewModel.isDark.get(), categoryModel.itemDatas.get().get(i));
                    }
                }

                mViewBinding.setViewModel(mViewModel);
                mViewBinding.setCategoryModel(categoryModel);

                mAdapterContentList.setDatas(categoryModel.itemDatas.get());
                mRecyclerViewContentList.loadmoreComplete();
            }
        });
        mRecyclerViewContentList.setOnLoadingListener(new HorizontalRecyclerView.OnLoadingListener() {
            @Override
            public void onLoadMore() {
                if (itemCategoryModel.getPage() < itemCategoryModel.getTotalPage()) {
                    mViewModel.getNextPageContents(itemCategoryModel);
                } else {
                    mRecyclerViewContentList.setLoadingMoreEnabled(false);
                }
            }
        });
    }

    public void clear() {
        mViewModel.getItemCategoryModel().statusModel.dataStatus.set(Constant.DATA_STATUS_WAIT_SEARCH);
        mViewModel.isShowToolbar.set(false);
        if (mAdapterContentList != null) {
            mViewModel.getItemCategoryModel().itemDatas.get().clear();
            mAdapterContentList.getDatas().clear();
            mAdapterContentList.notifyDataSetChanged();
        }
    }

    public void setViewModel(ContentListViewModel contentListViewModel) {
        mViewBinding.setViewModel(contentListViewModel);
    }
}

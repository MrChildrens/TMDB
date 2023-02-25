package com.goku.tmdb.ui.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goku.tmdb.R;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseFragment;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.FragmentDetailAboutBinding;
import com.goku.tmdb.ui.home.CategoryAdapter;
import com.goku.tmdb.ui.home.ItemCategoryModel;
import com.goku.tmdb.ui.home.ItemMediaModel;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

public class DetailAboutFragment extends BaseFragment<FragmentDetailAboutBinding, DetailViewModel> {
    private static final String TAG = DetailAboutFragment.class.getSimpleName();
    private RecyclerView mRecyclerViewAbout;
    private CategoryAdapter mAdapterAbout;
    private boolean mIsReuqestFinish = false;

    public static DetailAboutFragment newInstance(ItemMediaModel mediaModel) {
        DetailAboutFragment fragment = new DetailAboutFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_ITEM_MODEL, mediaModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_detail_about;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public DetailViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(TmdbApplication.getInstance());
        return new ViewModelProvider(this, factory).get(DetailViewModel.class);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "[Ciel_Debug] setUserVisibleHint: " + isVisibleToUser);
        if (isVisibleToUser && !mIsReuqestFinish) {
            initData();
            getData();
        }
    }

    private void initData() {
        if (mViewModel == null) {
            mViewModel = initViewModel();
        }

        Bundle arguments = getArguments();
        if (arguments != null) {
            ItemMediaModel mediaModel = (ItemMediaModel) arguments.getSerializable(Constant.KEY_ITEM_MODEL);
            if (mediaModel != null) {
                mViewModel.setItemMediaModel(mediaModel);
            }
        }
    }

    private void initView() {
        mRecyclerViewAbout = mViewBinding.recyclerviewAbout;
        mRecyclerViewAbout.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapterAbout = new CategoryAdapter(getContext(), getViewLifecycleOwner(), mViewModel);
        mRecyclerViewAbout.setAdapter(mAdapterAbout);
    }

    private void getData() {
        mViewModel.getDetail(mViewModel.getItemMediaModel());
        mViewModel.refreshAbout.observe(this, new Observer<>() {
            @Override
            public void onChanged(ObservableField<List<ItemCategoryModel>> categoryModels) {
                mIsReuqestFinish = true;
                updateParentActivityModel();
                updateItemColor(categoryModels);

                mAdapterAbout.setDatas(categoryModels.get());
            }
        });
    }

    private void updateParentActivityModel() {
        DetailViewModel detailViewModel = new ViewModelProvider(getActivity(),
                AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(DetailViewModel.class);
        if (mViewModel.getItemMediaModel().getItemType() == PageParams.ITEM_TYPE_MOIVE
                || mViewModel.getItemMediaModel().getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                || mViewModel.getItemMediaModel().getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI
                || mViewModel.getItemMediaModel().getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
            detailViewModel.smallTitles.set(mViewModel.smallTitles.get());
            detailViewModel.isFav.set(mViewModel.isFav.get());
            detailViewModel.isBookmark.set(mViewModel.isBookmark.get());
            detailViewModel.isRating.set(mViewModel.isRating.get());
            detailViewModel.userRating.set(mViewModel.userRating.get());
            detailViewModel.isShowFav.set(mViewModel.isShowFav.get());
            detailViewModel.isShowBookmark.set(mViewModel.isShowBookmark.get());
            detailViewModel.isShowRating.set(mViewModel.isShowRating.get());
        }
        if (mViewModel.getItemMediaModel().getItemType() == PageParams.ITEM_TYPE_SEASONS) {
            detailViewModel.voteAverage.set(mViewModel.voteAverage.get());
            detailViewModel.voteCount.set(mViewModel.voteCount.get());
        }
        if (mViewModel.getItemMediaModel().getItemType() == PageParams.ITEM_TYPE_PEOPLE
                || mViewModel.getItemMediaModel().getItemType() == PageParams.ITEM_TYPE_PEOPLE_HORI) {
            detailViewModel.subTitles.set(mViewModel.subTitles.get());
        }
        mViewBinding.setViewModel(mViewModel);
    }

    private void updateItemColor(ObservableField<List<ItemCategoryModel>> categoryModels) {
        DetailViewModel detailViewModel = new ViewModelProvider(getActivity(),
                AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(DetailViewModel.class);
        for (int i = 0; i < categoryModels.get().size(); i++) {
            List<ItemMediaModel> mediaModels = categoryModels.get().get(i).itemDatas.get();
            Utils.initCategoryModelPalette(detailViewModel.dominantColor.get(), detailViewModel.titleTextColor.get(),
                    detailViewModel.bodyTextColor.get(), detailViewModel.isDark.get(),
                    categoryModels.get().get(i));

            if (mediaModels != null && mediaModels.size() > 0) {
                for (int j = 0; j < mediaModels.size(); j++) {
                    Utils.initMediaModelPalette(detailViewModel.dominantColor.get(), detailViewModel.titleTextColor.get(),
                            detailViewModel.bodyTextColor.get(), detailViewModel.isDark.get(), mediaModels.get(j));
                }
            }
        }

        mViewBinding.setViewModel(mViewModel);
    }


}
package com.goku.tmdb.ui.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goku.tmdb.BR;
import com.goku.tmdb.R;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseFragment;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.FragmentHomeBinding;
import com.goku.tmdb.ui.TmdbEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    private static final String TAG = "HomeFragment";

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = mViewBinding.recyclerviewHome;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CategoryAdapter(getContext(), getViewLifecycleOwner(), mViewModel);
        mRecyclerView.setAdapter(mAdapter);

        mViewModel.refreshCategory.observe(getViewLifecycleOwner(), new Observer<>() {
            @Override
            public void onChanged(List<ItemCategoryModel> mutiItemCategoryModels) {
                mAdapter.setDatas(mutiItemCategoryModels);
                mAdapter.notifyDataSetChanged();
            }
        });
        mViewModel.init();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public HomeViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(TmdbApplication.getInstance());
        return new ViewModelProvider(this, factory).get(HomeViewModel.class);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "[Ciel_Debug] onConfigurationChanged: " + newConfig);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTmdbEvent(TmdbEvent event) {
        if (event.isRefreshColor()) {
            mViewModel.initColor();
            mViewBinding.setViewModel(mViewModel);
            List<ItemCategoryModel> categoryModels = mViewModel.getData();
            for (int i = 0; i < categoryModels.size(); i++) {
                List<ItemMediaModel> mediaModels = categoryModels.get(i).itemDatas.get();
                Utils.initCategoryModelPalette(mViewModel.dominantColor.get(), mViewModel.titleTextColor.get(),
                        mViewModel.bodyTextColor.get(), mViewModel.isDark.get(),
                        categoryModels.get(i));

                if (mediaModels != null && mediaModels.size() > 0) {
                    for (int j = 0; j < mediaModels.size(); j++) {
                        Utils.initMediaModelPalette(mViewModel.dominantColor.get(), mViewModel.titleTextColor.get(),
                                mViewModel.bodyTextColor.get(), mViewModel.isDark.get(), mediaModels.get(j));
                    }
                }
            }
        }
    }
}
package com.goku.tmdb.ui.settings;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.goku.tmdb.BR;
import com.goku.tmdb.R;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.base.BaseFragment;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.FragmentSettingsBinding;
import com.goku.tmdb.ui.TmdbEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SettingsFragment extends BaseFragment<FragmentSettingsBinding, SettingsViewModel> {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
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
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_settings;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public SettingsViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(TmdbApplication.getInstance());
        return new ViewModelProvider(this, factory).get(SettingsViewModel.class);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTmdbEvent(TmdbEvent event) {
        if (event.isRefreshColor()) {
            mViewModel.initColor();
            mViewBinding.setViewModel(mViewModel);
        }
    }
}
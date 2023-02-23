package com.goku.tmdb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.goku.tmdb.BR;
import com.goku.tmdb.R;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseActivity;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.ActivityMainBinding;
import com.goku.tmdb.ui.home.HomeFragment;
import com.goku.tmdb.ui.search.SearchFragment;
import com.goku.tmdb.ui.settings.SettingsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private static final String TAG = "Ciel_MainActivity";
    private final List<Fragment> mFragments = new ArrayList<>();


    public static void newInstance(Context context) {
        Intent intent = new Intent();
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
//    private ActionBar mActionBar;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(mViewModel.dominantColor.get());
        mViewModel.getGetConfigFinish().observe(this, new Observer<>() {
            @Override
            public void onChanged(Object o) {
                initBottomNavigation();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initBottomNavigation() {
        if (!isFragmentExist(HomeFragment.class)) {
            addFragment(R.id.fl_fragment_container, HomeFragment.newInstance());
        }
        mViewBinding.rgNavigation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        if (isFragmentExist(HomeFragment.class)) {
                            showFragment(HomeFragment.class);
                        } else {
                            addFragment(R.id.fl_fragment_container, HomeFragment.newInstance());
                        }
                        break;
                    case R.id.rb_search:
//                        mActionBar.setTitle(R.string.movie);
                        if (isFragmentExist(SearchFragment.class)) {
                            showFragment(SearchFragment.class);
                        } else {
                            addFragment(R.id.fl_fragment_container, SearchFragment.newInstance());
                        }
                        break;
                    case R.id.rb_settings:
//                        mActionBar.setTitle(R.string.tv_show);
                        if (isFragmentExist(SettingsFragment.class)) {
                            showFragment(SettingsFragment.class);
                        } else {
                            addFragment(R.id.fl_fragment_container, SettingsFragment.newInstance());
                        }
                        break;

                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    private boolean isFragmentExist(Class className) {
        for (int i = 0; i < mFragments.size(); i++) {
            if (mFragments.get(i).getClass().getSimpleName().equals(className.getSimpleName())) {
                return true;
            }
        }
        return false;
    }

    private void addFragment(int containerId, Fragment targetFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(containerId, targetFragment).commit();
        mFragments.add(targetFragment);
        showFragment(targetFragment.getClass());
    }

    private void showFragment(Class className) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            if (mFragments.get(i).getClass().getSimpleName().equals(className.getSimpleName())) {
                fragmentTransaction.show(mFragments.get(i));
            } else {
                fragmentTransaction.hide(mFragments.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTmdbEvent(TmdbEvent event) {
        if (event.isRefreshColor()) {
            mViewModel.initColor();

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (!isNightMode()) {
//            if (!Utils.isNightMode()) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏文字颜色为暗色
            } else {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
            window.setStatusBarColor(mViewModel.dominantColor.get());

            mViewBinding.setViewModel(mViewModel);
        }
    }
}
package com.goku.tmdb.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.goku.tmdb.app.MultiLanguageUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.Map;

public abstract class BaseActivity<T extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity {

    protected T mViewBinding;
    protected VM mViewModel;
    private int mViewModelId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isNightMode()) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏文字颜色为暗色
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
        initViewDataBinding();
        mViewModel.getStartActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {
                Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
                Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
                startActivity(clz, bundle);
            }
        });
        mViewModel.getFinishEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    private void initViewDataBinding() {
        mViewBinding = DataBindingUtil.setContentView(this, initContentView());
        mViewModelId = initVariableId();
        mViewModel = initViewModel();
        mViewBinding.setVariable(mViewModelId, mViewModel);

        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(mViewModel);
        //注入RxLifecycle生命周期
        mViewModel.injectLifecycleProvider(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mViewBinding != null){
            mViewBinding.unbind();
        }
    }

    /**
     * /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView();

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public abstract VM initViewModel();

    protected boolean isNightMode() {
        int curNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return curNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        MultiLanguageUtil.attachBaseContext(newBase.getApplicationContext());
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }
}

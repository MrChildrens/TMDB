package com.goku.tmdb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.goku.tmdb.R;
import com.goku.tmdb.base.BaseActivity;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.ActivityLoginBinding;

import me.tatarka.bindingcollectionadapter2.BR;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    private static final String TAG = "LoginActivity";

    public static void newInstance(Context context) {
        Intent intent = new Intent();
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(mViewModel.dominantColor.get());
        mViewBinding.username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mViewBinding.username.setBackgroundResource(R.drawable.login_et_bg_focused);
                } else {
                    mViewBinding.username.setBackgroundResource(R.drawable.login_et_bg);
                }
            }
        });
        mViewBinding.password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mViewBinding.password.setBackgroundResource(R.drawable.login_et_bg_focused);
                } else {
                    mViewBinding.password.setBackgroundResource(R.drawable.login_et_bg);
                }
            }
        });
        mViewBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.newInstance(LoginActivity.this, "https://www.themoviedb.org/signup");
            }
        });
    }

    @Override
    public int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return new ViewModelProvider(this, factory).get(LoginViewModel.class);
    }
}
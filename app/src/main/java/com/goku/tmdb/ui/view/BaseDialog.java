package com.goku.tmdb.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

import com.goku.tmdb.R;
import com.goku.tmdb.base.BaseViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;


public abstract class BaseDialog<V extends ViewDataBinding, VM extends BaseViewModel> extends Dialog {

    protected Context mContext = null;
    protected V mViewDataBinding = null;
    protected VM mViewModel;
    private final int mViewModelId;
    protected LifecycleOwner mLifecycleOwner;

    protected BaseDialog(Context context, VM viewModel) {
        this(context, R.style.dialog, viewModel);
    }

    protected BaseDialog(Context context, int themeResId, VM viewModel) {
        super(context, themeResId);
        mViewModelId = initVariableId();
        mViewModel = viewModel;
        //region Added by ciel. If use context.getApplicationContext(), virtual keyboard can't show.
        this.mContext = context;
        //endregion
        if (context instanceof LifecycleProvider) {
            mViewModel.injectLifecycleProvider((LifecycleProvider) context);
        }
        mViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), getContentViewId(), null, false);
        setContentView(mViewDataBinding.getRoot());
        init();
        mViewDataBinding.setVariable(mViewModelId, mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getWindow() != null) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            if (getWidthId() > 0) {
                params.width = mContext.getResources().getDimensionPixelOffset(getWidthId());
            } else if (getWidthId() == WindowManager.LayoutParams.WRAP_CONTENT) {
                params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            } else if (getWidthId() == WindowManager.LayoutParams.MATCH_PARENT) {
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
            }
            if (getHeightId() > 0) {
                params.height = mContext.getResources().getDimensionPixelOffset(getHeightId());
            } else if (getHeightId() == WindowManager.LayoutParams.WRAP_CONTENT) {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else if (getHeightId() == WindowManager.LayoutParams.MATCH_PARENT) {
                params.height = WindowManager.LayoutParams.MATCH_PARENT;
            }
            getWindow().setAttributes(params);
        }
    }

    public void showAtLocation(int gravity, int x, int y) {
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = gravity;
        layoutParams.x = x;
        layoutParams.y = y;
        window.setAttributes(layoutParams);
        super.show();
    }

    protected abstract int getHeightId();

    protected abstract int getWidthId();

    protected abstract void init();

    protected abstract int getContentViewId();

    protected abstract int initVariableId();

}

package com.goku.tmdb.ui.view;

import android.content.Context;
import android.view.ViewGroup;

import com.goku.tmdb.R;
import com.goku.tmdb.databinding.DialogFilterBinding;

import me.tatarka.bindingcollectionadapter2.BR;

public class FliterDialog extends BaseDialog<DialogFilterBinding, FliterViewModel>{
    protected FliterDialog(Context context, FliterViewModel viewModel) {
        super(context, viewModel);
    }

    protected FliterDialog(Context context, int themeResId, FliterViewModel viewModel) {
        super(context, themeResId, viewModel);
    }

    @Override
    protected int getHeightId() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected int getWidthId() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_filter;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}

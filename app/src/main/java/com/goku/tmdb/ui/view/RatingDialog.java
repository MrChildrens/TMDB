package com.goku.tmdb.ui.view;

import android.content.Context;
import android.view.ViewGroup;

import com.goku.tmdb.R;
import com.goku.tmdb.databinding.DialogRatingBinding;

import me.tatarka.bindingcollectionadapter2.BR;

public class RatingDialog extends BaseDialog<DialogRatingBinding, RatingViewModel> {

    public RatingDialog(Context context, RatingViewModel viewModel) {
        super(context, viewModel);
    }

    public RatingDialog(Context context, int themeResId, RatingViewModel viewModel) {
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
        return R.layout.dialog_rating;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }
}

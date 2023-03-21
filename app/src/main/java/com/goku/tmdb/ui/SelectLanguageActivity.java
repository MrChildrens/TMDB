package com.goku.tmdb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goku.tmdb.R;
import com.goku.tmdb.base.BaseActivity;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.ActivitySelectLanguageBinding;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

public class SelectLanguageActivity extends BaseActivity<ActivitySelectLanguageBinding, SelectLanguageViewModel> {

    private RecyclerView mRecyclerSelectLanguae;
    private SelectLanguageAdapter mAdapterSelectLanguae;

    public static void newInstance(Context context) {
        Intent intent = new Intent();
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        intent.setClass(context, SelectLanguageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(mViewModel.dominantColor.get());
        mRecyclerSelectLanguae = mViewBinding.recyclerviewSelectLangugae;
        mRecyclerSelectLanguae.setLayoutManager(new LinearLayoutManager(this));

        mAdapterSelectLanguae = new SelectLanguageAdapter(this);
        mRecyclerSelectLanguae.setAdapter(mAdapterSelectLanguae);

        mViewModel.refresh.observe(this, new Observer<List<ItemSelectLanguageModel>>() {
            @Override
            public void onChanged(List<ItemSelectLanguageModel> itemSelectLanguageModels) {
                mAdapterSelectLanguae.setDatas(itemSelectLanguageModels);
                mAdapterSelectLanguae.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int initContentView() {
        return R.layout.activity_select_language;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public SelectLanguageViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return new ViewModelProvider(this, factory).get(SelectLanguageViewModel.class);
    }
}
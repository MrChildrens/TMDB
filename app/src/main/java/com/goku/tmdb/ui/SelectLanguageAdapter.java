package com.goku.tmdb.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.goku.tmdb.R;
import com.goku.tmdb.databinding.ItemSelectedLanguageBinding;

import java.util.ArrayList;
import java.util.List;

public class SelectLanguageAdapter extends RecyclerView.Adapter {
    private static final String TAG = SelectLanguageAdapter.class.getSimpleName();
    private List<ItemSelectLanguageModel> mDatas = new ArrayList<>();
    private Context mContext;

    public SelectLanguageAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SelectLanguageHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_selected_language, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SelectLanguageHolder selectLanguageHolder = (SelectLanguageHolder) holder;
        ItemSelectLanguageModel model = mDatas.get(position);
        SelectLanguageHolder mediaHolder = (SelectLanguageHolder) holder;
        mediaHolder.mBinding.setVariable(BR.viewModel, model);

        selectLanguageHolder.mBinding.rbSelectLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (ItemSelectLanguageModel data : mDatas) {
                        data.isSelected.set(false);
                    }
                    model.isSelected.set(true);
                } else {
                    model.isSelected.set(false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private static class SelectLanguageHolder extends RecyclerView.ViewHolder {

        ItemSelectedLanguageBinding mBinding;

        public SelectLanguageHolder(@NonNull ItemSelectedLanguageBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public List<ItemSelectLanguageModel> getDatas() {
        return mDatas;
    }

    public void setDatas(List<ItemSelectLanguageModel> datas) {
        mDatas = datas;
    }
}

package com.goku.tmdb.ui;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;

public class ContentListViewModel extends BaseContentViewModel {
    private static final String TAG = ContentListViewModel.class.getSimpleName();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<Boolean> isGridMode = new ObservableField<>(true);
    public MutableLiveData<Boolean> changeGridMode = new MutableLiveData<>();
    public ObservableField<Boolean> isShowToolbar = new ObservableField<>(true);

    public View.OnClickListener onBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    public View.OnClickListener onGridClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isGridMode.set(!isGridMode.get());
            Utils.setGridMode(isGridMode.get());
            changeGridMode.postValue(isGridMode.get());
        }
    };

    public ContentListViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
        isGridMode.set(Utils.isGridMode());
    }
}

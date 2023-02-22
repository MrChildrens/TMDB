package com.goku.tmdb.ui.search;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;

public class SearchViewModel extends BaseContentViewModel {

    public final ObservableField<String> searchText = new ObservableField<>();
    public ObservableField<Boolean> isGridMode = new ObservableField<>(true);
    public MutableLiveData<Boolean> changeGridMode = new MutableLiveData<>();

    public View.OnClickListener onGridClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isGridMode.set(!isGridMode.get());
            Utils.setGridMode(isGridMode.get());
            changeGridMode.postValue(isGridMode.get());
        }
    };

    public SearchViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
        isGridMode.set(Utils.isGridMode());
    }
}
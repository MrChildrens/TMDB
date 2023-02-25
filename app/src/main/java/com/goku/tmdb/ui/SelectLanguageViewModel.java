package com.goku.tmdb.ui;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.goku.tmdb.app.AppManager;
import com.goku.tmdb.app.MultiLanguageUtil;
import com.goku.tmdb.app.SPUtils;
import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectLanguageViewModel extends BaseContentViewModel {
    private static final String TAG = "SelectLanguageViewModel";
    List<ItemSelectLanguageModel> mDatas = new ArrayList<>();
    public MutableLiveData<List<ItemSelectLanguageModel>> refresh = new MutableLiveData<>();
    public View.OnClickListener onBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (ItemSelectLanguageModel data : mDatas) {
                if (data.isSelected.get()) {
                    MultiLanguageUtil.getInstance().updateLanguage(data.getLanguage(), data.getCountry());
                    break;
                }
            }
            MainActivity.newInstance(AppManager.getAppManager().currentActivity());
        }
    };

    public SelectLanguageViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
        init();
    }

    private void init() {
        addItem(Locale.SIMPLIFIED_CHINESE);
        addItem(new Locale("zh", "HK"));
        addItem(new Locale("zh", "TW"));
        addItem(Locale.ENGLISH);
        addItem(Locale.FRENCH);
        addItem(Locale.GERMAN);
        addItem(Locale.ITALIAN);
        addItem(Locale.JAPANESE);
        addItem(Locale.KOREAN);

        addItem(new Locale("uk"));//乌克兰语
        addItem(new Locale("ru"));//俄语
//        addItem(new Locale("hu"));//匈牙利语
//        addItem(new Locale("tr"));//土耳其语
//        addItem(new Locale("sr"));//塞尔维亚语
//        addItem(new Locale("el"));//希腊语
//        addItem(new Locale("ku"));//库尔德语
//        addItem(new Locale("no"));//挪威语
//        addItem(new Locale("cs"));//捷克语
//        addItem(new Locale("sk"));//斯洛伐克语
//        addItem(new Locale("pl"));//波兰语
//        addItem(new Locale("sv"));//瑞典语
//        addItem(new Locale("ro"));//罗马尼亚语
//        addItem(new Locale("fi"));//芬兰语
//        addItem(new Locale("nl"));//荷兰语
//        addItem(new Locale("pt"));//葡萄牙语
//        addItem(new Locale("es"));//西班牙语
//        addItem(new Locale("az"));//阿塞拜疆语

//        List<Languages> languages = TmdbApplication.getInstance().getConfiguration().getMLanguages();
//        for (Languages language : languages) {
//            if (!TextUtils.equals(Locale.SIMPLIFIED_CHINESE.getLanguage(), language.getIso6391())
//                    && !TextUtils.equals(Locale.TRADITIONAL_CHINESE.getLanguage(), language.getIso6391())
//                    && !TextUtils.equals(Locale.ENGLISH.getLanguage(), language.getIso6391())
//                    && !TextUtils.equals(Locale.JAPAN.getLanguage(), language.getIso6391())
//                    && !TextUtils.equals(Locale.KOREAN.getLanguage(), language.getIso6391())
//                    && !TextUtils.equals(Locale.FRENCH.getLanguage(), language.getIso6391()))
//                addItem(new Locale(language.getIso6391()));
//        }
        refresh.postValue(mDatas);
    }

    private void addItem(Locale locale) {
        String language = SPUtils.getInstance().getString(MultiLanguageUtil.LANGUAGE);
        String country = SPUtils.getInstance().getString(MultiLanguageUtil.COUNTRY);

        ItemSelectLanguageModel itemSelectLanguageModel = new ItemSelectLanguageModel();
        if (TextUtils.equals(language, locale.getLanguage()) && TextUtils.equals(country, locale.getCountry())) {
            itemSelectLanguageModel.isSelected.set(true);
        } else {
            itemSelectLanguageModel.isSelected.set(false);
        }
        itemSelectLanguageModel.languages.set(locale.getDisplayName());
        itemSelectLanguageModel.setLanguage(locale.getLanguage());
        itemSelectLanguageModel.setCountry(locale.getCountry());
        mDatas.add(itemSelectLanguageModel);
    }
}

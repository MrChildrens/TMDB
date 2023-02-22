package com.goku.tmdb.base;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.goku.tmdb.app.GlideUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BaseViewModel<M extends BaseModel> extends AndroidViewModel implements IBaseViewModel, Consumer<Disposable> {

    public M mModel;

    protected Application mApplication;
    //弱引用持有
    private WeakReference<LifecycleProvider> mLifecycle;
    //管理RxJava，主要针对RxJava异步操作造成的内存泄漏
    private CompositeDisposable mCompositeDisposable;

    private MutableLiveData<Map<String, Object>> startActivityEvent = new MutableLiveData<>();
    private MutableLiveData<Void> finishEvent = new MutableLiveData<>();

    public BaseViewModel(@NonNull Application application, M model) {
        super(application);
        mApplication = application;
        mCompositeDisposable = new CompositeDisposable();
        mModel = model;
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    public void injectLifecycleProvider(LifecycleProvider lifecycle) {
        this.mLifecycle = new WeakReference<>(lifecycle);
    }

    public LifecycleProvider getLifecycleProvider() {
        return mLifecycle.get();
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        GlideUtils.getInstance().clear();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        GlideUtils.getInstance().clear();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {
        GlideUtils.getInstance().clear();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        GlideUtils.getInstance().clear();
    }

    @Override
    public void accept(Disposable disposable) throws Exception {

    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Map<String, Object> params = new HashMap<>();
        params.put(ParameterField.CLASS, clz);
        if (bundle != null) {
            params.put(ParameterField.BUNDLE, bundle);
        }
        getStartActivityEvent().postValue(params);
    }

    public static final class ParameterField {
        public static String CLASS = "CLASS";
        public static String CANONICAL_NAME = "CANONICAL_NAME";
        public static String BUNDLE = "BUNDLE";
    }

    public MutableLiveData<Map<String, Object>> getStartActivityEvent() {
        if (startActivityEvent == null) {
            startActivityEvent = new MutableLiveData<>();
        }
        return startActivityEvent;
    }

    public MutableLiveData<Void> getFinishEvent() {
        return finishEvent;
    }

    public void setFinishEvent(MutableLiveData<Void> finishEvent) {
        this.finishEvent = finishEvent;
    }

    public void finish() {
        finishEvent.postValue(null);
    }
}

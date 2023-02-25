package com.goku.tmdb.ui;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.goku.tmdb.R;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseFragment;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.FragmentImageBinding;

import me.tatarka.bindingcollectionadapter2.BR;

public class ImageFragment extends BaseFragment<FragmentImageBinding, ImageViewModel> {
    private static final String TAG = ImageFragment.class.getSimpleName();
    private ImageView mImageView;


    public static ImageFragment newInstance(String url, int position, int targetPos) {
        ImageFragment fragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_IMAGE_URL, url);
        bundle.putInt(Constant.KEY_POSITION, position);
        bundle.putInt(Constant.KEY_TARGET_POSITION, targetPos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        mViewModel.showToolbarEvent.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ImagePageViewModel imagePageViewModel = new ViewModelProvider(getActivity(),
                        AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(ImagePageViewModel.class);
                imagePageViewModel.isShowToolbar.set(mViewModel.isShowToolbar.get());
            }
        });
        mImageView = mViewBinding.ivImage;
        mImageView.setTag(R.id.image_url, mViewModel.getUrl());
        if (mViewModel.getPosition() == mViewModel.getTargetPosition()) {
            initPaltte();
        }
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String url = bundle.getString(Constant.KEY_IMAGE_URL);
            int position = bundle.getInt(Constant.KEY_POSITION);
            int targetPosition = bundle.getInt(Constant.KEY_TARGET_POSITION);
            mViewModel.setUrl(url);
            mViewModel.setPosition(position);
            mViewModel.setTargetPosition(targetPosition);
        }
    }

    public void initPaltte() {
        if (mViewModel != null) {
            String url = null;
            if (!TextUtils.isEmpty(mViewModel.getUrl())) {
                url = mViewModel.getUrl();
            }
            Glide.with(getContext())
                    .asBitmap()
                    .load(url)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            String tag = (String) mImageView.getTag(R.id.image_url);
                            if (!TextUtils.equals(tag, mViewModel.getUrl())) {
                                return;
                            }
                            mImageView.setImageBitmap(resource);
                            Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(@Nullable Palette palette) {

                                    mViewModel.dominantColor.set(palette.getDominantColor(Color.WHITE));

                                    boolean isDark = Utils.isNightMode();
                                    Palette.Swatch mostPopularSwatch = null;
                                    for (Palette.Swatch swatch : palette.getSwatches()) {
                                        if (mostPopularSwatch == null
                                                || swatch.getPopulation() > mostPopularSwatch.getPopulation()) {
                                            mostPopularSwatch = swatch;
                                        }
                                    }
                                    if (mostPopularSwatch != null) {
                                        double luminance = ColorUtils.calculateLuminance(mostPopularSwatch.getRgb());
                                        // 当luminance小于0.5时，我们认为这是一个深色值.
                                        isDark = luminance < 0.5;
                                    }

                                    Window window = getActivity().getWindow();
                                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                                    Drawable drawable;
                                    if (isDark) {
                                        mViewModel.isDark.set(true);
                                        mViewModel.bodyTextColor.set(Color.WHITE);
                                        mViewModel.titleTextColor.set(Color.GRAY);
                                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                                    } else {
                                        mViewModel.isDark.set(false);
                                        mViewModel.bodyTextColor.set(Color.BLACK);
                                        mViewModel.titleTextColor.set(Color.GRAY);

                                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                                    }
                                    window.setStatusBarColor(mViewModel.dominantColor.get());

                                    ImagePageViewModel imagePageViewModel = new ViewModelProvider(getActivity(),
                                            AppViewModelFactory.getInstance(TmdbApplication.getInstance())).get(ImagePageViewModel.class);
                                    imagePageViewModel.dominantColor.set(mViewModel.dominantColor.get());
                                    imagePageViewModel.titleTextColor.set(mViewModel.titleTextColor.get());
                                    imagePageViewModel.bodyTextColor.set(mViewModel.bodyTextColor.get());
                                    imagePageViewModel.isDark.set(mViewModel.isDark.get());

                                }
                            });
                        }

                        @Override
                        public void onLoadFailed(Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                        }
                    });
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_image;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ImageViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(TmdbApplication.getInstance());
        return new ViewModelProvider(this, factory).get(ImageViewModel.class);
    }

}
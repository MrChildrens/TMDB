package com.goku.tmdb.app;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.goku.tmdb.R;

import java.util.LinkedList;

public class GlideUtils {
    private static final String TAG = "GlideUtils";
    private volatile static GlideUtils sInstance;

    public static GlideUtils getInstance() {
        if (sInstance == null) {
            synchronized (GlideUtils.class) {
                if (sInstance == null) {
                    sInstance = new GlideUtils();
                }
            }
        }
        return sInstance;
    }

    private boolean mIsExcuting = false;
    private final LinkedList<String> mQueue = new LinkedList<>();
    private final LinkedList<ImageView> mViewQueue = new LinkedList<>();

    public void add(String url, ImageView imageView) {
        mQueue.add(url);
        mViewQueue.add(imageView);
        excute();
    }

    private void excute() {
        if (mQueue.size() <= 0 || mIsExcuting) {
            return;
        }
        mIsExcuting = true;
        ImageView imageView = mViewQueue.getFirst();
        String url = mQueue.getFirst();
        imageView.setTag(R.id.image_url, url);
//        imageView.setImageDrawable(imageView.getContext().getResources().getDrawable(R.drawable.radius_bg_back));
        Glide.with(imageView.getContext())
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (TextUtils.equals(String.valueOf(imageView.getTag(R.id.image_url)), url)) {
                            imageView.setImageBitmap(resource);
                        }
                        if (mViewQueue.size() > 0) {
                            mViewQueue.removeFirst();
                            mQueue.removeFirst();
                        }
                        mIsExcuting = false;
                        excute();
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (mViewQueue.size() > 0) {
                            mViewQueue.removeFirst();
                            mQueue.removeFirst();
                        }
                        mIsExcuting = false;
                        excute();
                    }
                });
//                .into(new SimpleTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        if (TextUtils.equals(String.valueOf(imageView.getTag()), url)) {
//                            imageView.setImageDrawable(resource);
//                        }
//                        if (mViewQueue.size() > 0) {
//                            mViewQueue.removeFirst();
//                            mQueue.removeFirst();
//                        }
//                        mIsExcuting = false;
//                        excute();
//                    }
//
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                        super.onLoadFailed(errorDrawable);
//                        if (mViewQueue.size() > 0) {
//                            mViewQueue.removeFirst();
//                            mQueue.removeFirst();
//                        }
//                        mIsExcuting = false;
//                        excute();
//                    }
//                });
    }

    public void clear() {
        mQueue.clear();
        mViewQueue.clear();
        mIsExcuting = false;
    }
}

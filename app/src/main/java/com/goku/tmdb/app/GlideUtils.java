package com.goku.tmdb.app;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
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
    private final LinkedList<String> mThumbnailQueue = new LinkedList<>();
    private final LinkedList<ImageView> mViewQueue = new LinkedList<>();

    public void add(String url, String thumbnail, ImageView imageView) {
        mQueue.add(url);
        mThumbnailQueue.add(thumbnail);
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
        String thumbnailQueue = mThumbnailQueue.getFirst();
        imageView.setTag(R.id.image_url, url);
//        imageView.setImageDrawable(imageView.getContext().getResources().getDrawable(R.drawable.radius_bg_back));
        Glide.with(imageView.getContext())
                .load(url)
                .thumbnail(new RequestBuilder[]{Glide.with(imageView.getContext()).load(thumbnailQueue)})
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (TextUtils.equals(String.valueOf(imageView.getTag(R.id.image_url)), url)) {
                            imageView.setImageDrawable(resource);
                        }
                        if (mViewQueue.size() > 0) {
                            mViewQueue.removeFirst();
                            mQueue.removeFirst();
                            mThumbnailQueue.removeFirst();
                        }
                        mIsExcuting = false;
                        excute();
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        if (mViewQueue.size() > 0) {
                            mViewQueue.removeFirst();
                            mQueue.removeFirst();
                            mThumbnailQueue.removeFirst();
                        }
                        mIsExcuting = false;
                        excute();
                    }
                });
    }

    public void clear() {
        mQueue.clear();
        mThumbnailQueue.clear();
        mViewQueue.clear();
        mIsExcuting = false;
    }
}

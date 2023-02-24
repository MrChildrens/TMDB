package com.goku.tmdb.binding.viewadapter.imageview;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ViewAdapter {
    private static final String TAG = "Ciel_ViewAdapter";

    @BindingAdapter(value = {"url", "placeholder"}, requireAll = false)
    public static void setUrl(ImageView imageView, String url, int placeholderRes) {
        //使用Glide框架加载图片
//        TmdbApplication.PICASSO.load(url).into(imageView);
//        GlideUtils.getInstance().add(url, imageView);
//        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
//        } else {
//            Glide.with(imageView.getContext())
//                    .clear(imageView);
//        }
//        if (!TextUtils.isEmpty(url)) {
//            if (placeholderRes > 0) {
//                Picasso.with(imageView.getContext())
//                        .load(url)
//                        .error(placeholderRes)
//                        .placeholder(placeholderRes)//加载过程中的图片显示
//                        .into(imageView);
//            } else {
//                Picasso.with(imageView.getContext())
//                        .load(url)
//                        .into(imageView);
//            }
//        } else {
//            if (placeholderRes > 0) {
//                Picasso.with(imageView.getContext())
//                        .load(placeholderRes)
//                        .into(imageView);
//            }
//        }
    }

}

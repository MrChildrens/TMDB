package com.goku.tmdb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.goku.tmdb.R;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.base.BaseActivity;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.ActivityImagePageBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

public class ImagePageActivity extends BaseActivity<ActivityImagePageBinding, ImagePageViewModel> {
    private static final String TAG = "ImagePageActivity";


    private final List<ImageFragment> mFragments = new ArrayList<>();

    public static void newInstance(Context context, List<String> urls, int position, int itemType) {
        Intent intent = new Intent();
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.KEY_POSITION, position);
        bundle.putSerializable(Constant.KEY_IMAGE_URLS, (Serializable) urls);
        bundle.putInt(Constant.KEY_ITEM_TYPE, itemType);
        intent.putExtras(bundle);
        intent.setClass(context, ImagePageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        List<String> urls = (List<String>) extras.getSerializable(Constant.KEY_IMAGE_URLS);
        int position = extras.getInt(Constant.KEY_POSITION);
        int itemType = extras.getInt(Constant.KEY_ITEM_TYPE);
        int mCount = urls.size();
        if (itemType == PageParams.ITEM_TYPE_BAKCDROPS) {
//            requestWindowFeature(Window.FEATURE_NO_TITLE); // 去除标题
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onCreate(savedInstanceState);
        mViewModel.setUrls(urls);
        mViewModel.setPosition(position);
        mViewModel.setItemType(itemType);
        mViewModel.setCount(mCount);
        mViewModel.page.set((position + 1) + " / " + mCount);

        initView();
    }
    FragmentPagerAdapter fragmentPagerAdapter;
    private void initView() {
        for (int i = 0; i < mViewModel.getUrls().size(); i++) {
            mFragments.add(ImageFragment.newInstance(mViewModel.getUrls().get(i), i, mViewModel.getPosition()));
        }

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewBinding.viewpagerImage.setAdapter(fragmentPagerAdapter);
        mViewBinding.viewpagerImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewModel.setPosition(position);
                mViewModel.page.set((position + 1) + " / " + mViewModel.getCount());
                ImageFragment imageFragment = mFragments.get(position);
                imageFragment.initPaltte();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewBinding.viewpagerImage.post(new Runnable() {
            @Override
            public void run() {
                mViewBinding.viewpagerImage.setCurrentItem(mViewModel.getPosition());
            }
        });
    }

    @Override
    public int initContentView() {
        return R.layout.activity_image_page;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ImagePageViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return new ViewModelProvider(this, factory).get(ImagePageViewModel.class);
    }
}
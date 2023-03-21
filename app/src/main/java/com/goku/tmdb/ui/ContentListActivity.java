package com.goku.tmdb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goku.tmdb.R;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseActivity;
import com.goku.tmdb.data.AppViewModelFactory;
import com.goku.tmdb.databinding.ActivityContentListBinding;
import com.goku.tmdb.ui.home.ItemCategoryModel;
import com.goku.tmdb.ui.home.ItemMediaModel;
import com.goku.tmdb.ui.view.HorizontalRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.tatarka.bindingcollectionadapter2.BR;

public class ContentListActivity extends BaseActivity<ActivityContentListBinding, ContentListViewModel> {
    private static final String TAG = ContentListActivity.class.getSimpleName();
    private HorizontalRecyclerView mRecyclerContent;
    private ContentListAdapter mAdapterContent;
    private ItemCategoryModel mItemCategoryModel;

    public static void newInstance(Context context, ItemCategoryModel categoryModel, int categoryType) {
        Intent intent = new Intent();
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Bundle bundle = new Bundle();

        bundle.putSerializable(Constant.KEY_CATEGORY_MODEL, categoryModel);
        bundle.putInt(Constant.KEY_CATEGORY_TYPE, categoryType);
        intent.putExtras(bundle);
        intent.setClass(context, ContentListActivity.class);
        context.startActivity(intent);
    }

    public static void newInstance(Context context, ItemMediaModel itemMediaModel, int categoryType) {
        Intent intent = new Intent();
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Bundle bundle = new Bundle();

        bundle.putSerializable(Constant.KEY_ITEM_MODEL, itemMediaModel);
        bundle.putInt(Constant.KEY_CATEGORY_TYPE, categoryType);
        intent.putExtras(bundle);
        intent.setClass(context, ContentListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(mViewModel.dominantColor.get());

        ItemMediaModel mediaModel = null;
        int categoryType = 0;
        String title = "";
        String subTitle = "";
        boolean isToday = false;
        long id = 0;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mediaModel = (ItemMediaModel) bundle.getSerializable(Constant.KEY_ITEM_MODEL);
            mItemCategoryModel = (ItemCategoryModel) bundle.getSerializable(Constant.KEY_CATEGORY_MODEL);
            categoryType = bundle.getInt(Constant.KEY_CATEGORY_TYPE);
        }

        ItemCategoryModel itemCategoryModel = mViewModel.ownerModel.get();

        if (mediaModel != null) {
            id = mediaModel.getId();
            title = mediaModel.titles.get();
        }

        if (mItemCategoryModel != null) {
            id = mItemCategoryModel.getId();
            title = mItemCategoryModel.titles.get();
            subTitle = mItemCategoryModel.subTitles.get();
            isToday = mItemCategoryModel.isDay.get();
            if (!TextUtils.isEmpty(subTitle)) {
                if (TextUtils.equals(Utils.getLanguage(), Locale.SIMPLIFIED_CHINESE.getLanguage())) {
                    title += subTitle;
                } else {
                    title = title + " " + subTitle;
                }
            }
        }

        itemCategoryModel.titles.set(title);
        itemCategoryModel.setId(id);
        itemCategoryModel.setCategoryType(categoryType);
        itemCategoryModel.isDay.set(isToday);

        mViewModel.title.set(title);

        mViewModel.setItemCategoryModel(itemCategoryModel);
        initView(itemCategoryModel.getCategoryType());

        mViewBinding.setViewModel(mViewModel);
        Log.d(TAG, "[Ciel_Debug] onCreate: " + mViewModel.isDark.get());

        getDatas(itemCategoryModel);
    }

    private void initView(int viewType) {

        mRecyclerContent = mViewBinding.recyclerviewContent;
        mRecyclerContent.setLoadingMoreEnabled(true);
        if (viewType != PageParams.CATEGORY_TYPE_PERSON_HORI) {
            if (Utils.isGridMode()) {
                if (viewType == PageParams.CATEGORY_TYPE_BACKDROPS) {
                    mRecyclerContent.setLayoutManager(new GridLayoutManager(this, 2));
                } else {
                    mRecyclerContent.setLayoutManager(new GridLayoutManager(this, Constant.GIRD_LINE_COUNT));
                }
            } else {
                mRecyclerContent.setLayoutManager(new LinearLayoutManager(this));
            }
        } else {
            mRecyclerContent.setLayoutManager(new LinearLayoutManager(this));
        }

        mAdapterContent = new ContentListAdapter(this, Utils.isGridMode());
        mRecyclerContent.setAdapter(mAdapterContent);
        mViewModel.changeGridMode.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isGrid) {
                if (mViewModel.getItemCategoryModel().getCategoryType() != PageParams.CATEGORY_TYPE_PERSON_HORI) {

                    int lastVisibleItemPosition = 0;
                    if (mRecyclerContent.getLayoutManager() instanceof LinearLayoutManager) {
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerContent.getLayoutManager();
                        lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition() - mRecyclerContent.getChildCount() + 1;
                    } else if (mRecyclerContent.getLayoutManager() instanceof GridLayoutManager) {
                        GridLayoutManager gridLayoutManager = (GridLayoutManager) mRecyclerContent.getLayoutManager();
                        lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition() - mRecyclerContent.getChildCount() + 1;
                    }

                    mAdapterContent.setGridMode(isGrid);
                    if (isGrid) {
                        if (viewType == PageParams.CATEGORY_TYPE_POSTER || viewType == PageParams.CATEGORY_TYPE_BACKDROPS) {
                            mRecyclerContent.setLayoutManager(new GridLayoutManager(ContentListActivity.this, Constant.HORI_GIRD_LINE_COUNT));
                        } else {
                            mRecyclerContent.setLayoutManager(new GridLayoutManager(ContentListActivity.this, Constant.GIRD_LINE_COUNT));
                        }
                    } else {
                        mRecyclerContent.setLayoutManager(new LinearLayoutManager(ContentListActivity.this));
                    }
                    mRecyclerContent.setAdapter(mAdapterContent);

                    mRecyclerContent.scrollToPosition(lastVisibleItemPosition);
                }
            }
        });
    }

    private void getDatas(ItemCategoryModel itemCategoryModel) {
        if (itemCategoryModel.getCategoryType() != PageParams.CATEGORY_TYPE_POSTER
                && itemCategoryModel.getCategoryType() != PageParams.CATEGORY_TYPE_BACKDROPS) {
            mViewModel.getContents(itemCategoryModel);
            itemCategoryModel.refreshCategory.observe(this, new Observer<>() {
                @Override
                public void onChanged(ItemCategoryModel itemCategoryModel) {
                    mAdapterContent.setDatas(itemCategoryModel.itemDatas.get());
                    mRecyclerContent.loadmoreComplete();
                }
            });

            mRecyclerContent.setOnLoadingListener(new HorizontalRecyclerView.OnLoadingListener() {
                @Override
                public void onLoadMore() {
                    if (itemCategoryModel.getPage() < itemCategoryModel.getTotalPage()) {
                        mViewModel.getNextPageContents(itemCategoryModel);
                    } else {
                        mRecyclerContent.setLoadingMoreEnabled(false);
                    }
                }
            });
        } else {
            itemCategoryModel.statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
            List<ItemMediaModel> mediaModels = new ArrayList<>();
            for (int i = 0; i < mItemCategoryModel.itemDatas.get().size(); i++) {
                ItemMediaModel mediaModel = new ItemMediaModel();
                mediaModel.setItemType(PageParams.ITEM_TYPE_BAKCDROPS_PAGE);
                mediaModel.images.set(mItemCategoryModel.itemDatas.get().get(i).images.get());
                mediaModel.isHideVote.set(true);
                mediaModels.add(mediaModel);
            }
            mAdapterContent.setDatas(mediaModels);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public int initContentView() {
        return R.layout.activity_content_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ContentListViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return new ViewModelProvider(this, factory).get(ContentListViewModel.class);
    }
}
package com.goku.tmdb.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goku.tmdb.R;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.databinding.ItemCategoryBinding;
import com.goku.tmdb.databinding.ItemCategoryEpisodeBinding;
import com.goku.tmdb.databinding.ItemCategoryHoriTextBinding;
import com.goku.tmdb.databinding.ItemCategoryImageBinding;
import com.goku.tmdb.databinding.ItemCategoryMainTitleBinding;
import com.goku.tmdb.databinding.ItemCategoryMainTitleListBinding;
import com.goku.tmdb.databinding.ItemCategorySubTitleListBinding;
import com.goku.tmdb.databinding.ItemCategoryVerticalTextBinding;
import com.goku.tmdb.ui.ContentListActivity;
import com.goku.tmdb.ui.ContentListAdapter;
import com.goku.tmdb.ui.ImagePageActivity;
import com.goku.tmdb.ui.view.HorizontalRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter {
    private static final String TAG = CategoryAdapter.class.getSimpleName();

    private List<ItemCategoryModel> mDatas = new ArrayList<>();

    private final Context mContext;
    private final LifecycleOwner mLifecycleOwner;
    private final BaseContentViewModel mViewModel;

    public CategoryAdapter(Context context, LifecycleOwner viewLifecycleOwner, BaseContentViewModel viewModel) {
        mContext = context;
        mLifecycleOwner = viewLifecycleOwner;
        mViewModel = viewModel;
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getCategoryType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case PageParams.CATEGORY_TYPE_MOVIE_TRENDING:
            case PageParams.CATEGORY_TYPE_MOVIE_NOW_PLAYING:
            case PageParams.CATEGORY_TYPE_MOVIE_POPULAR:
            case PageParams.CATEGORY_TYPE_MOVIE_TOP_RATED:
            case PageParams.CATEGORY_TYPE_MOVIE_UPCOMING:
            case PageParams.CATEGORY_TYPE_TV_TRENDING:
            case PageParams.CATEGORY_TYPE_TV_AIRING_TODAY:
            case PageParams.CATEGORY_TYPE_TV_ON_AIR:
            case PageParams.CATEGORY_TYPE_TV_TOP_RATED:
            case PageParams.CATEGORY_TYPE_PERSON:
                return new CategoryHolder(DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false));
            case PageParams.CATEGORY_TYPE_MAIN_TITLE:
                return new CategoryMainTitleHolder(DataBindingUtil.inflate(inflater, R.layout.item_category_main_title, parent, false));
            case PageParams.CATEGORY_TYPE_VERTICAL_TEXT:
                return new CategoryVerticalTextHolder(DataBindingUtil.inflate(inflater, R.layout.item_category_vertical_text, parent, false));
            case PageParams.CATEGORY_TYPE_HORI_TEXT:
                return new CategoryHoriTextHolder(DataBindingUtil.inflate(inflater, R.layout.item_category_hori_text, parent, false));
            case PageParams.CATEGORY_TYPE_MAIN_TITLE_LIST:
                return new CategoryMainTitleListHolder(DataBindingUtil.inflate(inflater, R.layout.item_category_main_title_list, parent, false));
            case PageParams.CATEGORY_TYPE_SUB_TITLE_LIST:
                return new CategorySubTitleListHolder(DataBindingUtil.inflate(inflater, R.layout.item_category_sub_title_list, parent, false));
            case PageParams.CATEGORY_TYPE_IMAGE:
                return new CategoryImageHolder(DataBindingUtil.inflate(inflater, R.layout.item_category_image, parent, false));
            case PageParams.CATEGORY_TYPE_EPISODE:
                return new CategoryEpisodeHolder(DataBindingUtil.inflate(inflater, R.layout.item_category_episode, parent, false));
            default:
                return new CategoryHolder(DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        ItemCategoryModel model = mDatas.get(position);

        CategoryHolder categoryHolder = null;
        CategoryMainTitleHolder categoryMainTitleHolder = null;
        CategoryVerticalTextHolder categoryVerticalTextHolder = null;
        CategoryHoriTextHolder categoryHoriTextHolder = null;
        CategoryMainTitleListHolder categoryMainTitleListHolder = null;
        CategorySubTitleListHolder categorySubTitleListHolder = null;
        CategoryImageHolder categoryImageHolder = null;
        CategoryEpisodeHolder categoryEpisodeHolder = null;
        if (holder instanceof CategoryHolder) {
            categoryHolder = ((CategoryHolder) holder);
            categoryHolder.mBinding.setVariable(BR.viewModel, model);
        } else if (holder instanceof CategoryMainTitleHolder) {
            categoryMainTitleHolder = ((CategoryMainTitleHolder) holder);
            categoryMainTitleHolder.mBinding.setVariable(BR.viewModel, model);
        } else if (holder instanceof CategoryVerticalTextHolder) {
            categoryVerticalTextHolder = ((CategoryVerticalTextHolder) holder);
            categoryVerticalTextHolder.mBinding.setVariable(BR.viewModel, model);
        } else if (holder instanceof CategoryHoriTextHolder) {
            categoryHoriTextHolder = ((CategoryHoriTextHolder) holder);
            categoryHoriTextHolder.mBinding.setVariable(BR.viewModel, model);
        } else if (holder instanceof CategoryMainTitleListHolder) {
            categoryMainTitleListHolder = (CategoryMainTitleListHolder) holder;
            categoryMainTitleListHolder.mBinding.setVariable(BR.viewModel, model);
        } else if (holder instanceof CategorySubTitleListHolder) {
            categorySubTitleListHolder = ((CategorySubTitleListHolder) holder);
            categorySubTitleListHolder.mBinding.setVariable(BR.viewModel, model);
        } else if (holder instanceof CategoryImageHolder) {
            categoryImageHolder = ((CategoryImageHolder) holder);
            categoryImageHolder.mBinding.setVariable(BR.viewModel, model);
        } else if (holder instanceof CategoryEpisodeHolder) {
            categoryEpisodeHolder = (CategoryEpisodeHolder) holder;
            categoryEpisodeHolder.mBinding.setVariable(BR.viewModel, model);
        }

        switch (model.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_MOVIE_TRENDING:
            case PageParams.CATEGORY_TYPE_MOVIE_NOW_PLAYING:
            case PageParams.CATEGORY_TYPE_MOVIE_POPULAR:
            case PageParams.CATEGORY_TYPE_MOVIE_TOP_RATED:
            case PageParams.CATEGORY_TYPE_MOVIE_UPCOMING:
            case PageParams.CATEGORY_TYPE_TV_TRENDING:
            case PageParams.CATEGORY_TYPE_TV_AIRING_TODAY:
            case PageParams.CATEGORY_TYPE_TV_ON_AIR:
            case PageParams.CATEGORY_TYPE_TV_TOP_RATED:
                categoryHolder.mBinding.recyclerviewHori.getLayoutParams().height =
                        mContext.getResources().getDimensionPixelOffset(R.dimen.category_vertical_image_width) * 3 / 2
                                + mContext.getResources().getDimensionPixelOffset(R.dimen.percent_circle_width) / 2
                                + 4 * mContext.getResources().getDimensionPixelOffset(R.dimen.common_margin)
                                + 2 * mContext.getResources().getDimensionPixelOffset(R.dimen.category_vertical_title_height);
                break;
            case PageParams.CATEGORY_TYPE_PERSON:
                categoryHolder.mBinding.recyclerviewHori.getLayoutParams().height =
                        mContext.getResources().getDimensionPixelOffset(R.dimen.people_item_width)
                                + 4 * mContext.getResources().getDimensionPixelOffset(R.dimen.common_margin)
                                + 2 * mContext.getResources().getDimensionPixelOffset(R.dimen.category_vertical_title_height);
                break;
            default:
                break;
        }
        CategoryHolder finalViewHolder = categoryHolder;
        switch (model.getCategoryType()) {
            case PageParams.CATEGORY_TYPE_MOVIE_TRENDING:
            case PageParams.CATEGORY_TYPE_MOVIE_NOW_PLAYING:
            case PageParams.CATEGORY_TYPE_MOVIE_POPULAR:
            case PageParams.CATEGORY_TYPE_MOVIE_TOP_RATED:
            case PageParams.CATEGORY_TYPE_MOVIE_UPCOMING:
            case PageParams.CATEGORY_TYPE_TV_TRENDING:
            case PageParams.CATEGORY_TYPE_TV_AIRING_TODAY:
            case PageParams.CATEGORY_TYPE_TV_ON_AIR:
            case PageParams.CATEGORY_TYPE_TV_TOP_RATED:
            case PageParams.CATEGORY_TYPE_PERSON:
                if (model.itemDatas.get() != null && model.itemDatas.get().size() == 0) {
                    mViewModel.getContents(model);

                    model.refreshCategory.observe(mLifecycleOwner, new Observer<>() {
                        @Override
                        public void onChanged(ItemCategoryModel itemCategoryModel) {
                            Log.d(TAG, "[Ciel_Debug] onChanged: refreshCategory: " + itemCategoryModel.itemDatas.get().size());
                            finalViewHolder.mAdapter.setDatas(itemCategoryModel.itemDatas.get());
                            finalViewHolder.mBinding.recyclerviewHori.loadmoreComplete();
                        }
                    });
                    model.changeDayOrWeek.observe(mLifecycleOwner, new Observer<ItemCategoryModel>() {
                        @Override
                        public void onChanged(ItemCategoryModel categoryModel) {
                            categoryModel.itemDatas.get().clear();
                            finalViewHolder.mAdapter.getDatas().clear();
                            finalViewHolder.mAdapter.notifyDataSetChanged();
//                            finalViewHolder.mAdapter.setDatas(categoryModel.itemDatas.get());
                            Log.d(TAG, "[Ciel_Debug] onChanged: changeDayOrWeek: " + model.itemDatas.get().size());
                            if (categoryModel.itemDatas.get() != null && categoryModel.itemDatas.get().size() == 0) {
                                categoryModel.setPage(1);
                                mViewModel.getContents(categoryModel);
                            }
                        }
                    });

                    categoryHolder.mBinding.recyclerviewHori.setOnLoadingListener(new HorizontalRecyclerView.OnLoadingListener() {
                        @Override
                        public void onLoadMore() {
                            if (model.getPage() < model.getTotalPage()) {
                                mViewModel.getNextPageContents(model);
                            }
                        }
                    });

                    categoryHolder.mBinding.tvMore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int viewType;
                            if (model.getCategoryType() == PageParams.CATEGORY_TYPE_PERSON) {
                                viewType = PageParams.CATEGORY_TYPE_PERSON_HORI;
                            } else {
                                viewType = model.getCategoryType();
                            }
                            ContentListActivity.newInstance(mContext, model, viewType);
                        }
                    });
                }

                break;
            case PageParams.CATEGORY_TYPE_MAIN_TITLE_LIST:
                if (categoryMainTitleListHolder.mAdapter.getDatas() != null && categoryMainTitleListHolder.mAdapter.getDatas().size() == 0) {
                    categoryMainTitleListHolder.mAdapter.setDatas(model.itemDatas.get());
                }
                categoryMainTitleListHolder.mBinding.tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (model.itemDatas != null
                                && model.itemDatas.get() != null
                                && model.itemDatas.get().size() > 0) {
                            int itemType = model.itemDatas.get().get(0).getItemType();
                            List<String> urls = new ArrayList<>();
                            for (int i = 0; i < model.itemDatas.get().size(); i++) {
                                if (itemType == PageParams.ITEM_TYPE_POSTERS) {
                                    urls.add(model.itemDatas.get().get(i).orignalPoster.get());
                                } else if (itemType == PageParams.ITEM_TYPE_BAKCDROPS) {
                                    urls.add(model.itemDatas.get().get(i).orignalBackdrop.get());
                                }
                            }
                            ImagePageActivity.newInstance(mContext, urls, 0, model.itemDatas.get().get(0).getItemType());
                        }
                    }
                });
                break;
            case PageParams.CATEGORY_TYPE_SUB_TITLE_LIST:
                if (categorySubTitleListHolder.mAdapter.getDatas() != null && categorySubTitleListHolder.mAdapter.getDatas().size() == 0) {
                    categorySubTitleListHolder.mAdapter.setDatas(model.itemDatas.get());
                }
                break;
            case PageParams.CATEGORY_TYPE_IMAGE:
                categoryImageHolder.mBinding.ivCollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentListActivity.newInstance(mContext, model, PageParams.CATEGORY_TYPE_COLLECTIONS);
                    }
                });
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private class CategoryHolder extends RecyclerView.ViewHolder {

        ItemCategoryBinding mBinding;
        ContentListAdapter mAdapter;

        public CategoryHolder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

            HorizontalRecyclerView recyclerviewHori = mBinding.recyclerviewHori;
            recyclerviewHori.setNestedScrollingEnabled(false);
            recyclerviewHori.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mAdapter = new ContentListAdapter(mContext, true);
            recyclerviewHori.setAdapter(mAdapter);
        }
    }

    private static class CategoryMainTitleHolder extends RecyclerView.ViewHolder {

        ItemCategoryMainTitleBinding mBinding;

        public CategoryMainTitleHolder(@NonNull ItemCategoryMainTitleBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class CategoryVerticalTextHolder extends RecyclerView.ViewHolder {

        ItemCategoryVerticalTextBinding mBinding;

        public CategoryVerticalTextHolder(@NonNull ItemCategoryVerticalTextBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class CategoryHoriTextHolder extends RecyclerView.ViewHolder {

        ItemCategoryHoriTextBinding mBinding;

        public CategoryHoriTextHolder(@NonNull ItemCategoryHoriTextBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private class CategorySubTitleListHolder extends RecyclerView.ViewHolder {

        ItemCategorySubTitleListBinding mBinding;
        ContentListAdapter mAdapter;

        public CategorySubTitleListHolder(@NonNull ItemCategorySubTitleListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            RecyclerView recyclerviewList = mBinding.recyclerviewList;
            recyclerviewList.setNestedScrollingEnabled(false);
            recyclerviewList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mAdapter = new ContentListAdapter(mContext, true);
            recyclerviewList.setAdapter(mAdapter);
        }
    }

    private class CategoryMainTitleListHolder extends RecyclerView.ViewHolder {

        ItemCategoryMainTitleListBinding mBinding;
        ContentListAdapter mAdapter;

        public CategoryMainTitleListHolder(@NonNull ItemCategoryMainTitleListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            RecyclerView recyclerviewList = mBinding.recyclerviewList;
            recyclerviewList.setNestedScrollingEnabled(false);
            recyclerviewList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mAdapter = new ContentListAdapter(mContext, true);
            recyclerviewList.setAdapter(mAdapter);
        }
    }

    private static class CategoryImageHolder extends RecyclerView.ViewHolder {

        ItemCategoryImageBinding mBinding;

        public CategoryImageHolder(@NonNull ItemCategoryImageBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class CategoryEpisodeHolder extends RecyclerView.ViewHolder {

        ItemCategoryEpisodeBinding mBinding;

        public CategoryEpisodeHolder(@NonNull ItemCategoryEpisodeBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public void setDatas(List<ItemCategoryModel> datas) {
        mDatas = datas;
        notifyItemRangeChanged(0, mDatas.size());
    }
}

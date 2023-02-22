package com.goku.tmdb.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.goku.tmdb.R;
import com.goku.tmdb.app.GlideUtils;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.databinding.ItemBackdropsSingleBinding;
import com.goku.tmdb.databinding.ItemClickableButtonBinding;
import com.goku.tmdb.databinding.ItemCompanyBinding;
import com.goku.tmdb.databinding.ItemEpisodesBinding;
import com.goku.tmdb.databinding.ItemImageHoriBinding;
import com.goku.tmdb.databinding.ItemMediaBinding;
import com.goku.tmdb.databinding.ItemMediaHoriBinding;
import com.goku.tmdb.databinding.ItemMediaSingleBinding;
import com.goku.tmdb.databinding.ItemNetworkBinding;
import com.goku.tmdb.databinding.ItemPeopleBinding;
import com.goku.tmdb.databinding.ItemPeopleHoriBinding;
import com.goku.tmdb.databinding.ItemReleaseInfoBinding;
import com.goku.tmdb.databinding.ItemReviewBinding;
import com.goku.tmdb.databinding.ItemSeasonsBinding;
import com.goku.tmdb.ui.detail.DetailActivity;
import com.goku.tmdb.ui.home.ItemMediaModel;

import java.util.ArrayList;
import java.util.List;

public class ContentListAdapter extends RecyclerView.Adapter {
    private static final String TAG = ContentListAdapter.class.getSimpleName();
    private final List<ItemMediaModel> mDatas = new ArrayList<>();
    private final Context mContext;
    private boolean mIsGridMode;

    public ContentListAdapter(Context context, boolean isGridMode) {
        mContext = context;
        mIsGridMode = isGridMode;
    }

    public void setGridMode(boolean gridMode) {
        mIsGridMode = gridMode;
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getItemType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case PageParams.ITEM_TYPE_MOIVE:
            case PageParams.ITEM_TYPE_TV_SHOW:
            case PageParams.ITEM_TYPE_RECOMMENDATIONS:
            case PageParams.ITEM_TYPE_SIMILAR:
                if (mIsGridMode) {
                    return new MediaHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_media, parent, false));
                } else {
                    return new MediaSingleHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_media_single, parent, false));
                }
            case PageParams.ITEM_TYPE_MOIVE_HORI:
            case PageParams.ITEM_TYPE_TV_SHOW_HORI:
                return new MediaHoriHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_media_hori, parent, false));
            case PageParams.ITEM_TYPE_POSTERS:
                return new MediaHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_media, parent, false));
            case PageParams.ITEM_TYPE_TRAILERS:
            case PageParams.ITEM_TYPE_BAKCDROPS:
                return new ImageHoriHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_image_hori, parent, false));
            case PageParams.ITEM_TYPE_BAKCDROPS_PAGE:
                return new BackdropsSingleHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_backdrops_single, parent, false));
            case PageParams.ITEM_TYPE_PEOPLE:
                return new PeopleHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people, parent, false));
            case PageParams.ITEM_TYPE_PEOPLE_HORI:
                return new PeopleHoriHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people_hori, parent, false));
            case PageParams.ITEM_TYPE_MOVIE_GENRES:
            case PageParams.ITEM_TYPE_TV_GENRES:
            case PageParams.ITEM_TYPE_MOVIE_KEYWORDS:
            case PageParams.ITEM_TYPE_TV_KEYWORDS:
            case PageParams.ITEM_TYPE_ALTERNATIVE_TITLES:
            case PageParams.ITEM_TYPE_ALSO_KNOWN_AS:
            case PageParams.ITEM_TYPE_COUNTRY:
                return new ClickableButtonHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_clickable_button, parent, false));
            case PageParams.ITEM_TYPE_REVIEWS:
                return new ReviewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_review, parent, false));
            case PageParams.ITEM_TYPE_SEASONS:
                return new SeasonsHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_seasons, parent, false));
            case PageParams.ITEM_TYPE_EPISODES:
                return new EpisodesHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_episodes, parent, false));
            case PageParams.ITEM_TYPE_RELEASE_INFO:
                return new ReleaseInfoHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_release_info, parent, false));
            case PageParams.ITEM_TYPE_NETWORK:
                return new NetworkInfoHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_network, parent, false));
            case PageParams.ITEM_TYPE_COMPANIES:
                return new CompanyHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_company, parent, false));
            default:
                return new MediaHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_media, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemMediaModel model = mDatas.get(position);
        int viewType = model.getItemType();
        MediaHolder mediaHolder;
        MediaSingleHolder mediaSingleHolder;
        MediaHoriHolder mediaHoriHolder;
        ImageHoriHolder imageHoriHolder;
        BackdropsSingleHolder backdropsSingleHolder;
        PeopleHolder peopleHolder;
        PeopleHoriHolder peopleHoriHolder;
        ReviewHolder reviewHolder;
        ClickableButtonHolder clickableButtonHolder;
        SeasonsHolder seasonsHolder;
        EpisodesHolder episodesHolder;
        ReleaseInfoHolder releaseInfoHolder;
        NetworkInfoHolder networkInfoHolder;
        CompanyHolder companyHolder;
        ImageView imageView = null;
        Drawable raduisDrawable = mContext.getResources().getDrawable(Utils.isNightMode() ? R.drawable.radius_bg_while : R.drawable.radius_bg_black);
        Drawable rectangleDrawable = mContext.getResources().getDrawable(Utils.isNightMode() ? R.drawable.rectangle_bg_while : R.drawable.rectangle_bg_back);
        Drawable roundDrawable = mContext.getResources().getDrawable(Utils.isNightMode() ? R.drawable.round_bg_while : R.drawable.round_bg_black);
        switch (viewType) {
            case PageParams.ITEM_TYPE_MOIVE:
            case PageParams.ITEM_TYPE_TV_SHOW:
            case PageParams.ITEM_TYPE_RECOMMENDATIONS:
            case PageParams.ITEM_TYPE_SIMILAR:
                if (mIsGridMode) {
                    mediaHolder = (MediaHolder) holder;
                    mediaHolder.mBinding.setVariable(BR.viewModel, model);
                    mediaHolder.mBinding.percentView.setPercent(model.voteAverages.get().floatValue());
                    imageView = mediaHolder.mBinding.ivCover;
                } else {
                    mediaSingleHolder = (MediaSingleHolder) holder;
                    mediaSingleHolder.mBinding.setVariable(BR.viewModel, model);
                    imageView = mediaSingleHolder.mBinding.ivCover;
                }
                imageView.setImageDrawable(raduisDrawable);
                break;
            case PageParams.ITEM_TYPE_MOIVE_HORI:
            case PageParams.ITEM_TYPE_TV_SHOW_HORI:
                mediaHoriHolder = (MediaHoriHolder) holder;
                mediaHoriHolder.mBinding.setVariable(BR.viewModel, model);
                imageView = mediaHoriHolder.mBinding.ivCover;
                imageView.setImageDrawable(raduisDrawable);
                break;
                case PageParams.ITEM_TYPE_POSTERS:
                mediaHolder = (MediaHolder) holder;
                mediaHolder.mBinding.setVariable(BR.viewModel, model);
                imageView = mediaHolder.mBinding.ivCover;
                imageView.setImageDrawable(raduisDrawable);
                break;
            case PageParams.ITEM_TYPE_TRAILERS:
            case PageParams.ITEM_TYPE_BAKCDROPS:
                imageHoriHolder = (ImageHoriHolder) holder;
                imageHoriHolder.mBinding.setVariable(BR.viewModel, model);
                imageView = imageHoriHolder.mBinding.ivCover;
                imageView.setImageDrawable(raduisDrawable);
                break;
            case PageParams.ITEM_TYPE_BAKCDROPS_PAGE:
                backdropsSingleHolder = (BackdropsSingleHolder) holder;
                backdropsSingleHolder.mBinding.setVariable(BR.viewModel, model);
                imageView = backdropsSingleHolder.mBinding.ivCover;
                imageView.setImageDrawable(rectangleDrawable);
                break;
            case PageParams.ITEM_TYPE_PEOPLE:
                peopleHolder = (PeopleHolder) holder;
                peopleHolder.mBinding.setVariable(BR.viewModel, model);
                imageView = peopleHolder.mBinding.ivCover;
                imageView.setImageDrawable(roundDrawable);
                break;
            case PageParams.ITEM_TYPE_PEOPLE_HORI:
                peopleHoriHolder = (PeopleHoriHolder) holder;
                peopleHoriHolder.mBinding.setVariable(BR.viewModel, model);
                imageView = peopleHoriHolder.mBinding.ivCover;
                imageView.setImageDrawable(roundDrawable);
                break;
            case PageParams.ITEM_TYPE_MOVIE_GENRES:
            case PageParams.ITEM_TYPE_TV_GENRES:
            case PageParams.ITEM_TYPE_MOVIE_KEYWORDS:
            case PageParams.ITEM_TYPE_TV_KEYWORDS:
            case PageParams.ITEM_TYPE_ALTERNATIVE_TITLES:
            case PageParams.ITEM_TYPE_ALSO_KNOWN_AS:
            case PageParams.ITEM_TYPE_COUNTRY:
                clickableButtonHolder = (ClickableButtonHolder) holder;
                clickableButtonHolder.mBinding.setVariable(BR.viewModel, model);
                clickableButtonHolder.mBinding.btnNormal.setBackground(raduisDrawable);
                if (viewType == PageParams.ITEM_TYPE_COUNTRY || viewType == PageParams.ITEM_TYPE_ALTERNATIVE_TITLES) {
                    Drawable drawable = mContext.getResources().getDrawable(model.drawableId.get());
                    drawable.setBounds(0, 0, TmdbApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.button_drawable_width),
                            TmdbApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.button_drawable_height));
                    clickableButtonHolder.mBinding.btnNormal.setCompoundDrawablesRelative(null, null, drawable, null);
                }
                break;
            case PageParams.ITEM_TYPE_REVIEWS:
                reviewHolder = (ReviewHolder) holder;
                reviewHolder.mBinding.setVariable(BR.viewModel, model);
                reviewHolder.mBinding.getRoot().setBackground(raduisDrawable);
                break;
            case PageParams.ITEM_TYPE_SEASONS:
                seasonsHolder = (SeasonsHolder) holder;
                seasonsHolder.mBinding.setVariable(BR.viewModel, model);
                imageView = seasonsHolder.mBinding.ivCover;
                imageView.setImageDrawable(raduisDrawable);
                break;
            case PageParams.ITEM_TYPE_EPISODES:
                episodesHolder = (EpisodesHolder) holder;
                episodesHolder.mBinding.setVariable(BR.viewModel, model);
                imageView = episodesHolder.mBinding.ivCover;
                imageView.setImageDrawable(raduisDrawable);
                break;
            case PageParams.ITEM_TYPE_RELEASE_INFO:
                releaseInfoHolder = (ReleaseInfoHolder) holder;
                releaseInfoHolder.mBinding.setVariable(BR.viewModel, model);
                Drawable drawable = mContext.getResources().getDrawable(model.drawableId.get());
                drawable.setBounds(0, 0, TmdbApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.button_drawable_width),
                        TmdbApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.button_drawable_height));
                releaseInfoHolder.mBinding.tvReleaseType.setCompoundDrawablesRelative(null, null, drawable, null);
                releaseInfoHolder.mBinding.getRoot().setBackground(raduisDrawable);
                break;
            case PageParams.ITEM_TYPE_NETWORK:
                networkInfoHolder = (NetworkInfoHolder) holder;
                networkInfoHolder.mBinding.setVariable(BR.viewModel, model);
                imageView = networkInfoHolder.mBinding.ivCover;
                imageView.setImageDrawable(rectangleDrawable);
                break;
            case PageParams.ITEM_TYPE_COMPANIES:
                companyHolder = (CompanyHolder) holder;
                companyHolder.mBinding.setVariable(BR.viewModel, model);
                imageView = companyHolder.mBinding.ivCover;
                imageView.setImageDrawable(rectangleDrawable);
                break;
            default:
                break;
        }

        if (imageView != null) {
            loadImage(imageView, position, model);
        }

        int finalPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "[Ciel_Debug] onClick: " + model.getItemType());
                switch (model.getItemType()) {
                    case PageParams.ITEM_TYPE_MOIVE:
                    case PageParams.ITEM_TYPE_RECOMMENDATIONS:
                    case PageParams.ITEM_TYPE_SIMILAR:
                    case PageParams.ITEM_TYPE_TV_SHOW:
                    case PageParams.ITEM_TYPE_PEOPLE:
                    case PageParams.ITEM_TYPE_PEOPLE_HORI:
                    case PageParams.ITEM_TYPE_SEASONS:
                    case PageParams.ITEM_TYPE_MOIVE_HORI:
                    case PageParams.ITEM_TYPE_TV_SHOW_HORI:
                        DetailActivity.newInstance(mContext, model);
                        break;
                    case PageParams.ITEM_TYPE_EPISODES:
                        DetailActivity.newInstance(mContext, model, mDatas);
                        break;
                    case PageParams.ITEM_TYPE_MOVIE_GENRES:
                        ContentListActivity.newInstance(mContext, model, PageParams.CATEGORY_TYPE_MOVIE_GENRES);
                        break;
                    case PageParams.ITEM_TYPE_TV_GENRES:
                        ContentListActivity.newInstance(mContext, model, PageParams.CATEGORY_TYPE_TV_GENRES);
                        break;
                    case PageParams.ITEM_TYPE_COMPANIES:
                        break;
                    case PageParams.ITEM_TYPE_POSTERS:
                        List<String> urls = new ArrayList<>();
                        for (int i = 0; i < mDatas.size(); i++) {
                            urls.add(mDatas.get(i).orignalPoster.get());
                        }
                        ImagePageActivity.newInstance(mContext, urls, finalPosition, PageParams.ITEM_TYPE_POSTERS);
                        break;
                    case PageParams.ITEM_TYPE_BAKCDROPS:
                        urls = new ArrayList<>();
                        for (int i = 0; i < mDatas.size(); i++) {
                            urls.add(mDatas.get(i).orignalBackdrop.get());
                        }
                        ImagePageActivity.newInstance(mContext, urls, finalPosition, PageParams.ITEM_TYPE_BAKCDROPS);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    private void loadImage(ImageView imageView, int position, ItemMediaModel model) {
        String url = model.images.get();
        imageView.setTag(R.id.image_url, url);
//        if (true) {
        if (position < 3) {
            GlideUtils.getInstance().add(model.images.get(), imageView);
        } else {
            Glide.with(imageView.getContext())
                    .load(model.images.get())
                    .asBitmap()
//                    .skipMemoryCache(true)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Log.d(TAG, "[Ciel_Debug] onResourceReady: ");
                            if (TextUtils.equals(String.valueOf(imageView.getTag(R.id.image_url)), url)) {
                                imageView.setImageBitmap(resource);
                            } else {
                                Log.d(TAG, "[Ciel_Debug] onResourceReady: Tag 不相同");
                            }
                        }
                    });
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private static class MediaHolder extends RecyclerView.ViewHolder {

        ItemMediaBinding mBinding;

        public MediaHolder(@NonNull ItemMediaBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class MediaSingleHolder extends RecyclerView.ViewHolder {

        ItemMediaSingleBinding mBinding;

        public MediaSingleHolder(@NonNull ItemMediaSingleBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class MediaHoriHolder extends RecyclerView.ViewHolder {

        ItemMediaHoriBinding mBinding;

        public MediaHoriHolder(@NonNull ItemMediaHoriBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class ImageHoriHolder extends RecyclerView.ViewHolder {

        ItemImageHoriBinding mBinding;

        public ImageHoriHolder(@NonNull ItemImageHoriBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class BackdropsSingleHolder extends RecyclerView.ViewHolder {

        ItemBackdropsSingleBinding mBinding;

        public BackdropsSingleHolder(@NonNull ItemBackdropsSingleBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class PeopleHolder extends RecyclerView.ViewHolder {

        ItemPeopleBinding mBinding;

        public PeopleHolder(@NonNull ItemPeopleBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class PeopleHoriHolder extends RecyclerView.ViewHolder {

        ItemPeopleHoriBinding mBinding;

        public PeopleHoriHolder(@NonNull ItemPeopleHoriBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class ReviewHolder extends RecyclerView.ViewHolder {

        ItemReviewBinding mBinding;

        public ReviewHolder(@NonNull ItemReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class ClickableButtonHolder extends RecyclerView.ViewHolder {

        ItemClickableButtonBinding mBinding;

        public ClickableButtonHolder(@NonNull ItemClickableButtonBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class SeasonsHolder extends RecyclerView.ViewHolder {

        ItemSeasonsBinding mBinding;

        public SeasonsHolder(@NonNull ItemSeasonsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class EpisodesHolder extends RecyclerView.ViewHolder {

        ItemEpisodesBinding mBinding;

        public EpisodesHolder(@NonNull ItemEpisodesBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class ReleaseInfoHolder extends RecyclerView.ViewHolder {

        ItemReleaseInfoBinding mBinding;

        public ReleaseInfoHolder(@NonNull ItemReleaseInfoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class NetworkInfoHolder extends RecyclerView.ViewHolder {

        ItemNetworkBinding mBinding;

        public NetworkInfoHolder(@NonNull ItemNetworkBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private static class CompanyHolder extends RecyclerView.ViewHolder {

        ItemCompanyBinding mBinding;

        public CompanyHolder(@NonNull ItemCompanyBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public List<ItemMediaModel> getDatas() {
        return mDatas;
    }

    public void setDatas(List<ItemMediaModel> datas, int firstVisibleItemPosition) {
        int oldPostion = mDatas.size();
        mDatas.clear();
        mDatas.addAll(datas);

        if (firstVisibleItemPosition == RecyclerView.NO_POSITION) {
            notifyItemRangeChanged(oldPostion, datas.size() - oldPostion);
        } else {
            notifyItemRangeChanged(oldPostion + firstVisibleItemPosition, mDatas.size() - oldPostion);
        }
    }

    public void setDatas(List<ItemMediaModel> datas) {
        setDatas(datas, RecyclerView.NO_POSITION);
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        ImageView imageView = null;
        if (holder instanceof MediaHolder) {
            imageView = ((MediaHolder) holder).mBinding.ivCover;
        } else if (holder instanceof MediaSingleHolder) {
            imageView = ((MediaSingleHolder) holder).mBinding.ivCover;
        } else if (holder instanceof ImageHoriHolder) {
            imageView = ((ImageHoriHolder) holder).mBinding.ivCover;
        } else if (holder instanceof PeopleHolder) {
            imageView = ((PeopleHolder) holder).mBinding.ivCover;
        } else if (holder instanceof PeopleHoriHolder) {
            imageView = ((PeopleHoriHolder) holder).mBinding.ivCover;
        } else if (holder instanceof SeasonsHolder) {
            imageView = ((SeasonsHolder) holder).mBinding.ivCover;
        } else if (holder instanceof EpisodesHolder) {
            imageView = ((EpisodesHolder) holder).mBinding.ivCover;
        } else if (holder instanceof NetworkInfoHolder) {
            imageView = ((NetworkInfoHolder) holder).mBinding.ivCover;
        } else if (holder instanceof CompanyHolder) {
            imageView = ((CompanyHolder) holder).mBinding.ivCover;
        }
        if (imageView != null) {
            Glide.clear(imageView);
        }
    }
}

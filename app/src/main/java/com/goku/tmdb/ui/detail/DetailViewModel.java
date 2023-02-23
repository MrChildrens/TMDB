package com.goku.tmdb.ui.detail;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.blongho.country_data.World;
import com.goku.tmdb.R;
import com.goku.tmdb.app.AppManager;
import com.goku.tmdb.app.Constant;
import com.goku.tmdb.app.PageParams;
import com.goku.tmdb.app.ScreenUtil;
import com.goku.tmdb.app.TmdbApplication;
import com.goku.tmdb.app.Utils;
import com.goku.tmdb.base.BaseContentViewModel;
import com.goku.tmdb.data.TmdbRepository;
import com.goku.tmdb.data.entity.AccountStates;
import com.goku.tmdb.data.entity.AlternativeTitles;
import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.Genres;
import com.goku.tmdb.data.entity.GuestStars;
import com.goku.tmdb.data.entity.Keyword;
import com.goku.tmdb.data.entity.ProductionCompanies;
import com.goku.tmdb.data.entity.ProductionCountries;
import com.goku.tmdb.data.entity.StatusBean;
import com.goku.tmdb.data.entity.Video;
import com.goku.tmdb.data.entity.YoutubeVideo;
import com.goku.tmdb.data.entity.configuration.Languages;
import com.goku.tmdb.data.entity.movie.MediaImages;
import com.goku.tmdb.data.entity.movie.MovieDetail;
import com.goku.tmdb.data.entity.movie.MovieReleaseDates;
import com.goku.tmdb.data.entity.person.PersonDetail;
import com.goku.tmdb.data.entity.tv.TvDetail;
import com.goku.tmdb.data.entity.tvepisodes.EpisodesDetail;
import com.goku.tmdb.data.entity.tvseasons.SeasonDetail;
import com.goku.tmdb.ui.home.ItemCategoryModel;
import com.goku.tmdb.ui.home.ItemMediaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

public class DetailViewModel extends BaseContentViewModel {
    private static final String TAG = DetailViewModel.class.getSimpleName();

    private List<ItemMediaModel> mMediaModels = new ArrayList<>();
    public ObservableField<List<ItemCategoryModel>> categoryDatas = new ObservableField<>(new ArrayList<>());

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> releaseDate = new ObservableField<>();
    public ObservableField<String> subTitles = new ObservableField<>();
    public ObservableField<String> smallTitles = new ObservableField<>();
    public ObservableField<String> backdrop = new ObservableField<>();
    public ObservableField<String> poster = new ObservableField<>();
    public ObservableField<Double> voteAverage = new ObservableField<>();
    public ObservableField<String> voteCount = new ObservableField<>("0");
    public ObservableField<Double> popularity = new ObservableField<>();
    public ObservableField<Boolean> isHideVote = new ObservableField<>(false);
    public ObservableField<Boolean> isFav = new ObservableField<>(false);
    public ObservableField<Boolean> isShowFav = new ObservableField<>(false);
    public ObservableField<Boolean> isBookmark = new ObservableField<>(false);
    public ObservableField<Boolean> isShowBookmark = new ObservableField<>(false);
    public ObservableField<Boolean> isRating = new ObservableField<>(false);
    public ObservableField<Boolean> isShowRating = new ObservableField<>(false);
    public ObservableField<Float> userRating = new ObservableField<>();

    public View.OnClickListener onFavClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!Utils.isLogin()) {
                Toast.makeText(AppManager.getAppManager().currentActivity(), R.string.login_tip, Toast.LENGTH_SHORT).show();
                return;
            }
            String mediaType = "";
            if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                    || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
                mediaType = Constant.MEDIA_TYPE_MOVIE;
            } else if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                    || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
                mediaType = Constant.MEDIA_TYPE_TV;
            }
            addSubscribe(mModel.favorite(mediaType, mItemMediaModel.getId(), !isFav.get())
                    .subscribe(new Consumer<>() {
                        @Override
                        public void accept(StatusBean statusBean) throws Exception {
                            Log.d(TAG, "[Ciel_Debug] accept: " + statusBean.toString());
                            if (statusBean.isSuccess()) {
                                isFav.set(!isFav.get());
                                Toast.makeText(AppManager.getAppManager().currentActivity(),
                                        Utils.getContext().getString(R.string.successful_operation),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AppManager.getAppManager().currentActivity(),
                                        Utils.getContext().getString(R.string.operation_failed),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Consumer<>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Toast.makeText(AppManager.getAppManager().currentActivity(),
                                    throwable.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }));
        }
    };

    public View.OnClickListener onBookmarkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!Utils.isLogin()) {
                return;
            }
            String mediaType = "";
            if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                    || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
                mediaType = Constant.MEDIA_TYPE_MOVIE;
            } else if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                    || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
                mediaType = Constant.MEDIA_TYPE_TV;
            }
            addSubscribe(mModel.watchlist(mediaType, mItemMediaModel.getId(), !isBookmark.get())
                    .subscribe(new Consumer<>() {
                        @Override
                        public void accept(StatusBean statusBean) throws Exception {
                            Log.d(TAG, "[Ciel_Debug] accept: " + statusBean.toString());
                            if (statusBean.isSuccess()) {
                                isBookmark.set(!isBookmark.get());
                                Toast.makeText(AppManager.getAppManager().currentActivity(),
                                        Utils.getContext().getString(R.string.successful_operation),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AppManager.getAppManager().currentActivity(),
                                        Utils.getContext().getString(R.string.operation_failed),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Consumer<>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Toast.makeText(AppManager.getAppManager().currentActivity(),
                                    throwable.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }));
        }
    };

    public MutableLiveData<DetailViewModel> showRatingDialog = new MutableLiveData<>();

    public View.OnClickListener onRatingmarkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!Utils.isLogin()) {
                return;
            }
            showRatingDialog.postValue(DetailViewModel.this);
        }
    };

    public void rating(float rating) {
        Observable<StatusBean> ratingObservable = null;
        if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
            ratingObservable = mModel.ratingMovie(mItemMediaModel.getId(), rating);
        } else if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
            ratingObservable = mModel.ratingTv(mItemMediaModel.getId(), rating);
        }
        addSubscribe(ratingObservable.subscribe(new Consumer<StatusBean>() {
            @Override
            public void accept(StatusBean statusBean) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + statusBean.toString());
                if (statusBean.isSuccess()) {
                    Toast.makeText(AppManager.getAppManager().currentActivity(),
                            Utils.getContext().getString(R.string.successful_rating),
                            Toast.LENGTH_SHORT).show();
                    isRating.set(true);
                    userRating.set(rating);
                } else {
                    Toast.makeText(AppManager.getAppManager().currentActivity(),
                            Utils.getContext().getString(R.string.rating_failed),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(AppManager.getAppManager().currentActivity(),
                        throwable.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void removeRating() {
        Observable<StatusBean> removeRatingObservable = null;
        if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
            removeRatingObservable = mModel.removeMovieRating(mItemMediaModel.getId());
        } else if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
            removeRatingObservable = mModel.removeTvRating(mItemMediaModel.getId());
        }
        addSubscribe(removeRatingObservable.subscribe(new Consumer<StatusBean>() {
            @Override
            public void accept(StatusBean statusBean) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + statusBean.toString());
                if (statusBean.isSuccess()) {
                    Toast.makeText(AppManager.getAppManager().currentActivity(),
                            Utils.getContext().getString(R.string.delete_rating_successfully),
                            Toast.LENGTH_SHORT).show();
                    isRating.set(false);
                    userRating.set(null);
                } else {
                    Toast.makeText(AppManager.getAppManager().currentActivity(),
                            Utils.getContext().getString(R.string.delete_rating_failed),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(AppManager.getAppManager().currentActivity(),
                        throwable.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public final MutableLiveData<ObservableField<List<ItemCategoryModel>>> refreshAbout = new MutableLiveData<>();

    public DetailViewModel(@NonNull Application application, TmdbRepository model) {
        super(application, model);
    }

    public void initMovieDetail(ItemMediaModel mediaModel) {
        mItemMediaModel = mediaModel;
        if (!TextUtils.isEmpty(mediaModel.getTitle())) {
            title.set(mediaModel.getTitle());
        }

        if (!TextUtils.isEmpty(mediaModel.getOriginalTitle())) {
            subTitles.set(mediaModel.getOriginalTitle() + "(" + Utils.getYear(mediaModel.getReleaseDate()) + ")");
        }

        if (!TextUtils.isEmpty(mediaModel.getBackdropPath())) {
            backdrop.set(Utils.appendUrl(mediaModel.getBackdropPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getBackdropSizes(),
                    (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance())));
        }

        if (!TextUtils.isEmpty(mediaModel.getPosterPath())) {
            poster.set(Utils.appendUrl(mediaModel.getPosterPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getPosterSizes(),
                    R.dimen.category_vertical_image_width));
        }

        if (mediaModel.getVoteAverage() != null) {
            voteAverage.set(Utils.formatDouble(mediaModel.getVoteAverage()));
        }
        if (mediaModel.getVoteCount() != null) {
            voteCount.set(String.valueOf(mediaModel.getVoteCount()));
        }
        if (mediaModel.getPopularity() != null) {
            popularity.set(Utils.formatDouble(mediaModel.getPopularity()));
        }
    }

    public void initTvDetail(ItemMediaModel mediaModel) {
        mItemMediaModel = mediaModel;
        if (!TextUtils.isEmpty(mediaModel.getTitle())) {
            title.set(mediaModel.getTitle());
        }
        if (!TextUtils.isEmpty(mediaModel.getOriginalTitle())) {
            subTitles.set(mediaModel.getOriginalTitle() + "(" + Utils.getYear(mediaModel.getReleaseDate()) + ")");
        }

        if (!TextUtils.isEmpty(mediaModel.getBackdropPath())) {
            backdrop.set(Utils.appendUrl(mediaModel.getBackdropPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getBackdropSizes(),
                    (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance())));
        }

        if (!TextUtils.isEmpty(mediaModel.getPosterPath())) {
            poster.set(Utils.appendUrl(mediaModel.getPosterPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getPosterSizes(),
                    R.dimen.category_vertical_image_width));
        }

        if (mediaModel.getVoteAverage() != null) {
            voteAverage.set(Utils.formatDouble(mediaModel.getVoteAverage()));
        }
        if (mediaModel.getVoteCount() != null) {
            voteCount.set(String.valueOf(mediaModel.getVoteCount()));
        }
        if (mediaModel.getPopularity() != null) {
            popularity.set(Utils.formatDouble(mediaModel.getPopularity()));
        }
    }

    public void initSeasonDetail(ItemMediaModel mediaModel) {
        mItemMediaModel = mediaModel;

        String name = "";
        if (mediaModel.getSeasonNumber() == 0) {
            name = mediaModel.getTvTitle() + "●" + getString(R.string.specials);
        } else {
            name = mediaModel.getTvTitle() + "●" +
                    TmdbApplication.getInstance().getResources().getString(R.string.season_number_format, mediaModel.getSeasonNumber())
            ;
        }
        if (!TextUtils.isEmpty(name)) {
            title.set(name);
        }
        if (!TextUtils.isEmpty(mediaModel.getTitle())) {
            subTitles.set(mediaModel.getTitle());
        }

        if (!TextUtils.isEmpty(mediaModel.getBackdropPath())) {
            backdrop.set(Utils.appendUrl(mediaModel.getBackdropPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getBackdropSizes(),
                    (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance())));
        }

        if (!TextUtils.isEmpty(mediaModel.getPosterPath())) {
            poster.set(Utils.appendUrl(mediaModel.getPosterPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getPosterSizes(),
                    R.dimen.category_vertical_image_width));
        }

        isShowFav.set(false);
        isShowBookmark.set(false);
        isShowRating.set(false);

        if (mediaModel.getVoteAverage() != null) {
            voteAverage.set(Utils.formatDouble(mediaModel.getVoteAverage()));
        }
        if (mediaModel.getVoteCount() != null) {
            voteCount.set(String.valueOf(mediaModel.getVoteCount()));
        }
        if (mediaModel.getPopularity() != null) {
            popularity.set(Utils.formatDouble(mediaModel.getPopularity()));
        }
    }

    public void initEpisodeDetail(ItemMediaModel mediaModel) {
        mItemMediaModel = mediaModel;

        title.set(mediaModel.getTvTitle() + "●" +
                Utils.formatSeasonName(mediaModel.getSeasonNumber()) + "●" + Utils.formatEpisodeName(mediaModel.getEpisodeNumber()));
        subTitles.set(mediaModel.getTitle());

        if (!TextUtils.isEmpty(mediaModel.getPosterPath())) {
            backdrop.set(Utils.appendUrl(mediaModel.getPosterPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getStillSizes(),
                    (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance())));
        }

        if (!TextUtils.isEmpty(mediaModel.getPosterPath())) {
            poster.set(Utils.appendUrl(mediaModel.getPosterPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getStillSizes(),
                    R.dimen.category_vertical_image_width));
        }

        isShowFav.set(false);
        isShowBookmark.set(false);
        isShowRating.set(false);

        if (mediaModel.getVoteAverage() != null) {
            voteAverage.set(Utils.formatDouble(mediaModel.getVoteAverage()));
        }
        if (mediaModel.getVoteCount() != null) {
            voteCount.set(String.valueOf(mediaModel.getVoteCount()));
        }
        if (mediaModel.getPopularity() != null) {
            popularity.set(Utils.formatDouble(mediaModel.getPopularity()));
        }
    }

    public void initPersonDetail(ItemMediaModel mediaModel) {
        mItemMediaModel = mediaModel;

        title.set(mediaModel.getTitle());

        if (!TextUtils.isEmpty(mediaModel.getPosterPath())) {
            backdrop.set(Utils.appendUrl(mediaModel.getPosterPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getStillSizes(),
                    (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance())));
        }

        if (!TextUtils.isEmpty(mediaModel.getPosterPath())) {
            poster.set(Utils.appendUrl(mediaModel.getPosterPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getStillSizes(),
                    R.dimen.category_vertical_image_width));
        }

        isShowFav.set(false);
        isShowBookmark.set(false);
        isShowRating.set(false);

        if (mediaModel.getVoteAverage() != null) {
            voteAverage.set(Utils.formatDouble(mediaModel.getVoteAverage()));
        }
        if (mediaModel.getVoteCount() != null) {
            voteCount.set(String.valueOf(mediaModel.getVoteCount()));
        }
        if (mediaModel.getPopularity() != null) {
            popularity.set(Utils.formatDouble(mediaModel.getPopularity()));
        }
    }

    private List<ItemCategoryModel> handleMovieDetail(MovieDetail movieDetail, List<ItemMediaModel> videosDatas) {
        List<ItemCategoryModel> datas = new ArrayList<>();

        StringBuilder smallTitlesStr = appendSmalltitle(movieDetail);
        smallTitles.set(smallTitlesStr.toString());

        if (!TextUtils.isEmpty(movieDetail.getTagline())) {
            datas.add(addVerticalTextItem("", movieDetail.getTagline()));
        }

        if (!TextUtils.isEmpty(movieDetail.getOverview())) {
            datas.add(addVerticalTextItem(getString(R.string.overview), movieDetail.getOverview()));
        } else {
            datas.add(addVerticalTextItem(getString(R.string.overview), getString(R.string.no_overview_tip)));
        }

        List<ItemMediaModel> genresDatas = getGenresDatas(movieDetail.getGenres(), PageParams.ITEM_TYPE_MOVIE_GENRES);
        if (genresDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.genres), genresDatas));
        }

        List<ItemMediaModel> alternativeTitlesDatas = getAlternativeTitlesDatas(movieDetail.getAlternativeTitles().getTitles());
        if (alternativeTitlesDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.alternative_titles), alternativeTitlesDatas));
        }

        datas.add(addMainTitleItem(getString(R.string.movie_info)));

        List<ItemMediaModel> releaseInfoDatas = getReleaseInfoDatas(movieDetail.getMovieReleaseDates(), movieDetail.getProductionCountries());
        if (releaseInfoDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.release_info), releaseInfoDatas));
        }

        datas.add(addHoriTextItem(getString(R.string.original_title), Utils.showIfEmptySymbol(movieDetail.getOriginalTitle())));

        if (!TextUtils.isEmpty(movieDetail.getOriginalLanguage())) {
            datas.add(addHoriTextItem(getString(R.string.original_language), getLanguage(movieDetail.getOriginalLanguage())));
        }

        if (!TextUtils.isEmpty(movieDetail.getStatus())) {
            datas.add(addHoriTextItem(getString(R.string.status), getStatusStr(movieDetail.getStatus())));
        }

        if (movieDetail.getRuntime() != null) {
            datas.add(addHoriTextItem(getString(R.string.runtime), Utils.formatRuntime(movieDetail.getRuntime())));
        }

        if (movieDetail.getMovieReleaseDates() != null) {
            datas.add(addHoriTextItem(getString(R.string.certification), getCertificationStr(movieDetail.getMovieReleaseDates(), movieDetail.getProductionCountries())));
        }

        if (movieDetail.getBudget() != null) {
            datas.add(addHoriTextItem(getString(R.string.budget), "$ " + Utils.showIfEmptySymbol(Utils.numberAddComma(movieDetail.getBudget()))));
        }

        if (movieDetail.getRevenue() != null) {
            datas.add(addHoriTextItem(getString(R.string.revenue), "$ " + Utils.showIfEmptySymbol(Utils.numberAddComma(movieDetail.getRevenue()))));
        }

        List<ItemMediaModel> countriesDatas = getCountriesDatas(movieDetail.getProductionCountries());
        if (countriesDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.production_countries), countriesDatas));
        }

        List<ItemMediaModel> companiesDatas = getCompaniesDatas(movieDetail.getProductionCompanies());
        if (companiesDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.companies), companiesDatas));
        }

        List<ItemMediaModel> keywordsDatas = getKeywordsDatas(movieDetail.getMovieKeywords().getKeywords());
        if (keywordsDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.keywords), keywordsDatas));
        }

        if (movieDetail.getBelongsToCollection() != null) {
            datas.add(addImageItem(movieDetail.getBelongsToCollection().getId(), movieDetail.getBelongsToCollection().getName(), Utils.appendUrl(movieDetail.getBelongsToCollection().getBackdropPath(),
                    TmdbApplication.getInstance().getConfiguration().getImages().getBackdropSizes(),
                    (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance()))));
        }

        if (videosDatas.size() > 0) {
            datas.add(addMainTitleItem(getString(R.string.video)));
            datas.add(addSubTitleListItem("", videosDatas));
        }

        List<ItemMediaModel> postersDatas = getPostersDatas(movieDetail.getMovieImages());
        if (postersDatas.size() > 0) {
            datas.add(addMainTitleListItem(getString(R.string.posters), postersDatas, true));
        }

        List<ItemMediaModel> backdropsDatas = getBackdropsDatas(movieDetail.getMovieImages());
        List<ItemMediaModel> logosDatas = getLogosDatas(movieDetail.getMovieImages());
        backdropsDatas.addAll(logosDatas);
        if (backdropsDatas.size() > 0) {
            datas.add(addMainTitleListItem(getString(R.string.backdrops), backdropsDatas, true));
        }
        for (int i = 0; i < backdropsDatas.size(); i++) {
            Log.d(TAG, "[Ciel_Debug] handleMovieDetail: " + backdropsDatas.get(i).orignalBackdrop.get());
        }

        return datas;
    }

    @NonNull
    private StringBuilder appendSmalltitle(MovieDetail movieDetail) {
        StringBuilder smallTitlesStr = new StringBuilder();
        smallTitlesStr.append(Utils.formatDate(movieDetail.getReleaseDate()));
        smallTitlesStr.append("\n");

        if (movieDetail.getGenres() != null) {
            for (Genres genre : movieDetail.getGenres()) {
                smallTitlesStr.append(genre.getName());
                smallTitlesStr.append(",");
            }
        }
        return smallTitlesStr;
    }

    private List<ItemCategoryModel> handleTvDetail(TvDetail tvDetail, List<ItemMediaModel> videosDatas) {
        List<ItemCategoryModel> datas = new ArrayList<>();

        StringBuilder smallTitlesStr = appendSmallTitle(tvDetail);
        smallTitles.set(smallTitlesStr.toString());

        if (!TextUtils.isEmpty(tvDetail.getTagline())) {
            datas.add(addVerticalTextItem("", tvDetail.getTagline()));
        }

        if (!TextUtils.isEmpty(tvDetail.getOverview())) {
            datas.add(addVerticalTextItem(getString(R.string.overview), tvDetail.getOverview()));
        } else {
            datas.add(addVerticalTextItem(getString(R.string.overview), getString(R.string.no_overview_tip)));
        }

        List<ItemMediaModel> genresDatas = getGenresDatas(tvDetail.getGenres(), PageParams.ITEM_TYPE_TV_GENRES);
        if (genresDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.genres), genresDatas));
        }

        List<ItemMediaModel> networkDatas = getNetworkDatas(tvDetail.getNetworks());
        if (networkDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.network), networkDatas));
        }

        if (tvDetail.getLastEpisodeToAir() != null) {
            TvDetail.LastEpisodeToAir lastEpisodeToAir = tvDetail.getLastEpisodeToAir();
            String content = Utils.formatSeasonName(lastEpisodeToAir.getSeasonNumber()) + Utils.formatEpisodeName(lastEpisodeToAir.getSeasonNumber())
                    + "-" + Utils.formatDate(lastEpisodeToAir.getAirDate());
            datas.add(addEpisodeItem(lastEpisodeToAir.getId(), getString(R.string.last_episode_to_air), lastEpisodeToAir.getName(), content,
                    Utils.appendUrl(lastEpisodeToAir.getStillPath(),
                            TmdbApplication.getInstance().getConfiguration().getImages().getStillSizes(),
                            R.dimen.episode_item_width)));
        }

        if (tvDetail.getNextEpisodeToAir() != null) {
            TvDetail.LastEpisodeToAir nextEpisodeToAir = tvDetail.getNextEpisodeToAir();
            String content = Utils.formatSeasonName(nextEpisodeToAir.getSeasonNumber()) + Utils.formatEpisodeName(nextEpisodeToAir.getEpisodeNumber())
                    + "-" + Utils.formatDate(nextEpisodeToAir.getAirDate());
            datas.add(addEpisodeItem(nextEpisodeToAir.getId(), getString(R.string.next_episode_to_air), nextEpisodeToAir.getName(), content,
                    Utils.appendUrl(nextEpisodeToAir.getStillPath(),
                            TmdbApplication.getInstance().getConfiguration().getImages().getStillSizes(),
                            R.dimen.episode_item_width)));
        }

        List<ItemMediaModel> alternativeTitlesDatas = getAlternativeTitlesDatas(tvDetail.getAlternativeTitles().getResults());
        if (alternativeTitlesDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.alternative_titles), alternativeTitlesDatas));
        }

        datas.add(addMainTitleItem(getString(R.string.tv_info)));

        datas.add(addHoriTextItem(getString(R.string.original_title), Utils.showIfEmptySymbol(tvDetail.getOriginalName())));

        if (tvDetail.getNumberOfEpisodes() != null) {
            datas.add(addHoriTextItem(getString(R.string.aired_episodes), String.valueOf(tvDetail.getNumberOfEpisodes())));
        }

        if (!TextUtils.isEmpty(tvDetail.getFirstAirDate())) {
            datas.add(addHoriTextItem(getString(R.string.first_air_date), Utils.formatDate(tvDetail.getFirstAirDate())));
        }

        if (!TextUtils.isEmpty(tvDetail.getLastAirDate())) {
            datas.add(addHoriTextItem(getString(R.string.last_air_date), Utils.formatDate(tvDetail.getLastAirDate())));
        }

        if (!TextUtils.isEmpty(tvDetail.getOriginalLanguage())) {
            datas.add(addHoriTextItem(getString(R.string.original_language), getLanguage(tvDetail.getOriginalLanguage())));
        }

        if (!TextUtils.isEmpty(tvDetail.getStatus())) {
            datas.add(addHoriTextItem(getString(R.string.status), tvDetail.getStatus()));
        }

        if (tvDetail.getEpisodeRunTime() != null && tvDetail.getEpisodeRunTime().size() > 0) {
            datas.add(addHoriTextItem(getString(R.string.runtime), tvDetail.getEpisodeRunTime().get(0).toString()));
        }

        List<ItemMediaModel> countriesDatas = getCountriesDatas(tvDetail.getProductionCountries());
        if (countriesDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.production_countries), countriesDatas));
        }

        List<ItemMediaModel> companiesDatas = getCompaniesDatas(tvDetail.getProductionCompanies());
        if (companiesDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.companies), companiesDatas));
        }

        List<ItemMediaModel> keywordsDatas = getKeywordsDatas(tvDetail.getKeywords().getResults());
        if (keywordsDatas.size() > 0) {
            datas.add(addSubTitleListItem(getString(R.string.keywords), keywordsDatas));
        }

        if (videosDatas.size() > 0) {
            datas.add(addMainTitleItem(getString(R.string.video)));
            datas.add(addSubTitleListItem("", videosDatas));
        }

        List<ItemMediaModel> postersDatas = getPostersDatas(tvDetail.getMediaImages());
        if (postersDatas.size() > 0) {
            datas.add(addMainTitleListItem(getString(R.string.posters), postersDatas, true));
        }

        List<ItemMediaModel> backdropsDatas = getBackdropsDatas(tvDetail.getMediaImages());
        List<ItemMediaModel> logosDatas = getLogosDatas(tvDetail.getMediaImages());
        backdropsDatas.addAll(logosDatas);
        if (backdropsDatas.size() > 0) {
            datas.add(addMainTitleListItem(getString(R.string.backdrops), backdropsDatas, true));
        }

        return datas;
    }

    @NonNull
    private StringBuilder appendSmallTitle(TvDetail tvDetail) {
        StringBuilder smallTitlesStr = new StringBuilder();
        smallTitlesStr.append(Utils.formatDate(tvDetail.getFirstAirDate()));
        smallTitlesStr.append("\n");

        if (tvDetail.getGenres() != null) {
            for (Genres genre : tvDetail.getGenres()) {
                smallTitlesStr.append(genre.getName());
                smallTitlesStr.append(",");
            }
        }
        return smallTitlesStr;
    }

    private String getStatusStr(String status) {
        switch (status) {
            case Constant.RELEASED:
                return getString(R.string.released);
            case Constant.ENDED:
                return getString(R.string.ended);
            default:
                return status;
        }
    }

    public void getDetail(ItemMediaModel itemMediaModel) {
        if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE
                || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_MOIVE_HORI) {
            getMovieDetail(itemMediaModel);
        } else if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW
                || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_TV_SHOW_HORI) {
            getTvDetail(itemMediaModel);
        } else if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_PEOPLE
                || mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_PEOPLE_HORI) {
            getPeopleDetail(itemMediaModel);
        } else if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_SEASONS) {
            getSeasonsDetail(itemMediaModel);
        } else if (mItemMediaModel.getItemType() == PageParams.ITEM_TYPE_EPISODES) {
            getEpisodeDetail(itemMediaModel);
        }
    }

    private void getMovieDetail(ItemMediaModel itemMediaModel) {
        Observable<MediaImages> movieImage = null;
        if (!Utils.getLanguage().contains(Locale.ENGLISH.getLanguage())) {
            movieImage = Observable.zip(mModel.getMovieImages(itemMediaModel.getId(), Utils.getLanguage()),
                    mModel.getMovieImages(itemMediaModel.getId(), "null"),
                    mModel.getMovieImages(itemMediaModel.getId(), Locale.ENGLISH.getLanguage()),
                    new Function3<>() {
                        @Override
                        public MediaImages apply(MediaImages localMediaImages, MediaImages noLanguageMediaImages, MediaImages enMediaImages) throws Exception {
                            imageOverlay(localMediaImages, noLanguageMediaImages);
                            imageOverlay(localMediaImages, enMediaImages);
                            return localMediaImages;
                        }
                    });
        } else {
            movieImage = mModel.getMovieImages(itemMediaModel.getId(), Utils.getLanguage());
        }

        Observable<MovieDetail> flatMap = null;

        Observable<MovieDetail> movieDetailObservable =
                Observable.zip(mModel.getMovieDetail(itemMediaModel.getId()),
                        movieImage,
                        mModel.getMovieVideos(itemMediaModel.getId()),
                        new Function3<>() {
                            @Override
                            public MovieDetail apply(MovieDetail movieDetail, MediaImages mediaImages, BaseResultBean<Video> videoBean) throws Exception {
                                if (movieDetail != null) {
                                    imageOverlay(movieDetail.getMovieImages(), mediaImages);
                                }

                                if (movieDetail != null) {
                                    videoOverlay(movieDetail.getVideoBean(), videoBean);
                                }
                                return movieDetail;
                            }
                        });

        if (!TextUtils.isEmpty(Utils.getSessionId())) {
            flatMap = mModel.getMovieAccountStates(itemMediaModel.getId())
                    .flatMap(new Function<AccountStates, Observable<MovieDetail>>() {
                        @Override
                        public Observable<MovieDetail> apply(AccountStates accountStates) throws Exception {
                            handleAccountStates(accountStates);
                            return movieDetailObservable;
                        }
                    });
        } else {
            flatMap = movieDetailObservable;
        }
        addSubscribe(flatMap.subscribe(new Consumer<>() {
            @Override
            public void accept(MovieDetail movieDetail) throws Exception {
                showMovieDetail(movieDetail);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                statusModel.onErrorClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                        getMovieDetail(itemMediaModel);
                    }
                };
                statusModel.dataStatus.set(Constant.DATA_STATUS_ERROR);
                refreshAbout.postValue(categoryDatas);
            }
        }));
    }

    private void handleAccountStates(AccountStates accountStates) {
        if (accountStates != null) {
            isFav.set(accountStates.isFavorite());
            isShowFav.set(true);
            isBookmark.set(accountStates.isWatchlist());
            isShowBookmark.set(true);
            if (accountStates.getRated() != null) {
                isRating.set(true);
                userRating.set(accountStates.getRated().getValue());
            } else {
                isRating.set(false);
            }
            isShowRating.set(true);
        }
    }

    private void imageOverlay(MediaImages movieDetail, MediaImages mediaImages) {
        if (movieDetail != null && mediaImages != null) {
            if (movieDetail.getBackdrops() == null) {
                movieDetail.setBackdrops(new ArrayList<>());
            }
            if (movieDetail.getPosters() == null) {
                movieDetail.setPosters(new ArrayList<>());
            }
            if (movieDetail.getProfiles() == null) {
                movieDetail.setProfiles(new ArrayList<>());
            }
            if (movieDetail.getStills() == null) {
                movieDetail.setStills(new ArrayList<>());
            }
            if (movieDetail.getLogos() == null) {
                movieDetail.setLogos(new ArrayList<>());
            }

            if (mediaImages.getBackdrops() != null) {
                Log.d(TAG, "[Ciel_Debug] imageOverlay: getBackdrops: " + mediaImages.getBackdrops().size());
                movieDetail.getBackdrops().addAll(mediaImages.getBackdrops());
            }
            if (movieDetail.getPosters() != null && mediaImages.getPosters() != null) {
                Log.d(TAG, "[Ciel_Debug] imageOverlay: getPosters: " + mediaImages.getPosters().size());
                movieDetail.getPosters().addAll(mediaImages.getPosters());
            }
            if (movieDetail.getProfiles() != null && mediaImages.getProfiles() != null) {
                Log.d(TAG, "[Ciel_Debug] imageOverlay: getProfiles: " + mediaImages.getProfiles().size());
                movieDetail.getProfiles().addAll(mediaImages.getProfiles());
            }
            if (movieDetail.getStills() != null && mediaImages.getStills() != null) {
                Log.d(TAG, "[Ciel_Debug] imageOverlay: getStills: " + mediaImages.getStills().size());
                movieDetail.getStills().addAll(mediaImages.getStills());
            }
            if (movieDetail.getLogos() != null && mediaImages.getLogos() != null) {
                Log.d(TAG, "[Ciel_Debug] imageOverlay: getLogos: " + mediaImages.getLogos().size());
                movieDetail.getLogos().addAll(mediaImages.getLogos());
            }
        }
    }

    private void videoOverlay(BaseResultBean<Video> originVideo, BaseResultBean<Video> videoBaseResultBean) {
        if (originVideo != null && videoBaseResultBean != null) {
            if (originVideo.getResults() != null && videoBaseResultBean.getResults() != null) {
                originVideo.getResults().addAll(videoBaseResultBean.getResults());
            }
        }
    }

    private void getTvDetail(ItemMediaModel itemMediaModel) {
        Observable<MediaImages> movieImageObservable =
                Observable.zip(mModel.getTvImages(itemMediaModel.getId(), Utils.getLanguage()),
                        mModel.getTvImages(itemMediaModel.getId(), Locale.ENGLISH.getLanguage()),
                        mModel.getTvImages(itemMediaModel.getId(), "null"),
                        new Function3<>() {
                            @Override
                            public MediaImages apply(MediaImages localMediaImages, MediaImages noLanguageMediaImages, MediaImages enMediaImages) throws Exception {
                                imageOverlay(localMediaImages, noLanguageMediaImages);
                                if (!Utils.getLanguage().contains(Locale.ENGLISH.getLanguage())) {
                                    imageOverlay(localMediaImages, enMediaImages);
                                }
                                return localMediaImages;
                            }
                        });

        Observable<TvDetail> tvDetailObservable =
                Observable.zip(mModel.getTvDetial(itemMediaModel.getId()),
                        movieImageObservable,
                        mModel.getTvVideos(itemMediaModel.getId()), new Function3<>() {
                            @Override
                            public TvDetail apply(TvDetail tvDetail, MediaImages mediaImages, BaseResultBean<Video> videoBean) throws Exception {
                                if (tvDetail != null) {
                                    imageOverlay(tvDetail.getMediaImages(), mediaImages);
                                }

                                if (tvDetail != null) {
                                    videoOverlay(tvDetail.getVideoBean(), videoBean);
                                }
                                return tvDetail;
                            }
                        });
        Observable<TvDetail> observable = null;
        if (!TextUtils.isEmpty(Utils.getSessionId())) {
            observable = mModel.getTvAccountStates(itemMediaModel.getId())
                    .flatMap(new Function<AccountStates, Observable<TvDetail>>() {
                        @Override
                        public Observable<TvDetail> apply(AccountStates accountStates) throws Exception {
                            handleAccountStates(accountStates);
                            return tvDetailObservable;
                        }
                    });
        } else {
            observable = tvDetailObservable;
        }
        addSubscribe(observable.subscribe(new Consumer<>() {
            @Override
            public void accept(TvDetail tvDetail) throws Exception {
                showTvDetail(tvDetail);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                statusModel.onErrorClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                        getTvDetail(itemMediaModel);
                    }
                };
                statusModel.dataStatus.set(Constant.DATA_STATUS_ERROR);
                refreshAbout.postValue(categoryDatas);
            }
        }));
    }

    private void getPeopleDetail(ItemMediaModel itemMediaModel) {
        Observable<PersonDetail> movieDetailObservable = mModel.getPersonDetial(itemMediaModel.getId());
        addSubscribe(movieDetailObservable.subscribe(new Consumer<>() {
            @Override
            public void accept(PersonDetail person) throws Exception {
                showPersonDetail(person);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                statusModel.onErrorClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                        getPeopleDetail(itemMediaModel);
                    }
                };
                statusModel.dataStatus.set(Constant.DATA_STATUS_ERROR);
                refreshAbout.postValue(categoryDatas);
            }
        }));
    }

    private void getSeasonsDetail(ItemMediaModel itemMediaModel) {
        Observable<SeasonDetail> tvSeasonDetail = mModel.getTvSeasonDetail(itemMediaModel.getTvId(), itemMediaModel.getSeasonNumber());
        addSubscribe(tvSeasonDetail.subscribe(new Consumer<>() {
            @Override
            public void accept(SeasonDetail seasonDetail) throws Exception {
                showSeasonDetail(seasonDetail);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                statusModel.onErrorClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                        getSeasonsDetail(itemMediaModel);
                    }
                };
                statusModel.dataStatus.set(Constant.DATA_STATUS_ERROR);
                refreshAbout.postValue(categoryDatas);
            }
        }));
    }

    private void getEpisodeDetail(ItemMediaModel itemMediaModel) {
        Observable<EpisodesDetail> tvEpisodesDetail = mModel.getTvEpisodesDetail(itemMediaModel.getTvId(), itemMediaModel.getSeasonNumber(), itemMediaModel.getEpisodeNumber());
        addSubscribe(tvEpisodesDetail.subscribe(new Consumer<>() {
            @Override
            public void accept(EpisodesDetail episodeDetail) throws Exception {
                showEpisodeDetail(episodeDetail);
            }
        }, new Consumer<>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                statusModel.onErrorClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusModel.dataStatus.set(Constant.DATA_STATUS_LOADING);
                        getEpisodeDetail(itemMediaModel);
                    }
                };
                statusModel.dataStatus.set(Constant.DATA_STATUS_ERROR);
                refreshAbout.postValue(categoryDatas);
            }
        }));
    }

    private List<ItemCategoryModel> handlePerson(PersonDetail person) {
        List<ItemCategoryModel> datas = new ArrayList<>();
        if (person != null) {
            subTitles.set(person.getKnownForDepartment());
            datas.add(addHoriTextItem(getString(R.string.gender), getGenderString(person)));
            if (!TextUtils.isEmpty(person.getBirthday())) {
                datas.add(addHoriTextItem(getString(R.string.born_on), Utils.formatDate(person.getBirthday())));
            } else {
                datas.add(addHoriTextItem(getString(R.string.born_on), getString(R.string.unknown)));
            }
            if (!TextUtils.isEmpty(person.getDeathday())) {
                datas.add(addHoriTextItem(getString(R.string.died_on), Utils.formatDate(person.getDeathday())));
            }
            if (!TextUtils.isEmpty(person.getPlaceOfBirth())) {
                datas.add(addHoriTextItem(getString(R.string.from), person.getPlaceOfBirth()));
            } else {
                datas.add(addHoriTextItem(getString(R.string.from), getString(R.string.unknown)));
            }

            if (!TextUtils.isEmpty(person.getBiography())) {
                datas.add(addVerticalTextItem(getString(R.string.biography), person.getBiography()));
            } else {
                datas.add(addVerticalTextItem(getString(R.string.biography), getString(R.string.no_biography_tip)));
            }

            List<ItemMediaModel> alsoKnownAsDatas = getAlsoKnownAsDatas(person.getAlsoKnownAs());
            if (alsoKnownAsDatas.size() > 0) {
                datas.add(addSubTitleListItem(getString(R.string.also_known_as), alsoKnownAsDatas));
            }

            List<ItemMediaModel> profilesDatas = getProfilesDatas(person.getMediaImages());
            if (profilesDatas.size() > 0) {
                datas.add(addMainTitleListItem(getString(R.string.posters), profilesDatas, true));
            }

        }
        return datas;
    }

    private List<ItemCategoryModel> handleEpisodeDetail(EpisodesDetail episodesDetail) {
        List<ItemCategoryModel> datas = new ArrayList<>();
        String overviewStr = getString(R.string.no_overview_tip);
        if (episodesDetail != null) {
            if (!TextUtils.isEmpty(episodesDetail.getOverview())) {
                overviewStr = episodesDetail.getOverview();
            }
            datas.add(addVerticalTextItem(getString(R.string.overview), overviewStr));
            datas.add(addMainTitleItem(getString(R.string.episode_info)));
            if (episodesDetail.getSeasonNumber() != null && !TextUtils.isEmpty(String.valueOf(episodesDetail.getSeasonNumber()))) {
                datas.add(addHoriTextItem(getString(R.string.seasons_number), String.valueOf(episodesDetail.getSeasonNumber())));
            }
            if (episodesDetail.getEpisodeNumber() != null && !TextUtils.isEmpty(String.valueOf(episodesDetail.getEpisodeNumber()))) {
                datas.add(addHoriTextItem(getString(R.string.episode_number), String.valueOf(episodesDetail.getEpisodeNumber())));
            }
            if (episodesDetail.getRuntime() != null) {
                datas.add(addHoriTextItem(getString(R.string.runtime), Utils.formatRuntime(episodesDetail.getRuntime())));
            }
            if (!TextUtils.isEmpty(episodesDetail.getAirDate())) {
                datas.add(addHoriTextItem(getString(R.string.first_air_date), episodesDetail.getAirDate()));
            }

            if (episodesDetail.getCredits() != null) {
                List<ItemMediaModel> castDatas = Utils.castBeanToMediaModel(episodesDetail.getCredits().getCast(), PageParams.ITEM_TYPE_PEOPLE);
                if (castDatas.size() > 0) {
                    datas.add(addMainTitleItem(getString(R.string.cast)));
                    datas.add(addSubTitleListItem("", castDatas));
                }

                List<ItemMediaModel> crewDatas = Utils.crewBeanToMediaModel(episodesDetail.getCredits().getCrew(), PageParams.ITEM_TYPE_PEOPLE);
                if (crewDatas.size() > 0) {
                    datas.add(addMainTitleItem(getString(R.string.crew)));
                    datas.add(addSubTitleListItem("", crewDatas));
                }

                List<ItemMediaModel> guestStarsDatas = getGuestStarsDatas(episodesDetail.getCredits().getGuestStars());
                if (guestStarsDatas.size() > 0) {
                    datas.add(addMainTitleItem(getString(R.string.guest_stars)));
                    datas.add(addSubTitleListItem("", guestStarsDatas));
                }
            }

            List<ItemMediaModel> stillsDatas = getStillsDatas(episodesDetail.getMediaImages());
            if (stillsDatas.size() > 0) {
                datas.add(addMainTitleListItem(getString(R.string.backdrops), stillsDatas, true));
            }

            List<ItemMediaModel> postersDatas = getPostersDatas(episodesDetail.getMediaImages());
            if (postersDatas.size() > 0) {
                datas.add(addMainTitleListItem(getString(R.string.posters), postersDatas, true));
            }

        }
        return datas;
    }

    private List<ItemMediaModel> getGuestStarsDatas(List<GuestStars> guestStars) {
        List<ItemMediaModel> datas = new ArrayList<>();
        if (guestStars != null) {
            for (int i = 0; i < guestStars.size(); i++) {
                GuestStars stars = guestStars.get(i);
                if (stars != null) {
                    datas = Utils.guestStareToMediaModel(guestStars);
                }
            }
        }
        return datas;
    }

    private List<ItemCategoryModel> handleSeasonDetail(SeasonDetail seasonDetail) {
        List<ItemCategoryModel> datas = new ArrayList<>();
        if (seasonDetail != null) {
            List<SeasonDetail.Episodes> episodes = seasonDetail.getEpisodes();
            if (episodes != null) {
                double voteCountInteger = 0.0;
                double voteAverageTotal = 0.0;

                for (int i = 0; i < episodes.size(); i++) {
                    SeasonDetail.Episodes episode = episodes.get(i);
                    voteCountInteger += episode.getVoteCount();
                    voteAverageTotal += (episode.getVoteCount() * (episode.getVoteAverage() * 1.0));
                }
                if (voteCountInteger > 0) {
                    double voteAverageDouble = voteAverageTotal / voteCountInteger;
                    voteAverage.set(Utils.formatDouble(voteAverageDouble));
                    voteCount.set(String.valueOf(voteCountInteger));
                }
            }

            String overviewStr = getString(R.string.no_overview_tip);
            if (!TextUtils.isEmpty(seasonDetail.getOverview())) {
                overviewStr = seasonDetail.getOverview();
            }
            datas.add(addVerticalTextItem(getString(R.string.overview), overviewStr));

            datas.add(addMainTitleItem(getString(R.string.season_info)));
            if (seasonDetail.getSeasonNumber() != null) {
                datas.add(addHoriTextItem(getString(R.string.seasons_number), String.valueOf(seasonDetail.getSeasonNumber())));
            }
            if (episodes != null) {
                datas.add(addHoriTextItem(getString(R.string.episode_count), String.valueOf(episodes.size())));
            }
            if (!TextUtils.isEmpty(seasonDetail.getAirDate())) {
                datas.add(addHoriTextItem(getString(R.string.first_air_date), seasonDetail.getAirDate()));
            }
            if (seasonDetail.getCredits() != null) {
                List<ItemMediaModel> castDatas = Utils.castBeanToMediaModel(seasonDetail.getCredits().getCast(), PageParams.ITEM_TYPE_PEOPLE);
                if (castDatas.size() > 0) {
                    datas.add(addMainTitleItem(getString(R.string.cast)));
                    datas.add(addSubTitleListItem("", castDatas));
                }

                List<ItemMediaModel> crewDatas = Utils.crewBeanToMediaModel(seasonDetail.getCredits().getCrew(), PageParams.ITEM_TYPE_PEOPLE);
                if (crewDatas.size() > 0) {
                    datas.add(addMainTitleItem(getString(R.string.crew)));
                    datas.add(addSubTitleListItem("", crewDatas));
                }

                List<ItemMediaModel> guestStarsDatas = getGuestStarsDatas(seasonDetail.getCredits().getGuestStars());
                if (guestStarsDatas.size() > 0) {
                    datas.add(addMainTitleItem(getString(R.string.guest_stars)));
                    datas.add(addSubTitleListItem("", guestStarsDatas));
                }
            }

            List<ItemMediaModel> postersDatas = getPostersDatas(seasonDetail.getMediaImages());
            if (postersDatas.size() > 0) {
                datas.add(addMainTitleListItem(getString(R.string.posters), postersDatas, true));
            }

            List<ItemMediaModel> backdropsDatas = getBackdropsDatas(seasonDetail.getMediaImages());
            List<ItemMediaModel> logosDatas = getLogosDatas(seasonDetail.getMediaImages());
            backdropsDatas.addAll(logosDatas);
            if (backdropsDatas.size() > 0) {
                datas.add(addMainTitleListItem(getString(R.string.backdrops), backdropsDatas, true));
            }
        }
        return datas;
    }

    private String getGenderString(PersonDetail person) {
        String genderStr = "";
        if (person.getGender() == Constant.GENDER_FEMALE) {
            genderStr = getString(R.string.female);
        } else if (person.getGender() == Constant.GENDER_MALE) {
            genderStr = getString(R.string.male);
        } else {
            genderStr = getString(R.string.unknown);
        }
        return genderStr;
    }

    @NonNull
    private List<ItemMediaModel> getAlsoKnownAsDatas(List<String> alsoKnownAs) {
        List<ItemMediaModel> imagesDatas = new ArrayList<>();
        if (alsoKnownAs != null) {
            for (int i = 0; i < alsoKnownAs.size(); i++) {
                String genres1 = alsoKnownAs.get(i);
                if (genres1 != null) {
                    ItemMediaModel itemMediaModel = new ItemMediaModel();
                    itemMediaModel.setItemType(PageParams.ITEM_TYPE_ALSO_KNOWN_AS);
                    itemMediaModel.titles.set(genres1);
                    imagesDatas.add(itemMediaModel);
                }
            }
        }
        return imagesDatas;
    }

    public void showMovieDetail(MovieDetail movieDetail) {
        List<ItemMediaModel> videosDatas = getVideoDatas(movieDetail, null);
        if (videosDatas.size() > 0) {
            addSubscribe(mModel.getYoutubeVideo(videosDatas.get(0).getYoutubeVideoKey(), "snippet")
                    .subscribe(new Consumer<>() {
                        @Override
                        public void accept(YoutubeVideo youtubeVideo) throws Exception {
                            handleYoutubeVideo(youtubeVideo, videosDatas);
                            showMovieDetail(movieDetail, videosDatas);
                        }
                    }, new Consumer<>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                            showMovieDetail(movieDetail, videosDatas);
                        }
                    }));
        } else {
            showMovieDetail(movieDetail, videosDatas);
        }
    }

    public void showTvDetail(TvDetail tvDetail) {
        List<ItemMediaModel> videosDatas = getVideoDatas(null, tvDetail);
        if (videosDatas.size() > 0) {
            addSubscribe(mModel.getYoutubeVideo(videosDatas.get(0).getYoutubeVideoKey(), "snippet")
                    .subscribe(new Consumer<>() {
                        @Override
                        public void accept(YoutubeVideo youtubeVideo) throws Exception {
                            handleYoutubeVideo(youtubeVideo, videosDatas);
                            showTvDetail(tvDetail, videosDatas);
                        }
                    }, new Consumer<>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d(TAG, "[Ciel_Debug] accept: " + throwable);
                            showTvDetail(tvDetail, videosDatas);
                        }
                    }));
        } else {
            showTvDetail(tvDetail, videosDatas);
        }
    }

    private List<ItemMediaModel> getVideoDatas(MovieDetail movieDetail, TvDetail tvDetail) {
        BaseResultBean<Video> movieVideo = null;
        if (movieDetail != null) {
            movieVideo = movieDetail.getVideoBean();
        }
        if (tvDetail != null) {
            movieVideo = tvDetail.getVideoBean();
        }
        List<ItemMediaModel> datas = new ArrayList<>();
        if (movieVideo != null) {
            List<Video> videos = movieVideo.getResults();
            if (videos != null) {
                for (int i = 0; i < videos.size(); i++) {
                    Video video = videos.get(i);
                    if (video != null) {
                        ItemMediaModel itemMediaModel = new ItemMediaModel();
                        itemMediaModel.setItemType(PageParams.ITEM_TYPE_TRAILERS);
                        itemMediaModel.titles.set(video.getName());
                        itemMediaModel.setYoutubeVideoKey(video.getKey());
                        datas.add(itemMediaModel);
                    }
                }
            }
        }
        return datas;
    }

    private void handleYoutubeVideo(YoutubeVideo youtubeVideo, List<ItemMediaModel> videosDatas) {
        List<YoutubeVideo.Items> youtubeItems = youtubeVideo.getItems();
        if (youtubeItems != null && youtubeItems.size() > 0) {
            for (int i = 0; i < youtubeItems.size(); i++) {
                YoutubeVideo.Items items = youtubeItems.get(i);
                if (items != null) {
                    YoutubeVideo.Items.Snippet snippet = items.getSnippet();
                    if (snippet != null) {
                        YoutubeVideo.Items.Snippet.Thumbnails.Standard standard = snippet.getThumbnails().getStandard();
                        if (standard != null) {
                            String url = standard.getUrl();
                            if (!TextUtils.isEmpty(url)) {
                                for (int l = 0; l < videosDatas.size(); l++) {
                                    String[] split = url.split("/");
                                    StringBuilder image = new StringBuilder();
                                    for (int k = 0; k < split.length; k++) {
                                        if (k == split.length - 2) {
                                            image.append(videosDatas.get(l).getYoutubeVideoKey());
                                        } else {
                                            image.append(split[k]);
                                        }
                                        if (k == 0) {
                                            image.append("//");
                                        } else if (k != split.length - 1) {
                                            image.append("/");
                                        }
                                    }
                                    videosDatas.get(l).images.set(image.toString());
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void showMovieDetail(MovieDetail movieDetail, List<ItemMediaModel> videosDatas) {
        statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
        List<ItemCategoryModel> datas = handleMovieDetail(movieDetail, videosDatas);
        categoryDatas.get().addAll(datas);
        refreshAbout.postValue(categoryDatas);
    }

    private void showTvDetail(TvDetail tvDetail, List<ItemMediaModel> videosDatas) {
        statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
        List<ItemCategoryModel> datas = handleTvDetail(tvDetail, videosDatas);
        categoryDatas.get().addAll(datas);
        refreshAbout.postValue(categoryDatas);
    }

    private void showPersonDetail(PersonDetail movieDetail) {
        statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
        List<ItemCategoryModel> datas = handlePerson(movieDetail);
        categoryDatas.get().addAll(datas);
        refreshAbout.postValue(categoryDatas);
    }

    private void showSeasonDetail(SeasonDetail seasonDetail) {
        statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
        List<ItemCategoryModel> datas = handleSeasonDetail(seasonDetail);
        categoryDatas.get().addAll(datas);
        refreshAbout.postValue(categoryDatas);
    }

    private void showEpisodeDetail(EpisodesDetail tvSeasonDetail) {
        statusModel.dataStatus.set(Constant.DATA_STATUS_COMPLETE);
        List<ItemCategoryModel> datas = handleEpisodeDetail(tvSeasonDetail);
        categoryDatas.get().addAll(datas);
        refreshAbout.postValue(categoryDatas);
    }

    private String getLanguage(String language) {
        List<Languages> mLanguages = mModel.getConfiguration().getMLanguages();
        if (mLanguages != null) {
            for (int i = 0; i < mLanguages.size(); i++) {
                Languages translation = mLanguages.get(i);
                if (TextUtils.equals(language.toLowerCase(), translation.getIso6391().toLowerCase())) {
                    return translation.getName();
                }
            }
        }
        return language;
    }

    private List<ItemMediaModel> getGenresDatas(List<Genres> genres, int itemType) {
        List<ItemMediaModel> genresDatas = new ArrayList<>();
        if (genres != null) {
            for (int i = 0; i < genres.size(); i++) {
                Genres genre = genres.get(i);
                if (genre != null) {
                    ItemMediaModel mediaModel = new ItemMediaModel();
                    mediaModel.setItemType(itemType);
                    mediaModel.setId(genre.getId());
                    mediaModel.titles.set(genre.getName());
                    genresDatas.add(mediaModel);
                }
            }
        }
        return genresDatas;
    }

    private List<ItemMediaModel> getKeywordsDatas(List<Keyword> keywords) {
        List<ItemMediaModel> keywordsDatas = new ArrayList<>();
        if (keywords != null) {
            if (keywords != null) {
                for (int i = 0; i < keywords.size(); i++) {
                    Keyword keyword = keywords.get(i);
                    if (keyword != null) {
                        ItemMediaModel itemMediaModel = new ItemMediaModel();
                        itemMediaModel.setItemType(PageParams.ITEM_TYPE_MOVIE_KEYWORDS);
                        itemMediaModel.titles.set(keyword.getName());
                        keywordsDatas.add(itemMediaModel);
                    }
                }
            }
        }
        return keywordsDatas;
    }

    private List<ItemMediaModel> getCountriesDatas(List<ProductionCountries> movieDetail) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (movieDetail != null) {
            for (int i = 0; i < movieDetail.size(); i++) {
                ProductionCountries productionCountrie = movieDetail.get(i);
                if (productionCountrie != null) {
                    ItemMediaModel mediaModel = new ItemMediaModel();
                    mediaModel.setItemType(PageParams.ITEM_TYPE_COUNTRY);
                    mediaModel.titles.set(productionCountrie.getName());
                    mediaModel.drawableId.set(World.getFlagOf(productionCountrie.getIso31661()));
                    mediaModels.add(mediaModel);
                }
            }
        }
        return mediaModels;
    }

    private String getCertificationStr(BaseResultBean<MovieReleaseDates> movieReleaseDates, List<ProductionCountries> countries) {
        if (movieReleaseDates != null) {
            List<MovieReleaseDates> results = movieReleaseDates.getResults();
            if (countries != null) {
                for (int k = 0; k < countries.size(); k++) {
                    ProductionCountries country = countries.get(k);
                    if (country != null) {
                        String language = country.getIso31661();
                        if (language != null) {
                            if (results != null) {
                                for (int i = 0; i < results.size(); i++) {
                                    if (TextUtils.equals(results.get(i).getIso31661().toLowerCase(), language.toLowerCase())) {
                                        MovieReleaseDates movieReleaseDate = results.get(i);
                                        if (movieReleaseDate != null) {
                                            List<MovieReleaseDates.ReleaseDates> releaseDates = movieReleaseDate.getReleaseDates();
                                            if (releaseDates != null) {
                                                for (int j = 0; j < releaseDates.size(); j++) {
                                                    if (!TextUtils.isEmpty(releaseDates.get(j).getCertification())) {
                                                        return releaseDates.get(j).getCertification();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    private List<ItemMediaModel> getAlternativeTitlesDatas(List<AlternativeTitles> titles) {
        List<ItemMediaModel> titlesDatas = new ArrayList<>();
        if (titles != null) {
            for (int i = 0; i < titles.size(); i++) {
                AlternativeTitles titles1 = titles.get(i);
                if (titles1 != null) {
                    ItemMediaModel itemMediaModel = new ItemMediaModel();
                    itemMediaModel.setItemType(PageParams.ITEM_TYPE_ALTERNATIVE_TITLES);
                    itemMediaModel.titles.set(titles1.getTitle());
                    itemMediaModel.drawableId.set(World.getFlagOf(titles1.getIso31661()));
                    titlesDatas.add(itemMediaModel);
                }
            }
        }
        return titlesDatas;
    }

    private List<ItemMediaModel> getPostersDatas(MediaImages mediaImages) {
        List<ItemMediaModel> postersDatas = new ArrayList<>();
        if (mediaImages != null) {
            List<MediaImages.Posters> posters = mediaImages.getPosters();
            if (posters != null) {
                for (int i = 0; i < posters.size(); i++) {
                    MediaImages.Posters poster = posters.get(i);
                    if (poster != null) {
                        ItemMediaModel itemMediaModel = new ItemMediaModel();
                        itemMediaModel.setItemType(PageParams.ITEM_TYPE_POSTERS);
                        itemMediaModel.images.set(Utils.appendUrl(poster.getFilePath(),
                                TmdbApplication.getInstance().getConfiguration().getImages().getPosterSizes(),
                                R.dimen.category_vertical_image_width));
                        itemMediaModel.orignalPoster.set(Utils.appendUrl(poster.getFilePath(),
                                TmdbApplication.getInstance().getConfiguration().getImages().getPosterSizes(),
                                (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance())));
                        itemMediaModel.setPosterPath(poster.getFilePath());
                        itemMediaModel.isHideVote.set(true);
                        postersDatas.add(itemMediaModel);
                    }
                }
            }
        }
        return postersDatas;
    }

    private List<ItemMediaModel> getBackdropsDatas(MediaImages mediaImages) {
        List<ItemMediaModel> backdropsDatas = new ArrayList<>();
        if (mediaImages != null) {
            List<MediaImages.Backdrops> backdrops = mediaImages.getBackdrops();
            if (backdrops != null) {
                for (int i = 0; i < backdrops.size(); i++) {
                    MediaImages.Backdrops backdrops1 = backdrops.get(i);
                    if (backdrops1 != null) {
                        ItemMediaModel itemMediaModel = new ItemMediaModel();
                        itemMediaModel.setItemType(PageParams.ITEM_TYPE_BAKCDROPS);
                        itemMediaModel.images.set(Utils.appendUrl(backdrops1.getFilePath(),
                                TmdbApplication.getInstance().getConfiguration().getImages().getBackdropSizes(),
                                R.dimen.category_hori_image_width));
                        itemMediaModel.orignalBackdrop.set(Utils.appendUrl(backdrops1.getFilePath(),
                                TmdbApplication.getInstance().getConfiguration().getImages().getBackdropSizes(),
                                (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance())));
                        backdropsDatas.add(itemMediaModel);
                    }
                }
            }
        }
        return backdropsDatas;
    }

    private List<ItemMediaModel> getLogosDatas(MediaImages mediaImages) {
        List<ItemMediaModel> backdropsDatas = new ArrayList<>();
        if (mediaImages != null) {
            List<MediaImages.Logos> logos = mediaImages.getLogos();
            if (logos != null) {
                for (int i = 0; i < logos.size(); i++) {
                    MediaImages.Logos logo = logos.get(i);
                    if (logo != null) {
                        ItemMediaModel itemMediaModel = new ItemMediaModel();
                        itemMediaModel.setItemType(PageParams.ITEM_TYPE_BAKCDROPS);
                        itemMediaModel.images.set(Utils.appendUrl(logo.getFilePath(),
                                TmdbApplication.getInstance().getConfiguration().getImages().getBackdropSizes(),
                                R.dimen.category_hori_image_width));
                        itemMediaModel.orignalBackdrop.set(Utils.appendUrl(logo.getFilePath(),
                                TmdbApplication.getInstance().getConfiguration().getImages().getBackdropSizes(),
                                (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance())));
                        backdropsDatas.add(itemMediaModel);
                    }
                }
            }
        }
        return backdropsDatas;
    }

    private List<ItemMediaModel> getProfilesDatas(MediaImages mediaImages) {
        List<ItemMediaModel> postersDatas = new ArrayList<>();
        if (mediaImages != null) {
            List<MediaImages.Profiles> posters = mediaImages.getProfiles();
            if (posters != null) {
                for (int i = 0; i < posters.size(); i++) {
                    MediaImages.Profiles profiles = posters.get(i);
                    if (profiles != null) {
                        ItemMediaModel itemMediaModel = new ItemMediaModel();
                        itemMediaModel.setItemType(PageParams.ITEM_TYPE_POSTERS);
                        itemMediaModel.images.set(Utils.appendUrl(profiles.getFilePath(),
                                TmdbApplication.getInstance().getConfiguration().getImages().getPosterSizes(),
                                R.dimen.category_vertical_image_width));
                        itemMediaModel.orignalPoster.set(Utils.appendUrl(profiles.getFilePath(),
                                TmdbApplication.getInstance().getConfiguration().getImages().getPosterSizes(),
                                (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance())));
                        itemMediaModel.isHideVote.set(true);
                        postersDatas.add(itemMediaModel);
                    }
                }
            }
        }
        return postersDatas;
    }

    private List<ItemMediaModel> getStillsDatas(MediaImages mediaImages) {
        List<ItemMediaModel> postersDatas = new ArrayList<>();
        if (mediaImages != null) {
            List<MediaImages.Stills> stills = mediaImages.getStills();
            if (stills != null) {
                for (int i = 0; i < stills.size(); i++) {
                    MediaImages.Stills still = stills.get(i);
                    if (still != null) {
                        ItemMediaModel itemMediaModel = new ItemMediaModel();
                        itemMediaModel.setItemType(PageParams.ITEM_TYPE_BAKCDROPS);
                        itemMediaModel.images.set(Utils.appendUrl(still.getFilePath(),
                                TmdbApplication.getInstance().getConfiguration().getImages().getBackdropSizes(),
                                R.dimen.category_hori_image_width));
                        itemMediaModel.orignalBackdrop.set(Utils.appendUrl(still.getFilePath(),
                                TmdbApplication.getInstance().getConfiguration().getImages().getBackdropSizes(),
                                (float) ScreenUtil.getScreenWidth(TmdbApplication.getInstance())));
                        postersDatas.add(itemMediaModel);
                    }
                }
            }
        }
        return postersDatas;
    }

    private List<ItemMediaModel> getCompaniesDatas(List<ProductionCompanies> companies) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (companies != null) {
            for (int i = 0; i < companies.size(); i++) {
                ProductionCompanies company = companies.get(i);
                if (company != null) {
                    ItemMediaModel itemMediaModel = new ItemMediaModel();
                    itemMediaModel.setItemType(PageParams.ITEM_TYPE_COMPANIES);
                    itemMediaModel.setId(company.getId());
                    itemMediaModel.titles.set(company.getName());
                    itemMediaModel.images.set(Utils.appendUrl(company.getLogoPath(),
                            TmdbApplication.getInstance().getConfiguration().getImages().getLogoSizes(),
                            R.dimen.company_item_width));
                    itemMediaModel.drawableId.set(World.getFlagOf(company.getOriginCountry()));
                    mediaModels.add(itemMediaModel);
                }
            }
        }
        return mediaModels;
    }

    private List<ItemMediaModel> getNetworkDatas(List<TvDetail.Networks> networks) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (networks != null) {
            for (int i = 0; i < networks.size(); i++) {
                TvDetail.Networks network = networks.get(i);
                if (network != null) {
                    ItemMediaModel itemMediaModel = new ItemMediaModel();
                    itemMediaModel.setItemType(PageParams.ITEM_TYPE_NETWORK);
                    itemMediaModel.setId(network.getId());
                    itemMediaModel.titles.set(network.getName());
                    itemMediaModel.images.set(Utils.appendUrl(network.getLogoPath(),
                            TmdbApplication.getInstance().getConfiguration().getImages().getLogoSizes(),
                            R.dimen.network_item_width));
                    itemMediaModel.drawableId.set(World.getFlagOf(network.getOriginCountry()));
                    mediaModels.add(itemMediaModel);
                }
            }
        }
        return mediaModels;
    }

    private List<ItemMediaModel> getReleaseInfoDatas(BaseResultBean<MovieReleaseDates> movieReleaseDates, List<ProductionCountries> countries) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (movieReleaseDates != null) {
            List<MovieReleaseDates> results = movieReleaseDates.getResults();
            if (results != null && countries != null) {
                for (int j = 0; j < countries.size(); j++) {
                    ProductionCountries country = countries.get(j);
                    if (country != null && country.getIso31661() != null) {
                        for (int i = 0; i < results.size(); i++) {
                            if (TextUtils.equals(results.get(i).getIso31661().toLowerCase(), country.getIso31661().toLowerCase())) {
                                MovieReleaseDates movieReleaseDate = results.get(i);
                                if (movieReleaseDate != null) {
                                    List<MovieReleaseDates.ReleaseDates> releaseDates = movieReleaseDate.getReleaseDates();
                                    if (releaseDates != null) {
                                        for (int size = releaseDates.size() - 1; size >= 0; size--) {
                                            MovieReleaseDates.ReleaseDates releaseDate = releaseDates.get(size);
                                            ItemMediaModel itemMediaModel = new ItemMediaModel();
                                            itemMediaModel.setItemType(PageParams.ITEM_TYPE_RELEASE_INFO);
                                            String type = "";
                                            switch (releaseDate.getType()) {
                                                case Constant.RELEASE_TYPE_PREMIERE:
                                                    type = getString(R.string.premiere);
                                                    break;
                                                case Constant.RELEASE_TYPE_THEATRICAL_LIMITED:
                                                    type = getString(R.string.premiere);
                                                    break;
                                                case Constant.RELEASE_TYPE_THEATRICAL:
                                                    type = getString(R.string.theatrical);
                                                    break;
                                                case Constant.RELEASE_TYPE_DIGITAL:
                                                    type = getString(R.string.digital);
                                                    break;
                                                case Constant.RELEASE_TYPE_PHYSICAL:
                                                    type = getString(R.string.physical);
                                                    break;
                                            }
                                            itemMediaModel.titles.set(type);
                                            itemMediaModel.subTitles.set(releaseDate.getReleaseDate().substring(0, 10));
                                            itemMediaModel.drawableId.set(World.getFlagOf(country.getIso31661()));
                                            mediaModels.add(itemMediaModel);
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        return mediaModels;
    }

    private ItemCategoryModel addVerticalTextItem(String titles, String content) {
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
        itemCategoryModel.setCategoryType(PageParams.CATEGORY_TYPE_VERTICAL_TEXT);
        itemCategoryModel.titles.set(titles);
        itemCategoryModel.content.set(content);
        return itemCategoryModel;
    }

    private ItemCategoryModel addMainTitleItem(String titles) {
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
        itemCategoryModel.setCategoryType(PageParams.CATEGORY_TYPE_MAIN_TITLE);
        itemCategoryModel.titles.set(titles);
        return itemCategoryModel;
    }

    private ItemCategoryModel addHoriTextItem(String titles, String content) {
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
        itemCategoryModel.setCategoryType(PageParams.CATEGORY_TYPE_HORI_TEXT);
        itemCategoryModel.titles.set(titles);
        itemCategoryModel.content.set(content);
        return itemCategoryModel;
    }

    private ItemCategoryModel addSubTitleListItem(String titles, List<ItemMediaModel> itemCategoryModels) {
        return addSubTitleListItem(titles, itemCategoryModels, false);
    }

    private ItemCategoryModel addSubTitleListItem(String titles, List<ItemMediaModel> itemCategoryModels, boolean isShowCount) {
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
        itemCategoryModel.setCategoryType(PageParams.CATEGORY_TYPE_SUB_TITLE_LIST);
        itemCategoryModel.titles.set(titles);
        itemCategoryModel.itemDatas.set(itemCategoryModels);
        itemCategoryModel.count.set(String.valueOf(itemCategoryModels.size()));
        itemCategoryModel.isShowCount.set(isShowCount);
        return itemCategoryModel;
    }

    private ItemCategoryModel addMainTitleListItem(String titles, List<ItemMediaModel> itemCategoryModels) {
        return addMainTitleListItem(titles, itemCategoryModels, false);
    }

    private ItemCategoryModel addMainTitleListItem(String titles, List<ItemMediaModel> itemCategoryModels, boolean isShowCount) {
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
        itemCategoryModel.setCategoryType(PageParams.CATEGORY_TYPE_MAIN_TITLE_LIST);
        itemCategoryModel.titles.set(titles);
        itemCategoryModel.itemDatas.set(itemCategoryModels);
        itemCategoryModel.count.set(String.valueOf(itemCategoryModels.size()));
        itemCategoryModel.isShowCount.set(isShowCount);
        return itemCategoryModel;
    }

    private ItemCategoryModel addImageItem(int id, String titles, String image) {
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
        itemCategoryModel.setCategoryType(PageParams.CATEGORY_TYPE_IMAGE);
        itemCategoryModel.setId(id);
        itemCategoryModel.titles.set(titles);
        itemCategoryModel.images.set(image);
        return itemCategoryModel;
    }

    private ItemCategoryModel addEpisodeItem(int id, String titles, String subTitle, String content, String image) {
        ItemCategoryModel itemCategoryModel = new ItemCategoryModel();
        itemCategoryModel.setCategoryType(PageParams.CATEGORY_TYPE_EPISODE);
        itemCategoryModel.setId(id);
        itemCategoryModel.titles.set(titles);
        itemCategoryModel.subTitles.set(subTitle);
        itemCategoryModel.content.set(content);
        itemCategoryModel.images.set(image);
        return itemCategoryModel;
    }

    public List<ItemMediaModel> getMediaModels() {
        return mMediaModels;
    }

    public void setMediaModels(List<ItemMediaModel> mediaModels) {
        mMediaModels = mediaModels;
    }
}

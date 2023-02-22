package com.goku.tmdb.ui.home;

import androidx.databinding.ObservableField;

import com.goku.tmdb.R;
import com.goku.tmdb.app.Utils;

import java.io.Serializable;
import java.util.List;

public class ItemMediaModel implements Serializable {

    public transient ObservableField<Integer> dominantColor;
    public transient ObservableField<Integer> mutedColor;
    public transient ObservableField<Integer> lightMutedColor;
    public transient ObservableField<Boolean> isDark;

    //media
    public ObservableField<String> titles = new ObservableField<>();
    public transient ObservableField<String> subTitles = new ObservableField<>();
    public ObservableField<String> images = new ObservableField<>();
    public ObservableField<String> releaseDates = new ObservableField<>();
    public transient ObservableField<Double> voteAverages = new ObservableField<>();
    public transient ObservableField<Boolean> isHideVote = new ObservableField<>(false);
    public transient ObservableField<String> date = new ObservableField<>();
    public transient ObservableField<String> overview = new ObservableField<>();

    public transient ObservableField<Integer> drawableId = new ObservableField<>();
    public transient ObservableField<String> orignalPoster = new ObservableField<>();
    public transient ObservableField<String> orignalBackdrop = new ObservableField<>();

    //review
    public transient ObservableField<String> author = new ObservableField<>();
    public transient ObservableField<String> createdTime = new ObservableField<>();
    public transient ObservableField<String> content = new ObservableField<>();

    //seasons
    public transient ObservableField<String> airDate = new ObservableField<>();
    public transient ObservableField<Integer> episodeCounts = new ObservableField<>(Integer.valueOf(0));

    private int mItemType;

    private String mBackdropPath;
    private long mId;
    private String mTitle;
    private transient String mOriginalLanguage;
    private String mOriginalTitle;
    private String mPosterPath;
    private transient List<Integer> mGenreIds;
    private Double mPopularity;
    private String mReleaseDate;
    private Double mVoteAverage;
    private Integer mVoteCount;

    private long mTvId;
    private String mTvTitle;
    private Integer mSeasonNumber;
    private Integer mEpisodeCount;
    private Integer mEpisodeNumber;

    private transient String mYoutubeVideoKey;

    public ItemMediaModel() {
        if (!Utils.isNightMode()) {
            isDark = new ObservableField<>(false);
//            dominantColor = new ObservableField<>(Utils.getContext().getResources().getColor(R.color.main_bg_color));
            dominantColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.white));
            mutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.sub_title_color));
            lightMutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.black));
        } else {
            isDark = new ObservableField<>(true);
            dominantColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.black));
            mutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.sub_title_color));
            lightMutedColor = new ObservableField<>(Utils.getApplictionContext().getResources().getColor(R.color.white));
        }
    }

    public int getItemType() {
        return mItemType;
    }

    public void setItemType(int itemType) {
        mItemType = itemType;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        mGenreIds = genreIds;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Integer voteCount) {
        mVoteCount = voteCount;
    }

    public Integer getSeasonNumber() {
        return mSeasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        mSeasonNumber = seasonNumber;
    }

    public Integer getEpisodeCount() {
        return mEpisodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        mEpisodeCount = episodeCount;
    }

    public Integer getEpisodeNumber() {
        return mEpisodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        mEpisodeNumber = episodeNumber;
    }

    public String getYoutubeVideoKey() {
        return mYoutubeVideoKey;
    }

    public void setYoutubeVideoKey(String youtubeVideoKey) {
        mYoutubeVideoKey = youtubeVideoKey;
    }

    public long getTvId() {
        return mTvId;
    }

    public void setTvId(long tvId) {
        mTvId = tvId;
    }

    public String getTvTitle() {
        return mTvTitle;
    }

    public void setTvTitle(String tvTitle) {
        mTvTitle = tvTitle;
    }
}

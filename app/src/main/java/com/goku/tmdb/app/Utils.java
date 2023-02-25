package com.goku.tmdb.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import com.goku.tmdb.R;
import com.goku.tmdb.data.entity.Account;
import com.goku.tmdb.data.entity.BaseResultBean;
import com.goku.tmdb.data.entity.Cast;
import com.goku.tmdb.data.entity.Credits;
import com.goku.tmdb.data.entity.Crew;
import com.goku.tmdb.data.entity.GuestStars;
import com.goku.tmdb.data.entity.Movie;
import com.goku.tmdb.data.entity.Person;
import com.goku.tmdb.data.entity.Review;
import com.goku.tmdb.data.entity.Tv;
import com.goku.tmdb.data.entity.collections.CollectionsDetail;
import com.goku.tmdb.data.entity.search.SearchMulti;
import com.goku.tmdb.data.entity.tv.TvDetail;
import com.goku.tmdb.data.entity.tvseasons.SeasonDetail;
import com.goku.tmdb.db.Favorite;
import com.goku.tmdb.db.WatchList;
import com.goku.tmdb.ui.home.ItemCategoryModel;
import com.goku.tmdb.ui.home.ItemMediaModel;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    public static boolean isLogin() {
        return !TextUtils.isEmpty(getSessionId());
    }

    public static String getSessionId() {
        return SPUtils.getInstance().getString(Constant.KEY_SESSION_ID);
    }

    public static void setSessionId(String sessionId) {
        SPUtils.getInstance().put(Constant.KEY_SESSION_ID, sessionId);
    }

    public static String getGuestSessionId() {
        return SPUtils.getInstance().getString(Constant.KEY_GUEST_SESSION_ID);
    }

    public static void setGuestSessionId(String guestSessionId) {
        SPUtils.getInstance().put(Constant.KEY_GUEST_SESSION_ID, guestSessionId);
    }

    public static Account getAccount() {
        String accountJson = SPUtils.getInstance().getString(Constant.KEY_ACCOUNT);
        Account account = new GsonBuilder().create()
                .fromJson(accountJson, Account.class);
        return account;
    }

    public static void setAccount(Account account) {
        String accountJson = "";
        if (account != null) {
            accountJson = new GsonBuilder().create().toJson(account);
        }
        SPUtils.getInstance().put(Constant.KEY_ACCOUNT, accountJson);
    }

    public static Long getAccountId() {
        return SPUtils.getInstance().getLong(Constant.KEY_ACCOUNT_ID);
    }

    public static void setAccountId(Long accountId) {
        SPUtils.getInstance().put(Constant.KEY_ACCOUNT_ID, accountId);
    }

    public static String getGravatarPath() {
        return "https://www.themoviedb.org/t/p/w64_and_h64_face/";
    }

    public static boolean isNightMode() {
        return SPUtils.getInstance().getBoolean("isNightMode", false);
    }

    public static void setNightMode(boolean nightMode) {
        SPUtils.getInstance().put("isNightMode", nightMode);
    }

    public static boolean isRemeberPassword() {
        return SPUtils.getInstance().getBoolean("RemeberPassword", false);
    }

    public static void setRemeberPassword(boolean isRemeberPassword) {
        SPUtils.getInstance().put("RemeberPassword", isRemeberPassword);
    }

    public static String getUsername() {
        return SPUtils.getInstance().getString("Username");
    }

    public static void setUsername(String username) {
        SPUtils.getInstance().put("Username", username);
    }

    public static String getPassword() {
        return SPUtils.getInstance().getString("Password");
    }

    public static void setPassword(String username) {
        SPUtils.getInstance().put("Password", username);
    }

    public static boolean isAdult() {
        return SPUtils.getInstance().getBoolean("isAdult", false);
    }

    public static void setAdult(boolean isAdult) {
        SPUtils.getInstance().put("isAdult", isAdult);
    }

    public static String getLanguageStr() {
        Locale locale = getApplictionContext().getResources().getConfiguration().locale;
        if (!TextUtils.isEmpty(locale.getCountry())) {
            return locale.getLanguage() + "-" + locale.getCountry();
        } else {
            return locale.getLanguage();
        }
    }

    public static String getLanguage() {
        Locale locale = getApplictionContext().getResources().getConfiguration().locale;
        return locale.getLanguage();
    }

    public static String getCountry() {
        Locale locale = getApplictionContext().getResources().getConfiguration().locale;
        return locale.getCountry();
    }

    public static boolean isGridMode() {
        return SPUtils.getInstance().getBoolean("gridMode", false);
    }

    public static void setGridMode(boolean nightMode) {
        SPUtils.getInstance().put("gridMode", nightMode);
    }

    public static String formatDate(String date) {
//        Log.d(TAG, "[Ciel_Debug] formatDate22222: " + Date.parse(date));
//        Date date1 = new Date(Date.parse(date));
        String result;
        try {
            String[] split = date.split("-");
            if (TextUtils.equals(Utils.getLanguage(), Locale.SIMPLIFIED_CHINESE.getLanguage())) {
                result = Integer.valueOf(split[0]) + getContext().getString(R.string.year)
                        + Integer.valueOf(split[1]) + getContext().getString(R.string.month)
                        + Integer.valueOf(split[2]) + getContext().getString(R.string.day);
            } else {
                return date;
            }
        } catch (Exception e) {
            return date;
        }

        return date;
    }

    public static String getYear(String date) {
        try {
            String[] split = date.split("-");
            return split[0];
        } catch (Exception e) {
            return date;
        }
    }

    public static String appendUrl(String path, List<String> imageSizes, int id) {
        return appendUrl(path, imageSizes, getApplictionContext().getResources().getDimension(id));
    }

    public static String appendUrl(String path, List<String> imageSizes, float widthFloat) {
        StringBuilder url = new StringBuilder(getApplictionContext().getConfiguration().getImages().getBaseUrl());
        String width = "original";

        int viewWidth = (int) widthFloat;

        List<Integer> posterIntegerSizes = new ArrayList<>();
        for (int i = 0; i < imageSizes.size(); i++) {
            if (imageSizes.get(i).startsWith("w")) {
                String s = imageSizes.get(i).replace("w", "");
                int a = Integer.valueOf(s);
                posterIntegerSizes.add(a);
            }
        }

        for (int i = 0; i < posterIntegerSizes.size(); i++) {
            if (i == 0) {
                if (viewWidth <= posterIntegerSizes.get(i)) {
                    width = imageSizes.get(i);
                    break;
                }
            } else if (i == posterIntegerSizes.size() - 1) {
                if (viewWidth > posterIntegerSizes.get(i)) {
                    width = imageSizes.get(imageSizes.size() - 1);
                    break;
                }
            } else {
                if (viewWidth <= posterIntegerSizes.get(i) && viewWidth > posterIntegerSizes.get(i - 1)) {
                    width = imageSizes.get(i);
                    break;
                }
            }
        }
        url.append(width)
                .append(path);
        Log.d(TAG, "[Ciel_Debug] appendUrl: " + url);
        return url.toString();
    }

    public static String appendThumbnail(String path, List<String> imageSizes) {
        StringBuilder url = new StringBuilder(getApplictionContext().getConfiguration().getImages().getBaseUrl());
        url.append(imageSizes.get(0))
                .append(path);
        Log.d(TAG, "[Ciel_Debug] appendThumbnail: " + url);
        return url.toString();
    }

    public static int intTointByte(int args) {

        int hex_all = 0;
        if (args > 10) {
            int ten_num = Integer.parseInt((args + "").substring(0, 1));
            int hex_1 = ten_num * 16;
            int hex_2 = Integer.parseInt((args + "").substring(1, 2));
            hex_all = hex_1 + hex_2;
        } else {
            hex_all = args;
        }
        Log.e("hex_all", hex_all + "");
        return hex_all;
    }

    public static String showIfEmptySymbol(String string) {
        return TextUtils.isEmpty(string) ? "-" : string;
    }

    public static String numberAddComma(long num) {
        try {
            String number = String.valueOf(num);
            char[] bytes = number.toCharArray();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                result.append(bytes[i]);
                if (i != bytes.length - 1 && (bytes.length - i) % 3 == 1) {
                    result.append(",");
                }
            }
            return result.toString();
        } catch (Exception e) {
            Log.d(TAG, "[Ciel_Debug] numberAddComma: " + e);
            e.printStackTrace();
            return "";
        }
    }

    public static String formatRuntime(Integer runtime) {
        if (runtime != null) {
            String hour = runtime / 60 + getContext().getString(R.string.hour);
            String min = runtime % 60 + getContext().getString(R.string.min);
            if (runtime / 60 > 0) {
                return hour + " " + min;
            } else {
                return min;
            }
        }
        return "";
    }

    public static int getDimensionPixelOffset(int id) {
        return getApplictionContext().getResources().getDimensionPixelOffset(id);
    }

    public static TmdbApplication getApplictionContext() {
        return TmdbApplication.getInstance();
    }

    public static Context getContext() {
        return AppManager.getAppManager().currentActivity();
    }

    public static List<ItemMediaModel> movieBeanToMediaModel(List<Movie> movies, ItemCategoryModel itemCategoryModel) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (movies != null) {
            for (int i = 0; i < movies.size(); i++) {
                Movie movie = movies.get(i);
                ItemMediaModel mediaModel = new ItemMediaModel();
                if (false) {
                    mediaModel.setItemType(PageParams.ITEM_TYPE_MOIVE_HORI);
                    mediaModel.images.set(Utils.appendUrl(movie.getBackdropPath(),
                            getApplictionContext().getConfiguration().getImages().getBackdropSizes(),
                            R.dimen.hori_image_item_width));
                } else {
                    mediaModel.setItemType(PageParams.ITEM_TYPE_MOIVE);
                    mediaModel.images.set(Utils.appendUrl(movie.getPosterPath(),
                            getApplictionContext().getConfiguration().getImages().getPosterSizes(),
                            R.dimen.category_vertical_image_width));
                    mediaModel.thumbnail.set(Utils.appendThumbnail(movie.getPosterPath(),
                            getApplictionContext().getConfiguration().getImages().getPosterSizes()));
                }

                mediaModel.titles.set(movie.getTitle());
                mediaModel.releaseDates.set(Utils.formatDate(movie.getReleaseDate()));
                mediaModel.voteAverages.set(Utils.formatDouble(movie.getVoteAverage()));
                if (!TextUtils.isEmpty(movie.getReleaseDate())) {
                    mediaModel.releaseDates.set(Utils.formatDate(movie.getReleaseDate()));
                } else {
                    mediaModel.releaseDates.set(getContext().getString(R.string.unknow_date));
                }
                if (!TextUtils.isEmpty(movie.getOverview())) {
                    mediaModel.overview.set(movie.getOverview());
                } else {
                    mediaModel.overview.set(getContext().getString(R.string.no_overview_tip));
                }

                mediaModel.setBackdropPath(movie.getBackdropPath());
                mediaModel.setId(movie.getId());
                mediaModel.setTitle(movie.getTitle());
                mediaModel.setOriginalLanguage(movie.getOriginalLanguage());
                mediaModel.setOriginalTitle(movie.getOriginalTitle());
                mediaModel.setPosterPath(movie.getPosterPath());
                mediaModel.setGenreIds(movie.getGenreIds());
                mediaModel.setPopularity(movie.getPopularity());
                mediaModel.setReleaseDate(movie.getReleaseDate());
                mediaModel.setVoteAverage(movie.getVoteAverage());
                mediaModel.setVoteCount(movie.getVoteCount());

                mediaModels.add(mediaModel);
            }
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> collectionToMediaModel(List<CollectionsDetail.Parts> parts) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (parts != null) {
            for (int i = 0; i < parts.size(); i++) {
                CollectionsDetail.Parts part = parts.get(i);
                ItemMediaModel mediaModel = new ItemMediaModel();
                mediaModel.setItemType(PageParams.ITEM_TYPE_MOIVE);
                mediaModel.titles.set(part.getTitle());
                mediaModel.images.set(Utils.appendUrl(part.getPosterPath(),
                        getApplictionContext().getConfiguration().getImages().getPosterSizes(),
                        R.dimen.category_vertical_image_width));
                mediaModel.thumbnail.set(Utils.appendThumbnail(part.getPosterPath(),
                        getApplictionContext().getConfiguration().getImages().getPosterSizes()));
                mediaModel.voteAverages.set(Utils.formatDouble(part.getVoteAverage()));
                if (!TextUtils.isEmpty(part.getReleaseDate())) {
                    mediaModel.releaseDates.set(Utils.formatDate(part.getReleaseDate()));
                } else {
                    mediaModel.releaseDates.set(getContext().getString(R.string.unknow_date));
                }
                if (!TextUtils.isEmpty(part.getOverview())) {
                    mediaModel.overview.set(part.getOverview());
                } else {
                    mediaModel.overview.set(getContext().getString(R.string.no_overview_tip));
                }

                mediaModel.setBackdropPath(part.getBackdropPath());
                mediaModel.setId(part.getId());
                mediaModel.setTitle(part.getTitle());
                mediaModel.setOriginalLanguage(part.getOriginalLanguage());
                mediaModel.setOriginalTitle(part.getOriginalTitle());
                mediaModel.setPosterPath(part.getPosterPath());
                mediaModel.setGenreIds(part.getGenreIds());
                mediaModel.setPopularity(part.getPopularity());
                mediaModel.setReleaseDate(part.getReleaseDate());
                mediaModel.setVoteAverage(part.getVoteAverage());
                mediaModel.setVoteCount(part.getVoteCount());

                mediaModels.add(mediaModel);
            }
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> tvBeanToMediaModel(List<Tv> tvs, ItemCategoryModel itemCategoryModel) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (tvs != null) {
            for (int i = 0; i < tvs.size(); i++) {
                Tv tv = tvs.get(i);
                ItemMediaModel mediaModel = new ItemMediaModel();
                if (false) {
                    mediaModel.setItemType(PageParams.ITEM_TYPE_TV_SHOW_HORI);
                } else {
                    mediaModel.setItemType(PageParams.ITEM_TYPE_TV_SHOW);
                }
                mediaModel.titles.set(tv.getName());
                mediaModel.images.set(Utils.appendUrl(tv.getPosterPath(),
                        getApplictionContext().getConfiguration().getImages().getPosterSizes(),
                        R.dimen.category_vertical_image_width));
                mediaModel.thumbnail.set(Utils.appendThumbnail(tv.getPosterPath(),
                        getApplictionContext().getConfiguration().getImages().getPosterSizes()));
                mediaModel.releaseDates.set(Utils.formatDate(tv.getFirstAirDate()));
                mediaModel.voteAverages.set(Utils.formatDouble(tv.getVoteAverage()));
                if (!TextUtils.isEmpty(tv.getFirstAirDate())) {
                    mediaModel.releaseDates.set(Utils.formatDate(tv.getFirstAirDate()));
                } else {
                    mediaModel.releaseDates.set(getContext().getString(R.string.unknow_date));
                }
                if (!TextUtils.isEmpty(tv.getOverview())) {
                    mediaModel.overview.set(tv.getOverview());
                } else {
                    mediaModel.overview.set(getContext().getString(R.string.no_overview_tip));
                }

                mediaModel.setBackdropPath(tv.getBackdropPath());
                mediaModel.setId(tv.getId());
                mediaModel.setTitle(tv.getName());
                mediaModel.setOriginalLanguage(tv.getOriginalLanguage());
                mediaModel.setOriginalTitle(tv.getOriginalName());
                mediaModel.setPosterPath(tv.getPosterPath());
                mediaModel.setGenreIds(tv.getGenreIds());
                mediaModel.setPopularity(tv.getPopularity());
                mediaModel.setReleaseDate(tv.getFirstAirDate());
                mediaModel.setVoteAverage(tv.getVoteAverage());
                mediaModel.setVoteCount(tv.getVoteCount());

                mediaModels.add(mediaModel);
            }
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> seasonBeanToMediaModel(List<TvDetail.Seasons> seasons, ItemCategoryModel itemCategoryModel) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (seasons != null) {
            for (int i = 0; i < seasons.size(); i++) {
                TvDetail.Seasons season = seasons.get(i);
                if (season != null) {
                    ItemMediaModel mediaModel = new ItemMediaModel();
                    mediaModel.setItemType(PageParams.ITEM_TYPE_SEASONS);
                    String name = "";
                    if (season.getSeasonNumber() == 0) {
                        name = getContext().getString(R.string.specials);
                    } else {
                        name = getContext().getString(R.string.season_number_format, season.getSeasonNumber());
                    }
                    mediaModel.titles.set(name);

                    if (!TextUtils.isEmpty(season.getPosterPath())) {
                        mediaModel.images.set(Utils.appendUrl(season.getPosterPath(),
                                getApplictionContext().getConfiguration().getImages().getPosterSizes(),
                                R.dimen.season_item_width));
                        mediaModel.thumbnail.set(Utils.appendThumbnail(season.getPosterPath(),
                                getApplictionContext().getConfiguration().getImages().getPosterSizes()));
                    } else {
                        mediaModel.images.set(Utils.appendUrl(itemCategoryModel.getPosterPath(),
                                getApplictionContext().getConfiguration().getImages().getPosterSizes(),
                                R.dimen.season_item_width));
                        mediaModel.thumbnail.set(Utils.appendThumbnail(itemCategoryModel.getPosterPath(),
                                getApplictionContext().getConfiguration().getImages().getPosterSizes()));
                    }

                    mediaModel.airDate.set(season.getAirDate());
                    mediaModel.episodeCounts.set(season.getEpisodeCount());

                    mediaModel.setTvId(itemCategoryModel.getTvId());
                    mediaModel.setTvTitle(itemCategoryModel.getTvTitle());

                    mediaModel.setBackdropPath(itemCategoryModel.getBackdropPath());
                    mediaModel.setId(season.getId());
                    mediaModel.setTitle(season.getName());
                    if (!TextUtils.isEmpty(season.getPosterPath())) {
                        mediaModel.setPosterPath(season.getPosterPath());
                    } else {
                        mediaModel.setPosterPath(itemCategoryModel.getPosterPath());
                    }
                    mediaModel.setReleaseDate(season.getAirDate());
                    mediaModel.setSeasonNumber(season.getSeasonNumber());
                    mediaModel.setEpisodeCount(season.getEpisodeCount());

                    mediaModels.add(mediaModel);
                }
            }
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> episodeBeanToMediaModel(List<SeasonDetail.Episodes> episodes, ItemCategoryModel itemCategoryModel) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (episodes != null) {
            for (int i = 0; i < episodes.size(); i++) {
                SeasonDetail.Episodes episode = episodes.get(i);
                if (episode != null) {
                    ItemMediaModel mediaModel = new ItemMediaModel();
                    mediaModel.setItemType(PageParams.ITEM_TYPE_EPISODES);
                    mediaModel.titles.set(episode.getEpisodeNumber() + "." + episode.getName());
                    if (!TextUtils.isEmpty(episode.getStillPath())) {
                        mediaModel.images.set(Utils.appendUrl(episode.getStillPath(),
                                getApplictionContext().getConfiguration().getImages().getStillSizes(),
                                R.dimen.episode_item_width));
                        mediaModel.thumbnail.set(Utils.appendThumbnail(episode.getStillPath(),
                                getApplictionContext().getConfiguration().getImages().getStillSizes()));
                    } else {
                        mediaModel.images.set(Utils.appendUrl(itemCategoryModel.getBackdropPath(),
                                getApplictionContext().getConfiguration().getImages().getBackdropSizes(),
                                R.dimen.episode_item_width));
                        mediaModel.thumbnail.set(Utils.appendThumbnail(itemCategoryModel.getBackdropPath(),
                                getApplictionContext().getConfiguration().getImages().getBackdropSizes()));
                    }
                    mediaModel.content.set(Utils.formatSeasonName(episode.getSeasonNumber())
                            + " | " + Utils.formatEpisodeName(episode.getEpisodeNumber())
                            + " | " + Utils.formatDate(episode.getAirDate()));


                    mediaModel.setBackdropPath(itemCategoryModel.getBackdropPath());
                    mediaModel.setId(episode.getId());
                    mediaModel.setTitle(episode.getName());

                    if (!TextUtils.isEmpty(episode.getStillPath())) {
                        mediaModel.setPosterPath(episode.getStillPath());
                    } else {
                        mediaModel.setPosterPath(itemCategoryModel.getBackdropPath());
                    }

                    mediaModel.setReleaseDate(episode.getAirDate());
                    mediaModel.setVoteAverage(episode.getVoteAverage());
                    mediaModel.setVoteCount(episode.getVoteCount());

                    mediaModel.setTvId(itemCategoryModel.getTvId());
                    mediaModel.setTvTitle(itemCategoryModel.getTvTitle());
                    mediaModel.setSeasonNumber(itemCategoryModel.getSeasonNumber());
                    mediaModel.setEpisodeCount(itemCategoryModel.getEpisodeCount());
                    mediaModel.setEpisodeNumber(episode.getEpisodeNumber());

                    mediaModels.add(mediaModel);
                }
            }
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> personBeanToMediaModel(List<Person> persons, int itemType) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (persons != null) {
            for (int i = 0; i < persons.size(); i++) {
                Person person = persons.get(i);
                ItemMediaModel mediaModel = new ItemMediaModel();
                mediaModel.setItemType(itemType);

                mediaModel.titles.set(person.getName());
                mediaModel.images.set(Utils.appendUrl(person.getProfilePath(),
                        getApplictionContext().getConfiguration().getImages().getProfileSizes(),
                        R.dimen.people_item_width));
                mediaModel.thumbnail.set(Utils.appendThumbnail(person.getProfilePath(),
                        getApplictionContext().getConfiguration().getImages().getProfileSizes()));

                mediaModel.setId(person.getId());
                mediaModel.setTitle(person.getName());
                mediaModel.setPosterPath(person.getProfilePath());
                mediaModels.add(mediaModel);
            }
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> guestStarToMediaModel(List<GuestStars> guestStars) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (guestStars != null) {
            for (int i = 0; i < guestStars.size(); i++) {
                GuestStars stars = guestStars.get(i);
                ItemMediaModel mediaModel = new ItemMediaModel();
                mediaModel.setItemType(PageParams.ITEM_TYPE_PEOPLE);

                mediaModel.titles.set(stars.getName());
                mediaModel.subTitles.set(stars.getCharacter());
                mediaModel.images.set(Utils.appendUrl(stars.getProfilePath(),
                        getApplictionContext().getConfiguration().getImages().getProfileSizes(),
                        R.dimen.people_item_width));
                mediaModel.thumbnail.set(Utils.appendThumbnail(stars.getProfilePath(),
                        getApplictionContext().getConfiguration().getImages().getProfileSizes()));

                mediaModel.setId(stars.getId());
                mediaModel.setTitle(stars.getName());
                mediaModel.setPosterPath(stars.getProfilePath());
                mediaModels.add(mediaModel);
            }
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> personCreditsToMediaModel(Credits credits, int itemType) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (credits != null) {
            List<Cast> casts = credits.getCast();
            for (int i = 0; i < casts.size(); i++) {
                Cast cast = casts.get(i);
                if (itemType == PageParams.ITEM_TYPE_MOIVE) {
                    if (!TextUtils.equals(cast.getMediaType(), "movie")) {
                        continue;
                    }
                } else if (itemType == PageParams.ITEM_TYPE_TV_SHOW) {
                    if (!TextUtils.equals(cast.getMediaType(), "tv")) {
                        continue;
                    }
                }

                ItemMediaModel mediaModel = new ItemMediaModel();
                mediaModel.setItemType(itemType);

                if (TextUtils.equals(cast.getMediaType(), "movie")) {
                    mediaModel.titles.set(cast.getTitle());
//                    if (!TextUtils.isEmpty(cast.getReleaseDate())) {
//                        mediaModel.releaseDates.set(Utils.formatDate(cast.getReleaseDate()));
//                    } else {
//                        mediaModel.releaseDates.set(getString(R.string.unknow_date));
//                    }
                }
                if (TextUtils.equals(cast.getMediaType(), "tv")) {
                    mediaModel.titles.set(cast.getName());
//                    if (!TextUtils.isEmpty(cast.getFirstAirDate())) {
//                        mediaModel.releaseDates.set(Utils.formatDate(cast.getFirstAirDate()));
//                    } else {
//                        mediaModel.releaseDates.set(getString(R.string.unknow_date));
//                    }
                }
                Log.d(TAG, "[Ciel_Debug] personCreditsToMediaModel: getCharacter: " + cast.getCharacter());
                if (!TextUtils.isEmpty(cast.getCharacter())) {
                    mediaModel.releaseDates.set(cast.getCharacter());
                }
                mediaModel.images.set(Utils.appendUrl(cast.getPosterPath(),
                        getApplictionContext().getConfiguration().getImages().getPosterSizes(),
                        R.dimen.category_vertical_image_width));
                mediaModel.thumbnail.set(Utils.appendThumbnail(cast.getPosterPath(),
                        getApplictionContext().getConfiguration().getImages().getPosterSizes()));

                if (!TextUtils.isEmpty(cast.getOverview())) {
                    mediaModel.overview.set(cast.getOverview());
                } else {
                    mediaModel.overview.set(getContext().getString(R.string.no_overview_tip));
                }
                mediaModel.voteAverages.set(Utils.formatDouble(cast.getVoteAverage()));

                mediaModel.setId(cast.getId());
                mediaModel.setTitle(mediaModel.titles.get());
                mediaModel.setBackdropPath(cast.getBackdropPath());
                mediaModel.setPosterPath(cast.getPosterPath());
                mediaModel.setReleaseDate(cast.getReleaseDate());
                mediaModel.setVoteAverage(cast.getVoteAverage());
                mediaModel.setVoteCount(cast.getVoteCount());
                mediaModels.add(mediaModel);
            }

            List<Crew> crews = credits.getCrew();
            Log.d(TAG, "[Ciel_Debug] personCreditsToMediaModel: crews: " + crews.size());
            for (int i = 0; i < crews.size(); i++) {
                Crew crew = crews.get(i);
                if (itemType == PageParams.ITEM_TYPE_MOIVE) {
                    if (!TextUtils.equals(crew.getMediaType(), "movie")) {
                        continue;
                    }
                } else if (itemType == PageParams.ITEM_TYPE_TV_SHOW) {
                    if (!TextUtils.equals(crew.getMediaType(), "tv")) {
                        continue;
                    }
                }

                ItemMediaModel mediaModel = new ItemMediaModel();
                mediaModel.setItemType(itemType);
                if (TextUtils.equals(crew.getMediaType(), "movie")) {
                    mediaModel.titles.set(crew.getTitle());
//                    if (!TextUtils.isEmpty(crew.getReleaseDate())) {
//                        mediaModel.releaseDates.set(Utils.formatDate(crew.getReleaseDate()));
//                    } else {
//                        mediaModel.releaseDates.set(getString(R.string.unknow_date));
//                    }
                }
                if (TextUtils.equals(crew.getMediaType(), "tv")) {
                    mediaModel.titles.set(crew.getName());
//                    if (!TextUtils.isEmpty(crew.getFirstAirDate())) {
//                        mediaModel.releaseDates.set(Utils.formatDate(crew.getFirstAirDate()));
//                    } else {
//                        mediaModel.releaseDates.set(getString(R.string.unknow_date));
//                    }
                }
                if (!TextUtils.isEmpty(crew.getJob())) {
                    mediaModel.releaseDates.set(crew.getJob());
                }
                mediaModel.images.set(Utils.appendUrl(crew.getPosterPath(),
                        getApplictionContext().getConfiguration().getImages().getPosterSizes(),
                        R.dimen.category_vertical_image_width));
                mediaModel.thumbnail.set(Utils.appendThumbnail(crew.getPosterPath(),
                        getApplictionContext().getConfiguration().getImages().getPosterSizes()));

                if (!TextUtils.isEmpty(crew.getOverview())) {
                    mediaModel.overview.set(crew.getOverview());
                } else {
                    mediaModel.overview.set(getContext().getString(R.string.no_overview_tip));
                }
                mediaModel.voteAverages.set(Utils.formatDouble(crew.getVoteAverage()));

                mediaModel.setId(crew.getId());
                mediaModel.setTitle(mediaModel.titles.get());
                mediaModel.setBackdropPath(crew.getBackdropPath());
                mediaModel.setPosterPath(crew.getPosterPath());
                mediaModel.setReleaseDate(crew.getReleaseDate());
                mediaModel.setVoteAverage(crew.getVoteAverage());
                mediaModel.setVoteCount(crew.getVoteCount());
                mediaModels.add(mediaModel);
            }
        }
        for (int i = 0; i < mediaModels.size(); i++) {
            Log.d(TAG, "[Ciel_Debug] personCreditsToMediaModel: " + mediaModels.get(i).releaseDates.get());
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> castBeanToMediaModel(List<Cast> casts, int itemTpye) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (casts != null) {
            for (int i = 0; i < casts.size(); i++) {
                Cast cast = casts.get(i);
                if (cast != null) {
                    ItemMediaModel mediaModel = new ItemMediaModel();
                    mediaModel.setItemType(itemTpye);

                    mediaModel.titles.set(cast.getName());
                    mediaModel.subTitles.set(cast.getCharacter());
                    mediaModel.images.set(Utils.appendUrl(cast.getProfilePath(),
                            getApplictionContext().getConfiguration().getImages().getProfileSizes(),
                            R.dimen.people_item_width));
                    mediaModel.thumbnail.set(Utils.appendThumbnail(cast.getProfilePath(),
                            getApplictionContext().getConfiguration().getImages().getProfileSizes()));

                    mediaModel.setId(cast.getId());
                    mediaModel.setTitle(cast.getName());
                    mediaModel.setPosterPath(cast.getProfilePath());
                    mediaModels.add(mediaModel);
                }
            }
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> crewBeanToMediaModel(List<Crew> crews, int itemTpye) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (crews != null) {
            List<String> names = new ArrayList<>();
            for (int i = 0; i < crews.size(); i++) {
                Crew crew = crews.get(i);
                if (crew != null) {
                    if (!names.contains(crew.getName())) {
                        names.add(crew.getName());
                        ItemMediaModel mediaModel = new ItemMediaModel();
                        mediaModel.setItemType(itemTpye);
                        mediaModel.titles.set(crew.getName());
                        mediaModel.subTitles.set(crew.getJob());

                        mediaModel.images.set(Utils.appendUrl(crew.getProfilePath(),
                                getApplictionContext().getConfiguration().getImages().getProfileSizes(),
                                R.dimen.people_item_width));
                        mediaModel.thumbnail.set(Utils.appendThumbnail(crew.getProfilePath(),
                                getApplictionContext().getConfiguration().getImages().getProfileSizes()));

                        mediaModel.setId(crew.getId());
                        mediaModel.setTitle(crew.getName());
                        mediaModel.setPosterPath(crew.getProfilePath());
                        mediaModels.add(mediaModel);
                    } else {
                        for (int j = 0; j < names.size(); j++) {
                            if (TextUtils.equals(names.get(j), crew.getName())) {
                                mediaModels.get(j).subTitles.set(mediaModels.get(j).subTitles.get() + ", " + crew.getJob());
                                break;
                            }
                        }
                    }
                }
            }
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> reviewBeanToItemModel(List<Review> reviews) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (reviews != null) {
            for (int i = 0; i < reviews.size(); i++) {
                Review review = reviews.get(i);
                if (review != null) {
                    Review.AuthorDetails authorDetails = review.getAuthorDetails();
                    if (authorDetails != null) {
                        ItemMediaModel mediaModel = new ItemMediaModel();
                        mediaModel.setItemType(PageParams.ITEM_TYPE_REVIEWS);
                        mediaModel.images.set(Utils.getGravatarPath() + review.getAuthorDetails().getAvatarPath());
                        mediaModel.author.set(review.getAuthor());
                        String createdStr = getContext().getString(R.string.review_created_time_fromat, review.getAuthor(), review.getCreatedAt().substring(0, 10));
                        mediaModel.createdTime.set(createdStr);
                        mediaModel.content.set(review.getContent());
                        Double rating = authorDetails.getRating();
                        if (rating != null) {
                            mediaModel.voteAverages.set(Utils.formatDouble(rating));
                        } else {
                            mediaModel.voteAverages.set(Utils.formatDouble(0.0));
                        }

                        mediaModels.add(mediaModel);
                    }
                }
            }
        }
        return mediaModels;
    }

    public static List<ItemMediaModel> searchBeanToMediaModel(BaseResultBean<SearchMulti> movies) {
        List<ItemMediaModel> mediaModels = new ArrayList<>();
        if (movies != null && movies.getResults() != null) {
            for (int i = 0; i < movies.getResults().size(); i++) {
                SearchMulti searchMulti = movies.getResults().get(i);
                ItemMediaModel mediaModel = new ItemMediaModel();
                if (TextUtils.equals(searchMulti.getMediaType(), "movie")) {
                    mediaModel.setItemType(PageParams.ITEM_TYPE_MOIVE);
                } else if (TextUtils.equals(searchMulti.getMediaType(), "tv")) {
                    mediaModel.setItemType(PageParams.ITEM_TYPE_TV_SHOW);
                } else if (TextUtils.equals(searchMulti.getMediaType(), "movie")) {
                    mediaModel.setItemType(PageParams.ITEM_TYPE_PEOPLE);
                }

                mediaModel.titles.set(searchMulti.getTitle());
                mediaModel.images.set(Utils.appendUrl(searchMulti.getPosterPath(),
                        getApplictionContext().getConfiguration().getImages().getPosterSizes(),
                        R.dimen.category_vertical_image_width));
                mediaModel.thumbnail.set(Utils.appendThumbnail(searchMulti.getPosterPath(),
                        getApplictionContext().getConfiguration().getImages().getPosterSizes()));
                mediaModel.voteAverages.set(Utils.formatDouble(searchMulti.getVoteAverage()));

                mediaModel.setId(searchMulti.getId());
                mediaModel.setTitle(searchMulti.getTitle());
                mediaModel.setBackdropPath(searchMulti.getBackdropPath());
                mediaModel.setPosterPath(searchMulti.getPosterPath());
                mediaModel.setReleaseDate(searchMulti.getReleaseDate());
                mediaModel.setVoteAverage(searchMulti.getVoteAverage());
                mediaModel.setVoteCount(searchMulti.getVoteCount());

                mediaModels.add(mediaModel);
            }
        }
        return mediaModels;
    }

    public static Favorite movieToFavorite(Movie movie) {
        Favorite favorite = null;
        if (movie != null) {
            favorite = new Favorite();
            favorite.setAccountId(Utils.getAccount().getId());
            favorite.setAdult(movie.isAdult());
            favorite.setBackdropPath(movie.getBackdropPath());
            favorite.setGenreIds(movie.getGenreIds());
            favorite.setId(Long.valueOf(movie.getId()));
            favorite.setOriginCountry(movie.getOriginCountry());
            favorite.setOriginalLanguage(movie.getOriginalLanguage());
            favorite.setOriginalName(movie.getOriginalName());
            favorite.setOverview(movie.getOverview());
            favorite.setPopularity(movie.getPopularity());
            favorite.setPosterPath(movie.getPosterPath());
            favorite.setFirstAirDate(movie.getReleaseDate());
            favorite.setName(movie.getTitle());
            favorite.setVoteAverage(movie.getVoteAverage());
            favorite.setVoteCount(movie.getVoteCount());
        }
        return favorite;
    }

    public static Movie favoriteToMovie(Favorite favorite) {
        Movie movie = null;
        if (favorite != null) {
            movie = new Movie();
            movie.setAdult(favorite.isAdult());
            movie.setBackdropPath(favorite.getBackdropPath());
            movie.setGenreIds(favorite.getGenreIds());
            movie.setId(Math.toIntExact(favorite.getId()));
            movie.setOriginCountry(favorite.getOriginCountry());
            movie.setOriginalLanguage(favorite.getOriginalLanguage());
            movie.setOriginalName(favorite.getOriginalName());
            movie.setOverview(favorite.getOverview());
            movie.setPopularity(favorite.getPopularity());
            movie.setPosterPath(favorite.getPosterPath());
            movie.setReleaseDate(favorite.getFirstAirDate());
            movie.setName(favorite.getName());
            movie.setVoteAverage(favorite.getVoteAverage());
            movie.setVoteCount(favorite.getVoteCount());
        }
        return movie;
    }

    public static Favorite tvToFavorite(Tv tv) {
        Favorite favorite = null;
        if (tv != null) {
            favorite = new Favorite();
            favorite.setAccountId(Utils.getAccount().getId());
            favorite.setAdult(tv.isAdult());
            favorite.setBackdropPath(tv.getBackdropPath());
            favorite.setGenreIds(tv.getGenreIds());
            favorite.setId(tv.getId());
            favorite.setOriginCountry(tv.getOriginCountry());
            favorite.setOriginalLanguage(tv.getOriginalLanguage());
            favorite.setOriginalName(tv.getOriginalName());
            favorite.setOverview(tv.getOverview());
            favorite.setPopularity(tv.getPopularity());
            favorite.setPosterPath(tv.getPosterPath());
            favorite.setFirstAirDate(tv.getFirstAirDate());
            favorite.setName(tv.getName());
            favorite.setVoteAverage(tv.getVoteAverage());
            favorite.setVoteCount(tv.getVoteCount());
        }
        return favorite;
    }

    public static WatchList movieToWatchList(Movie movie) {
        WatchList watchList = null;
        if (movie != null) {
            watchList = new WatchList();
            watchList.setAccountId(Utils.getAccount().getId());
            watchList.setAdult(movie.isAdult());
            watchList.setBackdropPath(movie.getBackdropPath());
            watchList.setGenreIds(movie.getGenreIds());
            watchList.setId(Long.valueOf(movie.getId()));
            watchList.setOriginCountry(movie.getOriginCountry());
            watchList.setOriginalLanguage(movie.getOriginalLanguage());
            watchList.setOriginalName(movie.getOriginalName());
            watchList.setOverview(movie.getOverview());
            watchList.setPopularity(movie.getPopularity());
            watchList.setPosterPath(movie.getPosterPath());
            watchList.setFirstAirDate(movie.getReleaseDate());
            watchList.setName(movie.getTitle());
            watchList.setVoteAverage(movie.getVoteAverage());
            watchList.setVoteCount(movie.getVoteCount());
        }
        return watchList;
    }

    public static Movie watchlistToMovie(WatchList watchList) {
        Movie movie = null;
        if (watchList != null) {
            movie = new Movie();
            movie.setAdult(watchList.getAdult());
            movie.setBackdropPath(watchList.getBackdropPath());
            movie.setGenreIds(watchList.getGenreIds());
            movie.setId(Math.toIntExact(watchList.getId()));
            movie.setOriginCountry(watchList.getOriginCountry());
            movie.setOriginalLanguage(watchList.getOriginalLanguage());
            movie.setOriginalName(watchList.getOriginalName());
            movie.setOverview(watchList.getOverview());
            movie.setPopularity(watchList.getPopularity());
            movie.setPosterPath(watchList.getPosterPath());
            movie.setReleaseDate(watchList.getFirstAirDate());
            movie.setName(watchList.getName());
            movie.setVoteAverage(watchList.getVoteAverage());
            movie.setVoteCount(watchList.getVoteCount());
        }
        return movie;
    }

    public static Tv favoriteToTv(Favorite favorite) {
        Tv tv = null;
        if (favorite != null) {
            tv = new Tv();
            tv.setAdult(favorite.isAdult());
            tv.setBackdropPath(favorite.getBackdropPath());
            tv.setGenreIds(favorite.getGenreIds());
            tv.setId(Math.toIntExact(favorite.getId()));
            tv.setOriginCountry(favorite.getOriginCountry());
            tv.setOriginalLanguage(favorite.getOriginalLanguage());
            tv.setOriginalName(favorite.getOriginalName());
            tv.setOverview(favorite.getOverview());
            tv.setPopularity(favorite.getPopularity());
            tv.setPosterPath(favorite.getPosterPath());
            tv.setFirstAirDate(favorite.getFirstAirDate());
            tv.setName(favorite.getName());
            tv.setVoteAverage(favorite.getVoteAverage());
            tv.setVoteCount(favorite.getVoteCount());
        }
        return tv;
    }

    public static WatchList tvToWatchlist(Tv tv) {
        WatchList watchList = null;
        if (tv != null) {
            watchList = new WatchList();
            watchList.setAccountId(Utils.getAccount().getId());
            watchList.setAdult(tv.isAdult());
            watchList.setBackdropPath(tv.getBackdropPath());
            watchList.setGenreIds(tv.getGenreIds());
            watchList.setId(tv.getId());
            watchList.setOriginCountry(tv.getOriginCountry());
            watchList.setOriginalLanguage(tv.getOriginalLanguage());
            watchList.setOriginalName(tv.getOriginalName());
            watchList.setOverview(tv.getOverview());
            watchList.setPopularity(tv.getPopularity());
            watchList.setPosterPath(tv.getPosterPath());
            watchList.setFirstAirDate(tv.getFirstAirDate());
            watchList.setName(tv.getName());
            watchList.setVoteAverage(tv.getVoteAverage());
            watchList.setVoteCount(tv.getVoteCount());
        }
        return watchList;
    }

    public static Tv watchlistToTv(WatchList watchList) {
        Tv tv = null;
        if (watchList != null) {
            tv = new Tv();
            tv.setAdult(watchList.getAdult());
            tv.setBackdropPath(watchList.getBackdropPath());
            tv.setGenreIds(watchList.getGenreIds());
            tv.setId(Math.toIntExact(watchList.getId()));
            tv.setOriginCountry(watchList.getOriginCountry());
            tv.setOriginalLanguage(watchList.getOriginalLanguage());
            tv.setOriginalName(watchList.getOriginalName());
            tv.setOverview(watchList.getOverview());
            tv.setPopularity(watchList.getPopularity());
            tv.setPosterPath(watchList.getPosterPath());
            tv.setFirstAirDate(watchList.getFirstAirDate());
            tv.setName(watchList.getName());
            tv.setVoteAverage(watchList.getVoteAverage());
            tv.setVoteCount(watchList.getVoteCount());
        }
        return tv;
    }

    public static void initMediaModelPalette(int dominantColor, int mutedColor, int lightMutedColor, boolean isDark, ItemMediaModel mediaModel) {
        if (dominantColor != 0) {
            mediaModel.dominantColor.set(dominantColor);
        }
        if (mutedColor != 0) {
            mediaModel.titleTextColor.set(mutedColor);
        }
        if (lightMutedColor != 0) {
            mediaModel.bodyTextColor.set(lightMutedColor);
        }
        mediaModel.isDark.set(isDark);
    }

    public static void initCategoryModelPalette(int dominantColor, int mutedColor, int lightMutedColor, boolean isDark, ItemCategoryModel categoryModel) {
        if (dominantColor != 0) {
            categoryModel.dominantColor.set(dominantColor);
        }
        if (mutedColor != 0) {
            categoryModel.titleTextColor.set(mutedColor);
        }
        if (lightMutedColor != 0) {
            categoryModel.bodyTextColor.set(lightMutedColor);
        }
        categoryModel.isDark.set(isDark);
    }

    public static String formatSeasonName(int number) {
        if (number < 10) {
            return "S0" + number;
        } else {
            return "S" + number;
        }
    }

    public static String formatEpisodeName(int number) {
        if (number < 10) {
            return "E0" + number;
        } else {
            return "E" + number;
        }
    }

    public static Double formatDouble(Double oteAverage) {
        if (oteAverage != null) {
            return BigDecimal.valueOf(oteAverage).setScale(1, RoundingMode.HALF_UP).doubleValue();
        }
        return 0.0;
//        return oteAverage;
    }

    public static void saveImage(Bitmap bmp, String path) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "TMDB");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = Environment.DIRECTORY_DOWNLOADS;
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


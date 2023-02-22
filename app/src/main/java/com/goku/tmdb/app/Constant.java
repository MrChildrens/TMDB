package com.goku.tmdb.app;

public class Constant {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String TMDB_API_KEY = "498f489e45baa0209584e4fb369edfad";
    public static final String TMDB_V4_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0OThmNDg5ZTQ1YmFhMDIwOTU4NGU0ZmIzNjllZGZhZCIsInN1YiI6IjYzYmE2ZDcxYzBhMzA4MDA5MzQzOTBlZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.BTbBHLWYuXSEArvPIQFRxHoXzVcZ_6ye1mOkVpvz7u8";
    public static final String LANGUAGE_ZH_CN = "zh-CN";
    public static final int HORI_GIRD_LINE_COUNT = 2;
    public static final int GIRD_LINE_COUNT = 3;

    public static final String PATH_ALL = "all";
    public static final String PATH_MOVIE = "movie";
    public static final String PATH_TV = "tv";
    public static final String PATH_PERSON = "person";
    public static final String PATH_DAY = "day";
    public static final String PATH_WEEK = "week";

    public static final String QUERY_KEY_ID = "id";
    public static final String QUERY_KEY_PART = "part";
    public static final String QUERY_KEY_PAGE = "page";
    public static final String QUERY_KEY_REGION = "region";
    public static final String QUERY_KEY_COUNTRY = "country";
    public static final String QUERY_KEY_LANGUAGE = "language";
    public static final String QUERY_KEY_INCLUDE_IMAGE_LANGUAGE = "include_image_language";
    public static final String QUERY_KEY_APPEND_TO_RESPONSE = "append_to_response";
    public static final String QUERY_KEY_WITH_GENRES = "with_genres";
    public static final String QUERY_KEY_WITH_COMPANIES = "with_companies";
    public static final String QUERY_KEY_SORT_BY = "sort_by";
    public static final String QUERY_KEY_PRIMARY_RELEASE_YEAR = "primary_release_year";
    public static final String QUERY_KEY_PRIMARY_RELEASE_DATE_GTE = "primary_release_date.gte";
    public static final String QUERY_KEY_PRIMARY_RELEASE_DATE_LTE = "primary_release_date.lte";
    public static final String QUERY_KEY_VOTE_AVERAGE_GTE = "vote_average.gte";
    public static final String QUERY_KEY_VOTE_AVERAGE_LTE = "vote_average.lte";
    public static final String QUERY_KEY_RELEASE_DATE_GTE = "release_date.gte";
    public static final String QUERY_KEY_QUERY = "query";
    public static final String QUERY_KEY_INCLUDE_ADULT = "include_adult";
    public static final String QUERY_KEY_SESSION_ID = "session_id";

    public static final String APPEND_KEY_ALTERNATIVE_TITLE = "alternative_titles,";
    public static final String APPEND_KEY_EXTERNAL_IDS = "external_ids,";
    public static final String APPEND_KEY_CREDITS = "credits,";
    public static final String APPEND_KEY_IMAGES = "images,";
    public static final String APPEND_KEY_KEYWORDS = "keywords,";
    public static final String APPEND_KEY_RELEASE_DATAS = "release_dates,";
    public static final String APPEND_KEY_TRANSLATIONS = "translations,";
    public static final String APPEND_KEY_VIDEOS = "videos,";
    public static final String APPEND_KEY_AGGREGATE_CREDITS = "aggregate_credits,";
    public static final String APPEND_KEY_CONTENT_RATINGS = "content_ratings,";
    public static final String APPEND_KEY_EPISODE_GROUPS = "episode_groups,";
    public static final String APPEND_KEY_SCREENED_THEATRICALLY = "screened_theatrically,";

    public static final String RELEASED = "Released";
    public static final String ENDED = "Ended";

    public static final String KEY_ITEM_MODEL = "item_model";
    public static final String KEY_ITEM_MODELS = "item_models";
    public static final String KEY_CATEGORY_MODEL = "category_model";
    public static final String KEY_ID = "id";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_ITEM_TYPE = "itemType";
    public static final String KEY_CATEGORY_TYPE = "categoryType";
    public static final String KEY_IMAGE_URLS = "image_urls";
    public static final String KEY_IMAGE_URL = "image_url";
    public static final String KEY_POSITION = "position";
    public static final String KEY_TARGET_POSITION = "target_position";
    public static final String KEY_SESSION_ID = "session_id";
    public static final String KEY_GUEST_SESSION_ID = "guest_session_id";
    public static final String KEY_ACCOUNT = "account";
    public static final String KEY_ACCOUNT_ID = "account_id";
    public static final String KEY_CONFIG_IMAGES = "config_images";

    public static final int DATA_STATUS_LOADING = 0;
    public static final int DATA_STATUS_COMPLETE = 1;
    public static final int DATA_STATUS_ERROR = 2;
    public static final int DATA_STATUS_NO_DATA = 3;
    public static final int DATA_STATUS_WAIT_SEARCH = 4;

    public static final int GENDER_FEMALE = 1;
    public static final int GENDER_MALE = 2;

    public static final int RELEASE_TYPE_PREMIERE = 1;
    public static final int RELEASE_TYPE_THEATRICAL_LIMITED = 2;
    public static final int RELEASE_TYPE_THEATRICAL = 3;
    public static final int RELEASE_TYPE_DIGITAL = 4;
    public static final int RELEASE_TYPE_PHYSICAL = 5;

    public static final String MEDIA_TYPE_MOVIE = "movie";
    public static final String MEDIA_TYPE_TV = "tv";

}

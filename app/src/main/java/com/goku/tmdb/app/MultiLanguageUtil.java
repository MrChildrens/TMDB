package com.goku.tmdb.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * @author: Ciel
 * @date: 2019/12/4
 * 多语言切换的帮助类
 * http://blog.csdn.net/finddreams
 */

public class MultiLanguageUtil {

    private static final String TAG = MultiLanguageUtil.class.getSimpleName();
    private static MultiLanguageUtil sInstance;
    private Context mContext;
    public static final String LANGUAGE = "language";
    public static final String COUNTRY = "countre";

    public static void init(Context mContext) {
        if (sInstance == null) {
            synchronized (MultiLanguageUtil.class) {
                if (sInstance == null) {
                    sInstance = new MultiLanguageUtil(mContext);
                }
            }
        }
    }

    public static MultiLanguageUtil getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("You must be init MultiLanguageUtil first");
        }
        return sInstance;
    }

    private MultiLanguageUtil(Context context) {
        this.mContext = context;
        //特殊处理，设置应用默认语言为英语
        String language = SPUtils.getInstance().getString(LANGUAGE);
        if (TextUtils.isEmpty(language)) {
            SPUtils.getInstance().put(LANGUAGE, Locale.getDefault().getLanguage());
        }
        String country = SPUtils.getInstance().getString(COUNTRY);
        if (TextUtils.isEmpty(language)) {
            SPUtils.getInstance().put(COUNTRY, Locale.getDefault().getCountry());
        }
    }

    /**
     * 设置语言
     */
    public void setConfiguration() {
        Locale targetLocale = getLanguageLocale();
        Configuration configuration = mContext.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(targetLocale);
        } else {
            configuration.locale = targetLocale;
        }
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);//语言更换生效的代码!
    }

    /**
     * 如果不是英文、简体中文、繁体中文，默认返回简体中文
     *
     * @return
     */
    private Locale getLanguageLocale() {
        String language = SPUtils.getInstance().getString(LANGUAGE);
        String country = SPUtils.getInstance().getString(COUNTRY);
        return new Locale(language, country);
    }

    private String getSystemLanguage(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();

    }

    //以上获取方式需要特殊处理一下
    public Locale getSysLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }

    /**
     * 更新语言
     *
     * @param language
     */
    public void updateLanguage(String language, String country) {
        SPUtils.getInstance().put(LANGUAGE, language);
        SPUtils.getInstance().put(COUNTRY, country);
        MultiLanguageUtil.getInstance().setConfiguration();
    }

    public static Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context);
        } else {
            MultiLanguageUtil.getInstance().setConfiguration();
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context createConfigurationResources(Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getInstance().getLanguageLocale();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }
}

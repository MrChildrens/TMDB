package com.goku.tmdb.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.goku.tmdb.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * @desciption: 初始化UniverImageLoader, 并用来加载网络图片
 */
public class ImageLoaderUtil {

    /**
     * UIL最多可以有多少条线程
     */
    private static final int THREAD_COUNT = 4;
    /**
     * 图片加载的优先级
     */
    private static final int PRIORITY = 2;
    /**
     * UIL最多可以缓存多少图片
     */
    private static final int DISC_CACHE_SIZE = 50 * 1024 * 1024;
    /**
     * 连接的超时时间
     */
    private static final int CONNECTION_TIME_OUT = 5 * 1000;
    /**
     * 读取的超时时间
     */
    private static final int READ_TIME_OUT = 30 * 10000;

    private static ImageLoader mImageLoader = null;
    private static ImageLoaderUtil mInstance = null;

    /**
     * 单例的私有构造方法
     *
     * @param context
     */
    private ImageLoaderUtil(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(context)
                //配置图片下载线程的最大数据
                .threadPoolSize(THREAD_COUNT)
                .threadPriority(Thread.NORM_PRIORITY - PRIORITY)
                //防止缓存多套尺寸图片到内存中
                .denyCacheImageMultipleSizesInMemory()
                //使用弱引用内存缓存
                .memoryCache(new WeakMemoryCache())
                //分配硬盘缓存大小
                .diskCacheSize(DISC_CACHE_SIZE)
                //保存的时候URI名称使用MD5加密
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                //图片下载顺序
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                //默认的图片加载Options
                .defaultDisplayImageOptions(getDefaultOptions())
                //设置图片下载器
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME_OUT, READ_TIME_OUT))
                //debug环境下输出日志
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();
    }

    /**
     * 实现默认的Options
     *
     * @return
     */
    private DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                //图片地址为空的时候
                .showImageForEmptyUri(R.drawable.ic_audience_while)
                //图片下载失败的时候显示
                .showImageOnFail(R.drawable.ic_audience_while)
                //图片可以缓存在内存
                .cacheInMemory(true)
                //图片可以缓存在硬盘
                .cacheOnDisk(true)
                //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .considerExifParams(true)
                //设置图片以如何的编码方式显示
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                //使用的图片解码类型
                .bitmapConfig(Bitmap.Config.RGB_565)
                //图片解码配置
                .decodingOptions(new BitmapFactory.Options())
                .build();
        return options;
    }

    public static ImageLoaderUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ImageLoaderUtil.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderUtil(context);
                }
            }
        }
        return mInstance;
    }

    public void displayImage(ImageView imageView, String url) {
        displayImage(imageView, url, null);
    }

    public void displayImage(ImageView imageView, String url, ImageLoadingListener listener) {
        if (mImageLoader != null) {
            mImageLoader.displayImage(url, imageView, listener);
        }
    }

    /**
     * 加载图片api
     *
     * @param imageView
     * @param url
     * @param options
     * @param listener
     */
    public void displayImage(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener listener) {
        if (mImageLoader != null) {
            mImageLoader.displayImage(url, imageView, options, listener);
        }
    }
}

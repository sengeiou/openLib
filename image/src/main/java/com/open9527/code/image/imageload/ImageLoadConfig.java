package com.open9527.code.image.imageload;

import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/30 13:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :图片加载配置.
 */
public class ImageLoadConfig {

    public int defaultRes;
    public int failRes;
    public int radius = 0;
    public RoundedCornersTransformation.CornerType cornerType;
    public ImageView.ScaleType scaleType;
    public DiskCacheStrategy diskCacheStrategy;
    public boolean skipMemoryCache = false;
    public boolean onlyRetrieveFromCache = false;
    public int width = -1;
    public int height = -1;

    /**
     * @param defaultRes            //默认占位符
     * @param failRes               //失败占位符
     * @param radius                //圆角大小
     * @param cornerType            //圆角方向(上下左右)
     * @param scaleType             //图片展示样式
     * @param diskCacheStrategy     //图片缓存模式
     * @param skipMemoryCache       //是否跳过缓存
     * @param onlyRetrieveFromCache //是否只加载缓存
     * @param width                 //图片宽
     * @param height                //图片高
     */
    public ImageLoadConfig(int defaultRes, int failRes, int radius, RoundedCornersTransformation.CornerType cornerType, ImageView.ScaleType scaleType, DiskCacheStrategy diskCacheStrategy, boolean skipMemoryCache, boolean onlyRetrieveFromCache, int width, int height) {
        this.defaultRes = defaultRes;
        this.failRes = failRes;
        this.radius = radius;
        this.cornerType = cornerType;
        this.scaleType = scaleType;
        this.diskCacheStrategy = diskCacheStrategy;
        this.skipMemoryCache = skipMemoryCache;
        this.onlyRetrieveFromCache = onlyRetrieveFromCache;
        this.width = width;
        this.height = height;
    }

    public ImageLoadConfig(int failRes) {
        this(0, failRes, 0, RoundedCornersTransformation.CornerType.ALL, ImageView.ScaleType.CENTER_CROP, DiskCacheStrategy.AUTOMATIC, false, false, -1, -1);
    }

    public ImageLoadConfig(int defaultRes, int failRes) {
        this(defaultRes, failRes, 0, RoundedCornersTransformation.CornerType.ALL, ImageView.ScaleType.CENTER_CROP, DiskCacheStrategy.AUTOMATIC, false, false, -1, -1);
    }

    public ImageLoadConfig(int defaultRes, int failRes, ImageView.ScaleType scaleType) {
        this(defaultRes, failRes, 0, RoundedCornersTransformation.CornerType.ALL, scaleType, DiskCacheStrategy.AUTOMATIC, false, false, -1, -1);
    }

    public ImageLoadConfig(int defaultRes, int failRes, int radius) {
        this(defaultRes, failRes, radius, RoundedCornersTransformation.CornerType.ALL, ImageView.ScaleType.CENTER_CROP, DiskCacheStrategy.AUTOMATIC, false, false, -1, -1);
    }


    public ImageLoadConfig(int defaultRes, int failRes, int radius, RoundedCornersTransformation.CornerType cornerType) {
        this(defaultRes, failRes, radius, cornerType, ImageView.ScaleType.CENTER_CROP, DiskCacheStrategy.AUTOMATIC, false, false, -1, -1);
    }
}
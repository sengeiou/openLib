package com.open9527.imageload;

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

    /**
     * 默认占位符
     */
    private int defaultRes;
    /**
     * 失败占位符
     */
    private int failRes;
    /**
     * 圆角大小
     */
    private int radius = 0;
    /**
     * 圆角方向(上下左右)
     */
    private RoundedCornersTransformation.CornerType cornerType;
    /**
     * 图片展示样式
     */
    private ImageView.ScaleType scaleType;
    /**
     * 图片缓存模式
     */
    private DiskCacheStrategy diskCacheStrategy;
    /**
     * 是否跳过缓存
     */
    private boolean skipMemoryCache = false;
    /**
     * 是否只加载缓存
     */
    private boolean onlyRetrieveFromCache = false;
    /**
     * 图片宽
     */
    private int width = -1;
    /**
     * 图片高
     */
    private int height = -1;

    public int getDefaultRes() {
        return defaultRes;
    }

    public void setDefaultRes(int defaultRes) {
        this.defaultRes = defaultRes;
    }

    public int getFailRes() {
        return failRes;
    }

    public void setFailRes(int failRes) {
        this.failRes = failRes;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public RoundedCornersTransformation.CornerType getCornerType() {
        return cornerType;
    }

    public void setCornerType(RoundedCornersTransformation.CornerType cornerType) {
        this.cornerType = cornerType;
    }

    public ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    public DiskCacheStrategy getDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public void setDiskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        this.diskCacheStrategy = diskCacheStrategy;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public void setSkipMemoryCache(boolean skipMemoryCache) {
        this.skipMemoryCache = skipMemoryCache;
    }

    public boolean isOnlyRetrieveFromCache() {
        return onlyRetrieveFromCache;
    }

    public void setOnlyRetrieveFromCache(boolean onlyRetrieveFromCache) {
        this.onlyRetrieveFromCache = onlyRetrieveFromCache;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static ImageLoadConfig.Bulider builder() {
        
        return new ImageLoadConfig.Bulider();
    }

    public static class Bulider {
        /**
         * 默认占位符
         */
        private int defaultRes;
        /**
         * 失败占位符
         */
        private int failRes;
        /**
         * 圆角大小
         */
        private int radius = 0;
        /**
         * 圆角方向(上下左右)
         */
        private RoundedCornersTransformation.CornerType cornerType;
        /**
         * 图片展示样式
         */
        private ImageView.ScaleType scaleType;
        /**
         * 图片缓存模式
         */
        private DiskCacheStrategy diskCacheStrategy;
        /**
         * 是否跳过缓存
         */
        private boolean skipMemoryCache = false;
        /**
         * 是否只加载缓存
         */
        private boolean onlyRetrieveFromCache = false;
        /**
         * 图片宽
         */
        private int width = -1;
        /**
         * 图片高
         */
        private int height = -1;

        public Bulider setDefaultRes(int defaultRes) {
            this.defaultRes = defaultRes;
            return this;
        }

        public Bulider setFailRes(int failRes) {
            this.failRes = failRes;
            return this;
        }

        public Bulider setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public Bulider setCornerType(RoundedCornersTransformation.CornerType cornerType) {
            this.cornerType = cornerType;
            return this;
        }

        public Bulider setScaleType(ImageView.ScaleType scaleType) {
            this.scaleType = scaleType;
            return this;
        }

        public Bulider setDiskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        public Bulider setSkipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }

        public Bulider setOnlyRetrieveFromCache(boolean onlyRetrieveFromCache) {
            this.onlyRetrieveFromCache = onlyRetrieveFromCache;
            return this;
        }

        public Bulider setWidth(int width) {
            this.width = width;
            return this;
        }

        public Bulider setHeight(int height) {
            this.height = height;
            return this;
        }
    }
}
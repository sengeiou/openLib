package com.open9527.code.image.imageload;

import android.widget.ImageView;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/30 13:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :图片加载配置.
 */
public class ImageLoadConfig {

    public int defaultRes;//默认占位符
    public int failRes;//失败占位符
    public int radius;// 圆角
    public RoundedCornersTransformation.CornerType cornerType;// 圆角方向
    public ImageView.ScaleType scaleType;//图片展示样式
    public int width = -1;//图片宽
    public int height = -1;//图片高

    /**
     * 构造函数
     *
     * @param defaultRes
     * @param failRes
     * @param radius
     * @param cornerType
     * @param width
     * @param height
     * @param scaleType
     */
    public ImageLoadConfig(int defaultRes, int failRes, int radius, RoundedCornersTransformation.CornerType cornerType, int width, int height, ImageView.ScaleType scaleType) {
        this.defaultRes = defaultRes;
        this.failRes = failRes;
        this.radius = radius;
        this.cornerType = cornerType;
        this.width = width;
        this.height = height;
        this.scaleType = scaleType;
    }

    public ImageLoadConfig(int defaultRes, int failRes, int radius, RoundedCornersTransformation.CornerType cornerType, int width, int height) {
        this(defaultRes, failRes, radius, cornerType, width, height, ImageView.ScaleType.FIT_CENTER);
    }

    public ImageLoadConfig(int defaultRes, int failRes, int width, int height) {
        this(defaultRes, failRes, 0, RoundedCornersTransformation.CornerType.ALL, width, height);
    }

    public ImageLoadConfig(int defaultRes, int failRes, int radius, RoundedCornersTransformation.CornerType cornerType) {
        this(defaultRes, failRes, radius, cornerType, -1, -1, ImageView.ScaleType.FIT_CENTER);
    }

    public ImageLoadConfig(int defaultRes, int failRes) {
        this(defaultRes, failRes, 0, RoundedCornersTransformation.CornerType.ALL);
    }

    public ImageLoadConfig(int defaultRes, int failRes, ImageView.ScaleType scaleType) {
        this.defaultRes = defaultRes;
        this.failRes = failRes;
        this.scaleType = scaleType;
    }

    public ImageLoadConfig(int defaultRes) {
        this(defaultRes, -1);
    }

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
}

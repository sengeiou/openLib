package com.open9527.code.image.imageload;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.open9527.code.image.glide.GlideImageLoad;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/30 13:43.
 * E-Mail Address ：open_9527@163.com.
 * DESC :图片加载管理.
 */
public class ImageLoadManger {

    private static final String TAG = "ImageLoadManger";
    private static ImageLoadInterface imageLoad = null;

    static {
        //TODO:调用图片加载,glide,picasso
        imageLoad = new GlideImageLoad();
    }

    /**
     * imageView中加载项目内资源
     *
     * @param mContext
     * @param view
     * @param resId
     */
    public static void display(Context mContext, final ImageView view, @DrawableRes int resId) {
        display(mContext, view, null, resId);
    }

    /**
     * 加载网络图片/本地图片
     *
     * @param mContext
     * @param view
     * @param url
     */
    public static void display(Context mContext, ImageView view, String url) {
        display(mContext, view, url, -1);
    }

    /**
     * 加载图片
     *
     * @param mContext     上下文
     * @param view         imageview
     * @param url          图片地址
     * @param defaultImage 默认显示内容
     */
    public static void display(Context mContext, ImageView view, String url, int defaultImage) {
        display(mContext, view, url, defaultImage, null);
    }

    /**
     * 加载图片
     *
     * @param mContext
     * @param view
     * @param url
     * @param imageLoadProcessInterface
     */
    public static void display(Context mContext, ImageView view, String url, ImageLoadProcessInterface imageLoadProcessInterface) {
        display(mContext, view, url, -1, imageLoadProcessInterface);
    }

    /**
     * @param mContext                  上下文
     * @param view                      imageview
     * @param url                       地址
     * @param defaultImage              默认图片
     * @param imageLoadProcessInterface 监听
     */
    public static void display(Context mContext, ImageView view, String url, int defaultImage, ImageLoadProcessInterface imageLoadProcessInterface) {
        display(mContext, view, url, defaultImage, -1, imageLoadProcessInterface);
    }

    public static void display(Context mContext, ImageView view, String url, int defaultImage, int failImage, ImageLoadProcessInterface imageLoadProcessInterface) {
        display(mContext, view, url, new ImageLoadConfig(defaultImage, failImage, 0, RoundedCornersTransformation.CornerType.ALL), imageLoadProcessInterface);
    }

    public static void display(Context mContext, ImageView view, String url, ImageLoadConfig config, ImageLoadProcessInterface imageLoadProcessInterface) {
        displayUrl(mContext, view, url, config, imageLoadProcessInterface);
    }

    public static void display(Context mContext, ImageView view, String url, ImageLoadConfig config) {
        displayUrl(mContext, view, url, config, null);
    }


    /**
     * 加载图片
     *
     * @param imageView view
     * @param url       url
     */
    private static void displayUrl(Context mContext, final ImageView imageView, final String url, final ImageLoadConfig config, final ImageLoadProcessInterface imageLoadProcessInterface) {
        try {
            imageLoad.display(mContext, imageView, url, config, imageLoadProcessInterface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复加载图片
     *
     * @param context
     */
    public static void resumeLoad(Context context, String url) {
        if (imageLoad != null) {
            imageLoad.resumeLoad(context, url);
        }
    }

    /**
     * 清除一个资源的加载
     *
     * @param context
     */
    public static void clearImageView(Context context, ImageView imageView, String url) {
        if (imageLoad != null) {
            imageLoad.clearImageView(context, imageView, url);
        }
    }

    /**
     * 暂停加载图片
     *
     * @param context
     */
    public static void pauseLoad(Context context, String url) {
        if (imageLoad != null) {
            imageLoad.pauseLoad(context, url);
        }
    }
}

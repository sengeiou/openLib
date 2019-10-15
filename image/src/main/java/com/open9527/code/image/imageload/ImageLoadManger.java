package com.open9527.code.image.imageload;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.open9527.code.image.glide.GlideImageLoad;


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
        //TODO:调用图片加载, glide / picasso
        imageLoad = new GlideImageLoad();
    }

    //load SD卡资源：load("file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg")
    //load assets资源：load("file:///android_asset/f003.gif")
    //load raw资源：load("android.resource://com.frank.glide/raw/raw_1")或load("android.resource://com.frank.glide/raw/"+R.raw.raw_1)
    //load drawable资源：load("android.resource://com.frank.glide/drawable/news")或load("android.resource://com.frank.glide/drawable/"+R.drawable.news)
    //load ContentProvider资源：load("content://media/external/images/media/139469")
    //load http资源：load("https://img-my.csdn.net/uploads/201508/05/1438760757_3588.jpg")
    //load https资源：load("https://img.alicdn.com/tps/TB1uyhoMpXXXXcLXVXXXXXXXXXX-476-538.jpg_240x5000q50.jpg_.webp")
    //load(Uri uri)，load(File file)，load(Integer resourceId)，load(URL url)，load(byte[] model)，load(T model)，loadFromMediaStore(Uri uri)。

    /**
     * 加载网络/本地/图片
     *
     * @param mContext
     * @param view
     * @param url
     * @param config
     * @param imageLoadProcessInterface
     */
    public static void display(Context mContext, ImageView view, String url, ImageLoadConfig config, ImageLoadProcessInterface imageLoadProcessInterface) {
        displayUrl(mContext, view, url, config, imageLoadProcessInterface);
    }

    public static void display(Context mContext, ImageView view, String url, ImageLoadConfig config) {
        displayUrl(mContext, view, url, config, null);
    }

    /**
     * 加载网络/本地/图片
     *
     * @param imageView view
     * @param url       url
     */
    private static void displayUrl(Context mContext, final ImageView imageView, final Object url, final ImageLoadConfig config, final ImageLoadProcessInterface imageLoadProcessInterface) {
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
     * @param url
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
     * @param imageView
     * @param url
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
     * @param url
     */
    public static void pauseLoad(Context context, String url) {
        if (imageLoad != null) {
            imageLoad.pauseLoad(context, url);
        }
    }
}

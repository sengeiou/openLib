package com.open9527.code.lib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.request.FutureTarget;
import com.open9527.code.common.interfaces.ILoadData;
import com.open9527.code.image.GlideApp;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;
import com.open9527.code.image.imageload.ImageLoadProcessInterface;
import com.open9527.code.lib.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/10 16:03.
 * E-Mail Address ：open_9527@163.com.
 * DESC :图片加载.
 */
public class ImageLoadUtils {

    /**
     * 图片加载
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void imageLoad(Context context, ImageView imageView, String url) {
        ImageLoadManger.display(context, imageView, CommonUtils.getUrl(url), new ImageLoadConfig(R.drawable.image_placeholder, R.drawable.image_placeholder));
    }

    /**
     * 图片加载
     *
     * @param context
     * @param imageView
     * @param url
     * @param radius
     * @param imageLoadProcessInterface
     */
    public static void imageLoad(Context context, ImageView imageView, String url, int radius, final ImageLoadProcessInterface imageLoadProcessInterface) {
        ImageLoadManger.display(context, imageView, CommonUtils.getUrl(url), new ImageLoadConfig(0, R.drawable.image_placeholder, radius), imageLoadProcessInterface);
    }

    /**
     * 图片加载
     *
     * @param context
     * @param imageView
     * @param url
     * @param imageLoadProcessInterface
     */
    public static void imageLoad(Context context, ImageView imageView, String url, final ImageLoadProcessInterface imageLoadProcessInterface) {
        ImageLoadManger.display(context, imageView, CommonUtils.getUrl(url), new ImageLoadConfig(0), imageLoadProcessInterface);
    }


    /**
     * 根据url 获取bitmap
     *
     * @param url
     * @return
     */

    public static void getBitmap(String url, ILoadData<Bitmap> interfaceCallBack) {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<Bitmap>() {
            @Nullable
            @Override
            public Bitmap doInBackground() throws Throwable {
                FutureTarget<Bitmap> futureTarget = GlideApp.with(Utils.getApp())
                        .asBitmap()
                        .load(url)
                        .submit();
                return futureTarget.get();
            }

            @Override
            public void onSuccess(@Nullable Bitmap result) {
                if (result != null && interfaceCallBack != null) {
                    interfaceCallBack.loadComplete(result);
                }
            }
        });
    }


}

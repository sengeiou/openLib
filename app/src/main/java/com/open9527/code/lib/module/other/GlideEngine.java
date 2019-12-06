package com.open9527.code.lib.module.other;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.luck.picture.lib.engine.ImageEngine;
import com.open9527.code.lib.utils.ImageLoadUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/26 15:57.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class GlideEngine implements  ImageEngine {
    @Override
    public void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        ImageLoadUtils.imageLoad(context,imageView,url);
    }

    @Override
    public void loadFolderAsBitmapImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView, int placeholderId) {
        ImageLoadUtils.imageLoad(context,imageView,url);
    }

    @Override
    public void loadAsGifImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        ImageLoadUtils.imageLoad(context,imageView,url);
    }

    @Override
    public void loadAsBitmapGridImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView, int placeholderId) {
        ImageLoadUtils.imageLoad(context,imageView,url);
    }

    private GlideEngine() {
    }

    private static GlideEngine instance;

    public static GlideEngine createGlideEngine() {
        if (null == instance) {
            synchronized (GlideEngine.class) {
                if (null == instance) {
                    instance = new GlideEngine();
                }
            }
        }
        return instance;
    }
}

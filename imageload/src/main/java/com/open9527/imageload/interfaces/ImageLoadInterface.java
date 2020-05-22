package com.open9527.imageload.interfaces;

import android.widget.ImageView;

import com.open9527.imageload.glide.GlideApp;
import com.open9527.imageload.glide.GlideRequests;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/30 13:41.
 * E-Mail Address ：open_9527@163.com.
 * DESC :图片加载监听.
 */
public interface ImageLoadInterface {

    default GlideRequests getGlideRequests(ImageView imageView) {

        return GlideApp.with(imageView);
    }

    /**
     * 暂停加载
     *
     * @param url
     */
    default void pauseLoad(Object url) {

    }

    /**
     * 重新加载
     *
     * @param url
     */
    default void resumeLoad(Object url) {
    }

    /**
     * 清除一个资源的加载
     *
     * @param imageView
     * @param url
     */
    default void clearImageView(ImageView imageView, Object url) {
    }
}

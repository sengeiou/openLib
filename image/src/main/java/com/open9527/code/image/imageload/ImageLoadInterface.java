package com.open9527.code.image.imageload;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/30 13:41.
 * E-Mail Address ：open_9527@163.com.
 * DESC :图片加载监听.
 */
public interface ImageLoadInterface {
    /**
     * 显示路径中的图片（网络、文件中）
     *
     * @param mContext
     * @param view
     * @param url
     * @param config                    配置参数
     * @param imageLoadProcessInterface 加载过程监听
     */
    default void display(Context mContext, final ImageView view, Object url, ImageLoadConfig config, ImageLoadProcessInterface imageLoadProcessInterface) {

    }

    /**
     * 开始加载
     *
     * @param context
     */
    default void resumeLoad(Context context, Object url) {
    }

    /**
     * 暂停加载
     *
     * @param context
     */
    default void pauseLoad(Context context, Object url) {
    }

    /**
     * 清除一个资源的加载
     *
     * @param context
     */
    default void clearImageView(Context context, ImageView imageView, Object url) {
    }
}

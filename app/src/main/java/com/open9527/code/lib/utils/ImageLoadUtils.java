package com.open9527.code.lib.utils;

import android.content.Context;
import android.widget.ImageView;

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
     * @param imageLoadProcessInterface
     */
    public static void imageLoad(Context context, ImageView imageView, String url, final ImageLoadProcessInterface imageLoadProcessInterface) {
        ImageLoadManger.display(context, imageView, CommonUtils.getUrl(url), new ImageLoadConfig( R.drawable.image_placeholder), imageLoadProcessInterface);
    }


}

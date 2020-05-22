package com.open9527.imageload;

import com.open9527.imageload.glide.GlideImageLoad;
import com.open9527.imageload.interfaces.ImageLoadInterface;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/30 13:43.
 * E-Mail Address ：open_9527@163.com.
 * DESC :图片加载管理.
 */
public class ImageLoadManger {

    private static final String TAG = "ImageLoadManger";

    /**
     * ImageLoadConfig 配置文件
     */
    private ImageLoadConfig ImageLoadConfig;

    /**
     * 图片加载器:默认 glide
     * 可自行配置
     */
    private ImageLoadInterface imageLoadInterface;


    public ImageLoadManger(ImageLoadConfig imageLoadConfig) {
        this.ImageLoadConfig = imageLoadConfig;
        imageLoadInterface = new GlideImageLoad();
    }

    public static ImageLoadManger build(ImageLoadConfig imageLoadConfig) {
        return new ImageLoadManger(imageLoadConfig);
    }

}

package com.open9527.compression;

import android.content.Context;
import android.text.TextUtils;

import com.open9527.compression.config.CompressConfig;
import com.open9527.compression.config.Photo;
import com.open9527.compression.interfaces.CompressImage;
import com.open9527.compression.interfaces.CompressResultListener;
import com.open9527.compression.utils.CompressImageUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 14:21.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CompressImageManager implements CompressImage {
    //图片压缩工具类
    private CompressImageUtil compressImageUtil;

    //需要压缩的图片集合
    private ArrayList<Photo> images;

    //压缩监听
    private CompressImage.CompressListener listener;

    //压缩配置
    private CompressConfig compressConfig;

    private CompressImageManager(Context context, CompressConfig config,
                                 ArrayList<Photo> photos,
                                 CompressListener listener) {
        this.compressImageUtil = new CompressImageUtil(context, config);
        this.compressConfig = config;
        this.images = photos;
        this.listener = listener;

    }

    public static CompressImageManager build(Context context, CompressConfig config,
                                             ArrayList<Photo> photos,
                                             CompressListener listener) {
        return new CompressImageManager(context, config, photos, listener);
    }

    @Override
    public void compress() {
        if (images == null || images.isEmpty()) {
            listener.onCompressFailed(images, "images is null !");
            return;
        }
        for (Photo image : images) {
            if (image == null) {
                listener.onCompressFailed(images, "image is null !");
                return;
            }
        }
        //开始递归压缩
        compress(images.get(0));
    }

    private void compress(Photo image) {
        if (TextUtils.isEmpty(image.getOriinalPath())) {
            continueCompress(image, false);
            return;
        }
        File file = new File(image.getOriinalPath());
        if (!file.isFile() || !file.exists()) {
            continueCompress(image, false);
            return;
        }
        if (file.length() < compressConfig.getMaxSize()) {
            continueCompress(image, true);
            return;
        }
        compressImageUtil.compress(image.getOriinalPath(), new CompressResultListener() {
            @Override
            public void onCompressSuccess(String imagePath) {
                image.setCompressPath(imagePath);
                continueCompress(image, true);
            }

            @Override
            public void onCompressFailed(String imagePath, String... error) {
                continueCompress(image, false, error);
            }
        });
    }

    private void continueCompress(Photo image, boolean isCompressed, String... error) {
        image.setCompressed(isCompressed);
        //获取当前压缩图片的索引
        int index = images.indexOf(image);
        //如果不是最后一张图片,则递归继续压缩
        if (index == images.size() - 1) {
            handlerCallback(error);
        } else {
            compress(images.get(index + 1));
        }

    }

    private void handlerCallback(String... error) {
        if (error.length > 0) {
            listener.onCompressFailed(images, error);
            return;
        }
        for (Photo image : images) {
            if (!image.getCompressed()) {
                listener.onCompressFailed(images, image.getOriinalPath() + "--compress is error !");
            }
        }
        listener.onCompressSuccess(images);
    }
}

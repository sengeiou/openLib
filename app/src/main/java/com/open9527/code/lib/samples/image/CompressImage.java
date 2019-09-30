package com.open9527.code.lib.samples.image;

import java.util.ArrayList;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 14:14.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface CompressImage {
    void compress();//开始压缩

    //返回压缩结果
    interface CompressListener {
        void onCompressSuccess(ArrayList<Photo> images);

        void onCompressFailed(ArrayList<Photo> images, String... error);
    }
}

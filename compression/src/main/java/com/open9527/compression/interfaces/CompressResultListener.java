package com.open9527.compression.interfaces;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 14:31.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface CompressResultListener {
    void onCompressSuccess(String imagePath);

    void onCompressFailed(String imagePath, String... error);
}

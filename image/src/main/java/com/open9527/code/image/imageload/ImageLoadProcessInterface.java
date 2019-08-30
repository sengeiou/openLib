package com.open9527.code.image.imageload;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/7/30 13:39.
 * E-Mail Address ：open_9527@163.com.
 * DESC :图片加载过程接口.
 */
public interface ImageLoadProcessInterface {
    /**
     * 开始加载
     */
    default void onLoadStarted() {
    }


    /**
     * 资源准备妥当
     */
    default void onResourceReady() {

    }

    /**
     * 资源已经释放
     */
    default void onLoadCleared() {
    }

    /**
     * 资源加载失败
     */
    default void onLoadFailed() {
    }
}

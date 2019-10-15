package com.open9527.code.common.interfaces;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/10 12:07.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface ILoadData<T> {

    /**
     * 配置接口回调数据
     * @param t
     */

    default void loadComplete(T t) {
    }
}

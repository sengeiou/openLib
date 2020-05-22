package com.open9527.code.lib.binding;

/**
 * Created by     : open9527
 * Created times  : on 2020/4/18/018 17:11.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface IRefresh<T> {

    default void loadComplete(T isRefresh) {
    }

}

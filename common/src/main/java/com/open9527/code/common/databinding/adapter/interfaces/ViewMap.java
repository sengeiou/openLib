package com.open9527.code.common.databinding.adapter.interfaces;

import androidx.annotation.LayoutRes;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2018/6/2 15:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :item绑定.
 */
public interface ViewMap<T> {
    @LayoutRes
    int layoutId(T t);
}

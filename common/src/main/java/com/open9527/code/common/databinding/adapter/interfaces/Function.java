package com.open9527.code.common.databinding.adapter.interfaces;

import android.view.View;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2018/6/2 15:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :事件处理.
 */
public interface Function<T> {
    void call(View view, T t);
}

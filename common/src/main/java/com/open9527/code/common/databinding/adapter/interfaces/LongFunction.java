package com.open9527.code.common.databinding.adapter.interfaces;

import android.view.View;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/1/30 13:29.
 * E-Mail Address ：open_9527@163.com.
 * DESC :长按事件.
 */
public interface LongFunction<T> {
    boolean call(View view, T t);
}

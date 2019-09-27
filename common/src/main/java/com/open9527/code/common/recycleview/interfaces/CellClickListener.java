package com.open9527.code.common.recycleview.interfaces;

import android.view.View;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/26 17:08.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface CellClickListener<T> {
    default void onItemClick(View view, int position, T data) {
    }

    default void onItemLongClick(View view, int position, T data) {
    }

    default void onItemChildClick(View view, int position, T data) {
    }
}

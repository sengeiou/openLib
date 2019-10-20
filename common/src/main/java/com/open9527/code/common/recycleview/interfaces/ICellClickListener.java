package com.open9527.code.common.recycleview.interfaces;

import android.view.View;

import com.open9527.code.common.recycleview.BaseCell;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/26 17:08.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface ICellClickListener {

    default void onItemClick(View view, int position, BaseCell... baseCells) {
    }

    default boolean onItemLongClick(View view, int position, BaseCell... baseCells) {
        return false;
    }
}

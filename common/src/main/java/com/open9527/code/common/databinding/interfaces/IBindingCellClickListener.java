package com.open9527.code.common.databinding.interfaces;

import android.view.View;

import com.open9527.code.common.databinding.BindingBaseCell;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/26 17:08.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface IBindingCellClickListener {


    /**
     * 点击事件
     *
     * @param view
     * @param position
     * @param bindingBaseCells
     */
    default void onItemClick(View view, int position, BindingBaseCell... bindingBaseCells) {
    }

    /**
     * 长按事件
     *
     * @param view
     * @param position
     * @param bindingBaseCells
     */
    default boolean onItemLongClick(View view, int position, BindingBaseCell... bindingBaseCells) {

        return false;
    }
}

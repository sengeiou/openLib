package com.open9527.code.customview.empty;

import android.view.View;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/21 14:54.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface IEmptyClick {

    /**
     * 错误重试
     *
     * @param view
     */
    default void onErrorRetry(View view) {

    }

}

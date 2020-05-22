package com.open9527.photoview.interfaces;

import android.graphics.RectF;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/29 16:39.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface OnMatrixChangedListener {

    /**
     * Callback for when the Matrix displaying the Drawable has changed. This could be because
     * the View's bounds have changed, or the user has zoomed.
     *
     * @param rect - Rectangle displaying the Drawable's new bounds.
     */
    void onMatrixChanged(RectF rect);
}

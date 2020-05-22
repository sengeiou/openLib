package com.open9527.photoview.interfaces;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/29 16:39.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface OnGestureListener {

    void onDrag(float dx, float dy);

    void onFling(float startX, float startY, float velocityX, float velocityY);

    void onScale(float scaleFactor, float focusX, float focusY);
}
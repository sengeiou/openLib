package com.open9527.photoview.interfaces;

import android.widget.ImageView;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/29 16:39.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface OnPhotoTapListener {

    /**
     * A callback to receive where the user taps on a photo. You will only receive a callback if
     * the user taps on the actual photo, tapping on 'whitespace' will be ignored.
     *
     * @param view ImageView the user tapped.
     * @param x where the user tapped from the of the Drawable, as percentage of the
     * Drawable width.
     * @param y where the user tapped from the top of the Drawable, as percentage of the
     * Drawable imageViewHeight.
     */
    void onPhotoTap(ImageView view, float x, float y);
}

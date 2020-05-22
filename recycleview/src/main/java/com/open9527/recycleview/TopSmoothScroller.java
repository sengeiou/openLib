package com.open9527.recycleview;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * 滑动到指定位置(带动画)
 * eg:  TopSmoothScroller topSmoothScroller = new TopSmoothScroller(this);
 *      topSmoothScroller.setTargetPosition(galleryBean.getIndex());
 *      linearLayoutManager.startSmoothScroll(topSmoothScroller);
 */
public class TopSmoothScroller extends LinearSmoothScroller {
    public TopSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        //具体见源码注释
        return SNAP_TO_START;
    }

    @Override
    protected int getVerticalSnapPreference() {
        //具体见源码注释
        return SNAP_TO_START;
    }
}

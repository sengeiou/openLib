package com.open9527.code.customview.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/1/14 18:14.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */

public class NoScrollViewPager extends ViewPager {

    private boolean isScroll = false;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIsScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScroll)
            return false;
        else
            return super.onTouchEvent(ev);
    }
}

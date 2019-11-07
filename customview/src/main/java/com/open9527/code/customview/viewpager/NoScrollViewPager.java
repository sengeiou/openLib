package com.open9527.code.customview.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
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
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

//    @Override
//    public boolean executeKeyEvent(@NonNull KeyEvent event) {
//        // 不响应按键事件
//        return false;
//    }
    @Override
    public void setCurrentItem(int item) {
        boolean smoothScroll;
        int currentItem = getCurrentItem();
        if (currentItem == 0) {
            // 如果当前是第一页，只有第二页才会有动画
            smoothScroll = item == currentItem + 1;
        } else if (currentItem == getCount() - 1) {
            // 如果当前是最后一页，只有最后第二页才会有动画
            smoothScroll = item == currentItem - 1;
        } else {
            // 如果当前是中间页，只有相邻页才会有动画
            smoothScroll = Math.abs(currentItem - item) == 1;
        }
        super.setCurrentItem(item, smoothScroll);
    }

    public int getCount() {
        PagerAdapter adapter = getAdapter();
        return adapter != null ? adapter.getCount() : 0;
    }

}

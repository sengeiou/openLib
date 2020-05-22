package com.open9527.recycleview;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

/**
 * Created by     : open9527
 * Created times  : on  2020/3/31.
 * E-Mail Address ：open_9527@163.com.
 * DESC :去除RecyclerView的动画.
 */
public class RecyclerViewAnimUtil {

    private static final RecyclerViewAnimUtil ourInstance = new RecyclerViewAnimUtil();

    public static RecyclerViewAnimUtil getInstance() {
        return ourInstance;
    }

    private RecyclerViewAnimUtil() {
    }

    /**
     * 关闭默认局部刷新动画
     */
    public void closeDefaultAnimator(RecyclerView recyclerView) {
        if (null == recyclerView) return;
        if (recyclerView.getItemAnimator() == null) return;
        recyclerView.getItemAnimator().setAddDuration(0);
        recyclerView.getItemAnimator().setChangeDuration(0);
        recyclerView.getItemAnimator().setMoveDuration(0);
        recyclerView.getItemAnimator().setRemoveDuration(0);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }
}

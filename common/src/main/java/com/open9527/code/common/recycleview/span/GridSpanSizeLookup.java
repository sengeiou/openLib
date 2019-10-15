package com.open9527.code.common.recycleview.span;

import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/11 12:30.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class GridSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private int mMaxCount;
    private List<Object> mObjects;


    public GridSpanSizeLookup(int maxCount, List<Object> objects) {
        this.mMaxCount = maxCount;
        this.mObjects = objects;
    }

    /**
     * 该方法的返回值就是指定position所占的列数
     *
     * @param position 指定索引
     * @return 列数
     */
    @Override
    public int getSpanSize(int position) {
        int i = position - mObjects.size();
        if (i >= 0) {
            return mMaxCount;
        }
        return 1;
    }

}

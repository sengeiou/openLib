package com.open9527.code.customview.banner;

import android.view.View;

import androidx.annotation.FloatRange;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 12:07.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class GalleryScaleTransformer implements GalleryLayoutManager.ItemTransformer {

    //设置缩放比例因子
    private float scaleDivisor = 0.2f;
    private int padding = 30;

    public GalleryScaleTransformer(@FloatRange(from = 0.0f, to = 1.0f) float scaleSize, int padding) {
        this.scaleDivisor = scaleSize;
        this.padding = padding;
    }

    @Override
    public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
        item.setPivotX(item.getWidth() / 2.0f);
        item.setPivotY(item.getHeight() / 2.0f);
        if (scaleDivisor == 0.0f) {
            int measuredWidth = item.getMeasuredWidth();
            //Log.d("GalleryScaleTransformer",measuredWidth+"");
            if (padding < 0 || padding > measuredWidth) {
                padding = 30;
            }
            item.setPadding(padding, 0, padding, 0);
        } else {
            float scale = 1 - scaleDivisor * Math.abs(fraction);
            //可以在这里对view设置动画效果
            item.setScaleX(scale);
            item.setScaleY(scale);
        }
    }

}

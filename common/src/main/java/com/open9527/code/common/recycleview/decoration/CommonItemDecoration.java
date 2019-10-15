package com.open9527.code.common.recycleview.decoration;

import android.content.Context;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.open9527.code.common.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/11 12:58.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CommonItemDecoration extends RvItemDecoration {
    private int spanCount = 0;

    public CommonItemDecoration(Context context) {
        super(context);
    }

    public CommonItemDecoration(Context context, int spanCount) {
        super(context);
        this.spanCount = spanCount;
    }

    @Nullable
    @Override
    public RvDivider getDivider(int itemPosition) {
        RvDividerBuilder divider = new RvDividerBuilder();
        divider.setLeftSideLine(true, ColorUtils.getColor(R.color.color_eee), AdaptScreenUtils.pt2Px(10), 0, 0);
        divider.setTopSideLine(true, ColorUtils.getColor(R.color.color_eee), AdaptScreenUtils.pt2Px(10), 0, 0);
        return divider.create();
    }
}

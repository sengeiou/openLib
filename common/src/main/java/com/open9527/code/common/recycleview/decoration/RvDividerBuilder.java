package com.open9527.code.common.recycleview.decoration;

import androidx.annotation.ColorInt;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/11 12:50.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class RvDividerBuilder {

    private RvSideLine leftSideLine;
    private RvSideLine topSideLine;
    private RvSideLine rightSideLine;
    private RvSideLine bottomSideLine;


    public RvDividerBuilder setLeftSideLine(boolean isHave, @ColorInt int color, float width, float startPadding, float endPadding) {
        this.leftSideLine = new RvSideLine(isHave, color, width, startPadding, endPadding);
        return this;
    }

    public RvDividerBuilder setTopSideLine(boolean isHave, @ColorInt int color, float width, float startPadding, float endPadding) {
        this.topSideLine = new RvSideLine(isHave, color, width, startPadding, endPadding);
        return this;
    }

    public RvDividerBuilder setRightSideLine(boolean isHave, @ColorInt int color, float width, float startPadding, float endPadding) {
        this.rightSideLine = new RvSideLine(isHave, color, width, startPadding, endPadding);
        return this;
    }

    public RvDividerBuilder setBottomSideLine(boolean isHave, @ColorInt int color, float width, float startPadding, float endPadding) {
        this.bottomSideLine = new RvSideLine(isHave, color, width, startPadding, endPadding);
        return this;
    }

    public RvDivider create() {
        //提供一个默认不显示的sideline，防止空指针
        RvSideLine defaultSideLine = new RvSideLine(false, 0xff666666, 0, 0, 0);

        leftSideLine = (leftSideLine != null ? leftSideLine : defaultSideLine);
        topSideLine = (topSideLine != null ? topSideLine : defaultSideLine);
        rightSideLine = (rightSideLine != null ? rightSideLine : defaultSideLine);
        bottomSideLine = (bottomSideLine != null ? bottomSideLine : defaultSideLine);

        return new RvDivider(leftSideLine, topSideLine, rightSideLine, bottomSideLine);
    }

}

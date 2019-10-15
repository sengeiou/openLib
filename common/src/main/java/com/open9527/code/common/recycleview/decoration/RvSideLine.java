package com.open9527.code.common.recycleview.decoration;

import androidx.annotation.ColorInt;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/11 12:46.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class RvSideLine {
    public boolean isHave = false;
    /**
     * A single color value in the form 0xAARRGGBB.
     **/
    public int color;
    /**
     * 单位px
     */
    public float width;
    /**
     * startPadding,分割线起始的padding，水平方向左为start，垂直方向上为start
     * endPadding,分割线尾部的padding，水平方向右为end，垂直方向下为end
     */
    public float startPadding;
    public float endPadding;

    public RvSideLine(boolean isHave, @ColorInt int color, float width, float startPadding, float endPadding) {
        this.isHave = isHave;
        this.color = color;
        this.width = width;
        this.startPadding = startPadding;
        this.endPadding = endPadding;
    }

    public boolean isHave() {
        return isHave;
    }

    public void setHave(boolean have) {
        isHave = have;
    }

    public int getColor() {
        return color;
    }

    public void setColor(@ColorInt int color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getStartPadding() {
        return startPadding;
    }

    public void setStartPadding(float startPadding) {
        this.startPadding = startPadding;
    }

    public float getEndPadding() {
        return endPadding;
    }

    public void setEndPadding(float endPadding) {
        this.endPadding = endPadding;
    }
}

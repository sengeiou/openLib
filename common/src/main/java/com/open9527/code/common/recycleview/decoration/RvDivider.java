package com.open9527.code.common.recycleview.decoration;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/11 12:46.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class RvDivider {
    public RvSideLine leftSideLine;
    public RvSideLine topSideLine;
    public RvSideLine rightSideLine;
    public RvSideLine bottomSideLine;


    public RvDivider(RvSideLine leftSideLine, RvSideLine topSideLine, RvSideLine rightSideLine, RvSideLine bottomSideLine) {
        this.leftSideLine = leftSideLine;
        this.topSideLine = topSideLine;
        this.rightSideLine = rightSideLine;
        this.bottomSideLine = bottomSideLine;
    }

    public RvSideLine getLeftSideLine() {
        return leftSideLine;
    }

    public void setLeftSideLine(RvSideLine leftSideLine) {
        this.leftSideLine = leftSideLine;
    }

    public RvSideLine getTopSideLine() {
        return topSideLine;
    }

    public void setTopSideLine(RvSideLine topSideLine) {
        this.topSideLine = topSideLine;
    }

    public RvSideLine getRightSideLine() {
        return rightSideLine;
    }

    public void setRightSideLine(RvSideLine rightSideLine) {
        this.rightSideLine = rightSideLine;
    }

    public RvSideLine getBottomSideLine() {
        return bottomSideLine;
    }

    public void setBottomSideLine(RvSideLine bottomSideLine) {
        this.bottomSideLine = bottomSideLine;
    }
}

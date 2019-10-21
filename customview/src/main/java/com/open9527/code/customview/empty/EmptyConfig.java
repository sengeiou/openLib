package com.open9527.code.customview.empty;

import android.view.View;

import androidx.annotation.LayoutRes;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/21 14:51.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class EmptyConfig {
    //原始布局
    private View contentLayout;
    //默认布局
    private View customLayout;
    @LayoutRes
    private int customLayoutID;
    //错误重试
    private IEmptyClick iEmptyClick;
    //布局处理
    private EmptyLayoutHelper emptyLayoutHelper;

    public View getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(View contentLayout) {
        this.contentLayout = contentLayout;
    }

    public View getCustomLayout() {
        return customLayout;
    }

    public void setCustomLayout(View customLayout) {
        this.customLayout = customLayout;
    }

    public int getCustomLayoutID() {
        return customLayoutID;
    }

    public void setCustomLayoutID(int customLayoutID) {
        this.customLayoutID = customLayoutID;
    }

    public EmptyLayoutHelper getEmptyLayoutHelper() {
        return emptyLayoutHelper;
    }

    public void setEmptyLayoutHelper(EmptyLayoutHelper emptyLayoutHelper) {
        this.emptyLayoutHelper = emptyLayoutHelper;
    }

    public IEmptyClick getiEmptyClick() {
        return iEmptyClick;
    }

    public void setiEmptyClick(IEmptyClick iEmptyClick) {
        this.iEmptyClick = iEmptyClick;
    }

    public static EmptyConfig.Bulider builder() {
        return new Bulider();
    }

    public EmptyConfig(Bulider builder) {
        this.contentLayout = builder.contentLayout;
        this.customLayout = builder.customLayout;
        this.customLayoutID = builder.customLayoutID;
        this.iEmptyClick = builder.iEmptyClick;
        this.emptyLayoutHelper = builder.emptyLayoutHelper;
    }

    public static class Bulider {
        //原始布局
        private View contentLayout;
        //默认布局
        private View customLayout;
        @LayoutRes
        private int customLayoutID;
        //错误重试
        private IEmptyClick iEmptyClick;

        private EmptyLayoutHelper emptyLayoutHelper;

        public Bulider setContentLayout(View contentLayout) {
            this.contentLayout = contentLayout;
            return this;
        }

        public Bulider setCustomLayout(View customLayout) {
            this.customLayout = customLayout;
            return this;
        }

        public Bulider getCustomLayoutID(int customLayoutID) {
            this.customLayoutID = customLayoutID;
            return this;
        }

        public Bulider setIEmptyClick(IEmptyClick iEmptyClick) {
            this.iEmptyClick = iEmptyClick;
            return this;
        }

        public Bulider setEmptyLayoutHelper(EmptyLayoutHelper emptyLayoutHelper) {
            this.emptyLayoutHelper = emptyLayoutHelper;
            return this;
        }


        public EmptyConfig create() {
            return new EmptyConfig(this);
        }
    }
}

package com.open9527.empty;

import android.view.View;

import androidx.annotation.LayoutRes;

import java.util.Objects;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/12 15:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class EmptyConfig {

    //需替换得根布局
    private View rootView;

    //空数据布局(ID的优先级比VIEW的高)
    @LayoutRes
    private int emptyLayoutID;
    private View emptyView;

    //加载布局(ID的优先级比VIEW的高)
    @LayoutRes
    private int loadingLayoutID;
    private View loadingView;

    //失败布局(ID的优先级比VIEW的高)
    @LayoutRes
    private int errorLayoutID;
    private View errorView;

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public int getEmptyLayoutID() {
        return emptyLayoutID;
    }

    public void setEmptyLayoutID(@LayoutRes int emptyLayoutID) {
        this.emptyLayoutID = emptyLayoutID;
    }

    public int getLoadingLayoutID() {
        return loadingLayoutID;
    }

    public void setLoadingLayoutID(int loadingLayoutID) {
        this.loadingLayoutID = loadingLayoutID;
    }

    public int getErrorLayoutID() {
        return errorLayoutID;
    }

    public void setErrorLayoutID(int errorLayoutID) {
        this.errorLayoutID = errorLayoutID;
    }

    public View getLoadingView() {
        return loadingView;
    }

    public void setLoadingView(View loadingView) {
        this.loadingView = loadingView;
    }

    public View getErrorView() {
        return errorView;
    }

    public void setErrorView(View errorView) {
        this.errorView = errorView;
    }

    public static EmptyConfig.Bulider builder() {
        return new EmptyConfig.Bulider();
    }

    public EmptyConfig(Bulider builder) {

        this.rootView = builder.rootView;

        this.emptyView = builder.emptyView;
        this.emptyLayoutID = builder.emptyLayoutID;

        this.loadingLayoutID = builder.loadingLayoutID;
        this.loadingView = builder.loadingView;

        this.errorLayoutID = builder.errorLayoutID;
        this.errorView = builder.errorView;
    }


    public static class Bulider {

        //需替换得根布局
        private View rootView;

        //空数据布局样式
        @LayoutRes
        private int emptyLayoutID;
        private View emptyView;

        //加载布局
        @LayoutRes
        private int loadingLayoutID;
        private View loadingView;

        //失败布局
        @LayoutRes
        private int errorLayoutID;
        private View errorView;


        public Bulider setRootView(View rootView) {
            this.rootView = rootView;
            return this;
        }

        public Bulider setEmptyLayoutID(@LayoutRes int emptyLayoutID) {
            this.emptyLayoutID = emptyLayoutID;
            return this;
        }

        public Bulider setEmptyView(View emptyView) {
            this.emptyView = emptyView;
            return this;
        }

        public Bulider setLoadingLayoutID(@LayoutRes int loadingLayoutID) {
            this.loadingLayoutID = loadingLayoutID;
            return this;
        }

        public Bulider setLoadingView(View loadingView) {
            this.loadingView = loadingView;
            return this;
        }

        public Bulider setErrorLayoutID(@LayoutRes int errorLayoutID) {
            this.errorLayoutID = errorLayoutID;
            return this;
        }

        public Bulider setErrorView(View errorView) {
            this.errorView = errorView;
            return this;
        }

        public EmptyConfig create() {
            return new EmptyConfig(this);
        }
    }


}

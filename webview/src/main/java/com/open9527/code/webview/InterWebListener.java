package com.open9527.code.webview;


import androidx.annotation.IntRange;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/24 18:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :web的接口回调，包括常见状态页面切换，进度条变化等.
 */
public interface InterWebListener {

    /**
     * 隐藏进度条
     */
    void hindProgressBar();

    /**
     * 展示异常页面
     */
    void showErrorView();

    /**
     * 进度条变化时调用，这里添加注解限定符，必须是在0到100之间
     * @param newProgress                   进度0-100
     */
    void startProgress(@IntRange(from = 0, to = 100) int newProgress);

    /**
     * 获取加载网页的标题
     * @param title                         title标题
     */
    void showTitle(String title);
}


package com.open9527.code.webview.video;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/24 18:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :web的接口回调，主要是视频相关回调，比如全频，取消全频，隐藏和现实webView.
 */
public interface VideoWebListener {

    /**
     * 显示全屏view
     */
    void showVideoFullView();

    /**
     * 隐藏全屏view
     */
    void hindVideoFullView();

    /**
     * 显示webview
     */
    void showWebView();

    /**
     * 隐藏webview
     */
    void hindWebView();

}

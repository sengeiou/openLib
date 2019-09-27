package com.open9527.code.webview.bridge;

import com.open9527.code.webview.CallBackFunction;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/24 18:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public interface WebViewJavascriptBridge {

    void send(String data);

    void send(String data, CallBackFunction responseCallback);

}

package com.open9527.code.webview.bridge;

import com.open9527.code.webview.CallBackFunction;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/24 18:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :自定义接口，处理消息回调逻辑.
 */
public interface BridgeHandler {

    /**
     * 处理消息
     *
     * @param data     消息内容
     * @param function 回调
     */
    void handler(String data, CallBackFunction function);

}

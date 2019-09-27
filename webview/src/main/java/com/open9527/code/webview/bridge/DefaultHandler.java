
package com.open9527.code.webview.bridge;


import com.open9527.code.webview.CallBackFunction;
import com.open9527.code.webview.bridge.BridgeHandler;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/24 18:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :默认的BridgeHandler.
 */

public class DefaultHandler implements BridgeHandler {

    @Override
    public void handler(String data, CallBackFunction function) {
        if (function != null) {
            function.onCallBack("DefaultHandler response data");
        }
    }

}

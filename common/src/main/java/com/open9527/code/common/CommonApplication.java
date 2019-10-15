package com.open9527.code.common;


import com.blankj.utilcode.util.CrashUtils;
import com.open9527.code.base.BaseApplication;
import com.open9527.code.webview.X5WebUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:53.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CommonApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initSDK();
    }

    private void initSDK() {
        CrashUtils.init();
        X5WebUtils.init(this, BuildConfig.DEBUG);
    }
}

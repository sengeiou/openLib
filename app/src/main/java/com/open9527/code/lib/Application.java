package com.open9527.code.lib;

import android.app.Activity;

import com.billy.android.swipe.SmartSwipeBack;
import com.open9527.code.common.CommonApplication;
//import com.open9527.code.webview.X5WebUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:48.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class Application extends CommonApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initSwipeBack();
//        X5WebUtils.init(this, BuildConfig.DEBUG);
    }

    /**
     * 配置侧滑样式( 微信样式)
     */
    private void initSwipeBack() {
        SmartSwipeBack.activitySlidingBack(this, activity -> !(activity instanceof MainActivity));
    }
}

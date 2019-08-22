package com.open9527.code.common;

import android.content.res.Resources;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.open9527.code.common.back.CommonBackActivity;
import com.open9527.code.common.config.Config;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:57.
 * E-Mail Address ：open_9527@163.com.
 * DESC :屏幕适配 ( pt).
 */
public abstract class ScreenBackBaseActivity extends CommonBackActivity {

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), Config.ADAPT_SCREEN_WIDTH);
    }
}

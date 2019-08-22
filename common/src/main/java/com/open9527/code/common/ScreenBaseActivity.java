package com.open9527.code.common;

import android.content.res.Resources;
import android.os.Bundle;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.open9527.code.base.BaseActivity;
import com.open9527.code.common.config.Config;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:57.
 * E-Mail Address ：open_9527@163.com.
 * DESC :屏幕适配 ( pt).
 */
public abstract class ScreenBaseActivity extends BaseActivity {


    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), Config.ADAPT_SCREEN_WIDTH);
    }
}

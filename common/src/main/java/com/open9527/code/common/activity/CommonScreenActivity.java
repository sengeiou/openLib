package com.open9527.code.common.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.open9527.code.base.BaseActivity;
import com.open9527.code.common.AppViewModel;
import com.open9527.code.common.CommonApplication;
import com.open9527.code.common.config.CommonConfig;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:57.
 * E-Mail Address ：open_9527@163.com.
 * DESC :屏幕适配 ( pt).
 */
public abstract class CommonScreenActivity extends BaseActivity {

    protected AppViewModel mAppViewModel;

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), CommonConfig.ADAPT_SCREEN_WIDTH);
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        //配置全局的ViewModel
        mAppViewModel = getAppViewModelProvider().get(AppViewModel.class);
    }


    protected ViewModelProvider getAppViewModelProvider() {
        return ((CommonApplication) getApplicationContext()).getAppViewModelProvider(this);
    }
}

package com.open9527.code.lib.samples.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.fragment.CommonTitleFragment;
import com.open9527.code.lib.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 15:50.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class HomeFragment extends CommonTitleFragment {

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public CharSequence bindTitle() {
        return "HomeFragment";
    }

    @Override
    public void doLazyBusiness() {
        LogUtils.i(TAG, "doLazyBusiness");
    }

    @Override
    protected void setStatusBar() {
        BarUtils.setStatusBarColor(mViewStatus, Color.WHITE);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}

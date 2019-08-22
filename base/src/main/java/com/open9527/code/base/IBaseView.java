package com.open9527.code.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
interface IBaseView {
    void initData(@Nullable Bundle bundle);

    int bindLayout();

    void setRootLayout(@LayoutRes int layoutId);

    void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView);

    void doBusiness();

    void onDebouncingClick(@NonNull View view);
}

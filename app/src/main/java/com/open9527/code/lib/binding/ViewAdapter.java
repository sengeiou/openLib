package com.open9527.code.lib.binding;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.ClickUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/21 19:43.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ViewAdapter {
    private static final String TAG = "ViewAdapter";

    @SuppressLint("CheckResult")
    @BindingAdapter(value = {"onBindViewClick",}, requireAll = false)
    public static void onBindingClick(View view, View.OnClickListener onClickListener) {
        ClickUtils.applyGlobalDebouncing(view, onClickListener);
    }
}

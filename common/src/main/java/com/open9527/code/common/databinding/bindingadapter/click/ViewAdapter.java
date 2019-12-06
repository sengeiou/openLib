package com.open9527.code.common.databinding.bindingadapter.click;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.ClickUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/27 10:45.
 * E-Mail Address ：open_9527@163.com.
 * DESC :点击事件处理.
 */
public class ViewAdapter {
    
    @SuppressLint("CheckResult")
    @BindingAdapter(value = {"onBindViewClick",}, requireAll = false)
    public static void onBindingClick(View view, View.OnClickListener onClickListener) {
        ClickUtils.applyGlobalDebouncing(view, onClickListener);
    }
}

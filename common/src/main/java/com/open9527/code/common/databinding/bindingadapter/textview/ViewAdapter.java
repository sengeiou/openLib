package com.open9527.code.common.databinding.bindingadapter.textview;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.LogUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/21 19:43.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class ViewAdapter {
    private static final String TAG = "ViewAdapter";

    @BindingAdapter(value = {"bindingText"}, requireAll = false)
    public static void bindingText(TextView textView, String string) {
        LogUtils.i(TAG, string);
        textView.setText(string);
    }
}

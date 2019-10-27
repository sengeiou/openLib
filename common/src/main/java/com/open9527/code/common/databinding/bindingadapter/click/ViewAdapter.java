package com.open9527.code.common.databinding.bindingadapter.click;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.open9527.code.common.databinding.click.BindingClick;
import com.open9527.code.common.databinding.click.RxView;

import java.util.concurrent.TimeUnit;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/27 10:45.
 * E-Mail Address ：open_9527@163.com.
 * DESC :点击事件处理.
 */
public class ViewAdapter {
    //防重复点击间隔(秒)
    private static final int CLICK_INTERVAL = 1;

    @SuppressLint("CheckResult")
    @BindingAdapter(value = {"onBindClick", "quickClick", "onBindData"}, requireAll = false)
    public static void onBindingClick(View view, final BindingClick bindingClick, final boolean quickClick, final Object data) {
        if (quickClick) {
            RxView.clicks(view)
                    .subscribe(object -> {
                        if (bindingClick != null) {
                            if (data != null) {
                                bindingClick.execute(data);
                            } else {
                                bindingClick.execute();
                            }

                        }
                    });
        } else {
            RxView.clicks(view)
                    .throttleFirst(CLICK_INTERVAL, TimeUnit.SECONDS)//1秒钟内只允许点击1次
                    .subscribe(object -> {
                        if (data != null) {
                            bindingClick.execute(data);
                        } else {
                            bindingClick.execute();
                        }
                    });
        }
    }
}

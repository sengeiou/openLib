package com.open9527.code.image.utils;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/10 10:57.
 * E-Mail Address ：open_9527@163.com.
 * DESC :LifecycleHandler.
 */
public class LifecycleHandler extends Handler implements LifecycleObserver {

    private static final String TAG = "LifecycleHandler";
    private LifecycleOwner mlifecycleOwner;


    public LifecycleHandler(LifecycleOwner mlifecycleOwner) {
        this.mlifecycleOwner = mlifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(Callback callback, LifecycleOwner mlifecycleOwner) {
        super(callback);
        this.mlifecycleOwner = mlifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(Looper looper, LifecycleOwner mlifecycleOwner) {
        super(looper);
        this.mlifecycleOwner = mlifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(Looper looper, Callback callback, LifecycleOwner mlifecycleOwner) {
        super(looper, callback);
        this.mlifecycleOwner = mlifecycleOwner;
        addObserver();
    }

    private void addObserver() {
        mlifecycleOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestory() {
        removeCallbacksAndMessages(null);
        CommonImageUtils.showLogInfo(TAG, "onDestory---->");
        mlifecycleOwner.getLifecycle().removeObserver(this);

    }
}

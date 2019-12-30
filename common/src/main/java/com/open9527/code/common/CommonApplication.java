package com.open9527.code.common;


import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.blankj.utilcode.util.CrashUtils;
import com.open9527.code.base.BaseApplication;
import com.open9527.code.webview.X5WebUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:53.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CommonApplication extends BaseApplication implements ViewModelStoreOwner {


    /**
     * 借助 Application 来管理一个应用级 的 AppViewModel，
     * 实现全应用范围内的 生命周期安全 且 事件源可追溯的 视图控制器 事件通知。
     */

    private ViewModelStore mAppViewModelStore;

    private ViewModelProvider.Factory mFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        initSDK();
        mAppViewModelStore = new ViewModelStore();
    }

    /**
     * 后期根据需要可异步操作加载
     */
    private void initSDK() {
        CrashUtils.init();
        X5WebUtils.init(this, BuildConfig.DEBUG);
    }


    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    public ViewModelProvider getAppViewModelProvider(Activity activity) {
        return new ViewModelProvider((CommonApplication) activity.getApplicationContext(),
                ((CommonApplication) activity.getApplicationContext()).getAppFactory(activity));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = checkApplication(activity);
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }

    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }
}

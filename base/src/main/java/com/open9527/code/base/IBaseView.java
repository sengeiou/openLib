package com.open9527.code.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public  interface IBaseView {

    /**
     * 初始化数据
     *
     * @param bundle
     */
    void initData(@Nullable Bundle bundle);

    /**
     * 绑定 layout
     *
     * @return
     */
    int bindLayout();

    /**
     * 配置 layout
     */
    void setContentView();

    /**
     * 配置 databing
     */
    default void bindingView() {

    }

    /**
     * 初始化view
     *
     * @param savedInstanceState
     * @param contentView
     */
    void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView);

    /**
     * 配置标题(默认是类名)
     *
     * @return
     */
    default CharSequence bindTitle() {
        return getClass().getSimpleName();
    }

    /**
     * 配置 执行业务代码
     */
    default void doBusiness() {

    }

    /**
     * 配置 懒加载执行业务代码
     */
    default void doLazyBusiness() {

    }

    /**
     * 配置防误触点击事件
     *
     * @param view
     */
    default void onDebouncingClick(@NonNull View view) {
    }

    /**
     * 配置内容是否滚动(默认不滚动)
     *
     * @return
     */
    default boolean hasContentScroll() {
        return false;
    }

    /**
     * 配置标题是否居中(默认居中)
     *
     * @return
     */
    default boolean hasCentre() {
        return true;
    }

    /**
     * 配置统一日志提示
     *
     * @param TAG
     * @param msg
     */
    default void logI(String TAG, String msg) {
        if (AppUtils.isAppDebug()) {
//            LogUtils.i(TAG, msg);
        }
    }
}

package com.open9527.code.common.utils;

import android.os.Build;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/10 10:57.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CommonUtils {


    /**
     * 判断是否是Android Q版本
     *
     * @return
     */
    public static boolean checkedAndroid_Q() {
        return Build.VERSION.SDK_INT >= 29;
    }
}

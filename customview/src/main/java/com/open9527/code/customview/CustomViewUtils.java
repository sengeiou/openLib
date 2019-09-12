package com.open9527.code.customview;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/2 13:24.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CustomViewUtils {

    public static int sp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 方便看日志
     *
     * @param tag
     * @param logInfo
     */
    public static void showLogInfo(String tag, String logInfo) {
        Log.i(tag, logInfo);
    }
}

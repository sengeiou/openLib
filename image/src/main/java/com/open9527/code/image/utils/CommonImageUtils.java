package com.open9527.code.image.utils;

import android.os.Environment;
import android.util.Log;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/9 17:21.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CommonImageUtils {
    /**
     * 判断SD卡是否可用
     *
     * @return
     */
    public static boolean isSDCardEnableByEnvironment() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
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

package com.open9527.imageload;

import android.os.Environment;
import android.util.Log;

public class Utils {

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
    public static void Log(String tag, String logInfo) {
        Log.i(tag, logInfo);
    }

}

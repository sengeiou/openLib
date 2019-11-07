package com.open9527.code.base.dialog;

import android.app.Activity;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by     : open9527
 * Created times  : on 2019/11/4 10:20.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public final class DialogUtils {

    public static void checkMainThread() {
        if (!isMainThread()) {
            throw new IllegalStateException("Please do not do pop-up operations in child threads");
        }
    }

    private static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 设置页面的透明度
     * 主要作用于：弹窗时设置宿主Activity的背景色
     *
     * @param bgAlpha 1表示不透明
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        Window window = activity.getWindow();
        if (window != null) {
            if (bgAlpha == 1) {
                //不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            } else {
                //此行代码主要是解决在华为手机上半透明效果无效的bug
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
            window.setAttributes(lp);
        }
    }


}

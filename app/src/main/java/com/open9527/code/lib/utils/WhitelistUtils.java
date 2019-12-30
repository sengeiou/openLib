package com.open9527.code.lib.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * Created by     : open9527
 * Created times  : on 2019/12/20/020 9:51.
 * E-Mail Address ：open_9527@163.com.
 * DESC :用户引导添加白名单.
 *       TODO:需要添加权限  <uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" />
 */
public final class WhitelistUtils {

    /**
     * 申请权限
     *
     * @return
     */

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestIgnoreBatteryOptimizations() {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + AppUtils.getAppPackageName()));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 判断是否存在权限
     */

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isIgnoringBatteryOptimizations() {
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) Utils.getApp().getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(AppUtils.getAppPackageName());
        }
        return isIgnoring;
    }




    /**
     * 华为
     *
     * @return
     */
    public static boolean isHuawei() {
        if (Build.BRAND == null) {
            return false;
        } else {
            return Build.BRAND.toLowerCase().equals("huawei") || Build.BRAND.toLowerCase().equals("honor");
        }
    }

    public static void goHuaweiSetting() {
        try {

            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        } catch (Exception e) {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
        }
    }


    /**
     * 小米
     *
     * @return
     */
    public static boolean isXiaomi() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("xiaomi");
    }

    public static void goXiaomiSetting() {
        showActivity("com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity");
    }


    /**
     * OPPO
     *
     * @return
     */
    public static boolean isOPPO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("oppo");
    }

    public static void goOPPOSetting() {
        try {
            showActivity("com.coloros.phonemanager");
        } catch (Exception e1) {
            try {
                showActivity("com.oppo.safe");
            } catch (Exception e2) {
                try {
                    showActivity("com.coloros.oppoguardelf");
                } catch (Exception e3) {
                    showActivity("com.coloros.safecenter");
                }
            }
        }
    }


    /**
     * VIVO
     */
    public static boolean isVIVO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("vivo");
    }

    public static void goVIVOSetting() {
        showActivity("com.iqoo.secure");
    }


    /**
     * 魅族
     */
    public static boolean isMeizu() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("meizu");
    }

    public static void goMeizuSetting() {
        showActivity("com.meizu.safe");
    }

    /**
     * 三星
     */
    public static boolean isSamsung() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("samsung");
    }

    public static void goSamsungSetting() {
        try {
            showActivity("com.samsung.android.sm_cn");
        } catch (Exception e) {
            showActivity("com.samsung.android.sm");
        }
    }

    /**
     * 乐视
     */

    public static boolean isLeTV() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("letv");
    }

    public static void goLetvSetting() {
        showActivity("com.letv.android.letvsafe",
                "com.letv.android.letvsafe.AutobootManageActivity");
    }


    /**
     * 锤子
     */

    public static boolean isSmartisan() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("smartisan");
    }

    private void goSmartisanSetting() {
        showActivity("com.smartisanos.security");
    }


    /**
     * 跳转到指定应用的首页
     */
    public static void showActivity(@NonNull String packageName) {
        Intent intent = Utils.getApp().getPackageManager().getLaunchIntentForPackage(packageName);
        startActivity(intent);
    }

    /**
     * 跳转到指定应用的指定页面
     */
    public static void showActivity(@NonNull String packageName, @NonNull String activityDir) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}

package com.open9527.code.webview;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/24 18:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public final class X5WebUtils {
    private static boolean mIsDebug = false;

    /**
     * 不能直接new，否则抛个异常
     */
    private X5WebUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化腾讯x5浏览器webView，建议在application初始化
     *
     * @param context 上下文
     */
    public static void init(Context context, boolean isDebug) {
        mIsDebug = isDebug;

        if (context instanceof Application) {

            //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
            QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
                @Override
                public void onViewInitFinished(boolean arg0) {
                    //x5內核初始化完成的回调，为true表示x5内核加载成功
                    //否则表示x5内核加载失败，会自动切换到系统内核。
                    log(" onViewInitFinished is " + arg0);
                }

                @Override
                public void onCoreInitFinished() {
                    log(" onCoreInitFinished");
                }
            };
            //x5内核初始化接口
            QbSdk.initX5Environment(context, cb);
        } else {
            throw new UnsupportedOperationException("context must be application...");
        }
    }


    /**
     * 判断网络是否连接
     * <p>需添加权限
     * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}</p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isConnected(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected();
    }

    /**
     * 获取活动网络信息
     * <p>需添加权限
     * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}</p>
     *
     * @return NetworkInfo
     */
    @SuppressLint("MissingPermission")
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return null;
        }
        return manager.getActiveNetworkInfo();
    }

    public static void log(String log) {
        if (mIsDebug) {
            Log.e("X5WebUtils---->", log);
        }
    }

}

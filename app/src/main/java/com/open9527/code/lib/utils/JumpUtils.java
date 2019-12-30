package com.open9527.code.lib.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.lib.MainActivity;
import com.open9527.code.lib.module.splash.SplashActivity;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by     : open9527
 * Created times  : on 2019/12/6/006 15:50.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class JumpUtils {
    private static final String TAG = JumpUtils.class.getSimpleName();

    @Nullable
    public static Intent parseIntent(Context context, String url) {
        LogUtils.i(TAG, "URL--->" + url);
        try {
            Uri data = Uri.parse(url);
            String scheme = data.getScheme();
            String host = data.getHost();
            String path = data.getPath();
            Set<String> queryParameterNames = data.getQueryParameterNames();
            HashMap<String, String> map = null;
            if (!queryParameterNames.isEmpty()) {
                map = new HashMap<>();
                for (String name : queryParameterNames) {
                    map.put(name, data.getQueryParameter(name));
                }
            }
            if (map != null) {
                LogUtils.i(TAG, "query: " + map.toString());
            }
            return parseSchemes(context, host, path, map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Nullable
    private static Intent parseSchemes(Context context, String host, String path, HashMap<String, String> queryies) {
        LogUtils.i(TAG, "parse: host:" + host);
        LogUtils.i(TAG, "parse: path:" + path);
        LogUtils.i(TAG, "parse: queryies:" + queryies.toString());
        String[] paths = path.split("/");
        Intent intent;
        if (queryies.containsKey("type") && queryies.containsKey("key")) {
            String type = queryies.get("type");
            switch (type) {
                case "1":
                    intent = new Intent(context, MainActivity.class);
                    return intent;
            }
        }
        //todo 这里看具体的业务场景，也有可能应该直接返回 null
        intent = new Intent(context, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}

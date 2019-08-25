package com.open9527.code.lib.utils;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.google.gson.reflect.TypeToken;
import com.open9527.code.lib.model.EntryBean;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/23 16:47.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CommonUtils {

    /**
     * 处理加载github图片
     *
     * @param url
     * @return
     */
    public static String getUrl(String url) {
        if (url.contains("blob")) {
            return url.replace("blob", "raw");
        }
        return url;
    }

    /**
     * @param assetsFilePath "json/entry.json"
     * @return
     */
    public static Object getObject(String assetsFilePath) {
//        String string = ResourceUtils.readAssets2String(assetsFilePath);
//        List<EntryBean> list = GsonUtils.fromJson(string, new TypeToken<List<EntryBean>>() {
//        }.getType());
        return null;
    }
}

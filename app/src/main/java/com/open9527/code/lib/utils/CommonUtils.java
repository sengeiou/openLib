package com.open9527.code.lib.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.reflect.TypeToken;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;
import com.open9527.code.lib.R;


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
        if (StringUtils.isEmpty(url)) return "";
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

    /**
     * 图片加载
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void imageLoad(Context context, ImageView imageView, String url) {
        ImageLoadManger.display(context, imageView, CommonUtils.getUrl(url), new ImageLoadConfig(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
    }

    public static void imageLoad(Context context, ImageView imageView, String url, ImageView.ScaleType type) {
        ImageLoadManger.display(context, imageView, CommonUtils.getUrl(url), new ImageLoadConfig(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, type));
    }

}

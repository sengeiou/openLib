package com.open9527.code.lib.utils;

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


    public static String getJson(String url) {
//        https://github.com/hpu-spring87/ebooks/blob/master/update.json
//        https://raw.githubusercontent.com/:owner/:repo/master/:path
//        https://raw.githubusercontent.com/hpu-spring87/ebooks/master/update.json
//
//        https://github.com/open9527/Images/blob/master/json/entry.json
        //https://raw.githubusercontent.com/open9527/Images/master/json/entry.json

        if (url.contains("blob")) {
            return url.replace("blob", "raw");
        }
        return url;
    }

}

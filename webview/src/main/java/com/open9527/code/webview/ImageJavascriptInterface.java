package com.open9527.code.webview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/24 18:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :WebView与android交互的接口设置实现类.
 */

public class ImageJavascriptInterface {

    private Context context;
    private String[] imageUrls;

    public ImageJavascriptInterface(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    public ImageJavascriptInterface(Context context) {
        this.context = context;
    }

    /**
     * 接口返回的方式
     */
    @android.webkit.JavascriptInterface
    public void openImage(String img, String[] imageUrls) {
        Intent intent = new Intent();
        intent.putExtra("imageUrls", imageUrls);
        intent.putExtra("curImageUrl", img);
//        intent.setClass(context, PhotoBrowserActivity.class);
        context.startActivity(intent);
        for (int i = 0; i < imageUrls.length; i++) {
            Log.e("图片地址" + i, imageUrls[i].toString());
        }
    }

    /**
     * 加载时获取资源的方式
     */
    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        Intent intent = new Intent();
        intent.putExtra("imageUrls", imageUrls);
        intent.putExtra("curImageUrl", img);
//        intent.setClass(context, PhotoBrowserActivity.class);
        context.startActivity(intent);
        for (int i = 0; i < imageUrls.length; i++) {
            Log.e("图片地址" + i, imageUrls[i].toString());
        }
    }
}

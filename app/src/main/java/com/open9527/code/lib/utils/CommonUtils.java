package com.open9527.code.lib.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.UriUtils;
import com.google.gson.reflect.TypeToken;
import com.open9527.code.image.imageload.ImageLoadConfig;
import com.open9527.code.image.imageload.ImageLoadManger;
import com.open9527.code.lib.BuildConfig;
import com.open9527.code.lib.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


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

    /**
     * 文件转base64
     *
     * @param file
     * @return
     */
    public static String File2Base64(File file) {
        String string = null;
        byte[] data = null;
        try {
            InputStream is = new FileInputStream(file);
            data = new byte[is.available()];
            is.read(data);
            is.close();
            string = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return string;
    }

    /**
     * 判断是否是Android Q版本
     *
     * @return
     */
    public static boolean checkedAndroid_Q() {
        return Build.VERSION.SDK_INT >= 29;
    }

    /**
     * 检测SD卡
     *
     * @return
     */
    public static boolean checkedSD() {
        return SDCardUtils.isSDCardEnableByEnvironment();
    }


    public static final String CAMERA_PATH = "/" + BuildConfig.APPLICATION_ID + "/CameraImage/";

    public static Uri createUri(final Context context) {
        if (checkedAndroid_Q()) {
            return createImagePathUri(context);
        }
        File rootDir = checkedSD() ? context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) : context.getCacheDir();
        File folderDir = new File(rootDir.getAbsolutePath() + CAMERA_PATH);
        if (!folderDir.exists() && folderDir.mkdirs()) {
        }
        File tmpFile = new File(folderDir, TimeUtils.getNowMills() + ".jpg");
        return UriUtils.file2Uri(tmpFile);
    }

    /**
     * 创建一条图片地址uri,用于保存拍照后的照片
     *
     * @param context
     * @return 图片的uri
     */
    public static Uri createImagePathUri(final Context context) {
        final Uri[] imageFilePath = {null};
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));
        // ContentValues是我们希望这条记录被创建时包含的数据信息
        ContentValues values = new ContentValues(3);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
        values.put(MediaStore.Images.Media.DATE_TAKEN, time);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        imageFilePath[0] = context.getContentResolver().insert(checkedSD() ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : MediaStore.Images.Media.INTERNAL_CONTENT_URI, values);
        return imageFilePath[0];
    }
}

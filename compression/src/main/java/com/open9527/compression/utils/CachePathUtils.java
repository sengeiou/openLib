package com.open9527.compression.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 12:46.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CachePathUtils {
    /**
     * 获取拍照缓存文件
     *
     * @return
     */
    public static File getCameraCacheFile() {
        String fileName = "camera_" + getBaseFileNme() + ".jpg";
        return getCameraCacheDir(fileName);
    }

    /**
     * 获取压缩缓存文件
     *
     * @param context
     * @param string
     * @return
     */
    public static File getCompressCacheFile(Context context, String... string) {
        String fileName = "compress_" + getBaseFileNme() + ".jpg";
        return getCompressCacheDir(context, fileName, ((string.length > 0) ? string[0] : ""));
    }


    /**
     * 创建压缩缓存文件夹
     *
     * @param filename
     * @return
     */
    public static File getCompressCacheDir(Context context, String filename, String directory_path) {
        File rootDir;
        if (checkedAndroid_Q()) {
            rootDir = isSDCardEnableByEnvironment() ? context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) : context.getCacheDir();
        } else {
            rootDir = isSDCardEnableByEnvironment() ? Environment.getExternalStorageDirectory() : context.getCacheDir();
        }
        File path;
        if (!TextUtils.isEmpty(directory_path)) {
            // 自定义保存目录
            path = new File(rootDir.getAbsolutePath() + directory_path);
        } else {
            path = new File(rootDir.getAbsolutePath() + "/CompressCache");
        }
        if (!path.exists()) {
            // 若不存在，创建目录，可以在应用启动的时候创建
            path.mkdirs();
        }
        return new File(path, filename);
    }


    /**
     * 创建拍照文件
     *
     * @param fileName
     * @return
     */
    private static File getCameraCacheDir(String fileName) {
        File cache = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!cache.mkdirs() && (!cache.exists() || !cache.isDirectory())) {
            return null;
        }
        return new File(cache, fileName);
    }

    /**
     * 获取图片文件名
     *
     * @return
     */
    private static String getBaseFileNme() {
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.ENGLISH).format(new Date());
    }

    /**
     * 判断是否是Android Q版本
     *
     * @return
     */
    private static boolean checkedAndroid_Q() {
        return Build.VERSION.SDK_INT >= 29;
    }

    /**
     * 判断SD卡是否可用
     *
     * @return
     */
    private static boolean isSDCardEnableByEnvironment() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}

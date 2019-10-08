package com.open9527.code.image.compression;

import android.os.Environment;

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

    public static File getCompressCacheFile(String... string) {
        String fileName = "compress_" + getBaseFileNme() + ".jpg";
        return getCameraCacheDir(fileName);
    }


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
        return new SimpleDateFormat("yyyyMMDD_HHmmss", Locale.ENGLISH).format(new Date());
    }

}

package com.open9527.code.image.compression;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.open9527.code.image.utils.CommonImageUtils;

import java.io.File;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 14:27.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CompressImageUtil {
    private static final String TAG = "CompressImageUtil";
    private CompressConfig config;
    private Context context;

    public CompressImageUtil(Context context, CompressConfig config) {
        this.context = context;
        this.config = config;
        CommonImageUtils.showLogInfo(TAG, "Image compression config---->" + config.toString());
    }


    /**
     * 开始压缩图片(像素/质量 压缩)
     *
     * @param imagePath
     * @param listener
     */
    public void compress(String imagePath, CompressResultListener listener) {
        CommonImageUtils.showLogInfo(TAG, "Image oriinalPath---->" + imagePath);
        Bitmap result = null;
        //创建压缩后的图片文件
        File compressFile = CachePathUtils.getCompressCacheFile(context, TextUtils.isEmpty(config.getCacheDir()) ? "" : config.getCacheDir());
        // 获取bitmap
        result = BitmapUtils.getBitmap(imagePath);
        if (config.isEnableCompressPixel()) {
            //进行像素压缩(标准像素480x720)
            result = BitmapUtils.compressBySampleSize(result, config.getMaxPixel(), config.getMaxPixel(), true);
        }
        if (config.isEnableQualityCompress()) {
            //进行质量压缩
            result = BitmapUtils.compressByQuality(result, config.getMaxSize(), true);
        }
        if (result != null) {
            //保存图片
            boolean isSave = BitmapUtils.save(result, compressFile, Bitmap.CompressFormat.JPEG, true);
            if (isSave) {
                listener.onCompressSuccess(compressFile.getAbsolutePath());
            } else {
                listener.onCompressFailed(imagePath, "isSave is false!");
            }
        } else {
            listener.onCompressFailed(imagePath, "result is null !");
        }

    }

}

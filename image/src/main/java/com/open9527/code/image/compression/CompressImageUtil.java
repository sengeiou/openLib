package com.open9527.code.image.compression;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.open9527.code.image.utils.CommonImageUtils;
import com.open9527.code.image.utils.LifecycleHandler;

import java.io.File;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 14:27.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CompressImageUtil {
    private static final String TAG = "CompressImageUtil";

    private static final int SUCCESS = 200;
    private static final int FAILED = 500;

    private CompressConfig config;
    private Context context;
    private CompressResultListener listener;

    private LifecycleHandler lifecycleHandler;

    @SuppressLint("HandlerLeak")
    public CompressImageUtil(Context context, CompressConfig config) {
        this.context = context;
        this.config = config;
        CommonImageUtils.showLogInfo(TAG, "Image compression config---->" + config.toString());
        if (lifecycleHandler == null) {
            lifecycleHandler = new LifecycleHandler((LifecycleOwner) context) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    lifecycleHandler.removeCallbacks(null);
                    if (msg.what == SUCCESS) {
                        String imagePath = msg.getData().getString("imagePath");
                        listener.onCompressSuccess(imagePath);
                    } else {
                        String imagePath = msg.getData().getString("imagePath");
                        String errorMsg = msg.getData().getString("errorMsg");
                        listener.onCompressFailed(imagePath, errorMsg);
                    }
                }
            };
        }
    }


    /**
     * 开始压缩图片(像素/质量 压缩)
     *
     * @param imagePath
     * @param listener
     */
    public void compress(String imagePath, CompressResultListener listener) {
        this.listener = listener;
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
                Message message = new Message();
                message.what = SUCCESS;
                Bundle bundle = new Bundle();
                bundle.putString("imagePath", compressFile.getAbsolutePath());
                message.setData(bundle);
                lifecycleHandler.sendMessage(message);
            } else {
                Message message = new Message();
                message.what = SUCCESS;
                Bundle bundle = new Bundle();
                bundle.putString("imagePath", compressFile.getAbsolutePath());
                bundle.putString("errorMsg", "isSave is false!");
                message.setData(bundle);
                lifecycleHandler.sendMessage(message);
            }
        } else {
            Message message = new Message();
            message.what = FAILED;
            Bundle bundle = new Bundle();
            bundle.putString("imagePath", compressFile.getAbsolutePath());
            bundle.putString("errorMsg", "result is null !");
            message.setData(bundle);
            lifecycleHandler.sendMessage(message);
        }

    }

}

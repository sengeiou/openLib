package com.open9527.code.lib.samples.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.image.utils.CommonImageUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 14:27.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
class CompressImageUtil {
    private CompressConfig config;
    private Context context;
//    private Handler handler = new Handler();

    public CompressImageUtil(Context context, CompressConfig config) {
        this.context = context;
        this.config = config;
        //TODO:
        LogUtils.i("CompressImageUtil", "根据配置进行压缩...." + GsonUtils.toJson(config));
    }

    public void compress(String imagePath, CompressResultListener listener) {
        if (config.isEnableCompressPixel()) {
            try {
                //像素压缩
                compressImageByPixel(imagePath, listener);
            } catch (Exception e) {
                listener.onCompressFailed(imagePath, String.format("Image compression failed ! %s", e.getMessage()));
            }

        }
        //TODO:
        LogUtils.i("CompressImageUtil", "开始获取压缩结果....");
        listener.onCompressFailed(imagePath, "压缩失败");
        listener.onCompressSuccess(imagePath);
    }

    //根据像素压缩
    private void compressImageByPixel(String imagePath, CompressResultListener listener) {
//        File file = CachePathUtils.getCompressCacheFile();
//        Bitmap bitmap = CommonImageUtils.getBitmap(new File(imagePath),config.getMaxPixel(),config.getMaxPixel());
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = CommonImageUtils.computeSize(bitmap.getWidth(), options.outHeight);
//
//        Bitmap tagBitmap = BitmapFactory.decodeStream(srcImg.open(), null, options);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//
//        if (Checker.SINGLE.isJPG(srcImg.open())) {
//            tagBitmap = rotatingImage(tagBitmap, Checker.SINGLE.getOrientation(srcImg.open()));
//        }
//        tagBitmap.compress(focusAlpha ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 60, stream);
//        tagBitmap.recycle();
//
//        FileOutputStream fos = new FileOutputStream(tagImg);
//        fos.write(stream.toByteArray());
//        fos.flush();
//        fos.close();
//        stream.close();

    }
}

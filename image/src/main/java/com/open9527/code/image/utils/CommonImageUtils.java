package com.open9527.code.image.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/9 17:21.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CommonImageUtils {
    /**
     * 判断SD卡是否可用
     *
     * @return
     */
    public static boolean isSDCardEnableByEnvironment() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 方便看日志
     *
     * @param tag
     * @param logInfo
     */
    public static void showLogInfo(String tag, String logInfo) {
        Log.e(tag, logInfo);
    }


    /**
     * Return bitmap.
     *
     * @param file      The file.
     * @param maxWidth  The maximum width.
     * @param maxHeight The maximum height.
     * @return bitmap
     */
    public static Bitmap getBitmap(final File file, final int maxWidth, final int maxHeight) {
        if (file == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }


    /**
     * Return the sample size.
     *
     * @param options   The options.
     * @param maxWidth  The maximum width.
     * @param maxHeight The maximum height.
     * @return the sample size
     */
    private static int calculateInSampleSize(final BitmapFactory.Options options, final int maxWidth, final int maxHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        while (height > maxHeight || width > maxWidth) {
            height >>= 1;
            width >>= 1;
            inSampleSize <<= 1;
        }
        return inSampleSize;
    }

    /**
     * 鲁班压缩算法
     *
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public static int computeSize(final int maxWidth, final int maxHeight) {
        int srcWidth;
        int srcHeight;
        srcWidth = maxWidth % 2 == 1 ? maxWidth + 1 : maxWidth;
        srcHeight = maxHeight % 2 == 1 ? maxHeight + 1 : maxHeight;
        int longSide = Math.max(srcWidth, srcHeight);
        int shortSide = Math.min(srcWidth, srcHeight);

        float scale = ((float) shortSide / longSide);
        if (scale <= 1 && scale > 0.5625) {
            if (longSide < 1664) {
                return 1;
            } else if (longSide < 4990) {
                return 2;
            } else if (longSide > 4990 && longSide < 10240) {
                return 4;
            } else {
                return longSide / 1280 == 0 ? 1 : longSide / 1280;
            }
        } else if (scale <= 0.5625 && scale > 0.5) {
            return longSide / 1280 == 0 ? 1 : longSide / 1280;
        } else {
            return (int) Math.ceil(longSide / (1280.0 / scale));
        }
    }


    /**
     * 判断当前设备是否有相机
     *
     * @param activity
     * @param intent
     * @param requestcode
     */
    public static void hasCamera(Activity activity, Intent intent, int requestcode) {
        if (activity == null) {
            throw new IllegalArgumentException("activity is null !");
        }
        PackageManager packageManager = activity.getPackageManager();
        boolean hasCamera = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
                || packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)
                || Camera.getNumberOfCameras() > 0;
        if (hasCamera) {
            activity.startActivityForResult(intent, requestcode);
        } else {
            throw new IllegalArgumentException("Current device does not have a camera !");
        }
    }


    /**
     * 打开相册
     *
     * @param activity
     * @param requestcode
     */
    public static void openAlbum(AppCompatActivity activity, int requestcode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, requestcode);

    }

    /**
     * 添加压缩进度对话框
     *
     * @param activity
     * @param progressTitle
     * @return
     */
    public static ProgressDialog showProgressDialog(AppCompatActivity activity, String... progressTitle) {
        if (activity == null || activity.isFinishing()) return null;
        String title = "提示";
        if (progressTitle != null && progressTitle.length > 0) title = progressTitle[0];
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(title);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

}

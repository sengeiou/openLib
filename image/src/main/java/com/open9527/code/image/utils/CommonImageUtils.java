package com.open9527.code.image.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

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
     * 方便看日志
     *
     * @param tag
     * @param logInfo
     */
    public static void showLogInfo(String tag, String logInfo) {
        Log.e(tag, logInfo);
    }

    /**
     * 判断SD卡是否可用
     *
     * @return
     */
    public static boolean isSDCardEnableByEnvironment() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
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
        String title = "提示...";
        if (progressTitle != null && progressTitle.length > 0) title = progressTitle[0];
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(title);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

}

package com.open9527.code.lib.samples.image;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.UriUtils;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.image.compression.Constants;
import com.open9527.code.image.utils.CommonImageUtils;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityCompressPicturesBinding;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 9:52.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CompressPicturesActivity extends CommonBindingActivity<ActivityCompressPicturesBinding> implements CompressImage.CompressListener {

    private CompressConfig compressConfig;//压缩配置
    private ProgressDialog dialog;//压缩dialog
    private String cameraCachePath;//拍照后,源文件路径

    @Override
    public CharSequence bindTitle() {
        return "图片压缩";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_compress_pictures;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        compressConfig = CompressConfig.builder()
                .setUnCompressMinPixel(1000)
                .setUnCompressNormalPixel(2000)
                .setMaxPixel(1200)
                .setMaxSize(200 * 1024)
                .setEnableCompressPixel(true)
                .setEnableQualityCompress(true)
                .setEnableReserveRaw(true)
                .setCacheDir("")
                .setShowCompressDialog(true)
                .create();
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }

    public void camera(View view) {
        Uri outputUri;
        File file = CachePathUtils.getCameraCacheFile();
        outputUri = UriUtils.file2Uri(file);
        cameraCachePath = file.getAbsolutePath();
        CommonImageUtils.hasCamera(this, IntentUtils.getCaptureIntent(outputUri), Constants.CAMERA_CODE);
    }

    public void album(View view) {
        CommonImageUtils.openAlbum(this, Constants.ALBUM_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.CAMERA_CODE && resultCode == RESULT_OK) {
            //拍照
            preCompress(cameraCachePath);
        }
        if (requestCode == Constants.ALBUM_CODE && resultCode == RESULT_OK) {
            //相册
            if (data != null) {
                Uri uri = data.getData();
                String path = UriUtils.uri2File(uri).getPath();
                preCompress(path);
            }

        }

    }

    //创建压缩对象
    private void preCompress(String path) {
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(new Photo(path));
        if (!photos.isEmpty()) compress(photos);
    }

    //开始压缩
    private void compress(ArrayList<Photo> photos) {
        if (compressConfig.isShowCompressDialog()) {
            dialog = CommonImageUtils.showProgressDialog(this, "图片压缩中...");
        }
        CompressImageManager.build(this, compressConfig, photos, this).compress();
    }

    @Override
    public void onCompressSuccess(ArrayList<Photo> images) {
        LogUtils.i(TAG, "图片压缩成功!");
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onCompressFailed(ArrayList<Photo> images, String... error) {
        if (error.length > 0) {
            LogUtils.i(TAG, error[0]);
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}

package com.open9527.code.lib.module.image.compression;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UriUtils;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.image.compression.CachePathUtils;
import com.open9527.code.image.compression.CompressConfig;
import com.open9527.code.image.compression.CompressImage;
import com.open9527.code.image.compression.CompressImageManager;
import com.open9527.code.image.compression.Constants;
import com.open9527.code.image.compression.Photo;
import com.open9527.code.image.utils.CommonImageUtils;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityCompressImageBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/9 17:40.
 * E-Mail Address ：open_9527@163.com.
 * DESC :图片压缩.
 */
public class CompressImageActivity extends CommonBindingActivity<ActivityCompressImageBinding> implements CompressImage.CompressListener {


    private CompressConfig compressConfig;//压缩配置
    private ProgressDialog dialog;//压缩dialog
    private String cameraCachePath;//拍照后,源文件路径

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_compress_image;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        //添加点击事件
        applyDebouncingClickListener(mBinding.tvAlbum, mBinding.tvCamera, mBinding.tvCompression);
    }

    @Override
    public void doBusiness() {
        //配置压缩属性
        compressConfig = CompressConfig.builder()
                .setUnCompressMinPixel(1000)
                .setUnCompressNormalPixel(2000)
                .setMaxPixel(1000)
                .setMaxSize(150 * 1024)//kb
                .setEnableCompressPixel(true)
                .setEnableQualityCompress(true)
                .setEnableReserveRaw(true)
                .setCacheDir("/CompressCache")
                .setShowCompressDialog(true)
                .create();


    }

    @Override
    public void onDebouncingClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.tv_album:
                requesPermission(Constants.ALBUM_CODE);
                break;
            case R.id.tv_camera:
                requesPermission(Constants.CAMERA_CODE);
                break;
            case R.id.tv_compression:
                requesPermission(Constants.OTHERS_CODE);
                break;
            default:
                break;
        }

    }

    @Override
    public void onCompressSuccess(ArrayList<Photo> images) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        LogUtils.i(TAG, "图片压缩成功!" + GsonUtils.toJson(images));
        ToastUtils.showLong("图片压缩成功!--->" + GsonUtils.toJson(images));
    }

    @Override
    public void onCompressFailed(ArrayList<Photo> images, String... error) {
        if (error.length > 0) {
            ToastUtils.showLong(TAG, "图片压缩失败!--->" + error[0]);
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        LogUtils.i(TAG, "图片压缩失败!" + GsonUtils.toJson(images));
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
                if (uri != null) {
                    String path = UriUtils.uri2File(uri).getAbsolutePath();
                    preCompress(path);
                } else {
                    ToastUtils.showShort(" album is null !");
                }
            } else {
                ToastUtils.showShort(" album is null !");
            }

        }

    }


    /**
     * 打开相机
     */
    private void openCamera() {
        Uri outputUri;
        File file = CachePathUtils.getCameraCacheFile();
        outputUri = UriUtils.file2Uri(file);
        cameraCachePath = file.getAbsolutePath();
        CommonImageUtils.hasCamera(this, IntentUtils.getCaptureIntent(outputUri), Constants.CAMERA_CODE);
    }

    /**
     * 打开相册
     */

    private void openAlbum() {
        CommonImageUtils.openAlbum(this, Constants.ALBUM_CODE);
    }


    /**
     * 获取权限(拍照,文件读取)
     */
    private void requesPermission(int code) {
        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE, PermissionConstants.PHONE)//设置请求权限
                .rationale(shouldRequest -> {
                    //拒绝再次请求
                    ToastUtils.showShort("拒绝再次请求");
                })
                .callback(new PermissionUtils.FullCallback() {//设置回调
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        //权限确认
                        ToastUtils.showShort("权限确认");
                        if (code == Constants.ALBUM_CODE) {
                            openAlbum();
                        } else if (code == Constants.CAMERA_CODE) {
                            openCamera();
                        } else {
                            compresList();
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        //权限拒绝
                        ToastUtils.showShort("权限拒绝");
                    }
                })
                .theme(ScreenUtils::setFullScreen)
                .request();//触发请求

    }

    /**
     * 创建压缩对象
     *
     * @param path
     */

    private void preCompress(String path) {
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(new Photo(path));
        if (!photos.isEmpty()) compress(photos);
    }


    /**
     * 创建批量压缩
     * <p>
     * 路径需要是手机相册的
     */
    public void compresList() {
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(new Photo("/storage/emulated/0/DCIM/Camera/IMG_20191008_170336.jpg"));
        photos.add(new Photo("/storage/emulated/0/DCIM/Camera/IMG_20190903_162027.jpg"));
        photos.add(new Photo("/storage/emulated/0/DCIM/Camera/IMG_20190903_162027.jpg"));
        if (!photos.isEmpty()) compress(photos);
    }


    /**
     * 开始压缩
     *
     * @param photos
     */

    private void compress(ArrayList<Photo> photos) {
        if (compressConfig.isShowCompressDialog()) {
            dialog = CommonImageUtils.showProgressDialog(this, "图片压缩中...");
        }
        CompressImageManager.build(this, compressConfig, photos, this).compress();
    }

}

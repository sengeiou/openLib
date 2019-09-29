package com.open9527.code.lib.samples.image;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UriUtils;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityImageBinding;
import com.open9527.code.lib.model.CommitterBean;
import com.open9527.code.lib.model.GitHubFileBean;
import com.open9527.code.lib.model.RequestGitHubBean;
import com.open9527.code.lib.utils.CommonUtils;
import com.open9527.code.network.status.NetStatus;

import java.io.File;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/29 9:36.
 * E-Mail Address ：open_9527@163.com.
 * DESC :ImageActivity.
 */
public class ImageActivity extends CommonBindingActivity<ActivityImageBinding> {

    private ImageViewModel mViewModel;

    @Override
    public CharSequence bindTitle() {
        return "ImageActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        mViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
//        mViewModel.getFileList();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_image;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        initClick();
        mViewModel.mUploadFileRepository.getStatus().observe(this, new Observer<NetStatus>() {
            @Override
            public void onChanged(NetStatus netStatus) {
                LogUtils.i(TAG, GsonUtils.toJson(netStatus));
            }
        });
        mViewModel.mUploadFileRepository.getData().observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                LogUtils.i(TAG, GsonUtils.toJson(o));
            }
        });
        mViewModel.mFileListRepository.getData().observe(this, new Observer<List<GitHubFileBean>>() {
            @Override
            public void onChanged(List<GitHubFileBean> gitHubFileBeans) {
                LogUtils.i(TAG, GsonUtils.toJson(gitHubFileBeans));
            }
        });

    }

    private void initClick() {
        //添加点击事件
        applyDebouncingClickListener(mBinding.tvAlbum, mBinding.tvCamera);
    }

    @Override
    public void doBusiness() {

    }

    private void requesPermission() {
        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE)//设置请求权限
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {
//                        shouldRequest.again(true);
                        //拒绝再次请求
                        ToastUtils.showShort("拒绝再次请求");
                    }
                })
                .callback(new PermissionUtils.FullCallback() {//设置回调
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        //权限确认
                        ToastUtils.showShort("权限确认");
                        openAlbum();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        //权限拒绝
                        ToastUtils.showShort("权限拒绝");
                    }
                })
                .theme(new PermissionUtils.ThemeCallback() {//配置主题
                    @Override
                    public void onActivityCreate(Activity activity) {
                        ScreenUtils.setFullScreen(activity);
                    }
                })
                .request();//触发请求

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.tv_album:
                requesPermission();
//                ToastUtils.showShort("相册");
                break;
            case R.id.tv_camera:
//                ToastUtils.showShort("相机");

                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAMERA://相机

                break;
            case REQUEST_ALBUM://相册
                if (resultCode == RESULT_OK) {
                    getUri(data.getData());
                }
                break;
            default:
                break;
        }
    }

    private void getUri(Uri uri) {
        File file = UriUtils.uri2File(uri);
        //触发上传文件
//        mViewModel.uploadFile(new RequestGitHubBean("Add 测试上传", CommonUtils.File2Base64(file),
//                        "",
//                        new CommitterBean("open_9527", "open_9527@163.com"),
//                        new CommitterBean("open_9527", "open_9527@163.com")),
//                TimeUtils.getNowMills() + file.getName()
//        );
        //LogUtils.i(TAG, CommonUtils.File2Base64(file));
    }

    public final static int REQUEST_CAMERA = 909;
    public final static int REQUEST_ALBUM = 908;

    /**
     * 拍照
     */
    public void startOpenCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CAMERA);
        }
//        //跳转相机
//        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent2.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);//拍照才需要传入URI
//        activity.startActivityForResult(intent2, REQ_TAKE_PHOTO);
    }

    /**
     * 打开相册：
     */
    public void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_ALBUM);
        }
    }
}

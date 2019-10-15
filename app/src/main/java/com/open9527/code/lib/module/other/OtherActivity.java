package com.open9527.code.lib.module.other;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.common.entity.Contacts;
import com.open9527.code.common.entity.LocalMediaFolder;
import com.open9527.code.common.interfaces.ILoadData;
import com.open9527.code.common.utils.MediaUtils;
import com.open9527.code.image.compression.CachePathUtils;
import com.open9527.code.image.compression.Constants;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityOtherBinding;
import com.open9527.code.lib.utils.ImageLoadUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/10 12:24.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class OtherActivity extends CommonBindingActivity<ActivityOtherBinding> {

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_other;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        //添加点击事件
        applyDebouncingClickListener(mBinding.tvAlbum, mBinding.tvContacts);

    }

    @Override
    public void doBusiness() {

        //解析array.xml
        String[] letter = getResources().getStringArray(R.array.letter);
        int[] number = getResources().getIntArray(R.array.number);

        LogUtils.i(TAG, "letter: " + GsonUtils.toJson(letter));
        LogUtils.i(TAG, "number: " + GsonUtils.toJson(number));
    }

    @Override
    public void onDebouncingClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.tv_album:
                requesPermission(Constants.ALBUM_CODE);
                break;
            case R.id.tv_contacts:
                requesPermission(Constants.OTHERS_CODE);
                break;
            default:
                break;
        }
    }


    /**
     * 获取权限(文件读取,通讯录)
     */
    private void requesPermission(int code) {
        PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants.CONTACTS)//设置请求权限
                .rationale(shouldRequest -> {
                    //拒绝再次请求
                    ToastUtils.showShort("拒绝再次请求");
//                    shouldRequest.again(true);
                })
                .callback(new PermissionUtils.FullCallback() {//设置回调
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        //权限确认
                        if (code == Constants.ALBUM_CODE) {
                            getMediaList();
//                            saveIamge();
//                            getImageBase64("/storage/emulated/0/DCIM/Camera/timg.jpg");

                        } else {
                            getContacts();
                        }
                        ToastUtils.showShort("权限确认");
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
     * 获取手机资源文件
     */
    private void getMediaList() {
        String durationCondition = MediaUtils.getDurationCondition(0, 0, 60, 3);
        String mediaCondition = MediaUtils.getAllMediaCondition(durationCondition, true);
        MediaUtils.getMediaList(this, mediaCondition, MediaUtils.SELECTION_ALL_ARGS, MediaUtils.TYPE_ALL, listILoadData);

    }

    /**
     * 获取通讯录
     */
    private void getContacts() {
        MediaUtils.getContacts(this, listILoadContacts);
    }

    private ILoadData<List<LocalMediaFolder>> listILoadData = new ILoadData<List<LocalMediaFolder>>() {
        @Override
        public void loadComplete(List<LocalMediaFolder> localMediaFolders) {
            LogUtils.i(TAG, GsonUtils.toJson(localMediaFolders));
            mBinding.tvDesc.setText(String.valueOf("获取手机资源文件: " + GsonUtils.toJson(localMediaFolders)));
        }
    };

    private ILoadData<List<Contacts>> listILoadContacts = new ILoadData<List<Contacts>>() {
        @Override
        public void loadComplete(List<Contacts> contactsList) {
            LogUtils.i(TAG, GsonUtils.toJson(contactsList));
            mBinding.tvDesc.setText(String.valueOf("获取通讯录: " + GsonUtils.toJson(contactsList)));
        }
    };


    /**
     * 根据url保存图片
     */
    private void saveIamge(String url) {
        ImageLoadUtils.getBitmap(url, new ILoadData<Bitmap>() {
            @Override
            public void loadComplete(Bitmap bitmap) {
                if (bitmap != null) {
                    LogUtils.i(TAG, bitmap);
                    File File = CachePathUtils.getCompressCacheFile(mActivity);
                    boolean hasSave = ImageUtils.save(bitmap, File, Bitmap.CompressFormat.JPEG, true);
                    if (hasSave) {
                        ToastUtils.showShort("保存成功!");
                    }
                }
            }
        });
    }

    /**
     * 根据 base64String 保存图片
     *
     * @param base64String
     */
    private void saveBase64Image(String base64String) {
        File File = CachePathUtils.getCompressCacheFile(mActivity);
        boolean hasSave = Base64ToImage(base64String, File.getAbsolutePath());
        if (hasSave) {
            ToastUtils.showShort("保存成功!");
        }
    }


    private void getImageBase64(String imgFile) {
        String s = imageToBase64(imgFile);
        saveBase64Image(s);
    }


    /**
     * base64字符串转换成图片
     *
     * @param base64String base64字符串
     * @param imgFilePath  图片存放路径
     * @return
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:42:17
     */
    public static boolean Base64ToImage(String base64String, String imgFilePath) {
        if (TextUtils.isEmpty(base64String)) // 图像数据为空
            return false;
        try {
            // Base64解码
            byte[] b = Base64.decode(base64String, Base64.DEFAULT);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    /**
     * 本地图片转换成base64字符串
     *
     * @param imgFile 图片本地路径
     * @return
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:40:46
     */
    public static String imageToBase64(String imgFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理


        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return new String(Base64.encode(data, Base64.DEFAULT));// 返回Base64编码过的字节数组字符串
    }
}
